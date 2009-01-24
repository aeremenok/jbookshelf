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
package org.jbookshelf.qtgui.widgets.completion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import org.jbookshelf.controller.settings.JBookShelfSettings;
import org.jbookshelf.controller.settings.Settings;
import org.jbookshelf.model.BookShelf;
import org.jbookshelf.model.Comment;
import org.jbookshelf.model.Commentable;
import org.jbookshelf.model.Unique;
import org.jbookshelf.qtgui.logic.JBookShelfConstants;

public class CompletionDictionary
    implements
        JBookShelfConstants
{
    private static final String               FILENAME    = "dictionary";
    private final static CompletionDictionary instance    = new CompletionDictionary();
    private final Properties                  completions = new Properties();

    public static CompletionDictionary getInstance()
    {
        return instance;
    }

    private CompletionDictionary()
    {
        File file = new File( getFileName() );
        if ( file.exists() )
        {
            try
            {
                completions.load( new FileInputStream( file ) );
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }
        }
    }

    public synchronized void add(
        String word )
    {
        completions.put( word, "" );
    }

    public void addText(
        String text )
    {
        StringTokenizer tokenizer = new StringTokenizer( text, EOW + " " );
        while ( tokenizer.hasMoreTokens() )
        {
            add( tokenizer.nextToken() );
        }
    }

    public void clear()
    {
        completions.clear();
    }

    public void collectWords(
        final BookShelf bookShelf )
    {
        // todo run in new Thread
        collectWords( bookShelf.getAuthors() );
        collectWords( bookShelf.getCategories() );
        collectWords( bookShelf.getReadingUnits() );
    }

    public List<String> getCompletions()
    {
        // todo optimize
        List<String> list = new ArrayList<String>();
        for ( Object object : completions.keySet() )
        {
            list.add( (String) object );
        }
        return list;
    }

    public void save()
    {
        try
        {
            completions.store( new FileOutputStream( getFileName() ), "completion dictionary" );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }

    private void collectWords(
        List<? extends Unique> list )
    {
        for ( Unique unique : list )
        {
            addText( unique.getName() );
            if ( unique instanceof Commentable )
            {
                for ( Comment comment : ((Commentable) unique).getComments() )
                {
                    addText( comment.getContent() );
                }
            }
        }
    }

    private String getFileName()
    {
        String folder = Settings.getInstance().getProperty( JBookShelfSettings.JBS_FOLDER );
        return folder + "/" + FILENAME;
    }
}
