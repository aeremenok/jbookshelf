package org.jbookshelf.qtgui.logic;

import java.util.ArrayList;
import java.util.List;

import com.trolltech.qt.core.QTranslator;

public class Translator
{
    private static final List<Translatable> translatables = new ArrayList<Translatable>();

    private static QTranslator              currentTranslator;

    public static final QTranslator         RU;
    public static final QTranslator         EN;

    static
    {
        RU = new QTranslator();
        RU.load( "classpath:/translations/jbookshelf_ru.qm" );

        EN = new QTranslator();
        EN.load( "classpath:/translations/jbookshelf_en.qm" );

        currentTranslator = EN;
    }

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
        currentTranslator = "Russian".equals( language ) ? RU : EN;
        for ( Translatable translatable : translatables )
        {
            translatable.retranslate();
        }
    }

    public static String tr(
        String context,
        String source )
    {
        String translate = currentTranslator.translate( context, source );
        if ( translate.equals( "" ) )
        {
            return source;
        }
        return translate;
    }
}
