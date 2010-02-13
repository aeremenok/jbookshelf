/**
 * 
 */
package org.jbookshelf.model.db;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.jbookshelf.view.i18n.I18N;

/**
 * @author eav
 */
@Entity
public class Book
    implements
    Serializable,
    Named,
    HasBooks
{
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE )
    private Long                id;

    @Column( nullable = false )
    @org.hibernate.annotations.Index( name = "book_name_ind" )
    private String              name;

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

    @OneToOne
    @JoinColumn( name = "lastread_id" )
    @org.hibernate.annotations.ForeignKey( name = "FK_LASTREAD_BOOK" )
    private Note                lastRead;

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

    public Note getLastRead()
    {
        return this.lastRead;
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
        return result;
    }

    @Transient
    public boolean isRead()
    {
        if ( lastRead == null )
        {
            return false;
        }
        return lastRead.getPosition() == 1f;
    }

    @Override
    public void setId(
        final Long id )
    {
        this.id = id;
    }

    public void setLastRead(
        final Note lastRead )
    {
        this.lastRead = lastRead;
    }

    public void setName(
        final String name )
    {
        this.name = name;
    }

    public void setPhysicalBook(
        final PhysicalBook physicalBook )
    {
        this.physicalBook = physicalBook;
        physicalBook.setBook( this );
    }

    public void setRead(
        final boolean isRead )
    {
        if ( lastRead == null )
        {
            lastRead = new Note();
            lastRead.setTitle( I18N.tr( "Last read position" ) );

            lastRead.setBook( this );
        }
        lastRead.setPosition( isRead
            ? 1f : 0f );
    }

    @Override
    public String toString()
    {
        return getName();
    }
}
