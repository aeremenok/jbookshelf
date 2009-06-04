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

    @Column( nullable = false )
    @Temporal( TemporalType.TIMESTAMP )
    private Date               changeDate;

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
        final PhysicalBook other = (PhysicalBook) obj;
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
        if ( this.charsetName == null )
        {
            if ( other.charsetName != null )
            {
                return false;
            }
        }
        else if ( !this.charsetName.equals( other.charsetName ) )
        {
            return false;
        }
        if ( this.fileName == null )
        {
            if ( other.fileName != null )
            {
                return false;
            }
        }
        else if ( !this.fileName.equals( other.fileName ) )
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
        if ( this.unpackedFileName == null )
        {
            if ( other.unpackedFileName != null )
            {
                return false;
            }
        }
        else if ( !this.unpackedFileName.equals( other.unpackedFileName ) )
        {
            return false;
        }
        if ( this.viewer == null )
        {
            if ( other.viewer != null )
            {
                return false;
            }
        }
        else if ( !this.viewer.equals( other.viewer ) )
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
        // todo
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
        result = prime * result + (this.charsetName == null
            ? 0 : this.charsetName.hashCode());
        result = prime * result + (this.fileName == null
            ? 0 : this.fileName.hashCode());
        result = prime * result + (this.id == null
            ? 0 : this.id.hashCode());
        result = prime * result + (this.unpackedFileName == null
            ? 0 : this.unpackedFileName.hashCode());
        result = prime * result + (this.viewer == null
            ? 0 : this.viewer.hashCode());
        return result;
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
