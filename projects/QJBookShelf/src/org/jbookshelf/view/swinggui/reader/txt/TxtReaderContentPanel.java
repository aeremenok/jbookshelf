/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.txt;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.text.PlainDocument;

import org.jbookshelf.view.swinggui.reader.ReaderContentPanel;
import org.jbookshelf.view.swinggui.reader.ReaderWindow;
import org.jdesktop.swingx.JXEditorPane;

/**
 * @author eav 2009
 */
public class TxtReaderContentPanel
    extends ReaderContentPanel<String>
{
    private final JXEditorPane editorPane      = new JXEditorPane();
    private final JScrollPane  scrollPane      = new JScrollPane( editorPane );

    private final int          initialFontSize = 20;

    public TxtReaderContentPanel(
        final ReaderWindow<String> readerWindow )
    {
        super( readerWindow );
        add( scrollPane );
        editorPane.setEditable( false );
        editorPane.setDocument( new PlainDocument() );

        final Font oldFont = editorPane.getFont();
        editorPane.setFont( new Font( oldFont.getName(), oldFont.getStyle(), initialFontSize ) );
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
