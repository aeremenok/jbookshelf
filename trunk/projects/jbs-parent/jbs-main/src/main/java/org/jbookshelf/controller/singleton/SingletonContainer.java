/**
 * <copyright> This file is part of JBookShelf, http://code.google.com/p/jbookshelf/<br>
 * <br>
 * Copyright (C) 2008-2009-2009 Andrey Yeremenok (eav1986_at_gmail_com) <br>
 * <br>
 * JBookShelf is free software; you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation; either version 3 of the License, or (at your option) any later
 * version.<br>
 * <br>
 * JBookShelf is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.<br>
 * <br>
 * You should have received a copy of the GNU General Public License along with this program. If not, see
 * <http://www.gnu.org/licenses/>. </copyright>
 */
package org.jbookshelf.controller.singleton;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;

/**
 * maps singleton classes to their instances and probable implementations
 * 
 * @author eav
 */
public class SingletonContainer
{
    private static final Logger           logger          = Logger.getLogger( SingletonContainer.class );

    private final Map<Class<?>, Object>   instances       = new Hashtable<Class<?>, Object>();

    private final Map<Class<?>, Class<?>> implementations = new Hashtable<Class<?>, Class<?>>();

    /**
     * get an instance of a specified class
     * 
     * @param <T> instance class literal
     * @param clazz instance class
     * @return instance
     */
    @SuppressWarnings( "unchecked" )
    public <T> T instance(
        final Class<T> clazz )
    {
        Object singleton = instances.get( clazz );
        if ( singleton == null )
        {
            // object not found - create
            try
            {
                final Class<?> impl = implementations.get( clazz );
                if ( impl != null )
                {
                    singleton = impl.newInstance();
                    instances.put( impl, singleton );
                }
                else
                {
                    singleton = clazz.newInstance();
                }
                instances.put( clazz, singleton );
            }
            catch ( final Exception e )
            {
                final String message = "cannot create " + clazz.getSimpleName();
                logger.error( message, e );
                throw new Error( message, e );
            }

            logger.debug( "created " + singleton.getClass().getSimpleName() );

            // initialize
            try
            {
                initSingleton( singleton );
            }
            catch ( final Exception e )
            {
                final String message = "cannot initialize " + singleton.getClass().getSimpleName();
                logger.error( message, e );
                throw new Error( message, e );
            }

            logger.debug( "initialized " + singleton.getClass().getSimpleName() );
        }

        return (T) singleton;
    }

    /**
     * create a new instance of the specified class without registering
     * 
     * @param <T> instance class literal
     * @param clazz instance class
     * @return new instance
     */
    @SuppressWarnings( "unchecked" )
    public <T> T newInstance(
        final Class<T> clazz )
    {
        Object newInstance;
        try
        {
            final Class<?> impl = implementations.get( clazz );
            if ( impl != null )
            {
                newInstance = impl.newInstance();
            }
            else
            {
                newInstance = clazz.newInstance();
            }
        }
        catch ( final Exception e )
        {
            final String message = "cannot create new " + clazz.getSimpleName();
            logger.error( message, e );
            throw new Error( message, e );
        }

        logger.debug( "created new " + newInstance.getClass().getSimpleName() );

        // initialize
        try
        {
            initSingleton( newInstance );
        }
        catch ( final Exception e )
        {
            final String message = "cannot initialize new " + newInstance.getClass().getSimpleName();
            logger.error( message, e );
            throw new Error( message, e );
        }

        logger.debug( "initialized new " + newInstance.getClass().getSimpleName() );

        return (T) newInstance;
    }

    /**
     * set implementation of a basic class
     * 
     * @param <B> basic class literal
     * @param <D> derived class literal
     * @param abstractClass basic class
     * @param concreteClass derived class
     */
    public <B, D extends B> void setImplementation(
        final Class<B> abstractClass,
        final Class<D> concreteClass )
    {
        implementations.put( abstractClass, concreteClass );
    }

    private void initSingleton(
        final Object singleton )
        throws IllegalArgumentException,
            IllegalAccessException,
            InvocationTargetException
    {
        final Method[] methods = singleton.getClass().getMethods();
        for ( final Method method : methods )
        {
            if ( method.getAnnotation( PostConstruct.class ) != null )
            {
                method.invoke( singleton );
                return;
            }
        }
    }

}
