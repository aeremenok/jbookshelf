/**
 * 
 */
package org.jbookshelf.view.swinggui.widgets.panel.tab;

import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.log4j.Logger;
import org.jbookshelf.model.db.HibernateUtil;
import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.logic.Parameters;
import org.jbookshelf.view.logic.Parameters.Keys;
import org.jdesktop.swingx.JXTable;

/**
 * displays books in a table
 * 
 * @author eav
 */
public class BookView
    extends CollectionView
{
    private static class BookTableModel
        extends DefaultTableModel
    {
        private static String[] names =
                                      { I18N.tr( "Name" ), I18N.tr( "Author" ), I18N.tr( "Category" ) };

        private List<Object[]>  rows  = new ArrayList<Object[]>();

        public BookTableModel()
        {
            super( names, 0 );
        }

        @Override
        public int getRowCount()
        {
            return rows != null
                ? rows.size() : 0;
        }

        @Override
        public Object getValueAt(
            final int row,
            final int column )
        {
            return rows.get( row )[column];
        }

        @Override
        public boolean isCellEditable(
            final int row,
            final int column )
        {
            return false;
        }

        /**
         * @param rows the rows to set
         */
        public void setRows(
            final List<Object[]> rows )
        {
            this.rows = rows;
            fireTableDataChanged();
        }
    }

    private static final Logger  log   = Logger.getLogger( BookView.class );

    private final JXTable        table = new JXTable();
    private final BookTableModel model = new BookTableModel();

    public BookView()
    {
        super();
        setName( "Books" );
        setLayout( new BorderLayout() );
        add( new JScrollPane( table ), BorderLayout.CENTER );

        table.setModel( model );
    }

    @SuppressWarnings( "unchecked" )
    @Override
    public void search(
        final Parameters p )
    {
        final QueryRunner runner = new QueryRunner();
        try
        {
            final Object rows = runner.query( HibernateUtil.connection(), buildQuery( p ), new ArrayListHandler() );
            model.setRows( (List<Object[]>) rows );
        }
        catch ( final SQLException e )
        {
            log.error( e, e );
            throw new Error( e );
        }
    }

    @Override
    protected String buildQuery(
        final Parameters p )
    {
        final StringBuilder q = new StringBuilder( "select b.name, a.name, c.name, b.id " );
        q.append( "from book b " );
        q.append( "left join author_book ab on ab.books_id = b.id " );
        q.append( "left join author a on a.id = ab.authors_id " );
        q.append( "left join category_book cb on cb.books_id = b.id " );
        q.append( "left join category c on c.id = cb.categories_id " );
        q.append( "where 1=1 " );

        final String text = p.get( Keys.SEARCH_TEXT );
        final Boolean isRead = p.get( Keys.SEARCH_IS_READ );
        // todo search content
        if ( text != null && !"".equals( text ) )
        {
            q.append( " and upper(b.name) like '%" ).append( text.toUpperCase() ).append( "%'" );
        }
        if ( Boolean.TRUE.equals( isRead ) )
        {
            q.append( " and b.read=1" );
        }
        else if ( Boolean.FALSE.equals( isRead ) )
        {
            q.append( " and b.read<1" );
        }

        return q.toString();
    }
}
