/**
 * 
 */
package org.jbookshelf.model.db;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author eav
 */
@Entity
public class Category
    implements
    Serializable,
    Timestampable,
    Unique
{
    @Id
    @GeneratedValue
    private Long                id;

    @Column( nullable = false, unique = true )
    private String              name;

    @SuppressWarnings( "unused" )
    @Column( nullable = false )
    @Temporal( TemporalType.TIMESTAMP )
    private Date                changeDate;

    @ManyToOne
    private Category            parent;

    @OneToMany( mappedBy = "parent" )
    @org.hibernate.annotations.ForeignKey( name = "FK_PARENT_CATEGORY" )
    private final Set<Category> children = new HashSet<Category>();

    @ManyToMany
    private final Set<Book>     books    = new HashSet<Book>();

    public void addChild(
        @Nonnull final Category category )
    {
        category.setParent( this );
        getChildren().add( category );
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

    @PreUpdate
    @PrePersist
    public void timestamp()
    {
        changeDate = new Date();
    }
}
