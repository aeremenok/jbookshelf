/**
 * 
 */
package org.jbookshelf.model.db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author eav
 */
@Entity
public class Note
{
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long   id;

    @Column( nullable = false )
    @Temporal( TemporalType.TIMESTAMP )
    private Date   changeDate;

    @Column( nullable = false, length = Short.MAX_VALUE )
    private String content;

    @Column( nullable = false )
    private String title;

    @ManyToOne( optional = false )
    @JoinColumn( name = "BOOK_ID", nullable = false )
    @org.hibernate.annotations.ForeignKey( name = "FK_BOOK_ID" )
    private Book   book;

    /**
     * @return the book
     */
    public Book getBook()
    {
        return this.book;
    }

    /**
     * @return the changeDate
     */
    public Date getChangeDate()
    {
        return this.changeDate;
    }

    /**
     * @return the content
     */
    public String getContent()
    {
        return this.content;
    }

    /**
     * @return the id
     */
    public Long getId()
    {
        return this.id;
    }

    /**
     * @return the title
     */
    public String getTitle()
    {
        return this.title;
    }

    /**
     * @param book the book to set
     */
    public void setBook(
        final Book book )
    {
        this.book = book;
    }

    /**
     * @param content the content to set
     */
    public void setContent(
        final String content )
    {
        this.content = content;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(
        final String title )
    {
        this.title = title;
    }
}
