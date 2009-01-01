package org.jbookshelf.qtgui.logic;

import java.util.ArrayList;
import java.util.List;

public class Translator
{
    private static final List<Translatable> translatables = new ArrayList<Translatable>();

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
        @SuppressWarnings( "unused" ) String language )
    {
        // todo switch translation
        // QApplication.installTranslator( language );
        for ( Translatable translatable : translatables )
        {
            translatable.retranslate();
        }
    }

}
