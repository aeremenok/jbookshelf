/**
 * 
 */
package org.jbookshelf.view.swinggui.dialog;

import icons.IMG;

import java.awt.Dimension;

import javax.swing.Action;
import javax.swing.Box;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import org.apache.log4j.Logger;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.controller.util.StringUtil;
import org.jbookshelf.controller.util.URIUtil;
import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.i18n.Translatable;
import org.jbookshelf.view.swinggui.main.MainWindow;
import org.jbookshelf.view.swinggui.widget.WrapperPanel;
import org.jdesktop.swingx.JXEditorPane;
import org.jdesktop.swingx.error.ErrorInfo;
import org.xnap.commons.gui.DefaultDialog;
import org.xnap.commons.i18n.I18n;

/**
 * @author eav
 */
public class BugReportDialog
    extends DefaultDialog
    implements
    Translatable
{
    @SuppressWarnings( "unused" )
    private static final Logger log              = Logger.getLogger( BugReportDialog.class );

    private final JXEditorPane  description      = new JXEditorPane();
    private final JXEditorPane  expected         = new JXEditorPane();
    private final JXEditorPane  actual           = new JXEditorPane();

    private final WrapperPanel  descriptionPanel = new WrapperPanel( description, true );
    private final WrapperPanel  expectedPanel    = new WrapperPanel( expected, true );
    private final WrapperPanel  actualPanel      = new WrapperPanel( actual, true );

    private final JLabel        infoLabel        = new JLabel();

    public BugReportDialog()
    {
        super( Single.instance( MainWindow.class ), BUTTON_OKAY | BUTTON_CANCEL );
        init();
    }

    public BugReportDialog(
        final JDialog dialog )
    {
        super( dialog, BUTTON_OKAY | BUTTON_CANCEL );
        init();
    }

    public BugReportDialog(
        final JDialog errorDialog,
        final ErrorInfo info )
    {
        this( errorDialog );
        actual.setText( StringUtil.printThrowable( info.getErrorException() ) );
    }

    @Override
    public boolean apply()
    {
        final StringBuilder body = new StringBuilder();

        body.append( "What steps will reproduce the problem?" ).append( "\n" );
        body.append( description.getText() ).append( "\n" );
        body.append( "What is the expected output?" ).append( "\n" );
        body.append( expected.getText() ).append( "\n" );
        body.append( "What do you see instead?" ).append( "\n" );
        body.append( actual.getText() ).append( "\n" );

        URIUtil.mail( "jbookshelf@gmail.com", "bug-report", body.toString() );

        return true;
    }

    @Override
    public void translate(
        final I18n i18n )
    {
        setTitle( i18n.tr( "Describe an issue" ) );

        getOkayAction().putValue( Action.NAME, i18n.tr( "Send..." ) );
        getCancelAction().putValue( Action.NAME, i18n.tr( "Cancel" ) );

        descriptionPanel.setBorder( new TitledBorder( i18n.tr( "What steps will reproduce the problem?" ) ) );
        expectedPanel.setBorder( new TitledBorder( i18n.tr( "What is the expected output?" ) ) );
        actualPanel.setBorder( new TitledBorder( i18n.tr( "What do you see instead?" ) ) );

        infoLabel
            .setText( i18n
                .tr( "<html><i>Please describe a bug or a suggestion. Click 'Send...' to send a report via e-mail.</i></html>" ) );
    }

    private void init()
    {
        I18N.translate( this );

        setButtonSeparatorVisible( false );

        final Box box = Box.createVerticalBox();
        setMainComponent( box );
        box.add( infoLabel );
        box.add( descriptionPanel );
        box.add( expectedPanel );
        box.add( actualPanel );

        infoLabel.setHorizontalAlignment( SwingConstants.CENTER );
        infoLabel.setIcon( IMG.icon( IMG.KTIP_PNG ) );

        setPreferredSize( new Dimension( 800, 600 ) );

        pack();
        setLocationRelativeTo( null );
    }
}
