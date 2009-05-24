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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author eav
 */
@Entity
public class Author
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
     * @param name the name to set
     */
    public void setName(
        final String name )
    {
        this.name = name;
    }
}
