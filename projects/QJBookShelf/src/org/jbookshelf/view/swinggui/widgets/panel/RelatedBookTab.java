/**
 * 
 */
package org.jbookshelf.view.swinggui.widgets.panel;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.BookShelf;
import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.logic.BookShelfMediator;
import org.jbookshelf.view.logic.BookShelfMediator.Properties;
import org.jdesktop.swingx.JXTable;

/**
 * @author eav 2009
 */
public class RelatedBookTab
    extends AdditionalTab
    implements
    PropertyChangeListener
{
    private static class RelatedBookTableModel
        extends DefaultTableModel
        implements
        PropertyChangeListener
    {
        private static String[]         names    =
                                                 { I18N.tr( "Name" ) };
        private List<Book>              books;

        private final BookShelfMediator mediator = Single.instance( BookShelfMediator.class );

        public RelatedBookTableModel()
        {
            super( names, 0 );
            mediator.addPropertyChangeListener( Properties.BOOKS_SELECTED, this );
        }

        @Override
        public int getRowCount()
        {
            return books != null
                ? books.size() : 0;
        }

        @Override
        public Object getValueAt(
            final int row,
            final int column )
        {
            return books.get( row ).getName();
        }

        @Override
        public boolean isCellEditable(
            final int row,
            final int column )
        {
            return false;
        }

        @Override
        public void propertyChange(
            final PropertyChangeEvent evt )
        {
            final List<Book> selectedBooks = mediator.getSelectedBooks();
            books = new ArrayList<Book>();
            if ( selectedBooks.size() == 1 )
            {
                final Book book = selectedBooks.get( 0 );
                books.addAll( BookShelf.getBooks( book ) );
            }
            fireTableDataChanged();
        }
    }

    private final JXTable       table = new JXTable( new RelatedBookTableModel() );

    private static final Logger log   = Logger.getLogger( RelatedBookTab.class );

    public RelatedBookTab()
    {
        super();
        setName( I18N.tr( "Related Books" ) );

        setLayout( new BorderLayout() );
        add( new JScrollPane( table ), BorderLayout.CENTER );

        Single.instance( BookShelfMediator.class ).addPropertyChangeListener( Properties.BOOKS_SELECTED, this );
    }

    /* (non-Javadoc)
     * @see org.jbookshelf.view.swinggui.widgets.panel.AdditionalTab#onAdd(org.jbookshelf.model.db.Book)
     */
    @Override
    public void onAdd(
        final Book book )
    {
        log.debug( "onAdd" );
    }

    /* (non-Javadoc)
     * @see org.jbookshelf.view.swinggui.widgets.panel.AdditionalTab#onRemove()
     */
    @Override
    public void onRemove()
    {
        log.debug( "onRemove" );
    }

    @Override
    public void propertyChange(
        final PropertyChangeEvent evt )
    {
        table.setEnabled( Single.instance( BookShelfMediator.class ).getSelectedBooks().size() == 1 );
    }

}
