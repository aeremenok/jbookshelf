/**
 * 
 */
package org.jbookshelf.model.db;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * @author eav
 */
@Entity
@Table( name = "PHYSICAL_BOOK" )
public class PhysicalBook
    implements
    Serializable,
    Timestampable
{
    public static final String INTERNAL_VIEWER = "internal";
    public static final String SYSTEM_VIEWER   = "system";

    @SuppressWarnings( "unused" )
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

    @SuppressWarnings( "unused" )
    @Column( nullable = false )
    @Temporal( TemporalType.TIMESTAMP )
    private Date               changeDate;

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
    @Nullable
    public String getCharsetName()
    {
        return this.charsetName;
    }

    /**
     * @return
     */
    @Transient
    public File getFile()
    {
        return new File( getFileName() );
    }

    /**
     * @return the fileName
     */
    public String getFileName()
    {
        return this.fileName;
    }

    /**
     * @return the unpackedFileName
     */
    @Nullable
    public String getUnpackedFileName()
    {
        return this.unpackedFileName;
    }

    /**
     * @return the viewer
     */
    @Nullable
    public String getViewer()
    {
        return this.viewer;
    }

    /**
     * @param charsetName the charsetName to set
     */
    public void setCharsetName(
        @Nonnull final String charsetName )
    {
        this.charsetName = charsetName;
    }

    /**
     * @param file
     */
    @Transient
    public void setFile(
        final File file )
    {
        // todo extract relative path
        setFileName( file.getAbsolutePath() );
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(
        @Nonnull final String fileName )
    {
        this.fileName = fileName;
    }

    /**
     * @param unpackedFileName the unpackedFileName to set
     */
    public void setUnpackedFileName(
        @Nonnull final String unpackedFileName )
    {
        this.unpackedFileName = unpackedFileName;
    }

    /**
     * @param viewer the viewer to set
     */
    public void setViewer(
        @Nonnull final String viewer )
    {
        this.viewer = viewer;
    }

    @PreUpdate
    @PrePersist
    public void timestamp()
    {
        changeDate = new Date();
    }

    /**
     * @param book the book to set
     */
    void setBook(
        @Nonnull final Book book )
    {
        this.book = book;
    }
}
