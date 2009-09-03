/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.textpanel.navigate;

import icons.IMG;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

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
import org.jbookshelf.view.swinggui.actions.TranslatableAction;
import org.jbookshelf.view.swinggui.dialog.NoteDialog;
import org.jbookshelf.view.swinggui.reader.ReaderFactory;
import org.jbookshelf.view.swinggui.reader.ReaderWindow;
import org.jdesktop.swingx.border.DropShadowBorder;

/**
 * @author eav 2009
 */
public class NotesPanel
    extends JPanel
{
    public static class NoteArea
        extends JPanel
    {
        private class DeleteAction
            extends TranslatableAction
        {
            public DeleteAction()
            {
                super( null, IMG.icon( IMG.LIST_REMOVE_PNG, 16 ) );
            }

            @Override
            public void actionPerformed(
                final ActionEvent e )
            {
                BookShelf.removeNote( note );
                EventBus.publish( note );
            }
        }

        private final JTextArea textArea = new JTextArea();
        private final JLabel    label    = new JLabel();
        private final Note      note;

        public NoteArea(
            final Note note )
        {
            super();
            this.note = note;
            setBorder( new DropShadowBorder() );

            add( textArea, BorderLayout.CENTER );
            final Box horizontalBox = Box.createHorizontalBox();
            horizontalBox.add( label );
            horizontalBox.add( new JButton( new DeleteAction() ) );
            add( horizontalBox, BorderLayout.NORTH );

            textArea.setEditable( false );
            textArea.setText( note.getContent() );
            label.setText( note.getTitle() );

            final MouseAdapter l = new MouseAdapter()
            {
                @Override
                public void mouseClicked(
                    final MouseEvent e )
                {
                    new NoteDialog( Single.instance( ReaderWindow.class ), note );
                }
            };
            textArea.addMouseListener( l );
            addMouseListener( l );
        }
    }

    private static final Logger log = Logger.getLogger( NotesPanel.class );
    private final boolean       isPageable;
    private final Box           box = Box.createVerticalBox();

    public NotesPanel()
    {
        super( new BorderLayout() );
        setBorder( new TitledBorder( I18N.tr( "Notes" ) ) );
        add( box, BorderLayout.CENTER );
        //        add( new JScrollPane( box ), BorderLayout.CENTER );
        setPreferredSize( new Dimension( 200, getPreferredSize().height ) );
        setVisible( false );
        AnnotationProcessor.process( this );

        isPageable = Single.instance( ReaderFactory.class ).getFeatures().contains( ReaderFactory.PAGING );
    }

    @EventSubscriber( eventClass = Note.class )
    public void onChangeNote(
        final Note note )
    {
        if ( isPageable )
        {
            onChangePage( Bookmark.PAGE, note );
        }
        else
        {
            onChangePosition( Bookmark.POSITION, note );
        }
    }

    @EventTopicSubscriber( topic = Bookmark.PAGE )
    public void onChangePage(
        @SuppressWarnings( "unused" ) final String topic,
        final Bookmark bookmark )
    {
        Single.instance( ProgressBar.class ).invoke( new SafeWorker<List<Note>, Note>()
        {
            @Override
            protected List<Note> doInBackground()
            {
                final List<Note> notes = BookShelf.getNotesByPage( bookmark );
                log.debug( "notes returned " + notes.size() );
                return notes;
            }

            @Override
            protected void doneSafe()
            {
                box.removeAll();
                for ( final Note note : getQuiet() )
                {
                    box.add( new NoteArea( note ) );
                }
            }
        } );
    }

    @EventTopicSubscriber( topic = Bookmark.POSITION )
    public void onChangePosition(
        @SuppressWarnings( "unused" ) final String topic,
        final Bookmark bookmark )
    {
        Single.instance( ProgressBar.class ).invoke( new SafeWorker<List<Note>, Note>()
        {
            @Override
            protected List<Note> doInBackground()
            {
                final List<Note> notes = BookShelf.getNotesByPosition( bookmark );
                log.debug( "notes returned " + notes.size() );
                return notes;
            }

            @Override
            protected void doneSafe()
            {
                box.removeAll();
                for ( final Note note : getQuiet() )
                {
                    box.add( new NoteArea( note ) );
                }
            }
        } );
    }
}
