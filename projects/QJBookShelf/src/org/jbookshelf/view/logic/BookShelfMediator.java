package org.jbookshelf.view.logic;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.Unique;

public class BookShelfMediator
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
