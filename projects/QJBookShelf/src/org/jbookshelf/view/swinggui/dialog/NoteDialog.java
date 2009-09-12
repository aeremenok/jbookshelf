/**
 * 
 */
package org.jbookshelf.view.swinggui.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Action;
import javax.swing.Box;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.bushe.swing.event.EventBus;
import org.jbookshelf.model.db.Note;
import org.jbookshelf.model.db.util.BookShelf;
import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.i18n.Translatable;
import org.jbookshelf.view.swinggui.widget.WrapperPanel;
import org.jdesktop.swingx.JXEditorPane;
import org.xnap.commons.gui.DefaultDialog;
import org.xnap.commons.i18n.I18n;

/**
 * a dialog for note editing
 * 
 * @author eav 2009
 */
public class NoteDialog
    extends DefaultDialog
    implements
    Translatable
{
    public static final SimpleDateFormat format = new SimpleDateFormat( "yy-MM-dd HH:mm" );

    public static String createTitle()
    {
        return I18N.tr( "Note" ) + " " + format.format( new Date() );
    }

    private final JTextField   title           = new JTextField();
    private final JXEditorPane citation        = new JXEditorPane();

    private final JXEditorPane content         = new JXEditorPane();
    private final WrapperPanel citationWrapper = new WrapperPanel( citation, true );
    private final WrapperPanel contentWrapper  = new WrapperPanel( content, true );

    private final JLabel       pageLabel       = new JLabel();
    private final JLabel       posLabel        = new JLabel();

    private final Note         note;

    public NoteDialog(
        final JDialog parent,
        final Note note )
    {
        super( parent, BUTTON_OKAY | BUTTON_CANCEL );
        this.note = note;
        init();
    }

    public NoteDialog(
        final JFrame parent,
        final Note note )
    {
        super( parent, BUTTON_OKAY | BUTTON_CANCEL );
        this.note = note;
        init();
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
            throw new Error( e );
        }
    }

    @Override
    public void translate(
        final I18n i18n )
    {
        setTitle( note.getBook().getName() );
        citationWrapper.setBorder( new TitledBorder( i18n.tr( "Citation" ) ) );
        contentWrapper.setBorder( new TitledBorder( i18n.tr( "Note" ) ) );
        getCancelAction().putValue( Action.NAME, i18n.tr( "Cancel" ) );
        pageLabel.setText( i18n.tr( "Page: " ) + (note.getPage() + 1) + "/" + note.getPageCount() + " " );
        posLabel.setText( i18n.tr( "Position: " ) + note.getPosition() );
    }

    private void init()
    {
        initComponents();
        I18N.translate( this );

        title.setText( note.getTitle() );
        content.setText( note.getContent() );
        citation.setText( note.getCitation() );
    }

    private void initComponents()
    {
        setButtonSeparatorVisible( false );
        final JPanel panel = new JPanel( new BorderLayout() );
        setMainComponent( panel );

        final Box horizontalBox = Box.createHorizontalBox();
        horizontalBox.add( title );
        horizontalBox.add( Box.createRigidArea( new Dimension( 10, 0 ) ) );
        horizontalBox.add( pageLabel );
        horizontalBox.add( Box.createRigidArea( new Dimension( 10, 0 ) ) );
        horizontalBox.add( posLabel );

        panel.add( horizontalBox, BorderLayout.NORTH );
        final JSplitPane split = new JSplitPane( JSplitPane.VERTICAL_SPLIT );
        panel.add( split );
        split.setTopComponent( citationWrapper );
        split.setBottomComponent( contentWrapper );

        split.setResizeWeight( 0.5 );

        setPreferredSize( new Dimension( 800, 800 ) );

        pack();
        setLocationRelativeTo( null );
    }
}
