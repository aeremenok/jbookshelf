/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.types.pdf;

import icons.IMG;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JPopupMenu;

import org.jbookshelf.controller.util.URIUtil;
import org.jbookshelf.model.db.api.spec.INote;
import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.i18n.Translatable;
import org.jbookshelf.view.swinggui.actions.TranslatableAction;
import org.jbookshelf.view.swinggui.dialog.NoteDialog;
import org.jbookshelf.view.swinggui.widget.WrapperPanel;
import org.jdesktop.swingx.JXEditorPane;
import org.xnap.commons.gui.DefaultDialog;
import org.xnap.commons.gui.util.PopupListener;
import org.xnap.commons.i18n.I18n;

/**
 * displays text, extracted from pdf, with ability of selecting it
 * 
 * @author eav 2009
 */
public class ExtractedTextDialog
    extends DefaultDialog
    implements
    Translatable
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
            final String text = editorPane.getSelectedText();
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
            final String text = editorPane.getSelectedText();
            final INote note = pdfPanel.createNote( text );
            new NoteDialog( ExtractedTextDialog.this, note ).setVisible( true );
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
            final String text = editorPane.getSelectedText();
            URIUtil.google( text );
        }
    }

    private final JXEditorPane editorPane = new JXEditorPane();
    private final PDFRenderer     pdfPanel;

    public ExtractedTextDialog(
        final JFrame parent,
        final String text,
        final PDFRenderer pdfPanel )
    {
        super( parent, BUTTON_CLOSE );
        this.pdfPanel = pdfPanel;
        I18N.translate( this );

        setMainComponent( new WrapperPanel( editorPane, true ) );
        editorPane.setText( text );
        setPreferredSize( new Dimension( 800, 800 ) );
        setButtonSeparatorVisible( false );

        initListeners();

        pack();
        setLocationRelativeTo( null );
    }

    @Override
    public void translate(
        final I18n i18n )
    {
        setTitle( i18n.tr( "Extracted text from PDF" ) );
        getCloseAction().putValue( Action.NAME, i18n.tr( "Close" ) );
    }

    private void initListeners()
    {
        final JPopupMenu menu = new JPopupMenu();
        menu.add( new CreateNoteAction() );
        menu.add( new ClipboardTextAction() );
        menu.add( new GoogleTextAction() );

        editorPane.addMouseListener( new PopupListener( menu )
        {
            @Override
            protected void showPopup(
                final Component source,
                final int x,
                final int y )
            {
                if ( editorPane.getSelectedText().trim().length() > 0 )
                {
                    super.showPopup( source, x, y );
                }
            }
        } );
    }
}
