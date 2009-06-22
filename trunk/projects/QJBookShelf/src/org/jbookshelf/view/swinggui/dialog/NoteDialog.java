/**
 * 
 */
package org.jbookshelf.view.swinggui.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.bushe.swing.event.EventBus;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.Note;
import org.jbookshelf.model.db.util.BookShelf;
import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.i18n.Translatable;
import org.jbookshelf.view.swinggui.WrapperPanel;
import org.jbookshelf.view.swinggui.main.MainWindow;
import org.jdesktop.swingx.JXEditorPane;
import org.xnap.commons.gui.DefaultDialog;
import org.xnap.commons.i18n.I18n;

/**
 * @author eav 2009
 */
public class NoteDialog
    extends DefaultDialog
    implements
    Translatable
{
    private final JTextField   title    = new JTextField();
    private final JXEditorPane citation = new JXEditorPane();
    private final JXEditorPane content  = new JXEditorPane();

    private final Note         note;

    public NoteDialog(
        final Note note )
    {
        super( Single.instance( MainWindow.class ), BUTTON_OKAY | BUTTON_CANCEL );
        this.note = note;
        init();
        I18N.translate( this );

        title.setText( note.getTitle() );
        content.setText( note.getContent() );
        citation.setText( note.getCitation() );
    }

    @Override
    public boolean apply()
    {
        note.setTitle( title.getText() );
        note.setContent( content.getText() );
        note.setCitation( citation.getText() );
        try
        {
            BookShelf.mergeNote( note );
            EventBus.publish( note );
            return true;
        }
        catch ( final Throwable e )
        {
            return false;
        }
    }

    @Override
    public void translate(
        final I18n i18n )
    {
        setTitle( note.getBook().getName() );
        ((JComponent) citation.getParent()).setBorder( new TitledBorder( i18n.tr( "Citation" ) ) );
        ((JComponent) content.getParent()).setBorder( new TitledBorder( i18n.tr( "Note" ) ) );
        getCancelAction().putValue( Action.NAME, i18n.tr( "Cancel" ) );
    }

    private void init()
    {
        setButtonSeparatorVisible( false );
        final JPanel panel = new JPanel( new BorderLayout() );
        setMainComponent( panel );

        panel.add( title, BorderLayout.NORTH );
        final JSplitPane split = new JSplitPane( JSplitPane.VERTICAL_SPLIT );
        panel.add( split );
        split.setTopComponent( new WrapperPanel( citation ) );
        split.setBottomComponent( new WrapperPanel( content ) );

        split.setResizeWeight( 0.5 );

        setPreferredSize( new Dimension( 800, 600 ) );

        pack();
        setLocationRelativeTo( null );
    }
}
