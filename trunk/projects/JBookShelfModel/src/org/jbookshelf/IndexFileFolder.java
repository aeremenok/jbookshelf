package org.jbookshelf;

import java.io.File;

/**
 * unit contained as many html-files in a folder, containig index.html or index.htm...
 * 
 * @author eav
 * @model
 */
public interface IndexFileFolder
    extends
        PhysicalUnit
{
    /**
     * @return file to start browsing
     * @model
     */
    File getIndexFile();

    /**
     * @return containing folder
     * @model
     */
    File getIndexFolder();

    /**
     * Sets the value of the '{@link org.jbookshelf.IndexFileFolder#getIndexFile <em>Index File</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Index File</em>' attribute.
     * @see #getIndexFile()
     * @generated
     */
    void setIndexFile(
        File value );

    /**
     * Sets the value of the '{@link org.jbookshelf.IndexFileFolder#getIndexFolder <em>Index Folder</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Index Folder</em>' attribute.
     * @see #getIndexFolder()
     * @generated
     */
    void setIndexFolder(
        File value );
}
