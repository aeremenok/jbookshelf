/**
 * 
 */
package org.jbookshelf.model.db;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author eav
 */
@Entity
public class Category
{
    @Id
    @GeneratedValue
    private Long                id;

    @Column( nullable = false, unique = true )
    private String              name;

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

    /**
     * @return the books
     */
    public Set<Book> getBooks()
    {
        return this.books;
    }

    /**
     * @return the changeDate
     */
    public Date getChangeDate()
    {
        return this.changeDate;
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
    public Category getParent()
    {
        return this.parent;
    }

    /**
     * @param name the name to set
     */
    public void setName(
        final String name )
    {
        this.name = name;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(
        final Category parent )
    {
        this.parent = parent;
    }
}
