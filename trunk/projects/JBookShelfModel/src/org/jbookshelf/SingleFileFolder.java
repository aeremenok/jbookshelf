package org.jbookshelf;

import java.io.File;

/**
 * unit contained as a file in a folder with something else
 * 
 * @author eav
 * @model
 */
public interface SingleFileFolder
    extends
        PhysicalUnit
{
    /**
     * @return folder
     * @model
     */
    File getFolder();

    /**
     * @return content file
     * @model
     */
    File getSingleFile();

    /**
     * open folder in a system file manager: konqueror or explorer
     * 
     * @model
     */
    void openFolder();

    /**
     * Sets the value of the '{@link org.jbookshelf.SingleFileFolder#getFolder <em>Folder</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Folder</em>' attribute.
     * @see #getFolder()
     * @generated
     */
    void setFolder(
        File value );

    /**
     * Sets the value of the '{@link org.jbookshelf.SingleFileFolder#getSingleFile <em>Single File</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Single File</em>' attribute.
     * @see #getSingleFile()
     * @generated
     */
    void setSingleFile(
        File value );
}
