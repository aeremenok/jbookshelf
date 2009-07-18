/**
 * 
 */
package org.jbookshelf.controller.featuring;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sun.xml.internal.txw2.IllegalAnnotationException;

/**
 * @author eav 2009
 */
public class FeatureManager
{
    private static final Logger         log             = Logger.getLogger( FeatureManager.class );

    private final Map<Class<?>, Method> receiverMethods = new HashMap<Class<?>, Method>();
    private final Map<String, Object>   features        = new HashMap<String, Object>();

    public FeatureManager(
        final Object featureContainer,
        final List<String> featureNames )
    {
        super();

        try
        {
            extractReceiverMethods( featureContainer );
            processFields( featureContainer, featureNames );
            processClasses( featureContainer, featureNames );
        }
        catch ( final Exception e )
        {
            log.error( e, e );
            throw new Error( e );
        }
    }

    public FeatureManager(
        final Object featureContainer,
        final String... featureNames )
    {
        this( featureContainer, Arrays.asList( featureNames ) );
    }

    @SuppressWarnings( "unchecked" )
    public <T> T get(
        final String featureName )
    {
        return (T) features.get( featureName );
    }

    private boolean contains(
        final List<?> container,
        final List<?> contained )
    {
        return !Collections.disjoint( container, contained );
    }

    private boolean contains(
        final List<?> container,
        final Object[] contained )
    {
        return contains( container, Arrays.asList( contained ) );
    }

    private void extractReceiverMethods(
        final Object featureContainer )
    {
        final Method[] methods = featureContainer.getClass().getMethods();
        for ( final Method method : methods )
        {
            final FeatureReceiver featureReceiver = method.getAnnotation( FeatureReceiver.class );
            if ( featureReceiver != null )
            {
                final Class<?> parameterType = featureReceiver.value();
                if ( parameterType.equals( method.getParameterTypes()[0] ) )
                {
                    receiverMethods.put( parameterType, method );
                }
                else
                { // todo check type duplication 
                    throw new IllegalAnnotationException( "method " + method
                        + " cannot be invoked with parameter of type " + parameterType );
                }
            }
        }
    }

    private Method getMethodByClass(
        final Class<?> clazz )
    {
        Method method = receiverMethods.get( clazz );
        if ( method != null )
        {
            return method;
        }

        if ( Object.class.equals( clazz ) )
        {
            return null;
        }

        method = getMethodByClass( clazz.getSuperclass() );
        if ( method != null )
        {
            return method;
        }

        final Class<?>[] interfaces = clazz.getInterfaces();
        for ( final Class<?> intf : interfaces )
        {
            method = getMethodByClass( intf );
            if ( method != null )
            {
                return method;
            }
        }

        return null;
    }

    /**
     * @param featureContainer
     * @param instance
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    private void invokeReceiver(
        final Object featureContainer,
        final Object instance )
        throws IllegalAccessException,
            InvocationTargetException
    {
        final Class<?> clazz = instance.getClass();
        final Method method = getMethodByClass( clazz );
        if ( method != null )
        {
            method.invoke( featureContainer, instance );
        }
    }

    private void processClasses(
        final Object featureContainer,
        final List<String> featureNames )
        throws IllegalAccessException,
            InstantiationException,
            IllegalArgumentException,
            InvocationTargetException
    {
        final Class<?>[] classes = featureContainer.getClass().getDeclaredClasses();
        for ( final Class<?> innerClass : classes )
        {
            final Feature feature = innerClass.getAnnotation( Feature.class );
            if ( feature != null )
            {
                final String[] classFeatureNames = feature.value();
                if ( contains( featureNames, classFeatureNames ) )
                {
                    final Object newInstance = innerClass.newInstance();
                    invokeReceiver( featureContainer, newInstance );
                    register( classFeatureNames, newInstance );
                }
            }

        }
    }

    private void processFields(
        final Object featureContainer,
        final List<String> featureNames )
        throws IllegalAccessException,
            InvocationTargetException
    {
        final Field[] fields = featureContainer.getClass().getDeclaredFields();
        for ( final Field field : fields )
        {
            final Feature feature = field.getAnnotation( Feature.class );
            if ( feature != null )
            {
                final String[] fieldFeatureNames = feature.value();
                if ( contains( featureNames, fieldFeatureNames ) )
                {
                    field.setAccessible( true );
                    final Object newInstance = field.get( featureContainer );
                    invokeReceiver( featureContainer, newInstance );
                    register( fieldFeatureNames, newInstance );
                }
            }
        }
    }

    /**
     * @param instanceFeatureNames
     * @param newInstance
     */
    private void register(
        final String[] instanceFeatureNames,
        final Object newInstance )
    {
        for ( final String name : instanceFeatureNames )
        {
            features.put( name, newInstance );
        }
    }
}
