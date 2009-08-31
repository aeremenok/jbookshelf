package org.jbookshelf.view.swinggui.reader.types.rtf;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.BoundedRangeModel;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.StyledDocument;

import org.apache.log4j.Logger;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.Bookmark;
import org.jbookshelf.view.swinggui.ProgressBar;
import org.jbookshelf.view.swinggui.reader.textpanel.SelectableTextPanel;
import org.jbookshelf.view.swinggui.reader.toolbar.FontChooser;
import org.jdesktop.swingx.JXEditorPane;

public class RTFPanel
    extends SelectableTextPanel<StyledDocument>
{
    private final JXEditorPane    editorPane = new JXEditorPane();
    private final JScrollPane     scroll     = new JScrollPane( editorPane );

    private final Queue<Runnable> runnables  = new ConcurrentLinkedQueue<Runnable>();

    private static final Logger   log        = Logger.getLogger( RTFPanel.class );

    public RTFPanel()
    {
        super();
        add( scroll, BorderLayout.CENTER );
        editorPane.setEditable( false );
        editorPane.getCaret().setSelectionVisible( true );
        editorPane.setFont( FontChooser.DEFAULT_FONT );
        editorPane.addMouseListener( popupListener );

        final BoundedRangeModel model = scroll.getVerticalScrollBar().getModel();
        model.addChangeListener( new ChangeListener()
        {
            @Override
            public void stateChanged(
                final ChangeEvent e )
            {
                if ( !model.getValueIsAdjusting() && model.getMaximum() > 0 )
                { // xxx invoke events from queue after initializing
                    while ( runnables.size() > 0 )
                    {
                        final Runnable runnable = runnables.poll();
                        log.debug( "queue size=" + runnables.size() );
                        EventQueue.invokeLater( runnable );
                    }
                }
            }
        } );
    }

    @Override
    public void goTo(
        final Bookmark bookmark )
    {
        final BoundedRangeModel model = scroll.getVerticalScrollBar().getModel();
        final Runnable runnable = new Runnable()
        {
            public void run()
            {
                final float max = model.getMaximum() - model.getExtent();
                final float value = bookmark.getPosition() * max;
                model.setValue( (int) value );
            }
        };
        // xxx in case editorPane is still initializing - add a task to local queue
        EventQueue.invokeLater( runnable );
        runnables.add( runnable );
        log.debug( "queue size=" + runnables.size() );
    }

    @Override
    public void highlightText(
        final String text )
    {
        editorPane.getSearchable().search( "(?iu).*" + text + ".*" );
    }

    @Override
    public void setContent(
        final StyledDocument content )
    {
        Single.instance( ProgressBar.class ).invoke( new Runnable()
        {
            @Override
            public void run()
            {
                editorPane.setDocument( content );
            }
        } );
    }

    @Override
    public void setReaderFont(
        final Font font )
    {
        editorPane.setFont( font );
    }

    @Override
    public void setScale(
        final int scalePercentage )
    {
        final Font oldFont = editorPane.getFont();
        editorPane.setFont( new Font( oldFont.getName(), oldFont.getStyle(), FontChooser.INITIAL_SIZE * scalePercentage
            / 100 ) );
    }

    @Override
    protected float getPosition(
        final Bookmark note )
    {
        final BoundedRangeModel model = scroll.getVerticalScrollBar().getModel();
        final float value = model.getValue();
        final float max = model.getMaximum() - model.getExtent();
        return value / max;
    }

    @Override
    protected String getSelectedText()
    {
        return editorPane.getSelectedText();
    }
}
