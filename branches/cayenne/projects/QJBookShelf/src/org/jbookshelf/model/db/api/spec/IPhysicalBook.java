/**
 * 
 */
package org.jbookshelf.model.db.api.spec;

import java.io.File;

import javax.persistence.Transient;

import org.jbookshelf.model.db.api.Identifiable;

/**
 * @author eav
 */
public interface IPhysicalBook
    extends
    Identifiable
{
    String INTERNAL_VIEWER = "internal";
    String SYSTEM_VIEWER   = "system";

    IBook getBook();

    String getCharsetName();

    @Transient
    File getFile();

    String getFileName();

    @Transient
    File getUnpackedFile();

    String getUnpackedFileName();

    String getViewer();

    void setBook(
        final IBook book );

    void setCharsetName(
        final String charsetName );

    /**
     * set a relative pathname by a file
     * 
     * @param file a file to relativize
     */
    @Transient
    void setFile(
        final File file );

    void setFileName(
        final String fileName );

    @Transient
    void setUnpackedFile(
        final File unpackedFile );

    void setUnpackedFileName(
        final String unpackedFileName );

    void setViewer(
        final String viewer );

}
