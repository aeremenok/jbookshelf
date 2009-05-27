/**
 * 
 */
package org.jbookshelf.model.db;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author eav
 */
@Entity
public class Note
    implements
    Serializable,
    Timestampable
{
    @SuppressWarnings( "unused" )
    @Id
    @GeneratedValue
    private Long   id;

    @SuppressWarnings( "unused" )
    @Column( nullable = false )
    @Temporal( TemporalType.TIMESTAMP )
    private Date   changeDate;

    @Column( length = Short.MAX_VALUE )
    private String content;

    @Column( length = Short.MAX_VALUE )
    private String citation;

    @Column( nullable = false )
    private String title;

    @Column
    private Float  pos = 0f;

    @ManyToOne( optional = false )
    @JoinColumn( name = "BOOK_ID", nullable = false )
    @org.hibernate.annotations.ForeignKey( name = "FK_NOTE_BOOK" )
    private Book   book;

    /**
     * @return the book
     */
    public Book getBook()
    {
        return this.book;
    }

    /**
     * @return the citation
     */
    @Nullable
    public String getCitation()
    {
        return this.citation;
    }

    /**
     * @return the content
     */
    @Nullable
    public String getContent()
    {
        return this.content;
    }

    /**
     * @return the position
     */
    public Float getPosition()
    {
        return this.pos;
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
        @Nonnull final Book book )
    {
        this.book = book;
    }

    /**
     * @param citation the citation to set
     */
    public void setCitation(
        @Nonnull final String citation )
    {
        this.citation = citation;
    }

    /**
     * @param content the content to set
     */
    public void setContent(
        @Nonnull final String content )
    {
        this.content = content;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(
        @Nonnull @Nonnegative final Float position )
    {
        this.pos = position;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(
        @Nonnull final String title )
    {
        this.title = title;
    }

    @PreUpdate
    @PrePersist
    public void timestamp()
    {
        changeDate = new Date();
    }
}
