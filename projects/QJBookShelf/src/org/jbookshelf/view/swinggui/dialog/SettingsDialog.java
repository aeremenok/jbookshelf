package org.jbookshelf.view.swinggui.dialog;

import javax.swing.Action;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import org.jbookshelf.controller.settings.Settings;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.i18n.Translatable;
import org.jbookshelf.view.swinggui.FileChooserPanelExt;
import org.jbookshelf.view.swinggui.GridBagPanel;
import org.jbookshelf.view.swinggui.LookAndFeelComboBoxModel;
import org.jbookshelf.view.swinggui.main.MainWindow;
import org.xnap.commons.gui.DefaultDialog;
import org.xnap.commons.gui.FileChooserPanel;
import org.xnap.commons.gui.settings.SettingComponentMediator;
import org.xnap.commons.i18n.I18n;

public class SettingsDialog
    extends DefaultDialog
    implements
    Translatable
{
    private final SettingComponentMediator mediator           = new SettingComponentMediator();
    private final Settings                 settings           = Single.instance( Settings.class );

    private final JComboBox                langComboBox       = new JComboBox();
    private final JComboBox                lafComboBox        = new JComboBox( new LookAndFeelComboBoxModel() );
    private final FileChooserPanel         jbsDirChooserPanel = new FileChooserPanelExt( 50, "jbs.dir.chooser" );
    private final FileChooserPanel         wspDirChooserPanel = new FileChooserPanelExt( 50, "wsp.dir.chooser" );

    private final JLabel                   langLabel          = new JLabel();
    private final JLabel                   lafLabel           = new JLabel();
    private final JLabel                   jbsDirLabel        = new JLabel();
    private final JLabel                   wspDirLabel        = new JLabel();

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

        getCancelAction().putValue( Action.NAME, i18n.tr( "Cancel" ) );
        getDefaultsAction().putValue( Action.NAME, i18n.tr( "Defaults" ) );

        langLabel.setText( i18n.tr( "Language" ) );
        lafLabel.setText( i18n.tr( "Look and feel" ) );
        jbsDirLabel.setText( i18n.tr( "JBookShelf directory" ) );
        wspDirLabel.setText( i18n.tr( "Workspace directory" ) );

        getMainPanel().setBorder( new TitledBorder( i18n.tr( "JBookShelf settings" ) ) );
    }

    private void initComponents()
    {
        setButtonSeparatorVisible( false );

        mediator.add( settings.LANGUAGE, langComboBox.getModel() );
        mediator.add( settings.LAF, lafComboBox.getModel() );
        mediator.add( settings.JBS_DIR, jbsDirChooserPanel.getTextField() );
        mediator.add( settings.WORKSPACE_DIR, wspDirChooserPanel.getTextField() );

        jbsDirChooserPanel.getFileChooser().setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
        wspDirChooserPanel.getFileChooser().setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );

        final GridBagPanel panel = new GridBagPanel();
        setMainComponent( panel );

        panel.add( langLabel, 0, 0 );
        panel.add( langComboBox, 0, 1 );

        panel.add( lafLabel, 1, 0 );
        panel.add( lafComboBox, 1, 1 );

        panel.add( jbsDirLabel, 2, 0 );
        panel.add( jbsDirChooserPanel, 2, 1 );

        panel.add( wspDirLabel, 3, 0 );
        panel.add( wspDirChooserPanel, 3, 1 );
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
