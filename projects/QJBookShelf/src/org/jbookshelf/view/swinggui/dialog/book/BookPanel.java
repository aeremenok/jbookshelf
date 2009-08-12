package org.jbookshelf.view.swinggui.dialog.book;

import java.io.File;
import java.util.List;

import javax.annotation.Nonnull;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.apache.commons.io.FilenameUtils;
import org.jbookshelf.controller.importer.FileImporter;
import org.jbookshelf.controller.settings.Settings;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.Author;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.Category;
import org.jbookshelf.model.db.PhysicalBook;
import org.jbookshelf.model.db.util.BookShelf;
import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.i18n.Translatable;
import org.jbookshelf.view.logic.Parameters;
import org.jbookshelf.view.logic.Parameters.Keys;
import org.jbookshelf.view.swinggui.multiedit.CategoryMultipleField;
import org.jbookshelf.view.swinggui.multiedit.MultipleField;
import org.jbookshelf.view.swinggui.multiedit.MultipleUniqueField;
import org.jbookshelf.view.swinggui.widget.FileChooserPanelExt;
import org.jbookshelf.view.swinggui.widget.GridBagPanel;
import org.xnap.commons.gui.FileChooserPanel;
import org.xnap.commons.i18n.I18n;

/**
 * a panel to display book data
 * 
 * @author eav 2009
 */
public class BookPanel
    extends GridBagPanel
    implements
    Translatable
{
    public static Book changeBook(
        final Book book,
        final Parameters parameters )
    {
        book.getAuthors().clear();
        final List<Author> authors = parameters.get( Keys.BOOK_AUTHORS );
        for ( final Author author : authors )
        {
            book.getAuthors().add( author );
        }

        book.getCategories().clear();
        final List<Category> categories = parameters.get( Keys.BOOK_CATEGORIES );
        for ( final Category category : categories )
        {
            book.getCategories().add( category );
        }

        book.setName( parameters.<String> get( Keys.BOOK_NAME ) );
        book.setRead( parameters.<Boolean> get( Keys.BOOK_IS_READ )
            ? 1f : 0f );

        final File file = parameters.get( Keys.BOOK_FILE );
        final PhysicalBook physical;
        if ( book.getPhysicalBook() == null )
        {
            physical = FileImporter.createPhysicalBook( file );
            book.setPhysicalBook( physical );
        }
        else
        {
            physical = book.getPhysicalBook();
            physical.setFile( file );
        }
        physical.setViewer( parameters.<String> get( Keys.BOOK_VIEWER ) );

        return book;
    }

    private final JLabel                  bookLabel        = new JLabel();
    private final JLabel                  authorLabel      = new JLabel();
    private final JLabel                  categoryLabel    = new JLabel();
    private final JLabel                  viewerLabel      = new JLabel();
    private final JLabel                  fileLabel        = new JLabel();

    private final JLabel                  typeField        = new JLabel();
    private final JLabel                  sizeField        = new JLabel();

    private final JTextField              bookTextField    = new JTextField( 50 );
    private final JCheckBox               isReadCheckBox   = new JCheckBox();
    private final JComboBox               viewerComboBox   = new JComboBox();

    private final MultipleField<Author>   authorField      = new MultipleUniqueField<Author>( Author.class );
    private final MultipleField<Category> categoryField    = new CategoryMultipleField();

    private final FileChooserPanel        fileChooserPanel = new FileChooserPanelExt( 50, "book.file.chooser" )
                                                           {
                                                               @Override
                                                               public void setFile(
                                                                   final File file )
                                                               {
                                                                   super.setFile( file );
                                                                   onSetFile( file );
                                                               }

                                                               @Override
                                                               protected void fileSelected(
                                                                   final File file )
                                                               {
                                                                   super.fileSelected( file );
                                                                   onFileSelected( file );
                                                               }
                                                           };

    private final FileImporter            fileImporter     = new FileImporter()
                                                           {
                                                               @Override
                                                               protected void onImportFailure(
                                                                   final File file,
                                                                   final Exception e )
                                                               {
                                                               // let user enters the data
                                                               }

                                                               @Override
                                                               protected void onImportSuccess(
                                                                   final Book book )
                                                               {
                                                                   setBook( book );
                                                               }
                                                           };

    public BookPanel()
    {
        super();
        I18N.translate( this );
        initComponents();
    }

    public void clear()
    {
        bookTextField.setText( "" );
        authorField.clear();
        categoryField.clear();
        fileChooserPanel.setFile( null );
        isReadCheckBox.setSelected( false );
    }

    @Nonnull
    public Parameters extractParameters()
    {
        final String bookName = bookTextField.getText();
        if ( bookName.equals( "" ) )
        {
            final String tr = I18N.tr( "Book name not specified" );
            JOptionPane.showMessageDialog( this, tr, I18N.tr( "Error" ), JOptionPane.ERROR_MESSAGE );
            return null;
        }

        final File file = fileChooserPanel.getFile();
        if ( file == null || !file.exists() )
        {
            final String tr = I18N.tr( "File does not exist: " ) + fileChooserPanel.getTextField().getText();
            JOptionPane.showMessageDialog( this, tr, I18N.tr( "Error" ), JOptionPane.ERROR_MESSAGE );
            return null;
        }

        final int viewerIndex = viewerComboBox.getSelectedIndex();
        final String viewer = viewerIndex == 0
            ? null : viewerIndex == 1
                ? PhysicalBook.INTERNAL_VIEWER : PhysicalBook.SYSTEM_VIEWER;

        final Parameters parameters = new Parameters();

        parameters.put( Keys.BOOK_NAME, bookName );
        parameters.put( Keys.BOOK_AUTHORS, authorField.getValues() );
        parameters.put( Keys.BOOK_CATEGORIES, categoryField.getValues() );
        parameters.put( Keys.BOOK_IS_READ, isReadCheckBox.isSelected() );
        parameters.put( Keys.BOOK_VIEWER, viewer );
        parameters.put( Keys.BOOK_FILE, file );

        return parameters;
    }

    /**
     * show book properties on panel
     * 
     * @param book a book to edit
     */
    public void setBook(
        final Book book )
    {
        // display book name
        bookTextField.setText( book.getName() );
        authorField.setValues( BookShelf.getAuthors( book ) );
        categoryField.setValues( BookShelf.getCategories( book ) );

        // display file of the physical unit
        fileChooserPanel.setFile( book.getPhysicalBook().getFile() );

        // display whether is read
        isReadCheckBox.setSelected( book.getRead() == 1 );

        // display viewer
        final String viewer = book.getPhysicalBook().getViewer();
        final int index = viewer == null
            ? 0 : PhysicalBook.INTERNAL_VIEWER.equals( viewer )
                ? 1 : 2;
        viewerComboBox.setSelectedIndex( index );
    }

    /**
     * @param file file to start addition with
     */
    public void setFile(
        final File file )
    {
        fileChooserPanel.setFile( file );
    }

    public void translate(
        final I18n i18n )
    {
        isReadCheckBox.setText( i18n.tr( "Is read" ) );
        bookLabel.setText( i18n.tr( "Book" ) );
        authorLabel.setText( i18n.tr( "Author" ) );
        categoryLabel.setText( i18n.tr( "Category" ) );
        fileLabel.setText( i18n.tr( "File" ) );
        viewerLabel.setText( i18n.tr( "Viewer" ) );

        viewerComboBox.removeAllItems();
        viewerComboBox.addItem( i18n.tr( "Auto" ) );
        viewerComboBox.addItem( i18n.tr( "Internal" ) );
        viewerComboBox.addItem( i18n.tr( "System" ) );
    }

    private void initComponents()
    {
        add( bookLabel, 0, 0 );
        add( bookTextField, 0, 1, 1, 2 );

        add( authorLabel, 1, 0 );
        add( authorField, 1, 1, 1, 2 );

        add( categoryLabel, 2, 0 );
        add( categoryField, 2, 1, 1, 2 );

        add( viewerLabel, 3, 0 );
        add( viewerComboBox, 3, 1, 1, 2 );

        add( isReadCheckBox, 4, 1, 1, 2 );

        add( fileLabel, 5, 0 );
        add( fileChooserPanel, 5, 1, 1, 2 );

        add( sizeField, 6, 1 );
        add( typeField, 6, 2 );

        fileChooserPanel.getFileChooser().setFileSelectionMode( JFileChooser.FILES_AND_DIRECTORIES );

        // todo book name completion
    }

    protected void onFileSelected(
        @Nonnull final File file )
    {
        if ( bookTextField.getText().equals( "" ) )
        {
            fileImporter.importFiles( Single.instance( Settings.class ).IMPORT_MASKS.getValue(), file );
        }
    }

    protected void onSetFile(
        final File file )
    {
        final String size = I18N.tr( "Size" ) + ": ";
        final String type = I18N.tr( "Type" ) + ": ";
        if ( file != null && file.isFile() )
        {
            sizeField.setText( size + file.length() / 1024 + I18N.tr( " kb" ) );
            typeField.setText( type + FilenameUtils.getExtension( file.getName() ).toUpperCase() );
        }
        else
        {
            sizeField.setText( size );
            typeField.setText( type );
        }
    }
}
