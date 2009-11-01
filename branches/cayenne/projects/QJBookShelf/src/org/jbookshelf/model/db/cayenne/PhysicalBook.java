package org.jbookshelf.model.db.cayenne;

import java.io.File;
import java.io.Serializable;

import org.apache.cayenne.ObjectId;
import org.jbookshelf.model.db.api.spec.IBook;
import org.jbookshelf.model.db.api.spec.IPhysicalBook;
import org.jbookshelf.model.db.cayenne.auto._PhysicalBook;

public class PhysicalBook
    extends _PhysicalBook
    implements
    IPhysicalBook
{
    @Override
    public File getFile()
    { // todo relativize
        return new File( getFileName() );
    }

    @Override
    public Serializable getId()
    {
        return getObjectId();
    }

    @Override
    public File getUnpackedFile()
    {
        return new File( getUnpackedFileName() );
    }

    @Override
    public void setBook(
        final IBook book )
    {
        super.setBook( (Book) book );
    }

    @Override
    public void setFile(
        final File file )
    {
        setFileName( file.getAbsolutePath() );
    }

    @Override
    public void setId(
        final Serializable id )
    {
        setObjectId( (ObjectId) id );
    }

    @Override
    public void setUnpackedFile(
        final File unpackedFile )
    {
        setUnpackedFileName( unpackedFile.getAbsolutePath() );
    }

}
