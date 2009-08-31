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
    Bookmark,
    Serializable
{
    @Id
    @GeneratedValue
    private Long    id;

    @Column( nullable = false )
    @Temporal( TemporalType.TIMESTAMP )
    private Date    changeDate;

    @Column( length = Short.MAX_VALUE )
    private String  content;

    @Column( length = Short.MAX_VALUE )
    private String  citation;

    @Column( nullable = false )
    private String  title;

    @Column
    private Float   position = 0f;

    @Column
    private Integer page;

    /**
     * xxx allows to recalculate the position in case of format conversion
     */
    @Column
    private Integer pageCount;

    @ManyToOne( optional = false )
    @JoinColumn( name = "BOOK_ID", nullable = false )
    @org.hibernate.annotations.ForeignKey( name = "FK_NOTE_BOOK" )
    private Book    book;

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
        if ( this.page == null )
        {
            if ( other.page != null )
            {
                return false;
            }
        }
        else if ( !this.page.equals( other.page ) )
        {
            return false;
        }
        if ( this.position == null )
        {
            if ( other.position != null )
            {
                return false;
            }
        }
        else if ( !this.position.equals( other.position ) )
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

    public Book getBook()
    {
        return this.book;
    }

    public Date getChangeDate()
    {
        return this.changeDate;
    }

    @Nullable
    public String getCitation()
    {
        return this.citation;
    }

    @Nullable
    public String getContent()
    {
        return this.content;
    }

    public Long getId()
    {
        return this.id;
    }

    public Integer getPage()
    {
        return this.page;
    }

    public Integer getPageCount()
    {
        return this.pageCount;
    }

    public Float getPosition()
    {
        return this.position;
    }

    public String getTitle()
    {
        return this.title;
    }

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
        result = prime * result + (this.page == null
            ? 0 : this.page.hashCode());
        result = prime * result + (this.position == null
            ? 0 : this.position.hashCode());
        result = prime * result + (this.title == null
            ? 0 : this.title.hashCode());
        return result;
    }

    public void setBook(
        @Nonnull final Book book )
    {
        this.book = book;
    }

    public void setCitation(
        @Nonnull final String citation )
    {
        this.citation = citation;
    }

    public void setContent(
        @Nonnull final String content )
    {
        this.content = content;
    }

    public void setPage(
        @Nonnull @Nonnegative final Integer page )
    {
        this.page = page;
    }

    public void setPageCount(
        final Integer pageCount )
    {
        this.pageCount = pageCount;
    }

    public void setPosition(
        @Nonnull @Nonnegative final Float position )
    {
        this.position = position;
    }

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
