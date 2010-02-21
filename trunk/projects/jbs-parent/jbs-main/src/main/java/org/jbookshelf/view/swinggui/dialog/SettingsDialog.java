package org.jbookshelf.view.swinggui.dialog;

import java.net.Proxy.Type;

import javax.swing.Action;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;

import org.jbookshelf.controller.settings.Settings;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.i18n.Translatable;
import org.jbookshelf.view.swinggui.main.Application;
import org.jbookshelf.view.swinggui.main.MainWindow;
import org.jbookshelf.view.swinggui.widget.ChangeDetectMediator;
import org.jbookshelf.view.swinggui.widget.model.LookAndFeelComboBoxModel;
import org.jbookshelf.view.swinggui.widget.panel.FileChooserPanelExt;
import org.jbookshelf.view.swinggui.widget.panel.GridBagPanel;
import org.xnap.commons.gui.DefaultDialog;
import org.xnap.commons.gui.FileChooserPanel;
import org.xnap.commons.i18n.I18n;

/**
 * a dialog to edit {@link Settings}
 * 
 * @author eav 2009
 */
public class SettingsDialog
    extends DefaultDialog
    implements
    Translatable
{
    private final ChangeDetectMediator mediator           = new ChangeDetectMediator();
    private final Settings             settings           = Single.instance( Settings.class );

    private final JComboBox            langComboBox       = new JComboBox();
    private final JComboBox            lafComboBox        = new JComboBox( new LookAndFeelComboBoxModel() );
    private final FileChooserPanel     wspDirChooserPanel = new FileChooserPanelExt( 50, "wsp.dir.chooser" );

    // todo add proxy checking
    private final JComboBox            proxyTypeComboBox  = new JComboBox();
    private final JTextField           proxyHostField     = new JTextField();
    private final JSpinner             proxyPortField     = new JSpinner( new SpinnerNumberModel( 0, 0, 99999, 1 ) );

    private final JLabel               langLabel          = new JLabel();
    private final JLabel               lafLabel           = new JLabel();
    private final JLabel               wspDirLabel        = new JLabel();
    private final JLabel               infoLabel          = new JLabel();
    private final JLabel               proxyTypeLabel     = new JLabel();
    private final JLabel               proxyHostLabel     = new JLabel();
    private final JLabel               proxyPortLabel     = new JLabel();

    public SettingsDialog()
    {
        super( Single.instance( MainWindow.class ), BUTTON_DEFAULTS | BUTTON_OKAY | BUTTON_CANCEL );
        initComponents();
        I18N.translate( this );
        mediator.revert();

        pack();
        setLocationRelativeTo( null );
    }

    @Override
    public boolean apply()
    {
        mediator.apply();
        settings.save();

        if ( mediator.isChanged( settings.LANGUAGE ) )
        { // restart
            final String msg = I18N.tr( "Restart needed in order changes to take effect. Restart now?" );
            if ( JOptionPane.showConfirmDialog( this, msg, "", JOptionPane.YES_NO_OPTION ) == JOptionPane.YES_OPTION )
            {
                MainWindow r = Single.instance( MainWindow.class );
                Application.INSTANCE.restart();
            }
        }

        // restart not required
        return true;
    }

    public void translate(
        final I18n i18n )
    {
        setTitle( i18n.tr( "Settings" ) );

        langComboBox.removeAllItems();
        for ( final String lang : I18N.getLocalizedLanguages() )
        {
            langComboBox.addItem( lang );
        }

        proxyTypeComboBox.addItem( Type.DIRECT );
        proxyTypeComboBox.addItem( Type.HTTP );
        proxyTypeComboBox.addItem( Type.SOCKS );

        getCancelAction().putValue( Action.NAME, i18n.tr( "Cancel" ) );
        getDefaultsAction().putValue( Action.NAME, i18n.tr( "Defaults" ) );

        langLabel.setText( i18n.tr( "Language" ) + " *" );
        lafLabel.setText( i18n.tr( "Look and feel" ) );
        wspDirLabel.setText( i18n.tr( "Workspace directory" ) );

        proxyTypeLabel.setText( i18n.tr( "Proxy Type" ) );
        proxyHostLabel.setText( i18n.tr( "Proxy Host" ) );
        proxyPortLabel.setText( i18n.tr( "Proxy Port" ) );

        infoLabel.setText( i18n.tr( " * - Requires restart" ) );

        getMainPanel().setBorder( new TitledBorder( i18n.tr( "JBookShelf settings" ) ) );
    }

    private void initComponents()
    {
        setButtonSeparatorVisible( false );

        mediator.add( settings.LANGUAGE, langComboBox.getModel() );
        mediator.add( settings.LAF, lafComboBox.getModel() );
        mediator.add( settings.WORKSPACE_DIR, wspDirChooserPanel.getTextField() );
        mediator.add( settings.PROXY_TYPE, proxyTypeComboBox.getModel() );
        mediator.add( settings.PROXY_HOST, proxyHostField );
        mediator.add( settings.PROXY_PORT, (SpinnerNumberModel) proxyPortField.getModel() );

        wspDirChooserPanel.getFileChooser().setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );

        final GridBagPanel panel = new GridBagPanel();
        setMainComponent( panel );

        panel.add( langLabel, 0, 0 );
        panel.add( langComboBox, 0, 1 );

        panel.add( lafLabel, 1, 0 );
        panel.add( lafComboBox, 1, 1 );

        panel.add( wspDirLabel, 2, 0 );
        panel.add( wspDirChooserPanel, 2, 1 );

        panel.add( proxyTypeLabel, 3, 0 );
        panel.add( proxyTypeComboBox, 3, 1 );

        panel.add( proxyHostLabel, 4, 0 );
        panel.add( proxyHostField, 4, 1 );

        panel.add( proxyPortLabel, 5, 0 );
        panel.add( proxyPortField, 5, 1 );

        panel.add( infoLabel, 6, 1, 1, 2 );
    }

    @Override
    protected void cancelled()
    {
        mediator.revert();
    }

    @Override
    protected void defaults()
    {
        mediator.revertToDefaults();
    }
}
