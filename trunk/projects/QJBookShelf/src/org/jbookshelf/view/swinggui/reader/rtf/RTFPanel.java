package org.jbookshelf.view.swinggui.reader.rtf;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JScrollPane;
import javax.swing.text.StyledDocument;

import org.jbookshelf.view.swinggui.reader.ReaderContentPanel;
import org.jbookshelf.view.swinggui.reader.ReaderWindow;
import org.jbookshelf.view.swinggui.widget.FontChooser;
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
    // TODO Auto-generated method stub
    }

    @Override
    public void setContent(
        final StyledDocument content )
    {
        EventQueue.invokeLater( new Runnable()
        {
            public void run()
            {
                editorPane.setDocument( content );
                scrollPane.getVerticalScrollBar().setValue( 0 );
            }
        } );
    }

    @Override
    public void setScale(
        final int scale )
    {
    // TODO Auto-generated method stub
    }

}
