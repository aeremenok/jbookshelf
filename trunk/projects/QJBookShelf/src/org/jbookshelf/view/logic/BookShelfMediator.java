package org.jbookshelf.view.logic;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Nonnull;

import org.jbookshelf.model.db.Author;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.Category;
import org.jbookshelf.model.db.Unique;

public class BookShelfMediator
{
    public enum Properties
    {
        BOOKS_SELECTED,
        AUTHORS_SELECTED,
        CATEGORIES_SELECTED,
        UNIQUES_SELECTED
    }

    @Nonnull
    private Set<Unique>                 selectedUniques       = new HashSet<Unique>();
    @Nonnull
    private List<Book>                  selectedBooks         = new ArrayList<Book>();
    @Nonnull
    private List<Author>                selectedAuthors       = new ArrayList<Author>();
    @Nonnull
    private List<Category>              selectedCategories    = new ArrayList<Category>();

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport( this );

    /**
     * @param property
     * @param listener
     * @see java.beans.PropertyChangeSupport#addPropertyChangeListener(java.lang.String,
     *      java.beans.PropertyChangeListener)
     */
    public void addPropertyChangeListener(
        final Properties property,
        final PropertyChangeListener listener )
    {
        this.propertyChangeSupport.addPropertyChangeListener( property.name(), listener );
    }

    public void authorsSelected(
        final List<Author> list )
    {
        selectedBooks = new ArrayList<Book>();
        selectedAuthors = list;
        selectedCategories = new ArrayList<Category>();

        selectedUniques = new HashSet<Unique>();
        selectedUniques.addAll( list );

        fireSelectionChanged();
    }

    public void booksSelected(
        final List<Book> list )
    {
        selectedBooks = list;
        selectedAuthors = new ArrayList<Author>();
        selectedCategories = new ArrayList<Category>();

        selectedUniques = new HashSet<Unique>();
        selectedUniques.addAll( list );

        fireSelectionChanged();
    }

    public void categoriesSelected(
        final List<Category> list )
    {
        selectedBooks = new ArrayList<Book>();
        selectedAuthors = new ArrayList<Author>();
        selectedCategories = list;

        selectedUniques = new HashSet<Unique>();
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

    /**
     * @return the selectedUniques
     */
    public Set<Unique> getSelectedUniques()
    {
        return this.selectedUniques;
    }

    public void nothingSelected()
    {
        selectedBooks = new ArrayList<Book>();
        selectedAuthors = new ArrayList<Author>();
        selectedCategories = new ArrayList<Category>();
        selectedUniques = new HashSet<Unique>();
        fireSelectionChanged();
    }

    /**
     * @param property
     * @param listener
     * @see java.beans.PropertyChangeSupport#removePropertyChangeListener(java.lang.String,
     *      java.beans.PropertyChangeListener)
     */
    public void removePropertyChangeListener(
        final Properties property,
        final PropertyChangeListener listener )
    {
        this.propertyChangeSupport.removePropertyChangeListener( property.name(), listener );
    }

    public void uniquesSelected(
        final Collection<Unique> list )
    {
        selectedBooks = new ArrayList<Book>();
        selectedAuthors = new ArrayList<Author>();
        selectedCategories = new ArrayList<Category>();

        selectedUniques = new HashSet<Unique>();
        selectedUniques.addAll( list );

        for ( final Unique unique : list )
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

    /**
     * @param property
     * @param oldValue
     * @param newValue
     * @see java.beans.PropertyChangeSupport#firePropertyChange(java.lang.String, java.lang.Object, java.lang.Object)
     */
    private void firePropertyChange(
        final Properties property,
        final Object oldValue,
        final Object newValue )
    {
        this.propertyChangeSupport.firePropertyChange( property.name(), oldValue, newValue );
    }

    private void fireSelectionChanged()
    {
        firePropertyChange( Properties.BOOKS_SELECTED, null, selectedBooks );
        firePropertyChange( Properties.AUTHORS_SELECTED, null, selectedAuthors );
        firePropertyChange( Properties.CATEGORIES_SELECTED, null, selectedCategories );
        firePropertyChange( Properties.UNIQUES_SELECTED, null, selectedUniques );
    }
}
