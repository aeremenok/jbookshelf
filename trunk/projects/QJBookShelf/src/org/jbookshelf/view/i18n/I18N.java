package org.jbookshelf.view.i18n;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


public class I18N
{
    public static final PropertyChangeListener LANGUAGE_LISTENER   = new PropertyChangeListener()
                                                                   {
                                                                       public void propertyChange(
                                                                           final PropertyChangeEvent evt )
                                                                       {
                                                                           setLanguage( evt.getNewValue().toString() );
                                                                       }
                                                                   };

    public final static List<String>           LOCALIZED_LANGUAGES =
                                                                       Arrays.asList( new String[] {
                    Locale.ENGLISH.getDisplayLanguage( Locale.ENGLISH ),
                    // todo
                    "Russian"                                         } );

    private static String                      defaultLanguage;

    public static String defaultLanguage()
    {
        if ( defaultLanguage == null )
        {
            defaultLanguage = Locale.getDefault().getDisplayLanguage( Locale.ENGLISH );
            if ( !LOCALIZED_LANGUAGES.contains( defaultLanguage ) )
            {
                defaultLanguage = LOCALIZED_LANGUAGES.get( 0 );
            }
        }
        return defaultLanguage;
    }

    public static void setLanguage(
        final String language )
    {
        // TODO Auto-generated method stub
        Translator.retranslate( language );
    }

    public static String tr(
        final String string )
    {
        // todo
        return string;
    }

}
