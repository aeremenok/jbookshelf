/**
 * 
 */
package org.jbookshelf.model.db;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

/**
 * @author eav
 */
@Entity
public class Category
    implements
    Serializable,
    HasBooks
{
    @Id
    @GeneratedValue
    private Long                id;

    @Column( nullable = false, unique = true )
    @org.hibernate.annotations.Index( name = "category_name_ind" )
    private String              name;

    @ManyToOne
    private Category            parent;

    @OneToMany( mappedBy = "parent" )
    @OrderBy( "name desc" )
    @org.hibernate.annotations.ForeignKey( name = "FK_PARENT_CATEGORY" )
    private final Set<Category> children = new HashSet<Category>();

    @ManyToMany
    @OrderBy( "name desc" )
    private final Set<Book>     books    = new HashSet<Book>();

    public Category()
    {
        super();
    }

    public void addChild(
        @Nonnull final Category category )
    {
        category.setParent( this );
        getChildren().add( category );
    }

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
        final Category other = (Category) obj;
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
        if ( this.parent == null )
        {
            if ( other.parent != null )
            {
                return false;
            }
        }
        else if ( !this.parent.equals( other.parent ) )
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
     * @return the children
     */
    public Set<Category> getChildren()
    {
        return this.children;
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

    /**
     * @return the parent
     */
    @Nullable
    public Category getParent()
    {
        return this.parent;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (this.id == null
            ? 0 : this.id.hashCode());
        result = prime * result + (this.name == null
            ? 0 : this.name.hashCode());
        result = prime * result + (this.parent == null
            ? 0 : this.parent.hashCode());
        return result;
    }

    public void removeChild(
        @Nonnull final Category category )
    {
        category.setParent( null );
        getChildren().remove( category );
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
     * @param parent the parent to set
     */
    public void setParent(
        @Nullable final Category parent )
    {
        this.parent = parent;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return getName();
    }
}
