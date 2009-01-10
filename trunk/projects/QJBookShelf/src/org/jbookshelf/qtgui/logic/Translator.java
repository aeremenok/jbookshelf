/**
 * <copyright> This file is part of JBookShelf, http://code.google.com/p/jbookshelf/<br>
 * <br>
 * Copyright (C) 2008-2009 Andrey Yeremenok (eav1986_at_gmail_com) <br>
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
package org.jbookshelf.qtgui.logic;

import java.util.ArrayList;
import java.util.List;

import com.trolltech.qt.core.QTranslator;
import com.trolltech.qt.gui.QApplication;

/**
 * Centralized {@link QTranslator} handler todo probably a bug of jambi:
 * {@link QApplication#removeTranslator(QTranslator)} doesn't take effect. This class deals with such a bug.
 * 
 * @author eav
 */
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
