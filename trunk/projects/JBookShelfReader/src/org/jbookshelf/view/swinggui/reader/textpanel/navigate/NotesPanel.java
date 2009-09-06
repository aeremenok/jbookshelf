/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.textpanel.navigate;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.apache.log4j.Logger;
import org.bushe.swing.event.annotation.AnnotationProcessor;
import org.bushe.swing.event.annotation.EventSubscriber;
import org.bushe.swing.event.annotation.EventTopicSubscriber;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.Bookmark;
import org.jbookshelf.model.db.Note;
import org.jbookshelf.model.db.util.BookShelf;
import org.jbookshelf.view.logic.SafeWorker;
import org.jbookshelf.view.swinggui.ProgressBar;
import org.jbookshelf.view.swinggui.reader.ReaderFactory;

/**
 * @author eav 2009
 */
public class NotesPanel
    extends JPanel
{
    @SuppressWarnings( "unused" )
    private static final Logger log = Logger.getLogger( NotesPanel.class );
    private final boolean       isPageable;
    private final Box           box = Box.createVerticalBox();

    public NotesPanel()
    {
        super( new BorderLayout() );
        add( new JScrollPane( box ), BorderLayout.CENTER );
        setVisible( false );
        AnnotationProcessor.process( this );

        isPageable = Single.instance( ReaderFactory.class ).getFeatures().contains( ReaderFactory.PAGING );
    }

    @EventSubscriber( eventClass = Note.class )
    public void onChangeNote(
        final Note note )
    {
        // fixme get a real bookmark
        final Bookmark bookmark = note;
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
        Single.instance( ProgressBar.class ).invoke( new SafeWorker<List<Note>, Note>()
        {
            @Override
            protected List<Note> doInBackground()
            {
                return BookShelf.getNotesByPage( bookmark );
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
                return BookShelf.getNotesByPosition( bookmark );
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
