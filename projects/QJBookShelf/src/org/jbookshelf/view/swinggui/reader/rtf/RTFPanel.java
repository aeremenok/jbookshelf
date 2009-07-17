package org.jbookshelf.view.swinggui.reader.rtf;

import java.awt.BorderLayout;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JScrollPane;
import javax.swing.text.StyledDocument;

import org.jbookshelf.view.swinggui.reader.ReaderContentPanel;
import org.jbookshelf.view.swinggui.reader.ReaderWindow;
import org.jbookshelf.view.swinggui.reader.txt.PlainTextToolBar;
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

        final PlainTextToolBar plainTextToolBar = (PlainTextToolBar) readerWindow.getReaderToolBar();
        plainTextToolBar.getFontChooser().addPropertyChangeListener( FontChooser.FONT_SELECTED,
            new PropertyChangeListener()
            {
                @Override
                public void propertyChange(
                    final PropertyChangeEvent evt )
                {
                    final Font newFont = (Font) evt.getNewValue();
                    editorPane.setFont( newFont );
                }
            } );
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
        final Runnable runnable = new Runnable()
        {
            public void run()
            {
                editorPane.setDocument( content );
                scrollPane.getVerticalScrollBar().setValue( 0 );
            }
        };
        readerWindow.getReaderToolBar().getProgressBar().invoke( runnable );
    }

    @Override
    public void setScale(
        final int scale )
    {
    // TODO Auto-generated method stub
    }

}
