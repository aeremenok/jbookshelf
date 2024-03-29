package org.jbookshelf.view.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.bushe.swing.event.EventBus;
import org.jbookshelf.model.db.Author;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.Category;
import org.jbookshelf.model.db.Named;

/**
 * manages the selection of bookshelf objects todo simplify
 * 
 * @author eav 2009
 */
public class BookShelfMediator
{
    public interface Properties
    {
        String BOOKS_SELECTED      = "BOOKS_SELECTED";
        String AUTHORS_SELECTED    = "AUTHORS_SELECTED";
        String CATEGORIES_SELECTED = "CATEGORIES_SELECTED";
        String UNIQUES_SELECTED    = "UNIQUES_SELECTED";
    }

    private Set<Named>         selectedUniques    = new HashSet<Named>();
    private List<Book>          selectedBooks      = new ArrayList<Book>();
    private List<Author>        selectedAuthors    = new ArrayList<Author>();
    private List<Category>      selectedCategories = new ArrayList<Category>();

    @SuppressWarnings( "unused" )
    private static final Logger log                = Logger.getLogger( BookShelfMediator.class );

    public void authorsSelected(
        final List<Author> list )
    {
        selectedBooks = new ArrayList<Book>();
        selectedAuthors = list;
        selectedCategories = new ArrayList<Category>();

        selectedUniques = new HashSet<Named>();
        selectedUniques.addAll( list );

        fireSelectionChanged();
    }

    public void booksSelected(
        final List<Book> list )
    {
        selectedBooks = list;
        selectedAuthors = new ArrayList<Author>();
        selectedCategories = new ArrayList<Category>();

        selectedUniques = new HashSet<Named>();
        selectedUniques.addAll( list );

        fireSelectionChanged();
    }

    public void categoriesSelected(
        final List<Category> list )
    {
        selectedBooks = new ArrayList<Book>();
        selectedAuthors = new ArrayList<Author>();
        selectedCategories = list;

        selectedUniques = new HashSet<Named>();
        selectedUniques.addAll( list );

        fireSelectionChanged();
    }

    public List<Author> getSelectedAuthors()
    {
        return this.selectedAuthors;
    }

    public List<Book> getSelectedBooks()
    {
        return selectedBooks;
    }

    public List<Category> getSelectedCategories()
    {
        return this.selectedCategories;
    }

    public Set<Named> getSelectedUniques()
    {
        return this.selectedUniques;
    }

    public void nothingSelected()
    {
        selectedBooks = new ArrayList<Book>();
        selectedAuthors = new ArrayList<Author>();
        selectedCategories = new ArrayList<Category>();
        selectedUniques = new HashSet<Named>();
        fireSelectionChanged();
    }

    public void uniquesSelected(
        final Collection<Named> list )
    {
        selectedBooks = new ArrayList<Book>();
        selectedAuthors = new ArrayList<Author>();
        selectedCategories = new ArrayList<Category>();

        selectedUniques = new HashSet<Named>();
        selectedUniques.addAll( list );

        for ( final Named unique : list )
        {
            if ( unique instanceof Author )
            {
                selectedAuthors.add( (Author) unique );
            }
            else if ( unique instanceof Book )
            {
                selectedBooks.add( (Book) unique );
            }
            else
            {
                selectedCategories.add( (Category) unique );
            }
        }

        fireSelectionChanged();
    }

    private void fireSelectionChanged()
    {
        EventBus.publish( Properties.BOOKS_SELECTED, this );
        EventBus.publish( Properties.AUTHORS_SELECTED, this );
        EventBus.publish( Properties.CATEGORIES_SELECTED, this );
        EventBus.publish( Properties.UNIQUES_SELECTED, this );
    }
}
