package org.jbookshelf;

import java.io.File;

/**
 * @model
 */
public interface ArchiveFile
    extends
        PhysicalUnit
{
    /**
     * @model
     */
    File getArchiveFile();

    /**
     * Sets the value of the '{@link org.jbookshelf.ArchiveFile#getArchiveFile <em>Archive File</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Archive File</em>' attribute.
     * @see #getArchiveFile()
     * @generated
     */
    void setArchiveFile(File value);
}
