package org.jbookshelf.qtgui.logic;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.jbookshelf.controller.singleton.Singleton;
import org.jbookshelf.model.Book;
import org.jbookshelf.model.Unique;

public class BookShelfMediator
    implements
        Singleton
{
    private final List<UniqueSelectionListener> listeners       = new ArrayList<UniqueSelectionListener>();
    private List<Unique>                        selectedUniques = new ArrayList<Unique>();
    private List<Book>                          selectedBooks;

    public void addUniqueSelectionListener(
        final UniqueSelectionListener listener )
    {
        listeners.add( listener );
    }

    public List<Book> getSelectedBooks()
    {
        if ( selectedBooks == null )
        {
            selectedBooks = new ArrayList<Book>();
            for ( final Unique unique : selectedUniques )
            {
                if ( unique instanceof Book )
                {
                    selectedBooks.add( (Book) unique );
                }
            }
        }
        return selectedBooks;
    }

    public List<Unique> getSelectedUniques()
    {
        return selectedUniques;
    }

    public void initSingleton()
    {
    }

    public void removeUniqueSelectionListener(
        final UniqueSelectionListener listener )
    {
        listeners.remove( listener );
    }

    public void selectedUniques(
        final List<Unique> list )
    {
        Assert.assertNotNull( list );
        selectedUniques = list;
        selectedBooks = null;
        for ( final UniqueSelectionListener listener : listeners )
        {
            listener.selectedUniques( list );
        }
    }
}
