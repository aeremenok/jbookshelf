package org.jbookshelf.view.i18n;

import java.beans.PropertyChangeListener;
import java.util.List;

import org.xnap.commons.i18n.I18n;

public class I18N
{
    private static final I18NImpl              impl                      = new I18NImpl();

    public static final PropertyChangeListener LANGUAGE_SETTING_LISTENER = impl;

    public static String defaultLanguage()
    {
        return impl.defaultLanguage();
    }

    public static List<String> getLocalizedLanguages()
    {
        return impl.getLocalizedLanguages();
    }

    public static I18n i18n(
        final Class<?> class1 )
    {
        return I18N.impl.i18n( class1 );
    }

    public static String tr(
        final String string,
        final Class<?> clazz )
    {
        return impl.tr( string, clazz );
    }

    public static void translate(
        final Translatable translatable )
    {
        impl.translate( translatable );
    }
}
