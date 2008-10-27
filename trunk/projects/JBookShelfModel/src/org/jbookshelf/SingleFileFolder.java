package org.jbookshelf;

import java.io.File;

/**
 * @model
 */
public interface SingleFileFolder
    extends
        PhysicalUnit
{
    /**
     * @model
     */
    File getFolder();

    /**
     * Sets the value of the '{@link org.jbookshelf.SingleFileFolder#getFolder <em>Folder</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Folder</em>' attribute.
     * @see #getFolder()
     * @generated
     */
    void setFolder(File value);

    /**
     * @model
     */
    File getSingleFile();

    /**
     * Sets the value of the '{@link org.jbookshelf.SingleFileFolder#getSingleFile <em>Single File</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Single File</em>' attribute.
     * @see #getSingleFile()
     * @generated
     */
    void setSingleFile(File value);

    /**
     * @model
     */
    void openFolder();
}
