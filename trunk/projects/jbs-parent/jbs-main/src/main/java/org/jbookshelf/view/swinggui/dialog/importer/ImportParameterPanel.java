/**
 * 
 */
package org.jbookshelf.view.swinggui.dialog.importer;

import icons.IMG;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;
import org.jbookshelf.controller.importer.DirCategoriesStrategy;
import org.jbookshelf.controller.importer.FileImportStrategy;
import org.jbookshelf.controller.importer.UseDirsStrategy;
import org.jbookshelf.controller.importer.UseMasksStrategy;
import org.jbookshelf.controller.settings.Settings;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.i18n.Translatable;
import org.jbookshelf.view.logic.Parameters;
import org.jbookshelf.view.logic.Parameters.Keys;
import org.jbookshelf.view.swinggui.multiedit.MultipleField;
import org.jbookshelf.view.swinggui.widget.FileChooserPanelExt;
import org.jbookshelf.view.swinggui.widget.GridBagPanel;
import org.xnap.commons.i18n.I18n;

/**
 * @author eav 2009
 */
public class ImportParameterPanel
    extends GridBagPanel
    implements
    Translatable
{
    @SuppressWarnings( "unused" )
    private static final Logger         log              = Logger.getLogger( ImportParameterPanel.class );

    private final JLabel                fileLabel        = new JLabel();
    private final JLabel                maskLabel        = new JLabel();
    private final JLabel                infoLabel        = new JLabel();
    private final JLabel                strategyLabel    = new JLabel();

    private final JComboBox             strategyComboBox = new JComboBox();
    /**
     * root directory of filesystem
     */
    private final FileChooserPanelExt   fileChooserPanel = new FileChooserPanelExt( 50, "import.dir.chooser" );
    /**
     * masks to parse filenames
     */
    private final MultipleField<String> maskField        = new MultipleField<String>();

    public ImportParameterPanel()
    {
        super();
        I18N.translate( this );
        initComponents();
        strategyComboBox.addItemListener( new ItemListener()
        {
            public void itemStateChanged(
                final ItemEvent e )
            {
                if ( e.getStateChange() == ItemEvent.SELECTED )
                {
                    switchStrategyPresentation();
                }
            }
        } );
        switchStrategyPresentation();
    }

    public Parameters getParameters()
    {
        // check preconditions
        final File file = fileChooserPanel.getFile();
        if ( file == null || !file.exists() )
        {
            final String path = file != null
                ? file.getAbsolutePath() : "";
            JOptionPane.showMessageDialog( this, I18N.tr( "File not found: " ) + path, "", JOptionPane.ERROR_MESSAGE );
            return null;
        }

        final FileImportStrategy strategy = (FileImportStrategy) strategyComboBox.getSelectedItem();
        if ( strategy instanceof UseMasksStrategy )
        {
            final UseMasksStrategy useMasksStrategy = (UseMasksStrategy) strategy;
            final List<String> masks = maskField.getValues();
            if ( masks.size() == 0 )
            {
                JOptionPane.showMessageDialog( this, I18N.tr( "Specify at least one mask" ), "",
                    JOptionPane.ERROR_MESSAGE );
                return null;
            }
            useMasksStrategy.setMasks( masks );
        }

        final Parameters parameters = new Parameters();
        parameters.put( Keys.IMPORT_STRATEGY, strategy );
        parameters.put( Keys.IMPORT_ROOTS, new File[]
        { file } );
        return parameters;
    }

    public void onClose()
    {
        // save the masks and the chosen directory
        final Settings settings = Single.instance( Settings.class );
        settings.IMPORT_MASKS.setValue( maskField.getValues() );
        settings.save();
    }

    @Override
    public void translate(
        final I18n i18n )
    {
        fileLabel.setText( i18n.tr( "File" ) );
        maskLabel.setText( i18n.tr( "Mask" ) );

        strategyLabel.setText( i18n.tr( "Strategy" ) );

    }

    private void initComponents()
    {
        final GridBagPanel grid = this;
        grid.add( fileLabel, 0, 0 );
        grid.add( fileChooserPanel, 0, 1 );
        grid.add( infoLabel, 1, 0, 1, 2 );
        grid.add( strategyLabel, 2, 0 );
        grid.add( strategyComboBox, 2, 1 );
        grid.add( maskLabel, 3, 0 );
        grid.add( maskField, 3, 1 );

        infoLabel.setHorizontalAlignment( SwingConstants.CENTER );
        infoLabel.setIcon( IMG.icon( IMG.KTIP_PNG ) );

        fileChooserPanel.getFileChooser().setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );

        // fill masks from settings
        maskField.setValues( Single.instance( Settings.class ).IMPORT_MASKS.getValue() );

        strategyComboBox.addItem( new UseMasksStrategy() );
        strategyComboBox.addItem( new DirCategoriesStrategy() );
        strategyComboBox.addItem( new UseDirsStrategy() );
    }

    private void switchStrategyPresentation()
    {
        final FileImportStrategy strategy = (FileImportStrategy) strategyComboBox.getSelectedItem();
        infoLabel.setText( strategy.longDescription() );
        final boolean useMasks = !(strategy instanceof UseDirsStrategy);
        maskLabel.setVisible( useMasks );
        maskField.setVisible( useMasks );
    }
}
