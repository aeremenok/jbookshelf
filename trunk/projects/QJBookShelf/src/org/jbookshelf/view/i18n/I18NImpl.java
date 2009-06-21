/**
 * 
 */
package org.jbookshelf.view.i18n;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.xnap.commons.i18n.I18n;
import org.xnap.commons.i18n.I18nFactory;

/**
 * @author eav 2009
 */
public class I18NImpl
    implements
    PropertyChangeListener
{
    private static String      defaultLanguage;
    private final List<String> localizedLanguages = new ArrayList<String>();

    public I18NImpl()
    {
        super();
        localizedLanguages.add( Locale.ENGLISH.getDisplayLanguage( Locale.ENGLISH ) );
        localizedLanguages.add( "Russian" );
    }

    public String defaultLanguage()
    {
        if ( defaultLanguage == null )
        {
            defaultLanguage = Locale.getDefault().getDisplayLanguage( Locale.ENGLISH );
            if ( !I18N.getLocalizedLanguages().contains( defaultLanguage ) )
            {
                defaultLanguage = I18N.getLocalizedLanguages().get( 0 );
            }
        }
        return defaultLanguage;
    }

    public List<String> getLocalizedLanguages()
    {
        return this.localizedLanguages;
    }

    public I18n i18n(
        final Class<?> class1 )
    {
        return I18nFactory.getI18n( class1, "org.jbookshelf.view.i18n.Messages" );
    }

    public void propertyChange(
        final PropertyChangeEvent evt )
    {
        setLanguage( evt.getNewValue().toString() );
    }

    public String tr(
        final String string,
        final Class<?> class1 )
    {
        return i18n( class1 ).tr( string );
    }

    public void translate(
        final Translatable translatable )
    {
        final I18n i18n = i18n( translatable.getClass() );
        translatable.retranslate( i18n );
    }

    private void setLanguage(
        @SuppressWarnings( "unused" ) final String language )
    {
    // TODO Auto-generated method stub
    //        Translator.retranslate( language );
    }
}
