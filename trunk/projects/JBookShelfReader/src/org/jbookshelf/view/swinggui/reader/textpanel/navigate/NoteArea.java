/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.textpanel.navigate;

import icons.IMG;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.bushe.swing.event.EventBus;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.Note;
import org.jbookshelf.model.db.util.BookShelf;
import org.jbookshelf.view.swinggui.actions.TranslatableAction;
import org.jbookshelf.view.swinggui.dialog.NoteDialog;
import org.jbookshelf.view.swinggui.reader.ReaderWindow;
import org.jdesktop.swingx.JXEditorPane;
import org.jdesktop.swingx.border.DropShadowBorder;

public class NoteArea
    extends JPanel
{
    private class DeleteAction
        extends TranslatableAction
    {
        public DeleteAction()
        {
            super( null, IMG.icon( IMG.LIST_REMOVE_PNG, 16 ) );
        }

        @Override
        public void actionPerformed(
            final ActionEvent e )
        {
            BookShelf.removeNote( note );
            EventBus.publish( note );
        }
    }

    private static final int   MAX_LEN  = 150;

    private final JXEditorPane textArea = new JXEditorPane();
    private final JLabel       label    = new JLabel();
    private final Note         note;

    public NoteArea(
        final Note note )
    {
        super( new BorderLayout() );
        setBorder( new DropShadowBorder() );

        this.note = note;

        add( textArea, BorderLayout.CENTER );
        final Box horizontalBox = Box.createHorizontalBox();
        horizontalBox.add( label );
        horizontalBox.add( Box.createHorizontalGlue() );
        horizontalBox.add( new JButton( new DeleteAction() ) );
        add( horizontalBox, BorderLayout.NORTH );

        textArea.setEditable( false );
        textArea.setFont( textArea.getFont().deriveFont( Font.ITALIC, 10f ) );

        final String content = note.getContent().trim();
        textArea.setText( content.length() > MAX_LEN
            ? content.substring( 0, MAX_LEN ) + "..." : content );
        final String title = note.getTitle().trim();
        label.setText( title.length() > MAX_LEN
            ? title.substring( 0, MAX_LEN ) + "..." : title );

        final MouseAdapter l = new MouseAdapter()
        {
            @Override
            public void mouseClicked(
                final MouseEvent e )
            {
                new NoteDialog( Single.instance( ReaderWindow.class ), note ).setVisible( true );
            }

            @Override
            public void mouseEntered(
                final MouseEvent e )
            {
                textArea.setCursor( new Cursor( Cursor.HAND_CURSOR ) );
                setCursor( new Cursor( Cursor.HAND_CURSOR ) );
            }
        };
        textArea.addMouseListener( l );
        addMouseListener( l );
    }
}