package org.jbookshelf.view.swinggui.reader.types.rtf;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.BoundedRangeModel;
import javax.swing.JScrollPane;
import javax.swing.text.StyledDocument;

import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.Bookmark;
import org.jbookshelf.view.logic.SafeWorker;
import org.jbookshelf.view.swinggui.ProgressBar;
import org.jbookshelf.view.swinggui.reader.textpanel.SelectableTextPanel;
import org.jbookshelf.view.swinggui.reader.toolbar.FontChooser;
import org.jdesktop.swingx.JXEditorPane;

public class RTFPanel
    extends SelectableTextPanel<StyledDocument>
{
    private final JXEditorPane editorPane = new JXEditorPane();
    private final JScrollPane  scroll     = new JScrollPane( editorPane );

    public RTFPanel()
    {
        super();
        add( scroll, BorderLayout.CENTER );
        editorPane.setEditable( false );
        editorPane.getCaret().setSelectionVisible( true );
        editorPane.setFont( FontChooser.DEFAULT_FONT );
        editorPane.addMouseListener( popupListener );
    }

    @Override
    public void goTo(
        final Bookmark bookmark )
    {
        final BoundedRangeModel model = scroll.getVerticalScrollBar().getModel();
        final float max = model.getMaximum() - model.getExtent();
        final float value = bookmark.getPosition() * max;
        model.setValue( (int) value );
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
        final SafeWorker<JXEditorPane, Object> worker = new SafeWorker<JXEditorPane, Object>()
        {
            @Override
            protected JXEditorPane doInBackground()
            {
                editorPane.setDocument( content );
                // todo remember position
                scroll.getVerticalScrollBar().setValue( 0 );
                return editorPane;
            }
        };
        Single.instance( ProgressBar.class ).invoke( worker );
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
