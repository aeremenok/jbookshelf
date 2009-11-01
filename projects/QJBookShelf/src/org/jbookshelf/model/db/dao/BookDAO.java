/**
 * 
 */
package org.jbookshelf.model.db.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.log4j.Logger;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.api.spec.IAuthor;
import org.jbookshelf.model.db.api.spec.IBook;
import org.jbookshelf.model.db.api.spec.ICategory;
import org.jbookshelf.model.db.api.spec.INote;
import org.jbookshelf.model.db.util.DBUtil;
import org.jbookshelf.model.db.util.TBeanListHandler;

/**
 * @author eav 2009
 */
public class BookDAO
    extends NamedDAO<IBook>
{
    private static final Logger log = Logger.getLogger( BookDAO.class );

    public Set<IAuthor> getAuthors(
        final IBook book )
    {
        if ( book.getId() != null )
        {
            final StringBuilder q = new StringBuilder( "select * from Author where id in " );
            q.append( "(select authors_id from Author_Book where books_id=?)" );

            final TBeanListHandler<IAuthor> handler = new TBeanListHandler<IAuthor>( IAuthor.class );
            final List<IAuthor> authors = runner.query( q.toString(), handler, new Object[]
            { book.getId() } );

            book.getAuthors().clear();
            book.getAuthors().addAll( authors );
        }
        return book.getAuthors();
    }

    @Override
    public IBook getById(
        final Serializable id )
    {
        final IBook byId = super.getById( id );
        if ( byId != null )
        {
            final PhysicalDAO physicalDAO = new PhysicalDAO();
            byId.setPhysicalBook( physicalDAO.getByBook( byId ) );
        }
        return byId;
    }

    public Set<ICategory> getCategories(
        final IBook book )
    {
        if ( book.getId() != null )
        {
            final StringBuilder q = new StringBuilder( "select * from category where id in " );
            q.append( "(select categories_id from Category_Book where books_id=?)" );

            final TBeanListHandler<ICategory> handler = new TBeanListHandler<ICategory>( ICategory.class );
            final List<ICategory> categories = runner.query( q.toString(), handler, new Object[]
            { book.getId() } );

            book.getCategories().clear();
            book.getCategories().addAll( categories );
        }
        return book.getCategories();
    }

    public Set<INote> getNotes(
        final IBook book )
    {
        if ( book.getId() != null )
        {
            final TBeanListHandler<INote> handler = new TBeanListHandler<INote>( INote.class );
            final List<INote> notes = runner.query( "select * from Note where BOOK_ID=?", handler, new Object[]
            { book.getId() } );

            book.getNotes().clear();
            book.getNotes().addAll( notes );
        }
        return book.getNotes();
    }

    @Override
    public IBook makePersistent(
        final IBook book )
    {
        if ( !checkIfPersistent( book ) )
        {
            final NoteDAO noteDAO = new NoteDAO();
            if ( book.getLastRead() != null )
            {
                noteDAO.makePersistent( book.getLastRead() );
            }

            final Serializable id = generateId();
            final Serializable lastReadId = book.getLastRead() != null
                ? book.getLastRead().getId() : null;

            runner.update( "insert into Book (id,name,lastread_id) values(?,?,?)", new Object[]
            { id, book.getName(), lastReadId } );
            book.setId( id );

            final AuthorDAO authorDAO = new AuthorDAO();
            for ( final IAuthor author : book.getAuthors() )
            {
                authorDAO.makePersistent( author );
                runner.update( "insert into author_book (authors_id,books_id) values (?,?)", new Object[]
                { author.getId(), book.getId() } );
            }

            final CategoryDAO categoryDAO = new CategoryDAO();
            for ( final ICategory category : book.getCategories() )
            {
                categoryDAO.makePersistent( category );
                runner.update( "insert into category_book (categories_id,books_id) values (?,?)", new Object[]
                { category.getId(), book.getId() } );
            }

            for ( final INote note : book.getNotes() )
            {
                noteDAO.makePersistent( note );
            }

            final PhysicalDAO physicalDAO = new PhysicalDAO();
            physicalDAO.makePersistent( book.getPhysicalBook() );
        }
        return book;
    }

    @Override
    public void makeTransient(
        final IBook unique )
    {
        checkIfTransient( unique );

        final Connection c = Single.instance( DBUtil.class ).openConnection();
        try
        {
            final Serializable id = unique.getId();

            runner.update( c, "delete from author_book where books_id=?; ", id );
            runner.update( c, "delete from category_book where books_id=?; ", id );
            runner.update( c, "delete from physical_book where book_id=?; ", id );
            runner.update( c, "update book set LASTREAD_ID=null where id=?; ", id );
            runner.update( c, "delete from note where book_id=?; ", id );
            runner.update( c, "delete from book where id=?; ", id );

            c.commit();

            unique.setId( null );
        }
        catch ( final SQLException e )
        {
            log.error( e, e );
            DbUtils.rollbackAndCloseQuietly( c );
            throw new Error( e );
        }
        finally
        {
            DbUtils.closeQuietly( c );
        }
    }

    public int totalCount()
    {
        final Object object = runner.query( "select count(*) from book", new ScalarHandler() );
        return Integer.valueOf( object.toString() );
    }
}
