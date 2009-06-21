package org.jbookshelf.view.i18n;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.jbookshelf.controller.singleton.Single;
import org.xnap.commons.i18n.I18n;

public class I18N
{
    private static final List<String> localizedLanguages = new ArrayList<String>();
    private static String             defaultLanguage;

    static
    {
        localizedLanguages.add( Locale.ENGLISH.getDisplayLanguage( Locale.ENGLISH ) );
        localizedLanguages.add( "Russian" );
    }

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

    public static I18n i18n()
    {
        return Single.instance( I18NImpl.class ).i18n();
    }

    public static String tr(
        final String string )
    {
        return Single.instance( I18NImpl.class ).tr( string );
    }

    public static void translate(
        final Translatable translatable )
    {
        Single.instance( I18NImpl.class ).translate( translatable );
    }
}
