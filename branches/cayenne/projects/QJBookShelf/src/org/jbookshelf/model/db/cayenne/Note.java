package org.jbookshelf.model.db.cayenne;

import java.io.Serializable;
import java.util.Date;

import org.apache.cayenne.ObjectId;
import org.jbookshelf.model.db.api.spec.IBook;
import org.jbookshelf.model.db.api.spec.INote;
import org.jbookshelf.model.db.cayenne.auto._Note;

public class Note
    extends _Note
    implements
    INote
{
    private Float relativePageSize;

    @Override
    public Serializable getId()
    {
        return getObjectId();
    }

    @Override
    public Float getRelativePageSize()
    {
        return relativePageSize;
    }

    @Override
    public void setBook(
        final IBook book )
    {
        super.setBook( (Book) book );
    }

    @Override
    public void setId(
        final Serializable id )
    {
        setObjectId( (ObjectId) id );
    }

    @Override
    public void setRelativePageSize(
        final Float relativePageSize )
    {
        this.relativePageSize = relativePageSize;
    }

    @Override
    public void timestamp()
    {
        setChangeDate( new Date() );
    }
}
