package org.jbookshelf;

import java.io.File;

/**
 * unit contained as a single file in an archive
 * 
 * @author eav
 * @model
 */
public interface ArchiveFile
    extends
        PhysicalUnit
{
    /**
     * @return archive file
     * @model
     */
    File getArchiveFile();

    /**
     * Sets the value of the '{@link org.jbookshelf.ArchiveFile#getArchiveFile <em>Archive File</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Archive File</em>' attribute.
     * @see #getArchiveFile()
     * @generated
     */
    void setArchiveFile(
        File value );
}
