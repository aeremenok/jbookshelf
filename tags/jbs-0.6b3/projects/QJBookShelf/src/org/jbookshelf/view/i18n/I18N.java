package org.jbookshelf.view.i18n;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.jbookshelf.controller.singleton.Single;
import org.xnap.commons.i18n.I18n;

/**
 * static delegate for translations
 * 
 * @author eav 2009
 */
public class I18N
{
    /**
     * language names in {@link Locale#ENGLISH} which have translations
     */
    private static final List<String> localizedLanguages = new ArrayList<String>();
    private static String             defaultLanguage;

    static
    {
        localizedLanguages.add( Locale.ENGLISH.getDisplayLanguage( Locale.ENGLISH ) );
        localizedLanguages.add( "Russian" );
    }

    /**
     * @return define system default language name in {@link Locale#ENGLISH}
     */
    public static String defaultLanguage()
    {
        if ( defaultLanguage == null )
        {
            defaultLanguage = Locale.getDefault().getDisplayLanguage( Locale.ENGLISH );
            if ( !I18N.getLocalizedLanguages().contains( defaultLanguage ) )
            {
                defaultLanguage = getLocalizedLanguages().get( 0 );
            }
        }
        return defaultLanguage;
    }

    public static List<String> getLocalizedLanguages()
    {
        return localizedLanguages;
    }

    /**
     * @return a common translator
     */
    public static I18n i18n()
    {
        return Single.instance( I18NImpl.class ).i18n();
    }

    /**
     * @param string source string (en)
     * @return its translation
     */
    public static String tr(
        final String string )
    {
        return Single.instance( I18NImpl.class ).tr( string );
    }

    /**
     * start a translation of component
     * 
     * @param translatable a component to translate
     */
    public static void translate(
        final Translatable translatable )
    {
        Single.instance( I18NImpl.class ).translate( translatable );
    }
}
