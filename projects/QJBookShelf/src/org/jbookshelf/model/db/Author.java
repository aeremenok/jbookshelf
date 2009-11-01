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
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;

import org.jbookshelf.model.db.api.spec.IAuthor;
import org.jbookshelf.model.db.api.spec.IBook;

/**
 * @author eav
 */
@Entity
class Author
    implements
    IAuthor
{
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE )
    private Long            id;

    @Column( nullable = false, unique = true )
    @org.hibernate.annotations.Index( name = "author_name_ind" )
    private String          name;

    @ManyToMany
    @OrderBy( "name DESC" )
    private final Set<Book> booksImpl = new HashSet<Book>();

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
        final Author other = (Author) obj;
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

    public Set<IBook> getBooks()
    {
        return new HashSet<IBook>( booksImpl );
    }

    public Set<Book> getBooksImpl()
    {
        return this.booksImpl;
    }

    public Long getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
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

    @Override
    public void setId(
        final Serializable id )
    {
        this.id = (Long) id;
    }

    public void setName(
        final String name )
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return getName();
    }
}
