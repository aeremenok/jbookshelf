/**
 * 
 */
package org.jbookshelf.view.swinggui.widgets.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.bushe.swing.event.EventBus;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.BookShelf;
import org.jbookshelf.model.db.Note;
import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.logic.Translatable;
import org.jbookshelf.view.logic.Translator;
import org.jbookshelf.view.swinggui.MainWindow;
import org.jdesktop.swingx.JXEditorPane;
import org.xnap.commons.gui.DefaultDialog;

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
        Translator.addTranslatable( this );
        init();

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
    public void retranslate()
    {
        citation.setBorder( new TitledBorder( I18N.tr( "Citation" ) ) );
        content.setBorder( new TitledBorder( I18N.tr( "Note" ) ) );
        getCancelAction().putValue( Action.NAME, I18N.tr( "Cancel" ) );
    }

    private void init()
    {
        setButtonSeparatorVisible( false );
        final JPanel panel = new JPanel( new BorderLayout() );
        setMainComponent( panel );

        panel.add( title, BorderLayout.NORTH );
        final JSplitPane split = new JSplitPane( JSplitPane.VERTICAL_SPLIT );
        panel.add( split );
        split.setTopComponent( citation );
        split.setBottomComponent( content );

        split.setResizeWeight( 0.5 );

        setPreferredSize( new Dimension( 800, 600 ) );

        pack();
        setLocationRelativeTo( null );
    }
}
