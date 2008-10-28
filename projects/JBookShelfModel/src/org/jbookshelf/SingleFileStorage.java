package org.jbookshelf;

import java.io.File;

/**
 * stores collection in a sigle xml file
 * 
 * @author eav
 * @model
 */
public interface SingleFileStorage
    extends
        BookShelfStorage
{
    /**
     * @param externalFile place to save backup
     * @model
     */
    void backupCollection(
        File externalFile );

    /**
     * @return default collection storage file
     * @model
     */
    File getCollectionStorageFile();

    /**
     * Sets the value of the '{@link org.jbookshelf.SingleFileStorage#getCollectionStorageFile <em>Collection Storage File</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Collection Storage File</em>' attribute.
     * @see #getCollectionStorageFile()
     * @generated
     */
    void setCollectionStorageFile(File value);

    /**
     * @param externalFile where backup resides
     * @model
     */
    void restoreCollection(
        File externalFile );
}
