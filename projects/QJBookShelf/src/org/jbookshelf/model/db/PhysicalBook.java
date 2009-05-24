/**
 * 
 */
package org.jbookshelf.model.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author eav
 */
@Entity
@Table( name = "PHYSICAL_BOOK" )
public class PhysicalBook
{
    public static final String INTERNAL_VIEWER = "internal";
    public static final String SYSTEM_VIEWER   = "system";

    @Id
    @GeneratedValue
    private Long               id;

    @OneToOne( optional = false )
    @org.hibernate.annotations.ForeignKey( name = "FK_PHYSICAL_BOOK" )
    private Book               book;

    @Column( nullable = false )
    private String             fileName;

    @Column
    private String             viewer;

    @Column
    private String             charsetName;

    @Column
    private String             unpackedFileName;

    /**
     * @return the book
     */
    public Book getBook()
    {
        return this.book;
    }

    /**
     * @return the charsetName
     */
    public String getCharsetName()
    {
        return this.charsetName;
    }

    /**
     * @return the fileName
     */
    public String getFileName()
    {
        return this.fileName;
    }

    /**
     * @return the id
     */
    public Long getId()
    {
        return this.id;
    }

    /**
     * @return the unpackedFileName
     */
    public String getUnpackedFileName()
    {
        return this.unpackedFileName;
    }

    /**
     * @return the viewer
     */
    public String getViewer()
    {
        return this.viewer;
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
     * @param charsetName the charsetName to set
     */
    public void setCharsetName(
        final String charsetName )
    {
        this.charsetName = charsetName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(
        final String fileName )
    {
        this.fileName = fileName;
    }

    /**
     * @param unpackedFileName the unpackedFileName to set
     */
    public void setUnpackedFileName(
        final String unpackedFileName )
    {
        this.unpackedFileName = unpackedFileName;
    }

    /**
     * @param viewer the viewer to set
     */
    public void setViewer(
        final String viewer )
    {
        this.viewer = viewer;
    }
}
