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
    @SuppressWarnings( "unused" )
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
}
