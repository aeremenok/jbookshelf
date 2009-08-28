/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.textpanel;

import icons.IMG;

import java.awt.Component;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;

import javax.swing.JPopupMenu;

import org.apache.log4j.Logger;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.controller.util.URIUtil;
import org.jbookshelf.model.db.Note;
import org.jbookshelf.view.swinggui.actions.TranslatableAction;
import org.jbookshelf.view.swinggui.dialog.NoteDialog;
import org.jbookshelf.view.swinggui.reader.ReaderWindow;
import org.jbookshelf.view.swinggui.reader.types.pdf.ExtractedTextDialog;
import org.jbookshelf.view.swinggui.reader.types.pdf.PDFPanel;
import org.xnap.commons.gui.util.PopupListener;

/**
 * todo merge with {@link ExtractedTextDialog} or make {@link PDFPanel} text-selectable =)
 * 
 * @author eav 2009
 * @param <PageType>
 */
public abstract class SelectableTextPanel<PageType>
    extends ReaderContentPanel<PageType>
{
    private class ClipboardTextAction
        extends TranslatableAction
    {
        public ClipboardTextAction()
        {
            super( tr( "To clipboard" ), IMG.icon( IMG.CLIPBOARD_PNG, 32 ) );
        }

        @Override
        public void actionPerformed(
            final ActionEvent e )
        {
            final String text = getSelectedText();
            final StringSelection clipString = new StringSelection( text );
            getToolkit().getSystemClipboard().setContents( clipString, clipString );
        }
    }

    private class CreateNoteAction
        extends TranslatableAction
    {
        public CreateNoteAction()
        {
            super( tr( "Create note" ), IMG.icon( IMG.KNOTES_PNG, 32 ) );
        }

        @Override
        public void actionPerformed(
            final ActionEvent e )
        {
            final String text = getSelectedText();
            final Note note = createNote( text );
            new NoteDialog( Single.instance( ReaderWindow.class ), note ).setVisible( true );
        }
    }

    private class GoogleTextAction
        extends TranslatableAction
    {
        public GoogleTextAction()
        {
            super( tr( "Google" ), IMG.icon( IMG.GOOGLE_PNG, 32 ) );
        }

        @Override
        public void actionPerformed(
            final ActionEvent e )
        {
            final String text = getSelectedText();
            URIUtil.google( text );
        }
    }

    protected class SelectionPopupListener
        extends PopupListener
    {
        private SelectionPopupListener(
            final JPopupMenu popup )
        {
            super( popup );
        }

        @Override
        public void showPopup(
            final Component source,
            final int x,
            final int y )
        {
            if ( getSelectedText().trim().length() > 0 )
            {
                super.showPopup( source, x, y );
            }
        }
    }

    @SuppressWarnings( "unused" )
    private static final Logger      log           = Logger.getLogger( SelectableTextPanel.class );

    protected JPopupMenu             menu          = new JPopupMenu();
    protected SelectionPopupListener popupListener = new SelectionPopupListener( menu );

    public SelectableTextPanel()
    {
        super();

        menu.add( new CreateNoteAction() );
        menu.add( new GoogleTextAction() );
        menu.add( new ClipboardTextAction() );
    }

    protected abstract String getSelectedText();
}