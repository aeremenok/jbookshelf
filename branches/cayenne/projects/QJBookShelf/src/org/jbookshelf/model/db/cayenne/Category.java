package org.jbookshelf.model.db.cayenne;

import java.io.Serializable;

import org.apache.cayenne.ObjectId;
import org.jbookshelf.model.db.api.spec.ICategory;
import org.jbookshelf.model.db.cayenne.auto._Category;

public class Category
    extends _Category
    implements
    ICategory<Category, Book>
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

    @Override
    public void setParent(
        final ICategory parent )
    {
        super.setParent( (Category) parent );
    }

}
