/**
 * 
 */
package org.jbookshelf.model.db;

import java.io.Serializable;
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
import javax.persistence.Transient;

/**
 * @author eav
 */
@Entity
public class Book
    implements
    Serializable,
    Unique,
    HasBooks
{
    @Id
    @GeneratedValue
    private Long                id;

    @Column( nullable = false )
    @org.hibernate.annotations.Index( name = "book_name_ind" )
    private String              name;

    @Column
    private Float               read         = 0f;

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

    @OneToOne( mappedBy = "book" )
    private PhysicalBook        physicalBook;

    public static final String  RELATED      = "related";

    public Book()
    {
        super();
    }

    @Override
    public boolean equals(
        final Object obj )
    {
        if ( this == obj )
        {
            return true;
        }
        if ( obj == null )
        {
            return false;
        }
        if ( getClass() != obj.getClass() )
        {
            return false;
        }
        final Book other = (Book) obj;
        if ( this.id == null )
        {
            if ( other.id != null )
            {
                return false;
            }
        }
        else if ( !this.id.equals( other.id ) )
        {
            return false;
        }
        if ( this.name == null )
        {
            if ( other.name != null )
            {
                return false;
            }
        }
        else if ( !this.name.equals( other.name ) )
        {
            return false;
        }
        if ( this.read == null )
        {
            if ( other.read != null )
            {
                return false;
            }
        }
        else if ( !this.read.equals( other.read ) )
        {
            return false;
        }
        return true;
    }

    public Set<Author> getAuthors()
    {
        return this.authors;
    }

    @Override
    @Transient
    public Set<Book> getBooks()
    {
        return getRelatedBooks();
    }

    public Set<Category> getCategories()
    {
        return this.categories;
    }

    public Long getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

    public Set<Note> getNotes()
    {
        return this.notes;
    }

    public PhysicalBook getPhysicalBook()
    {
        return this.physicalBook;
    }

    public Float getRead()
    {
        return this.read;
    }

    public Set<Book> getRelatedBooks()
    {
        return this.relatedBooks;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (this.id == null
            ? 0 : this.id.hashCode());
        result = prime * result + (this.name == null
            ? 0 : this.name.hashCode());
        result = prime * result + (this.read == null
            ? 0 : this.read.hashCode());
        return result;
    }

    public void setName(
        @Nonnull final String name )
    {
        this.name = name;
    }

    public void setPhysicalBook(
        @Nonnull final PhysicalBook physicalBook )
    {
        this.physicalBook = physicalBook;
        physicalBook.setBook( this );
    }

    public void setRead(
        @Nonnull @Nonnegative final Float read )
    {
        this.read = read;
    }

    @Override
    public String toString()
    {
        return getName();
    }
}
