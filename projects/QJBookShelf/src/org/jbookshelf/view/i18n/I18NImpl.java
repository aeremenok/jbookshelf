/**
 * 
 */
package org.jbookshelf.view.i18n;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.jbookshelf.controller.settings.Settings;
import org.jbookshelf.controller.singleton.Single;
import org.xnap.commons.i18n.I18n;
import org.xnap.commons.i18n.I18nFactory;

/**
 * @author eav 2009
 */
public class I18NImpl
{
    private Locale currentLocale;

    public I18n i18n()
    {
        if ( currentLocale == null )
        {
            return I18nFactory.getI18n( getClass(), "org.jbookshelf.view.i18n.Messages" );
        }
        return I18nFactory.getI18n( getClass(), "org.jbookshelf.view.i18n.Messages", currentLocale );
    }

    @PostConstruct
    public void init()
    {
        final String language = Single.instance( Settings.class ).LANGUAGE.getValue();
        // todo reorder
        currentLocale = I18N.getLocalizedLanguages().get( 0 ).equals( language )
            ? Locale.ENGLISH : null;
    }

    public String tr(
        final String string )
    {
        return i18n().tr( string );
    }

    public void translate(
        final Translatable translatable )
    {
        final I18n i18n = i18n();
        translatable.translate( i18n );
    }
}
