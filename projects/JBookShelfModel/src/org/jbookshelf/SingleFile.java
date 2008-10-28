package org.jbookshelf;

import java.io.File;

/**
 * unit as single file
 * 
 * @author eav
 * @model
 */
public interface SingleFile
    extends
        PhysicalUnit
{
    /**
     * @return content file
     * @model
     */
    File getFile();

    /**
     * Sets the value of the '{@link org.jbookshelf.SingleFile#getFile <em>File</em>}' attribute.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @param value the new value of the '<em>File</em>' attribute.
     * @see #getFile()
     * @generated
     */
    void setFile(
        File value );
}
