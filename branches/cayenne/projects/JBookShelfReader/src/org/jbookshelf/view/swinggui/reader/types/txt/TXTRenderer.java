/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.types.txt;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.text.PlainDocument;

import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.api.Bookmark;
import org.jbookshelf.view.swinggui.reader.textview.SelectableTextRenderer;
import org.jbookshelf.view.swinggui.reader.toolbar.FontChooser;
import org.jbookshelf.view.swinggui.reader.toolbar.Paginator;
import org.jdesktop.swingx.JXEditorPane;

/**
 * displays plain text
 * 
 * @author eav 2009
 */
public class TXTRenderer
    extends SelectableTextRenderer<String>
{
    private final JXEditorPane  editorPane = new JXEditorPane();
    private final PlainDocument document   = new PlainDocument();
    private final JScrollPane   scrollPane = new JScrollPane( editorPane );

    public TXTRenderer()
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
    public void displayContent(
        final String content )
    {
        editorPane.setText( content );
        EventQueue.invokeLater( new Runnable()
        {
            public void run()
            {
                // todo better break into pages and don't do this 
                scrollPane.getVerticalScrollBar().setValue( 0 );
            }
        } );
    }

    @Override
    public void goTo(
        final Bookmark bookmark )
    {
        Single.instance( Paginator.class ).setNewPage( bookmark.getPage() );
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
        final Bookmark note )
    {
        final float page = note.getPage() + 1;
        return page / note.getPageCount();
    }

    @Override
    protected String getSelectedText()
    {
        return editorPane.getSelectedText();
    }
}
