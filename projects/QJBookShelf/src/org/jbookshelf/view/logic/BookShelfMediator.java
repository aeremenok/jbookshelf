package org.jbookshelf.view.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.bushe.swing.event.EventBus;
import org.jbookshelf.model.db.api.Named;
import org.jbookshelf.model.db.api.spec.IAuthor;
import org.jbookshelf.model.db.api.spec.IBook;
import org.jbookshelf.model.db.api.spec.ICategory;

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

    private Set<Named>          selectedUniques    = new HashSet<Named>();
    private List<IBook>         selectedBooks      = new ArrayList<IBook>();
    private List<IAuthor>       selectedAuthors    = new ArrayList<IAuthor>();
    private List<ICategory>     selectedCategories = new ArrayList<ICategory>();

    @SuppressWarnings( "unused" )
    private static final Logger log                = Logger.getLogger( BookShelfMediator.class );

    public void authorsSelected(
        final List<IAuthor> list )
    {
        selectedBooks = new ArrayList<IBook>();
        selectedAuthors = list;
        selectedCategories = new ArrayList<ICategory>();

        selectedUniques = new HashSet<Named>();
        selectedUniques.addAll( list );

        fireSelectionChanged();
    }

    public void booksSelected(
        final List<IBook> list )
    {
        selectedBooks = list;
        selectedAuthors = new ArrayList<IAuthor>();
        selectedCategories = new ArrayList<ICategory>();

        selectedUniques = new HashSet<Named>();
        selectedUniques.addAll( list );

        fireSelectionChanged();
    }

    public void categoriesSelected(
        final List<ICategory> list )
    {
        selectedBooks = new ArrayList<IBook>();
        selectedAuthors = new ArrayList<IAuthor>();
        selectedCategories = list;

        selectedUniques = new HashSet<Named>();
        selectedUniques.addAll( list );

        fireSelectionChanged();
    }

    public List<IAuthor> getSelectedAuthors()
    {
        return this.selectedAuthors;
    }

    public List<IBook> getSelectedBooks()
    {
        return selectedBooks;
    }

    public List<ICategory> getSelectedCategories()
    {
        return this.selectedCategories;
    }

    public Set<Named> getSelectedUniques()
    {
        return this.selectedUniques;
    }

    public void nothingSelected()
    {
        selectedBooks = new ArrayList<IBook>();
        selectedAuthors = new ArrayList<IAuthor>();
        selectedCategories = new ArrayList<ICategory>();
        selectedUniques = new HashSet<Named>();
        fireSelectionChanged();
    }

    public void uniquesSelected(
        final Collection<Named> list )
    {
        selectedBooks = new ArrayList<IBook>();
        selectedAuthors = new ArrayList<IAuthor>();
        selectedCategories = new ArrayList<ICategory>();

        selectedUniques = new HashSet<Named>();
        selectedUniques.addAll( list );

        for ( final Named unique : list )
        {
            if ( unique instanceof IAuthor )
            {
                selectedAuthors.add( (IAuthor) unique );
            }
            else if ( unique instanceof IBook )
            {
                selectedBooks.add( (IBook) unique );
            }
            else
            {
                selectedCategories.add( (ICategory) unique );
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
