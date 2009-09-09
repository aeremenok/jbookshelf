/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.textpanel.navigate;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.apache.log4j.Logger;
import org.bushe.swing.event.EventBus;
import org.bushe.swing.event.annotation.AnnotationProcessor;
import org.bushe.swing.event.annotation.EventSubscriber;
import org.bushe.swing.event.annotation.EventTopicSubscriber;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.Bookmark;
import org.jbookshelf.model.db.Note;
import org.jbookshelf.model.db.util.BookShelf;
import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.logic.SafeWorker;
import org.jbookshelf.view.swinggui.ProgressBar;
import org.jbookshelf.view.swinggui.dialog.NoteDialog;
import org.jbookshelf.view.swinggui.reader.ReaderFactory;
import org.jbookshelf.view.swinggui.reader.ReaderWindow;
import org.jbookshelf.view.swinggui.reader.textpanel.LayoutablePanel;
import org.jdesktop.swingx.JXTable;

/**
 * @author eav 2009
 */
public class NotesPanel
    extends JPanel
{
    public static class NoteTable
        extends JXTable
        implements
        MouseListener
    {
        public NoteTable()
        {
            super( new NoteTableModel() );

            this.setDefaultRenderer( Note.class, new NoteArea() );
            this.getColumn( 0 ).setMinWidth( 150 );
            this.setRowHeight( this.getRowHeight() * 7 );
            this.addMouseListener( this );
        }

        @Override
        public NoteTableModel getModel()
        {
            return (NoteTableModel) super.getModel();
        }

        @Override
        public void mouseClicked(
            final MouseEvent e )
        {
            final ReaderWindow window = Single.instance( ReaderWindow.class );
            final Point point = e.getPoint();

            final int column = this.columnAtPoint( point );
            final int row = this.rowAtPoint( point );
            if ( row > -1 )
            {
                final Note note = getModel().getNotes().get( this.convertRowIndexToModel( row ) );

                switch ( column )
                {
                    case 0:
                        new NoteDialog( window, note ).setVisible( true );
                        break;
                    case 1:
                        if ( JOptionPane
                            .showConfirmDialog( window, I18N.tr( "Remove?" ), "", JOptionPane.YES_NO_OPTION ) == JOptionPane.YES_OPTION )
                        {
                            BookShelf.removeNote( note );
                            EventBus.publish( note );
                        }
                        break;
                }
            }
        }

        @Override
        public void mouseEntered(
            final MouseEvent e )
        {
            setCursor( new Cursor( Cursor.HAND_CURSOR ) );
        }

        @Override
        public void mouseExited(
            final MouseEvent e )
        {}

        @Override
        public void mousePressed(
            final MouseEvent e )
        {}

        @Override
        public void mouseReleased(
            final MouseEvent e )
        {}
    }

    @SuppressWarnings( "unused" )
    private static final Logger log        = Logger.getLogger( NotesPanel.class );
    private final boolean       isPageable;
    private final NoteTable     noteTable  = new NoteTable();
    private int                 pageOffset = 0;

    public NotesPanel()
    {
        super( new BorderLayout() );
        add( new JScrollPane( noteTable ), BorderLayout.CENTER );
        setVisible( false );
        AnnotationProcessor.process( this );

        isPageable = Single.instance( ReaderFactory.class ).getFeatures().contains( ReaderFactory.PAGING );
    }

    @EventSubscriber( eventClass = Note.class )
    public void onChangeNote(
        @SuppressWarnings( "unused" ) final Note note )
    {
        final Bookmark bookmark = Single.instance( LayoutablePanel.class ).getCurrentPanels().createBookmark();
        if ( isPageable )
        {
            onChangePage( Bookmark.PAGE, bookmark );
        }
        else
        {
            onChangePosition( Bookmark.POSITION, bookmark );
        }
    }

    @EventTopicSubscriber( topic = Bookmark.PAGE )
    public void onChangePage(
        @SuppressWarnings( "unused" ) final String topic,
        final Bookmark bookmark )
    {
        if ( isVisible() )
        {
            Single.instance( ProgressBar.class ).invoke( new SafeWorker<List<Note>, Note>()
            {
                @Override
                protected List<Note> doInBackground()
                {
                    bookmark.setPage( bookmark.getPage() + pageOffset );
                    return BookShelf.getNotesByPage( bookmark );
                }

                @Override
                protected void doneSafe()
                {
                    noteTable.getModel().setNotes( getQuiet() );
                    noteTable.packAll();
                }
            } );
        }
    }

    @EventTopicSubscriber( topic = Bookmark.POSITION )
    public void onChangePosition(
        @SuppressWarnings( "unused" ) final String topic,
        final Bookmark bookmark )
    {
        if ( isVisible() )
        {
            Single.instance( ProgressBar.class ).invoke( new SafeWorker<List<Note>, Note>()
            {
                @Override
                protected List<Note> doInBackground()
                {
                    return BookShelf.getNotesByPosition( bookmark );
                }

                @Override
                protected void doneSafe()
                {
                    noteTable.getModel().setNotes( getQuiet() );
                    noteTable.packAll();
                }
            } );
        }
    }

    public void setPageOffset(
        final int pageOffset )
    {
        this.pageOffset = pageOffset;
    }
}
