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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author eav
 */
@Entity
public class Note
    implements
    Serializable
{
    @Id
    @GeneratedValue
    private Long   id;

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
        final Note other = (Note) obj;
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
        if ( this.citation == null )
        {
            if ( other.citation != null )
            {
                return false;
            }
        }
        else if ( !this.citation.equals( other.citation ) )
        {
            return false;
        }
        if ( this.content == null )
        {
            if ( other.content != null )
            {
                return false;
            }
        }
        else if ( !this.content.equals( other.content ) )
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
        if ( this.pos == null )
        {
            if ( other.pos != null )
            {
                return false;
            }
        }
        else if ( !this.pos.equals( other.pos ) )
        {
            return false;
        }
        if ( this.title == null )
        {
            if ( other.title != null )
            {
                return false;
            }
        }
        else if ( !this.title.equals( other.title ) )
        {
            return false;
        }
        return true;
    }

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
     * @return the id
     */
    public Long getId()
    {
        return this.id;
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
        result = prime * result + (this.citation == null
            ? 0 : this.citation.hashCode());
        result = prime * result + (this.content == null
            ? 0 : this.content.hashCode());
        result = prime * result + (this.id == null
            ? 0 : this.id.hashCode());
        result = prime * result + (this.pos == null
            ? 0 : this.pos.hashCode());
        result = prime * result + (this.title == null
            ? 0 : this.title.hashCode());
        return result;
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

    public void timestamp()
    {
        changeDate = new Date();
    }
}
