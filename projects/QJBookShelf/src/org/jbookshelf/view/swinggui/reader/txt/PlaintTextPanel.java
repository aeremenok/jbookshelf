/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.txt;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.text.PlainDocument;

import org.jbookshelf.view.swinggui.reader.ReaderContentPanel;
import org.jbookshelf.view.swinggui.reader.ReaderWindow;
import org.jbookshelf.view.swinggui.reader.toolbar.FontChooser;
import org.jdesktop.swingx.JXEditorPane;

/**
 * @author eav 2009
 */
public class PlaintTextPanel
    extends ReaderContentPanel<String>
{
    private final JXEditorPane  editorPane = new JXEditorPane();
    private final PlainDocument document   = new PlainDocument();
    private final JScrollPane   scrollPane = new JScrollPane( editorPane );

    public PlaintTextPanel(
        final ReaderWindow<String> readerWindow )
    {
        super( readerWindow );
        add( scrollPane, BorderLayout.CENTER );
        editorPane.setEditable( false );
        editorPane.setDocument( document );
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
        final String content )
    {
        editorPane.setText( content );
        EventQueue.invokeLater( new Runnable()
        {
            public void run()
            {
                scrollPane.getVerticalScrollBar().setValue( 0 );
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
        final int scale )
    {
        final Font oldFont = editorPane.getFont();
        editorPane.setFont( new Font( oldFont.getName(), oldFont.getStyle(), FontChooser.INITIAL_SIZE * scale / 100 ) );
    }
}
