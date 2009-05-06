package org.jbookshelf.swinggui.widgets.dialog;

import images.IMG;

import java.awt.BorderLayout;
import java.awt.Font;
import java.net.MalformedURLException;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.jbookshelf.i18n.I18N;
import org.jbookshelf.qtgui.logic.Translatable;
import org.jbookshelf.qtgui.logic.Translator;
import org.jbookshelf.swinggui.MainWindow;
import org.jbookshelf.swinggui.widgets.panel.GridBagPanel;
import org.jdesktop.swingx.JXHyperlink;
import org.jdesktop.swingx.action.OpenBrowserAction;
import org.xnap.commons.gui.DefaultDialog;

public class JBSAboutDialog
    extends DefaultDialog
    implements
        Translatable
{
    private static final OpenBrowserAction GPL_ACTION;

    private final JLabel                   header                = new JLabel();

    private final JLabel                   authorLabel           = new JLabel();
    private final JLabel                   authorValueLabel      = new JLabel();
    private final JLabel                   licenseLabel          = new JLabel();
    private final JXHyperlink              licenseValueHyperLink = new JXHyperlink();
    private final JLabel                   versionLabel          = new JLabel();
    private final JLabel                   versionValueLabel     = new JLabel();

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

    public JBSAboutDialog(
        final JFrame owner )
    {
        super( owner, BUTTON_CLOSE );
        setModal( true );

        initComponents();
        Translator.addTranslatable( this );

        pack();
        setLocationRelativeTo( null );
    }

    public void retranslate()
    {
        setTitle( I18N.tr( "About" ) );

        header.setIcon( IMG.icon( "logo-128.png" ) );
        header.setText( MainWindow.APP_NAME );
        header.setFont( new Font( Font.SERIF, Font.BOLD | Font.ITALIC, 32 ) );

        authorLabel.setText( I18N.tr( "Author" ) );
        licenseLabel.setText( I18N.tr( "License" ) );
        versionLabel.setText( I18N.tr( "Version" ) );

        authorValueLabel.setText( I18N.tr( "Andrey Yeremenok (eav1986_at_gmail_com)" ) );
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
