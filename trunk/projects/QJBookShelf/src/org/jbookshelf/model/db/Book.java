/**
 * 
 */
package org.jbookshelf.model.db;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author eav
 */
@Entity
public class Book
    implements
    Serializable
{
    @SuppressWarnings( "unused" )
    @Id
    @GeneratedValue
    private Long                id;

    @Column( nullable = false, unique = true )
    private String              name;

    @Column
    private Float               read         = 0f;

    @SuppressWarnings( "unused" )
    @Column( nullable = false )
    @Temporal( TemporalType.TIMESTAMP )
    private Date                changeDate;

    @OneToMany( mappedBy = "book" )
    private final Set<Note>     notes        = new HashSet<Note>();

    @ManyToMany
    @JoinTable( name = "RELATED_BOOKS" )
    @org.hibernate.annotations.ForeignKey( name = "FK_MAIN_BOOK", inverseName = "FK_RELATED_BOOK" )
    private final Set<Book>     relatedBooks = new HashSet<Book>();

    @ManyToMany( mappedBy = "books" )
    @org.hibernate.annotations.ForeignKey( name = "FK_AUTHOR_BOOK", inverseName = "FK_BOOK_AUTHOR" )
    private final Set<Author>   authors      = new HashSet<Author>();

    @ManyToMany( mappedBy = "books" )
    @org.hibernate.annotations.ForeignKey( name = "FK_CATEGORY_BOOK", inverseName = "FK_BOOK_CATEGORY" )
    private final Set<Category> categories   = new HashSet<Category>();

    @OneToOne( mappedBy = "book", optional = false )
    private PhysicalBook        physicalBook;

    public void addAuthor(
        @Nonnull final Author author )
    {
        author.getBooks().add( this );
        getAuthors().add( author );
    }

    public void addCategory(
        @Nonnull final Category category )
    {
        category.getBooks().add( this );
        getCategories().add( category );
    }

    public void addRelatedBook(
        @Nonnull final Book book )
    {
        book.getRelatedBooks().add( this );
        getRelatedBooks().add( book );
    }

    /**
     * @return the authors
     */
    public Set<Author> getAuthors()
    {
        return this.authors;
    }

    /**
     * @return the categories
     */
    public Set<Category> getCategories()
    {
        return this.categories;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * @return the comments
     */
    public Set<Note> getNotes()
    {
        return this.notes;
    }

    /**
     * @return the physicalBook
     */
    public PhysicalBook getPhysicalBook()
    {
        return this.physicalBook;
    }

    /**
     * @return the read
     */
    public Float getRead()
    {
        return this.read;
    }

    /**
     * @return the relatedBooks
     */
    public Set<Book> getRelatedBooks()
    {
        return this.relatedBooks;
    }

    public void removeAuthor(
        @Nonnull final Author author )
    {
        author.getBooks().remove( this );
        getAuthors().remove( author );
    }

    public void removeCategory(
        @Nonnull final Category category )
    {
        category.getBooks().remove( this );
        getAuthors().remove( category );
    }

    public void removeRelatedBook(
        @Nonnull final Book book )
    {
        book.getRelatedBooks().remove( this );
        getRelatedBooks().remove( book );
    }

    /**
     * @param name the name to set
     */
    public void setName(
        @Nonnull final String name )
    {
        this.name = name;
    }

    /**
     * @param physicalBook the physicalBook to set
     */
    public void setPhysicalBook(
        @Nonnull final PhysicalBook physicalBook )
    {
        this.physicalBook = physicalBook;
        physicalBook.setBook( this );
    }

    /**
     * @param read the read to set
     */
    public void setRead(
        @Nonnull @Nonnegative final Float read )
    {
        this.read = read;
    }

    @PreUpdate
    @PrePersist
    public void timestamp()
    {
        changeDate = new Date();
    }
}
