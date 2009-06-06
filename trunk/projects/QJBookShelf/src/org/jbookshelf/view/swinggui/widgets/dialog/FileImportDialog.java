/**
 * 
 */
package org.jbookshelf.view.swinggui.widgets.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.TitledBorder;

import org.jbookshelf.controller.importer.FileImporter;
import org.jbookshelf.controller.settings.Settings;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.BookShelf;
import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.logic.Translatable;
import org.jbookshelf.view.logic.Translator;
import org.jbookshelf.view.swinggui.MainWindow;
import org.jbookshelf.view.swinggui.widgets.FileChooserPanelExt;
import org.jbookshelf.view.swinggui.widgets.MultipleField;
import org.jbookshelf.view.swinggui.widgets.panel.CollectionPanel;
import org.jbookshelf.view.swinggui.widgets.panel.GridBagPanel;
import org.jdesktop.swingx.JXTable;
import org.xnap.commons.gui.DefaultDialog;

/**
 * @author eav 2009
 */
public class FileImportDialog
    extends DefaultDialog
    implements
    Translatable
{
    private final SuccessTableModel     successModel     = new SuccessTableModel();
    private final FailTableModel        failModel        = new FailTableModel();

    private final JXTable               successTable     = new JXTable( successModel );
    private final JXTable               failTable        = new JXTable( failModel );

    private final MultipleField<String> maskField        = new MultipleField<String>();

    private final FileImporter          importer         = new FileImporter()
                                                         {
                                                             @Override
                                                             protected void onImportFailure(
                                                                 final File file,
                                                                 final Exception e )
                                                             {
                                                                 failModel.addFile( file );
                                                                 // todo e as tooltip
                                                             }

                                                             @Override
                                                             protected void onImportSuccess(
                                                                 final Book book )
                                                             {
                                                                 successModel.addBook( book );
                                                             }
                                                         };
    private final JLabel                fileLabel        = new JLabel();
    private final JLabel                maskLabel        = new JLabel();

    private final FileChooserPanelExt   fileChooserPanel = new FileChooserPanelExt( 50, "import.dir.chooser" );

    private final JProgressBar          progressBar      = new JProgressBar();

    private final JSplitPane            splitPane        = new JSplitPane();

    public FileImportDialog()
    {
        super( Single.instance( MainWindow.class ), BUTTON_OKAY | BUTTON_CLOSE | BUTTON_DEFAULTS );

        initComponents();
        Translator.addTranslatable( this );

        setPreferredSize( new Dimension( 1024, 768 ) );

        pack();
        setLocationRelativeTo( null );
    }

    @Override
    public boolean apply()
    {
        for ( final Book book : successModel.getBooks() )
        {
            BookShelf.persistBook( book );
        }
        Single.instance( CollectionPanel.class ).updateActiveView();
        return true;
    }

    @Override
    public void close()
    {
        Single.instance( Settings.class ).save();
        super.close();
    }

    @Override
    public void retranslate()
    {
        setTitle( I18N.tr( "Import Files" ) );
        getCloseAction().putValue( Action.NAME, I18N.tr( "Close" ) );
        getDefaultsAction().putValue( Action.NAME, I18N.tr( "Import" ) );
        fileLabel.setText( I18N.tr( "File" ) );
        maskLabel.setText( I18N.tr( "Mask" ) );

        final TitledBorder leftBorder = new TitledBorder( I18N.tr( "Successfully imported books" ) );
        ((JComponent) splitPane.getLeftComponent()).setBorder( leftBorder );
        final TitledBorder rightBorder = new TitledBorder( I18N.tr( "Unimported files" ) );
        ((JComponent) splitPane.getRightComponent()).setBorder( rightBorder );
    }

    private void initComponents()
    {
        setButtonSeparatorVisible( false );

        final JPanel panel = new JPanel( new BorderLayout() );
        setMainComponent( panel );

        final GridBagPanel grid = new GridBagPanel();
        panel.add( grid, BorderLayout.NORTH );

        grid.add( fileLabel, 0, 0 );
        grid.add( fileChooserPanel, 0, 1 );
        grid.add( maskLabel, 1, 0 );
        grid.add( maskField, 1, 1 );

        fileChooserPanel.getFileChooser().setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );

        panel.add( splitPane, BorderLayout.CENTER );

        splitPane.setOneTouchExpandable( true );
        splitPane.setLeftComponent( new JScrollPane( successTable ) );
        splitPane.setRightComponent( new JScrollPane( failTable ) );
        splitPane.setResizeWeight( 0.5 );

        successTable.getColumn( 1 ).setMaxWidth( 40 );
        failTable.getColumn( 1 ).setMaxWidth( 40 );

        progressBar.setIndeterminate( true );
        progressBar.setVisible( false );
        panel.add( progressBar, BorderLayout.SOUTH );

        final List<String> maskList = new ArrayList<String>();
        maskList.add( Single.instance( Settings.class ).IMPORT_MASK.getValue() );
        maskField.setValues( maskList );
    }

    @Override
    protected void defaults()
    {
        final File file = fileChooserPanel.getFile();
        if ( file == null || !file.exists() )
        {
            final String path = file != null
                ? file.getAbsolutePath() : "";
            JOptionPane.showMessageDialog( this, I18N.tr( "File not found: " ) + path, "", JOptionPane.ERROR_MESSAGE );
            return;
        }

        final Collection<String> masks = maskField.getValues();
        if ( masks.size() == 0 )
        {
            JOptionPane.showMessageDialog( this, I18N.tr( "Specify at least one mask" ), "", JOptionPane.ERROR_MESSAGE );
            return;
        }

        progressBar.setVisible( true );
        successModel.setBooks( new ArrayList<Book>() );
        failModel.setFiles( new ArrayList<File>() );
        importer.importFiles( masks.toArray( new String[masks.size()] ), file );
        progressBar.setVisible( false );
    }

}
