package org.jbookshelf;

import java.io.File;

/**
 * @model
 */
public interface IndexFileFolder
    extends
        PhysicalUnit
{
    /**
     * @model
     */
    File getIndexFile();

    /**
     * Sets the value of the '{@link org.jbookshelf.IndexFileFolder#getIndexFile <em>Index File</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Index File</em>' attribute.
     * @see #getIndexFile()
     * @generated
     */
    void setIndexFile(File value);

    /**
     * @model
     */
    File getIndexFolder();

    /**
     * Sets the value of the '{@link org.jbookshelf.IndexFileFolder#getIndexFolder <em>Index Folder</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Index Folder</em>' attribute.
     * @see #getIndexFolder()
     * @generated
     */
    void setIndexFolder(File value);
}
