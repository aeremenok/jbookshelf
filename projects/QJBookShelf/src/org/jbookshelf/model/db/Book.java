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
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author eav
 */
@Entity
public class Book
{
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long            id;

    @Column( nullable = false, unique = true )
    private String          name;

    @Column( nullable = false )
    private Float           read;

    @Column( nullable = false )
    @Temporal( TemporalType.TIMESTAMP )
    private Date            changeDate;

    @OneToMany( mappedBy = "book" )
    private final Set<Note> notes = new HashSet<Note>();

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
     * @return the comments
     */
    public Set<Note> getNotes()
    {
        return this.notes;
    }

    /**
     * @return the read
     */
    public Float getRead()
    {
        return this.read;
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
     * @param read the read to set
     */
    public void setRead(
        final Float read )
    {
        this.read = read;
    }
}
