package org.jbookshelf.model.db.cayenne;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;

import org.apache.cayenne.ObjectId;
import org.jbookshelf.model.db.api.spec.IBook;
import org.jbookshelf.model.db.api.spec.INote;
import org.jbookshelf.model.db.api.spec.IPhysicalBook;
import org.jbookshelf.model.db.cayenne.auto._Book;

public class Book
    extends _Book
    implements
    IBook<Book, Author, Category, Note>
{
    @Override
    public Set<? extends IBook> getBooks()
    {
        return getRelatedBooks();
    }

    @Override
    public Serializable getId()
    {
        return getObjectId();
    }

    @Override
    public IPhysicalBook getPhysicalBook()
    {
        final Iterator<PhysicalBook> iterator = getPhysicalBooks().iterator();
        return iterator.hasNext()
            ? iterator.next() : null;
    }

    @Override
    public boolean isRead()
    {
        // todo
        return false;
    }

    @Override
    public void setId(
        final Serializable id )
    {
        setObjectId( (ObjectId) id );
    }

    @Override
    public void setLastRead(
        final INote lastRead )
    {
        super.setLastRead( (Note) lastRead );
    }

    @Override
    public void setPhysicalBook(
        final IPhysicalBook physicalBook )
    {
        getPhysicalBooks().clear();
        getPhysicalBooks().add( (PhysicalBook) physicalBook );
    }

    @Override
    public void setRead(
        final boolean isRead )
    {
    // TODO Auto-generated method stub
    }
}
