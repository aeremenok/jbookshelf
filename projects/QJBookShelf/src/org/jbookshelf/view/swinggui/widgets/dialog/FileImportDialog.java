/**
 * 
 */
package org.jbookshelf.view.swinggui.widgets.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
import org.jbookshelf.view.swinggui.widgets.ProgressBar;
import org.jbookshelf.view.swinggui.widgets.panel.CollectionPanel;
import org.jbookshelf.view.swinggui.widgets.panel.GridBagPanel;
import org.jdesktop.swingx.JXTable;
import org.xnap.commons.gui.DefaultDialog;

/**
 * a dialog for importing collection from filesystem
 * 
 * @author eav 2009
 */
public class FileImportDialog
    extends DefaultDialog
    implements
    Translatable
{
    private final JLabel                fileLabel        = new JLabel();
    /**
     * root directory of filesystem
     */
    private final FileChooserPanelExt   fileChooserPanel = new FileChooserPanelExt( 50, "import.dir.chooser" );

    private final JLabel                maskLabel        = new JLabel();
    /**
     * masks to parse filenames
     */
    private final MultipleField<String> maskField        = new MultipleField<String>();

    private final JSplitPane            splitPane        = new JSplitPane();

    private final SuccessTableModel     successModel     = new SuccessTableModel();
    private final FailTableModel        failModel        = new FailTableModel();
    /**
     * displays successfully imported books
     */
    private final JXTable               successTable     = new JXTable( successModel );
    /**
     * displays unimported files
     */
    private final JXTable               failTable        = new JXTable( failModel );

    /**
     * performs import
     */
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

    /**
     * shown while import goes on
     */
    private final ProgressBar           progressBar      = new ProgressBar();

    public FileImportDialog()
    {
        super( Single.instance( MainWindow.class ), BUTTON_OKAY | BUTTON_CLOSE | BUTTON_DEFAULTS );

        initComponents();
        initListeners();
        Translator.addTranslatable( this );

        setPreferredSize( new Dimension( 1024, 768 ) );

        pack();
        setLocationRelativeTo( null );
    }

    @Override
    public boolean apply()
    {
        // persist all successfully imported books
        for ( final Book book : successModel.getBooks() )
        {
            BookShelf.persistBook( book );
        }
        // show them
        Single.instance( CollectionPanel.class ).updateActiveView();
        return true;
    }

    @Override
    public void close()
    {
        // save the masks and the chosen directory
        final Settings settings = Single.instance( Settings.class );

        final String[] masks = maskField.getValues().toArray( new String[maskField.getValues().size()] );
        settings.IMPORT_MASKS.setValue( masks );

        settings.save();
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
        setModal( true );
        setButtonSeparatorVisible( false );

        final JPanel panel = new JPanel( new BorderLayout() );
        setMainComponent( panel );

        // add directory chooser and mask field
        final GridBagPanel grid = new GridBagPanel();
        panel.add( grid, BorderLayout.NORTH );

        grid.add( fileLabel, 0, 0 );
        grid.add( fileChooserPanel, 0, 1 );
        grid.add( maskLabel, 1, 0 );
        grid.add( maskField, 1, 1 );

        fileChooserPanel.getFileChooser().setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );

        // add two tables
        panel.add( splitPane, BorderLayout.CENTER );

        splitPane.setOneTouchExpandable( true );
        splitPane.setLeftComponent( new JScrollPane( successTable ) );
        splitPane.setRightComponent( new JScrollPane( failTable ) );
        splitPane.setResizeWeight( 0.5 );

        successTable.getColumn( 1 ).setMaxWidth( 40 );
        failTable.getColumn( 1 ).setMaxWidth( 40 );

        // add progressbar
        panel.add( progressBar, BorderLayout.SOUTH );

        // fill masks from settings
        maskField.setValues( Arrays.asList( Single.instance( Settings.class ).IMPORT_MASKS.getValue() ) );
    }

    private void initListeners()
    {
        successTable.addMouseListener( new MouseAdapter()
        {
            @Override
            public void mouseClicked(
                final MouseEvent e )
            {
                if ( e.getButton() == MouseEvent.BUTTON1 )
                {
                    final int col = successTable.columnAtPoint( e.getPoint() );
                    if ( col == 1 || e.getClickCount() == 2 )
                    {}
                }
            }
        } );
        failTable.addMouseListener( new MouseAdapter()
        {
            @Override
            public void mouseClicked(
                final MouseEvent e )
            {
                if ( e.getButton() == MouseEvent.BUTTON1 )
                {
                    final int col = failTable.columnAtPoint( e.getPoint() );
                    if ( col == 1 || e.getClickCount() == 2 )
                    {
                        final int row = failTable.rowAtPoint( e.getPoint() );
                        final File file = failModel.getFiles().get( row );

                        final BookAdditionDialog dialog = new BookAdditionDialog( FileImportDialog.this, file );
                        dialog.setVisible( true );

                        final Book result = dialog.getResult();
                        if ( result != null )
                        {
                            successModel.addBook( result );
                            failModel.removeFile( file );
                        }
                    }
                }
            }
        } );
    }

    @Override
    protected void defaults()
    {
        // "Import" button pressed

        // check preconditions
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

        // preconditions are fine, run the import
        progressBar.invoke( new Runnable()
        {
            @Override
            public void run()
            {
                successModel.setBooks( new ArrayList<Book>() );
                failModel.setFiles( new ArrayList<File>() );
                importer.importFiles( masks.toArray( new String[masks.size()] ), file );
            }
        } );
    }

}
