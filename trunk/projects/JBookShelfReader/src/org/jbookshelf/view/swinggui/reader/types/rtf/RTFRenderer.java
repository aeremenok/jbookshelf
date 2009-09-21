package org.jbookshelf.view.swinggui.reader.types.rtf;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BoundedRangeModel;
import javax.swing.JScrollPane;
import javax.swing.text.StyledDocument;

import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.Bookmark;
import org.jbookshelf.view.swinggui.ProgressBar;
import org.jbookshelf.view.swinggui.reader.BookmarkChangeListener;
import org.jbookshelf.view.swinggui.reader.TaskQueue;
import org.jbookshelf.view.swinggui.reader.textpanel.SelectableTextRenderer;
import org.jbookshelf.view.swinggui.reader.toolbar.FontChooser;
import org.jdesktop.swingx.JXEditorPane;

/**
 * displays pdf content
 * 
 * @author eav 2009
 */
public class RTFRenderer
    extends SelectableTextRenderer<StyledDocument>
{
    private final JXEditorPane editorPane = new JXEditorPane();
    private final JScrollPane  scroll     = new JScrollPane( editorPane );
    private final TaskQueue    taskQueue  = new TaskQueue();

    public RTFRenderer()
    {
        super();
        add( scroll, BorderLayout.CENTER );
        editorPane.setEditable( false );
        editorPane.getCaret().setSelectionVisible( true );
        editorPane.setFont( FontChooser.DEFAULT_FONT );
        editorPane.addMouseListener( popupListener );

        final BoundedRangeModel model = scroll.getVerticalScrollBar().getModel();
        model.addChangeListener( taskQueue );
        model.addChangeListener( new BookmarkChangeListener() );
    }

    @Override
    public void displayContent(
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
        taskQueue.enqueue( runnable );
    }

    @Override
    public void highlightText(
        final String text )
    {
        editorPane.getSearchable().search( "(?iu).*" + text + ".*" );
    }

    @Override
    public void scale(
        final int scalePercentage )
    {
        final Font oldFont = editorPane.getFont();
        final float newSize = FontChooser.INITIAL_SIZE * scalePercentage / 100;
        editorPane.setFont( oldFont.deriveFont( newSize ) );
    }

    @Override
    public void useFont(
        final Font font )
    {
        editorPane.setFont( font );
    }

    @Override
    protected float calcRelativePosition(
        final Bookmark bookmark )
    {
        final BoundedRangeModel model = scroll.getVerticalScrollBar().getModel();
        final float value = model.getValue();
        final float max = model.getMaximum() - model.getExtent();
        final float pageSize = model.getExtent() / max;
        bookmark.setRelativePageSize( pageSize );
        return value / max;
    }

    @Override
    protected String getSelectedText()
    {
        return editorPane.getSelectedText();
    }
}
