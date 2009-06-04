/**
 * 
 */
package org.jbookshelf.model.db;

import java.io.Serializable;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author eav
 */
@Entity
@Immutable
public class Word
    implements
    Serializable
{
    @Id
    @GeneratedValue
    private Long   id;

    @ManyToOne( optional = false )
    @org.hibernate.annotations.ForeignKey( name = "FK_WORD_BOOK" )
    private Book   book;

    @Column( nullable = false, length = Byte.MAX_VALUE )
    private String word;

    @Column( nullable = false )
    private Long   pos;

    public Word()
    {
        super();
    }

    /**
     * @param word
     * @param book
     * @param pos
     */
    public Word(
        @Nonnull final String word,
        @Nonnull final Book book,
        @Nonnull @Nonnegative final Long pos )
    {
        super();
        this.word = word;
        this.book = book;
        this.pos = pos;
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
        final Word other = (Word) obj;
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
        if ( this.word == null )
        {
            if ( other.word != null )
            {
                return false;
            }
        }
        else if ( !this.word.equals( other.word ) )
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
     * @return the pos
     */
    public Long getPos()
    {
        return this.pos;
    }

    /**
     * @return the word
     */
    public String getWord()
    {
        return this.word;
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
        result = prime * result + (this.pos == null
            ? 0 : this.pos.hashCode());
        result = prime * result + (this.word == null
            ? 0 : this.word.hashCode());
        return result;
    }
}
