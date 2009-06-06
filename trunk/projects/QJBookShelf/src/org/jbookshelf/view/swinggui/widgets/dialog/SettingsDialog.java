package org.jbookshelf.view.swinggui.widgets.dialog;

import javax.swing.Action;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import org.jbookshelf.controller.settings.Settings;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.logic.Translatable;
import org.jbookshelf.view.logic.Translator;
import org.jbookshelf.view.swinggui.MainWindow;
import org.jbookshelf.view.swinggui.widgets.FileChooserPanelExt;
import org.jbookshelf.view.swinggui.widgets.LookAndFeelComboBoxModel;
import org.jbookshelf.view.swinggui.widgets.panel.GridBagPanel;
import org.xnap.commons.gui.DefaultDialog;
import org.xnap.commons.gui.FileChooserPanel;
import org.xnap.commons.gui.settings.SettingComponentMediator;

public class SettingsDialog
    extends DefaultDialog
    implements
    Translatable
{
    private final SettingComponentMediator mediator         = new SettingComponentMediator();
    private final Settings                 settings         = Single.instance( Settings.class );

    private final JComboBox                langComboBox     = new JComboBox();
    private final JComboBox                lafComboBox      = new JComboBox( new LookAndFeelComboBoxModel() );
    private final FileChooserPanel         fileChooserPanel = new FileChooserPanelExt( 50, "jbs.dir.chooser" );

    private final JLabel                   langLabel        = new JLabel();
    private final JLabel                   lafLabel         = new JLabel();
    private final JLabel                   dirLabel         = new JLabel();

    public SettingsDialog()
    {
        super( Single.instance( MainWindow.class ), BUTTON_APPLY | BUTTON_DEFAULTS | BUTTON_OKAY | BUTTON_CANCEL );
        initComponents();
        Translator.addTranslatable( this );
        mediator.revert();

        pack();
        setLocationRelativeTo( null );
    }

    @Override
    public boolean apply()
    {
        mediator.apply();
        return true;
    }

    public void retranslate()
    {
        setTitle( I18N.tr( "Settings" ) );

        langComboBox.removeAllItems();
        for ( final String lang : I18N.LOCALIZED_LANGUAGES )
        {
            langComboBox.addItem( lang );
        }

        getCancelAction().putValue( Action.NAME, I18N.tr( "Cancel" ) );
        getDefaultsAction().putValue( Action.NAME, I18N.tr( "Defaults" ) );
        getApplyAction().putValue( Action.NAME, I18N.tr( "Apply" ) );

        langLabel.setText( I18N.tr( "Language" ) );
        lafLabel.setText( I18N.tr( "Look and feel" ) );
        dirLabel.setText( I18N.tr( "JBookShelf directory" ) );

        getMainPanel().setBorder( new TitledBorder( I18N.tr( "JBookShelf settings" ) ) );
    }

    private void initComponents()
    {
        setButtonSeparatorVisible( false );

        mediator.add( settings.LANGUAGE, langComboBox.getModel() );
        mediator.add( settings.LAF, lafComboBox.getModel() );
        mediator.add( settings.JBS_DIR, fileChooserPanel.getTextField() );

        fileChooserPanel.getFileChooser().setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );

        final GridBagPanel panel = new GridBagPanel();
        setMainComponent( panel );

        panel.add( langLabel, 0, 0 );
        panel.add( langComboBox, 0, 1 );

        panel.add( lafLabel, 1, 0 );
        panel.add( lafComboBox, 1, 1 );

        panel.add( dirLabel, 2, 0 );
        panel.add( fileChooserPanel, 2, 1 );
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
