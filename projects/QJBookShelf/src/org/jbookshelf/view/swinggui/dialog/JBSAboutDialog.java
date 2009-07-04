package org.jbookshelf.view.swinggui.dialog;

import icons.IMG;

import java.awt.BorderLayout;
import java.awt.Font;
import java.net.MalformedURLException;

import javax.swing.JLabel;

import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.i18n.Translatable;
import org.jbookshelf.view.swinggui.main.MainWindow;
import org.jbookshelf.view.swinggui.widget.GridBagPanel;
import org.jdesktop.swingx.JXHyperlink;
import org.jdesktop.swingx.action.OpenBrowserAction;
import org.xnap.commons.gui.DefaultDialog;
import org.xnap.commons.i18n.I18n;

/**
 * displays "about" info
 * 
 * @author eav 2009
 */
public class JBSAboutDialog
    extends DefaultDialog
    implements
    Translatable
{
    private static final OpenBrowserAction GPL_ACTION;

    static
    {
        try
        {
            GPL_ACTION = new OpenBrowserAction( "http://www.gnu.org/licenses/gpl.html" );
        }
        catch ( final MalformedURLException e )
        {
            throw new ExceptionInInitializerError( e );
        }
    }

    private final JLabel                   header                = new JLabel();
    private final JLabel                   authorLabel           = new JLabel();
    private final JLabel                   authorValueLabel      = new JLabel();
    private final JLabel                   licenseLabel          = new JLabel();
    private final JXHyperlink              licenseValueHyperLink = new JXHyperlink();
    private final JLabel                   versionLabel          = new JLabel();

    private final JLabel                   versionValueLabel     = new JLabel();

    public JBSAboutDialog()
    {
        super( Single.instance( MainWindow.class ), BUTTON_CLOSE );
        setModal( true );

        initComponents();
        I18N.translate( this );

        pack();
        setLocationRelativeTo( null );
    }

    public void translate(
        final I18n i18n )
    {
        setTitle( i18n.tr( "About" ) );

        header.setIcon( IMG.icon( IMG.LOGO_PNG, 128 ) );
        header.setText( MainWindow.APP_NAME );
        header.setFont( new Font( Font.SERIF, Font.BOLD | Font.ITALIC, 32 ) );

        authorLabel.setText( i18n.tr( "Author" ) );
        licenseLabel.setText( i18n.tr( "License" ) );
        versionLabel.setText( i18n.tr( "Version" ) );

        authorValueLabel.setText( i18n.tr( "Andrey Yeremenok (eav1986_at_gmail_com)" ) );
        licenseValueHyperLink.setAction( GPL_ACTION );
        licenseValueHyperLink.setText( "GPL v.3" );
        versionValueLabel.setText( MainWindow.VERSION );
    }

    private void initComponents()
    {
        getMainPanel().setLayout( new BorderLayout() );
        getMainPanel().add( header, BorderLayout.NORTH );

        final GridBagPanel panel = new GridBagPanel();
        getMainPanel().add( panel, BorderLayout.CENTER );

        panel.add( versionLabel, 0, 0 );
        panel.add( versionValueLabel, 0, 1 );

        panel.add( authorLabel, 1, 0 );
        panel.add( authorValueLabel, 1, 1 );

        panel.add( licenseLabel, 2, 0 );
        panel.add( licenseValueHyperLink, 2, 1 );
    }
}
