/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.txt;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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
public class PlaintTextPanel
    extends ReaderContentPanel<String>
{
    private final JXEditorPane  editorPane = new JXEditorPane();
    private final PlainDocument document   = new PlainDocument();
    private final JScrollPane   scrollPane = new JScrollPane( editorPane );

    private static final Logger log        = Logger.getLogger( PlaintTextPanel.class );

    public PlaintTextPanel(
        final ReaderWindow<String> readerWindow )
    {
        super( readerWindow );
        add( scrollPane, BorderLayout.CENTER );
        editorPane.setEditable( false );
        editorPane.setDocument( document );
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
    public void setScale(
        final int scale )
    {
        final Font oldFont = editorPane.getFont();
        editorPane.setFont( new Font( oldFont.getName(), oldFont.getStyle(), FontChooser.INITIAL_SIZE * scale / 100 ) );
    }
}
