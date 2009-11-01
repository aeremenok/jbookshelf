package org.jbookshelf.model.db.cayenne;

import java.io.Serializable;

import org.apache.cayenne.ObjectId;
import org.jbookshelf.model.db.api.spec.IAuthor;
import org.jbookshelf.model.db.cayenne.auto._Author;

public class Author
    extends _Author
    implements
    IAuthor<Book>
{
    @Override
    public Serializable getId()
    {
        return getObjectId();
    }

    @Override
    public void setId(
        final Serializable id )
    {
        setObjectId( (ObjectId) id );
    }
}
