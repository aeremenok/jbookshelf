/**
 * 
 */
package org.jbookshelf.model.db;

import java.io.File;
import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.jbookshelf.controller.settings.Settings;
import org.jbookshelf.controller.singleton.Single;

/**
 * @author eav
 */
@Entity
@Table( name = "PHYSICAL_BOOK" )
public class PhysicalBook
    implements
    Serializable
{
    // todo enum
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
     * @return file from the relative pathname
     */
    @Transient
    public File getFile()
    {
        final String fullPath = Single.instance( Settings.class ).WORKSPACE_DIR.getValue() + "/" + fileName;
        return new File( fullPath );
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
     * @return the unpackedFile
     */
    @Transient
    @Nullable
    public File getUnpackedFile()
    {
        if ( unpackedFileName == null )
        {
            return null;
        }
        final String fullPath = System.getProperty( "java.io.tmpdir" ) + "/" + unpackedFileName;
        return new File( fullPath );
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
     * @param book the book to set
     */
    public void setBook(
        @Nonnull final Book book )
    {
        this.book = book;
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
     * set a relative pathname by a file
     * 
     * @param file a file to relativize
     */
    @Transient
    public void setFile(
        @Nonnull final File file )
    {
        final File wsp = new File( Single.instance( Settings.class ).WORKSPACE_DIR.getValue() );
        setFileName( wsp.toURI().relativize( file.toURI() ).getPath() );
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(
        @Nonnull final String fileName )
    {
        this.fileName = fileName;
    }

    @Transient
    public void setUnpackedFile(
        @Nonnull final File unpackedFile )
    {
        final File tmp = new File( System.getProperty( "java.io.tmpdir" ) );
        setUnpackedFileName( tmp.toURI().relativize( unpackedFile.toURI() ).getPath() );
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
}
