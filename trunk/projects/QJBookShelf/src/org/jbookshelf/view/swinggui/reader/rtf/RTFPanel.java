package org.jbookshelf.view.swinggui.reader.rtf;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.text.StyledDocument;

import org.jbookshelf.view.logic.SafeWorker;
import org.jbookshelf.view.swinggui.reader.ReaderContentPanel;
import org.jbookshelf.view.swinggui.reader.ReaderWindow;
import org.jbookshelf.view.swinggui.reader.toolbar.FontChooser;
import org.jdesktop.swingx.JXEditorPane;

public class RTFPanel
    extends ReaderContentPanel<StyledDocument>
{
    private final JXEditorPane editorPane = new JXEditorPane();

    private final JScrollPane  scrollPane = new JScrollPane( editorPane );

    public RTFPanel(
        final ReaderWindow<StyledDocument> readerWindow )
    {
        super( readerWindow );
        add( scrollPane, BorderLayout.CENTER );
        editorPane.setEditable( false );
        editorPane.getCaret().setSelectionVisible( true );

        editorPane.setFont( FontChooser.DEFAULT_FONT );
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
                scrollPane.getVerticalScrollBar().setValue( 0 );
                return editorPane;
            }
        };
        readerWindow.getReaderToolBar().getProgressBar().invoke( worker );
    }

    @Override
    public void setReaderFont(
        final Font font )
    {
        editorPane.setFont( font );
    }

    @Override
    public void setScale(
        final int scale )
    {
        final Font oldFont = editorPane.getFont();
        editorPane.setFont( new Font( oldFont.getName(), oldFont.getStyle(), FontChooser.INITIAL_SIZE * scale / 100 ) );
    }

}
