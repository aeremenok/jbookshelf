/**
 * 
 */
package org.jbookshelf.model.db;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author eav
 */
@Entity
public class Author
    implements
    Serializable,
    Timestampable,
    Unique
{
    @Id
    @GeneratedValue
    private Long            id;

    @Column( nullable = false, unique = true )
    private String          name;

    @Column( nullable = false )
    @Temporal( TemporalType.TIMESTAMP )
    private Date            changeDate;

    @ManyToMany
    private final Set<Book> books = new HashSet<Book>();

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
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
        if ( this.changeDate == null )
        {
            if ( other.changeDate != null )
            {
                return false;
            }
        }
        else if ( !this.changeDate.equals( other.changeDate ) )
        {
            return false;
        }
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

    /**
     * @return the books
     */
    public Set<Book> getBooks()
    {
        return this.books;
    }

    /**
     * @return the id
     */
    public Long getId()
    {
        return this.id;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return this.name;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (this.changeDate == null
            ? 0 : this.changeDate.hashCode());
        result = prime * result + (this.id == null
            ? 0 : this.id.hashCode());
        result = prime * result + (this.name == null
            ? 0 : this.name.hashCode());
        return result;
    }

    /**
     * @param name the name to set
     */
    public void setName(
        @Nonnull final String name )
    {
        this.name = name;
    }

    @PreUpdate
    @PrePersist
    public void timestamp()
    {
        changeDate = new Date();
    }
}
