/**
 * 
 */
package org.jbookshelf.model.db;

import static org.jbookshelf.controller.singleton.Single.instance;

import java.io.File;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.jbookshelf.controller.settings.Settings;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.controller.util.JBSSystem;

/**
 * @author eav
 */
@Entity
@Table( name = "PHYSICAL_BOOK" )
public class PhysicalBook
    implements
    Identifiable
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

    public Book getBook()
    {
        return this.book;
    }

    public String getCharsetName()
    {
        return this.charsetName;
    }

    @Transient
    public File getFile()
    {
        final String fullPath = Single.instance( Settings.class ).WORKSPACE_DIR.getValue() + "/" + fileName;
        return new File( fullPath );
    }

    public String getFileName()
    {
        return this.fileName;
    }

    public Long getId()
    {
        return this.id;
    }

    @Transient
    public File getUnpackedFile()
    {
        if ( unpackedFileName == null )
        {
            return null;
        }
        final String fullPath = instance( JBSSystem.class ).tempDir() + "/" + unpackedFileName;
        return new File( fullPath );
    }

    public String getUnpackedFileName()
    {
        return this.unpackedFileName;
    }

    public String getViewer()
    {
        return this.viewer;
    }

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

    public void setBook(
        final Book book )
    {
        this.book = book;
    }

    public void setCharsetName(
        final String charsetName )
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
        final File file )
    {
        final File wsp = new File( Single.instance( Settings.class ).WORKSPACE_DIR.getValue() );
        setFileName( wsp.toURI().relativize( file.toURI() ).getPath() );
    }

    public void setFileName(
        final String fileName )
    {
        this.fileName = fileName;
    }

    @Override
    public void setId(
        final Long id )
    {
        this.id = id;
    }

    @Transient
    public void setUnpackedFile(
        final File unpackedFile )
    {
        final File tmp = new File( instance( JBSSystem.class ).tempDir() );
        setUnpackedFileName( tmp.toURI().relativize( unpackedFile.toURI() ).getPath() );
    }

    public void setUnpackedFileName(
        final String unpackedFileName )
    {
        this.unpackedFileName = unpackedFileName;
    }

    public void setViewer(
        final String viewer )
    {
        this.viewer = viewer;
    }
}
