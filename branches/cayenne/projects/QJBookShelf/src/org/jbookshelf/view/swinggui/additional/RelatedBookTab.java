/**
 * 
 */
package org.jbookshelf.view.swinggui.additional;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import org.bushe.swing.event.EventBus;
import org.bushe.swing.event.EventTopicSubscriber;
import org.bushe.swing.event.annotation.AnnotationProcessor;
import org.bushe.swing.event.annotation.EventSubscriber;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.BookShelf;
import org.jbookshelf.model.db.api.spec.IBook;
import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.logic.BookShelfMediator;
import org.jbookshelf.view.logic.BookShelfMediator.Properties;
import org.jbookshelf.view.swinggui.dialog.RelatedBookDialog;
import org.jbookshelf.view.swinggui.dialog.RelatedBookDialog.RelatedBookEvent;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.FilterPipeline;

/**
 * displays {@link IBook#getRelatedBooks()}
 * 
 * @author eav 2009
 */
public class RelatedBookTab
    extends AdditionalTab
{
    public static class RelatedBookTableModel
        extends DefaultTableModel
        implements
        EventTopicSubscriber<BookShelfMediator>
    {
        private static String[] names =
                                      { I18N.tr( "Name" ) };
        private List<IBook>     books;

        public RelatedBookTableModel()
        {
            super( names, 0 );
            EventBus.subscribe( Properties.BOOKS_SELECTED, this );
            AnnotationProcessor.process( this );
        }

        @EventSubscriber( eventClass = RelatedBookEvent.class )
        public void booksChanged(
            @SuppressWarnings( "unused" ) final RelatedBookEvent event )
        {
            onEvent( Properties.BOOKS_SELECTED, Single.instance( BookShelfMediator.class ) );
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
        public void onEvent(
            final String string,
            final BookShelfMediator mediator )
        {
            final List<IBook> selectedBooks = mediator.getSelectedBooks();
            books = new ArrayList<IBook>();
            if ( selectedBooks.size() == 1 )
            {
                final IBook book = selectedBooks.get( 0 );
                books.addAll( BookShelf.getBooks( book ) );
            }
            fireTableDataChanged();
        }
    }

    private final JXTable table = new JXTable( new RelatedBookTableModel() );

    public RelatedBookTab()
    {
        super();
        setName( I18N.tr( "Related Books" ) );

        setLayout( new BorderLayout() );
        add( new JScrollPane( table ), BorderLayout.CENTER );

        table.setFilters( new FilterPipeline( filter ) );

        table.addMouseListener( new MouseAdapter()
        {
            @Override
            public void mouseClicked(
                final MouseEvent e )
            {
                if ( e.getClickCount() == 2 )
                {
                    onAdd( Single.instance( BookShelfMediator.class ).getSelectedBooks().get( 0 ) );
                }
            }
        } );
    }

    @Override
    public void onAdd(
        final IBook book )
    {
        new RelatedBookDialog( book ).setVisible( true );
    }
}
