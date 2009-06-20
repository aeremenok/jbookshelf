/**
 * 
 */
package org.jbookshelf.view.swinggui.widgets.panel;

import icons.IMG;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import org.bushe.swing.event.EventBus;
import org.bushe.swing.event.EventTopicSubscriber;
import org.bushe.swing.event.annotation.AnnotationProcessor;
import org.bushe.swing.event.annotation.EventSubscriber;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.BookShelf;
import org.jbookshelf.model.db.Note;
import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.logic.BookShelfMediator;
import org.jbookshelf.view.logic.BookShelfMediator.Properties;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.FilterPipeline;

/**
 * @author eav 2009
 */
public class NoteTab
    extends AdditionalTab
{
    public static class NoteTableModel
        extends DefaultTableModel
        implements
        EventTopicSubscriber<BookShelfMediator>
    {
        private static String[]         names  =
                                               { I18N.tr( "Title" ), I18N.tr( "Date" ), "" };
        private static SimpleDateFormat format = new SimpleDateFormat( "yy-MM-dd HH:mm" );
        private List<Note>              notes;

        public NoteTableModel()
        {
            super( names, 0 );
            AnnotationProcessor.process( this );
            EventBus.subscribe( Properties.BOOKS_SELECTED, this );
        }

        @Override
        public Class<?> getColumnClass(
            final int columnIndex )
        {
            if ( columnIndex == 2 )
            {
                return Icon.class;
            }
            return super.getColumnClass( columnIndex );
        }

        public List<Note> getNotes()
        {
            return this.notes;
        }

        @Override
        public int getRowCount()
        {
            return notes != null
                ? notes.size() : 0;
        }

        @Override
        public Object getValueAt(
            final int row,
            final int column )
        {
            switch ( column )
            {
                case 0:
                    return notes.get( row ).getTitle();
                case 1:
                    return format.format( notes.get( row ).getChangeDate() );
                case 2:
                    return IMG.icon( IMG.LIST_REMOVE_PNG );
            }
            return super.getValueAt( row, column );
        }

        @Override
        public boolean isCellEditable(
            final int row,
            final int column )
        {
            return false;
        }

        @EventSubscriber( eventClass = Note.class )
        public void noteChanged(
            @SuppressWarnings( "unused" ) final Note note )
        { // todo for lots of notes can be optimized by adding, removing or changing the exact row 
            onEvent( Properties.BOOKS_SELECTED, Single.instance( BookShelfMediator.class ) );
        }

        @Override
        public void onEvent(
            final String string,
            final BookShelfMediator mediator )
        {
            final List<Book> selectedBooks = mediator.getSelectedBooks();
            notes = new ArrayList<Note>();
            if ( selectedBooks.size() == 1 )
            {
                final Book book = selectedBooks.get( 0 );
                notes.addAll( BookShelf.getNotes( book ) );
            }
            fireTableDataChanged();
        }
    }

    private final NoteTableModel model = new NoteTableModel();
    private final JXTable        table = new JXTable( model );

    public NoteTab()
    {
        super();

        setName( I18N.tr( "Notes" ) );

        setLayout( new BorderLayout() );
        add( new JScrollPane( table ), BorderLayout.CENTER );

        table.getColumn( 2 ).setMaxWidth( 40 );
        table.setFilters( new FilterPipeline( filter ) );

        table.addMouseListener( new MouseAdapter()
        {
            @Override
            public void mouseClicked(
                final MouseEvent e )
            {
                final int row = table.rowAtPoint( e.getPoint() );
                final Note note = model.getNotes().get( table.convertRowIndexToModel( row ) );
                if ( e.getClickCount() == 2 )
                {
                    new NoteDialog( note ).setVisible( true );
                }
                else if ( table.columnAtPoint( e.getPoint() ) == 2 )
                {
                    BookShelf.removeNote( note );
                    model.noteChanged( note );
                }
            }
        } );
    }

    @Override
    public void onAdd(
        final Book book )
    {
        final Note note = new Note();
        note.setBook( book );
        new NoteDialog( note ).setVisible( true );
    }
}
