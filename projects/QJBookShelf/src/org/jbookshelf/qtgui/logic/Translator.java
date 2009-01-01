package org.jbookshelf.qtgui.logic;

import java.util.ArrayList;
import java.util.List;

import com.trolltech.qt.core.QTranslator;
import com.trolltech.qt.gui.QApplication;

public class Translator
{
    private static final List<Translatable> translatables = new ArrayList<Translatable>();

    private static QTranslator              translator;

    public static void addTranslatable(
        Translatable translatable )
    {
        translatables.add( translatable );
        translatable.retranslate();
    }

    public static void removeTranslatable(
        Translatable translatable )
    {
        translatables.remove( translatable );
    }

    public static void retranslate(
        String language )
    {
        if ( "Russian".equals( language ) )
        {
            translator = new QTranslator();
            QApplication.installTranslator( translator );
            translator.load( "classpath:/../translations/jbookshelf.qm" );
        }
        else if ( translator != null )
        {
            QApplication.removeTranslator( translator );
        }

        for ( Translatable translatable : translatables )
        {
            translatable.retranslate();
        }
    }
}
