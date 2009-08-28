/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.types.txt;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.text.PlainDocument;

import org.jbookshelf.model.db.Note;
import org.jbookshelf.view.swinggui.reader.textpanel.SelectableTextPanel;
import org.jbookshelf.view.swinggui.reader.toolbar.FontChooser;
import org.jdesktop.swingx.JXEditorPane;

/**
 * @author eav 2009
 */
public class PlaintTextPanel
    extends SelectableTextPanel<String>
{
    private final JXEditorPane  editorPane = new JXEditorPane();
    private final PlainDocument document   = new PlainDocument();
    private final JScrollPane   scrollPane = new JScrollPane( editorPane );

    public PlaintTextPanel()
    {
        super();
        add( scrollPane, BorderLayout.CENTER );
        editorPane.setEditable( false );
        editorPane.setDocument( document );
        editorPane.getCaret().setSelectionVisible( true );
        editorPane.setFont( FontChooser.DEFAULT_FONT );
        editorPane.addMouseListener( popupListener );
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
        final int scalePercentage )
    {
        final Font oldFont = editorPane.getFont();
        editorPane.setFont( new Font( oldFont.getName(), oldFont.getStyle(), FontChooser.INITIAL_SIZE * scalePercentage
            / 100 ) );
    }

    @Override
    protected float getPosition(
        final Note note )
    {
        final float page = note.getPage();
        return page / note.getPageCount();
    }

    @Override
    protected String getSelectedText()
    {
        return editorPane.getSelectedText();
    }
}
