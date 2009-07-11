/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.txt;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import org.apache.log4j.Logger;
import org.jbookshelf.view.swinggui.reader.ReaderContentPanel;
import org.jbookshelf.view.swinggui.reader.ReaderWindow;
import org.jdesktop.swingx.JXEditorPane;

/**
 * @author eav 2009
 */
public class TxtReaderContentPanel
    extends ReaderContentPanel<String>
{
    private final JXEditorPane  editorPane      = new JXEditorPane();
    private final PlainDocument document        = new PlainDocument();
    private final JScrollPane   scrollPane      = new JScrollPane( editorPane );

    private final int           initialFontSize = 20;

    private static final Logger log             = Logger.getLogger( TxtReaderContentPanel.class );

    public TxtReaderContentPanel(
        final ReaderWindow<String> readerWindow )
    {
        super( readerWindow );
        add( scrollPane );
        editorPane.setEditable( false );
        editorPane.setDocument( document );
        editorPane.getCaret().setSelectionVisible( true );

        final Font oldFont = editorPane.getFont();
        editorPane.setFont( new Font( oldFont.getName(), oldFont.getStyle(), initialFontSize ) );
    }

    /* (non-Javadoc)
     * @see org.jbookshelf.view.swinggui.reader.ReaderContentPanel#highloghtText(java.lang.String)
     */
    @Override
    public void highlightText(
        final String text )
    {
        try
        {
            editorPane.getCaret().setSelectionVisible( true );
            final String lowerCase = document.getText( 0, document.getLength() ).toLowerCase();
            final int search = lowerCase.indexOf( text.toLowerCase() );
            if ( search > -1 )
            {
                editorPane.select( search, search + text.length() );
            }
        }
        catch ( final BadLocationException e )
        {
            log.error( e, e );
            throw new Error( e );
        }
    }

    /* (non-Javadoc)
     * @see org.jbookshelf.view.swinggui.reader.ReaderContentPanel#setContent(java.lang.Object)
     */
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

    /* (non-Javadoc)
     * @see org.jbookshelf.view.swinggui.reader.ReaderContentPanel#setScale(int)
     */
    @Override
    public void setScale(
        final int scale )
    {
        final Font oldFont = editorPane.getFont();
        editorPane.setFont( new Font( oldFont.getName(), oldFont.getStyle(), initialFontSize * scale / 100 ) );
    }
}
