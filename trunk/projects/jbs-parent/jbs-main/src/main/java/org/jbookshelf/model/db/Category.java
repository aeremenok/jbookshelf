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
    @GeneratedValue( strategy = GenerationType.SEQUENCE )
    private Long                id;

    @Column( nullable = false )
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

    public static final String  ROOT     = "!ROOT!";

    public Category()
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

    public Set<Book> getBooks()
    {
        return this.books;
    }

    public Set<Category> getChildren()
    {
        return this.children;
    }

    public Long getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

    public Category getParent()
    {
        return this.parent;
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
        result = prime * result + (this.parent == null
            ? 0 : this.parent.hashCode());
        return result;
    }

    @Override
    public void setId(
        final Long id )
    {
        this.id = id;
    }

    public void setName(
        final String name )
    {
        this.name = name;
    }

    public void setParent(
        final Category parent )
    {
        this.parent = parent;
    }

    @Override
    public String toString()
    {
        return getName();
    }
}
