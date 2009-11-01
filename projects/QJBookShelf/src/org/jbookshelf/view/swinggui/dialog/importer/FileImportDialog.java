/**
 * 
 */
package org.jbookshelf.view.swinggui.dialog.importer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.TitledBorder;

import org.jbookshelf.controller.importer.FileImporter;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.BookShelf;
import org.jbookshelf.model.db.api.spec.IBook;
import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.i18n.Translatable;
import org.jbookshelf.view.logic.Parameters;
import org.jbookshelf.view.swinggui.ProgressBar;
import org.jbookshelf.view.swinggui.collection.CollectionPanel;
import org.jbookshelf.view.swinggui.dialog.book.BookAdditionDialog;
import org.jbookshelf.view.swinggui.dialog.book.BookEditDialog;
import org.jbookshelf.view.swinggui.main.MainWindow;
import org.jdesktop.swingx.JXTable;
import org.xnap.commons.gui.DefaultDialog;
import org.xnap.commons.i18n.I18n;

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
    private final ImportParameterPanel parameterPanel = new ImportParameterPanel();

    private final JSplitPane           splitPane      = new JSplitPane();

    private final SuccessTableModel    successModel   = new SuccessTableModel();
    private final FailTableModel       failModel      = new FailTableModel();
    /**
     * displays successfully imported books
     */
    private final JXTable              successTable   = new JXTable( successModel );
    /**
     * displays unimported files
     */
    private final JXTable              failTable      = new JXTable( failModel );

    /**
     * performs import
     */
    private final FileImporter         importer       = new FileImporter()
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
                                                              final IBook book )
                                                          {
                                                              successModel.addBook( book );
                                                          }
                                                      };

    /**
     * shown while import goes on
     */
    private final ProgressBar          progressBar    = new ProgressBar();

    public FileImportDialog()
    {
        super( Single.instance( MainWindow.class ), BUTTON_OKAY | BUTTON_CLOSE | BUTTON_DEFAULTS );

        initComponents();
        initListeners();
        I18N.translate( this );

        setPreferredSize( new Dimension( 1024, 768 ) );

        pack();
        setLocationRelativeTo( null );
    }

    @Override
    public boolean apply()
    {
        final List<IBook> successBooks = successModel.getBooks();
        // close dialog immediately
        // general progressbar will do this job in a new thread 
        Single.instance( ProgressBar.class ).invoke( new Runnable()
        {
            @Override
            public void run()
            {
                // persist all successfully imported books
                while ( successBooks.size() > 0 )
                {
                    final IBook book = successBooks.get( 0 );
                    BookShelf.persistBook( book );
                    // surrender a book to GC
                    successBooks.remove( book );
                }
                // refresh collection view
                Single.instance( CollectionPanel.class ).updateActiveView();
            }
        } );
        return true;
    }

    @Override
    public void close()
    {
        parameterPanel.onClose();
        super.close();
    }

    @Override
    public void translate(
        final I18n i18n )
    {
        setTitle( i18n.tr( "Import Files" ) );
        getCloseAction().putValue( Action.NAME, i18n.tr( "Close" ) );
        getDefaultsAction().putValue( Action.NAME, i18n.tr( "Import" ) );

        final String leftTitle = i18n.tr( "Successfully imported books" ) + " (" + successModel.getRowCount() + ") ";
        final TitledBorder leftBorder = new TitledBorder( leftTitle );
        ((JComponent) splitPane.getLeftComponent()).setBorder( leftBorder );

        final String rightTitle = i18n.tr( "Unimported files" ) + " (" + failModel.getRowCount() + ") ";
        final TitledBorder rightBorder = new TitledBorder( rightTitle );
        ((JComponent) splitPane.getRightComponent()).setBorder( rightBorder );
    }

    private void initComponents()
    {
        setModal( true );
        setButtonSeparatorVisible( false );

        final JPanel panel = new JPanel( new BorderLayout() );
        setMainComponent( panel );

        // add directory chooser and mask field
        panel.add( parameterPanel, BorderLayout.NORTH );

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
                    {
                        final int row = successTable.rowAtPoint( e.getPoint() );
                        final IBook book = successModel.getBooks().get( row );

                        new BookEditDialog( FileImportDialog.this, book ).setVisible( true );
                    }
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

                        final IBook result = dialog.getResult();
                        if ( result != null )
                        {
                            successModel.addBook( result );
                            failModel.removeFile( file );
                            translate( null );
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
        final Parameters parameters = parameterPanel.getParameters();
        if ( parameters != null )
        {
            // preconditions are fine, run the import
            progressBar.invoke( new Runnable()
            {
                @Override
                public void run()
                {
                    successModel.setBooks( new ArrayList<IBook>() );
                    failModel.setFiles( new LinkedList<File>() );
                    importer.importFiles( parameters );
                    translate( I18N.i18n() );
                }
            } );
        }
    }
}
