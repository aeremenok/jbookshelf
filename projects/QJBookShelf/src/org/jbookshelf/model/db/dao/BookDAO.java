/**
 * 
 */
package org.jbookshelf.model.db.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.log4j.Logger;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.Author;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.Category;
import org.jbookshelf.model.db.Note;
import org.jbookshelf.model.db.util.DBUtil;
import org.jbookshelf.model.db.util.TBeanListHandler;

/**
 * @author eav 2009
 */
public class BookDAO
    extends UniqueDAO<Book>
{
    private static final Logger log = Logger.getLogger( BookDAO.class );

    public Set<Author> getAuthors(
        final Book book )
    {
        if ( book.getId() != null )
        {
            final StringBuilder q = new StringBuilder( "select * from Author where id in " );
            q.append( "(select authors_id from Author_Book where books_id=?)" );

            final TBeanListHandler<Author> handler = new TBeanListHandler<Author>( Author.class );
            final List<Author> authors = runner.query( q.toString(), handler, new Object[]
            { book.getId() } );

            book.getAuthors().clear();
            book.getAuthors().addAll( authors );
        }
        return book.getAuthors();
    }

    @Override
    public Book getById(
        final Long id )
    {
        final Book byId = super.getById( id );
        if ( byId != null )
        {
            final PhysicalDAO physicalDAO = new PhysicalDAO();
            byId.setPhysicalBook( physicalDAO.getByBook( byId ) );
        }
        return byId;
    }

    public Set<Category> getCategories(
        final Book book )
    {
        if ( book.getId() != null )
        {
            final StringBuilder q = new StringBuilder( "select * from category where id in " );
            q.append( "(select categories_id from Category_Book where books_id=?)" );

            final TBeanListHandler<Category> handler = new TBeanListHandler<Category>( Category.class );
            final List<Category> categories = runner.query( q.toString(), handler, new Object[]
            { book.getId() } );

            book.getCategories().clear();
            book.getCategories().addAll( categories );
        }
        return book.getCategories();
    }

    public Set<Note> getNotes(
        final Book book )
    {
        if ( book.getId() != null )
        {
            final TBeanListHandler<Note> handler = new TBeanListHandler<Note>( Note.class );
            final List<Note> notes = runner.query( "select * from Note where BOOK_ID=?", handler, new Object[]
            { book.getId() } );

            book.getNotes().clear();
            book.getNotes().addAll( notes );
        }
        return book.getNotes();
    }

    @Override
    public Book makePersistent(
        final Book book )
    {
        if ( !checkIfPersistent( book ) )
        {
            final NoteDAO noteDAO = new NoteDAO();
            if ( book.getLastRead() != null )
            {
                noteDAO.makePersistent( book.getLastRead() );
            }

            final Long id = generateId();
            final Long lastReadId = book.getLastRead() != null
                ? book.getLastRead().getId() : null;

            runner.update( "insert into Book (id,name,lastread_id) values(?,?,?)", new Object[]
            { id, book.getName(), lastReadId } );
            book.setId( id );

            final AuthorDAO authorDAO = new AuthorDAO();
            for ( final Author author : book.getAuthors() )
            {
                authorDAO.makePersistent( author );
                runner.update( "insert into author_book (authors_id,books_id) values (?,?)", new Object[]
                { author.getId(), book.getId() } );
            }

            final CategoryDAO categoryDAO = new CategoryDAO();
            for ( final Category category : book.getCategories() )
            {
                categoryDAO.makePersistent( category );
                runner.update( "insert into category_book (categories_id,books_id) values (?,?)", new Object[]
                { category.getId(), book.getId() } );
            }

            for ( final Note note : book.getNotes() )
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
        final Book unique )
    {
        checkIfTransient( unique );

        final Connection c = Single.instance( DBUtil.class ).openConnection();
        try
        {
            final Long id = unique.getId();

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
