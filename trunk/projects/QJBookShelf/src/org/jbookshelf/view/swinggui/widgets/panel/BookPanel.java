package org.jbookshelf.view.swinggui.widgets.panel;

import java.io.File;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.jbookshelf.controller.importer.FileImporter;
import org.jbookshelf.model.db.Author;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.Category;
import org.jbookshelf.model.db.PhysicalBook;
import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.logic.Translatable;
import org.jbookshelf.view.logic.Translator;
import org.jbookshelf.view.swinggui.widgets.FileChooserPanelExt;
import org.jbookshelf.view.swinggui.widgets.MultipleField;
import org.xnap.commons.gui.FileChooserPanel;

public class BookPanel
    extends GridBagPanel
    implements
    Translatable
{
    public static Book changeBook(
        final Book book,
        final BookParameters parameters )
    {
        // todo reflective
        //        book.getAuthors().clear();
        //        for ( final String name : parameters.getAuthorNames() )
        //        {
        //            Author author;
        //            final String trim = name.trim();
        //            final List<Author> authors = Storage.getBookShelf().queryAuthors( trim );
        //            if ( authors.size() > 0 )
        //            { // todo what if we've found more than 1 author with equal names?
        //                author = authors.get( 0 );
        //            }
        //            else
        //            {
        //                author = Storage.getBookShelf().addAuthor( trim );
        //            }
        //            book.getAuthors().add( author );
        //        }
        //
        //        book.getCategories().clear();
        //        for ( final String name : parameters.getCategoryNames() )
        //        {
        //            Category category;
        //            final String trim = name.trim();
        //            final List<Category> authors = Storage.getBookShelf().queryCategories( trim );
        //            if ( authors.size() > 0 )
        //            { // todo what if we've found more than 1 author with equal names?
        //                category = authors.get( 0 );
        //            }
        //            else
        //            {
        //                category = Storage.getBookShelf().addCategory( trim );
        //            }
        //            book.getCategories().add( category );
        //        }
        //
        //        book.setName( parameters.getBookName() );
        //
        //        PhysicalUnit physical = book.getPhysical();
        //        if ( physical == null || !physical.getFile().equals( parameters.getFile() ) )
        //        {
        //            physical = FileImporter.createPhysicalUnit( parameters.getFile() );
        //        }
        //        physical.setViewer( parameters.getViewer() );
        //
        //        book.setPhysical( physical );
        //        book.setRead( parameters.isRead()
        //            ? 1 : 0 );

        return book;
    }

    private final JLabel                  bookLabel        = new JLabel();
    private final JLabel                  authorLabel      = new JLabel();
    private final JLabel                  categoryLabel    = new JLabel();

    private final JLabel                  viewerLabel      = new JLabel();
    private final JLabel                  fileLabel        = new JLabel();
    private final JTextField              bookTextField    = new JTextField( 50 );

    private final JCheckBox               isReadCheckBox   = new JCheckBox();

    private final JComboBox               viewerComboBox   = new JComboBox();

    private final MultipleField<Author>   authorField      = new MultipleField<Author>( Author.class );
    private final MultipleField<Category> categoryField    = new MultipleField<Category>( Category.class );

    private final FileChooserPanel        fileChooserPanel = new FileChooserPanelExt( 50 )
                                                           {
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
        Translator.addTranslatable( this );
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

    public BookParameters extractParameters()
    {
        final String bookName = bookTextField.getText();
        final String title = I18N.tr( "Error" );
        if ( bookName.equals( "" ) )
        {
            final String tr = I18N.tr( "Book name not specified" );
            JOptionPane.showMessageDialog( this, tr, title, JOptionPane.ERROR_MESSAGE );
            return null;
        }

        final File file = fileChooserPanel.getFile();
        if ( file == null || !file.exists() )
        {
            final String tr = I18N.tr( "File does not exist: " ) + fileChooserPanel.getTextField().getText();
            JOptionPane.showMessageDialog( this, tr, title, JOptionPane.ERROR_MESSAGE );
            return null;
        }

        final boolean isRead = isReadCheckBox.isSelected();

        final int viewerIndex = viewerComboBox.getSelectedIndex();
        final String viewer = viewerIndex == 0
            ? null : viewerIndex == 1
                ? PhysicalBook.INTERNAL_VIEWER : PhysicalBook.SYSTEM_VIEWER;

        return new BookParameters( bookName, authorField.getValues(), categoryField.getValues(), file, isRead, viewer );
    }

    public void retranslate()
    {
        isReadCheckBox.setText( I18N.tr( "Is read" ) );
        bookLabel.setText( I18N.tr( "Book" ) );
        authorLabel.setText( I18N.tr( "Author" ) );
        categoryLabel.setText( I18N.tr( "Category" ) );
        fileLabel.setText( I18N.tr( "File" ) );
        viewerLabel.setText( I18N.tr( "Viewer" ) );

        viewerComboBox.removeAllItems();
        viewerComboBox.addItem( I18N.tr( "Auto" ) );
        viewerComboBox.addItem( I18N.tr( "Internal" ) );
        viewerComboBox.addItem( I18N.tr( "System" ) );
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
        authorField.setValues( book.getAuthors() );
        categoryField.setValues( book.getCategories() );

        // display file of the physical unit
        fileChooserPanel.setFile( new File( book.getPhysicalBook().getFileName() ) );

        // display whether is read
        isReadCheckBox.setSelected( book.getRead() == 1 );

        // display viewer
        final String viewer = book.getPhysicalBook().getViewer();
        final int index = viewer == null
            ? 0 : PhysicalBook.INTERNAL_VIEWER.equals( viewer )
                ? 1 : 2;
        viewerComboBox.setSelectedIndex( index );
    }

    private void initComponents()
    {
        add( bookLabel, 0, 0 );
        add( bookTextField, 0, 1 );

        add( authorLabel, 1, 0 );
        add( authorField, 1, 1 );

        add( categoryLabel, 2, 0 );
        add( categoryField, 2, 1 );

        add( viewerLabel, 3, 0 );
        add( viewerComboBox, 3, 1 );

        add( fileLabel, 4, 0 );
        add( fileChooserPanel, 4, 1 );

        add( isReadCheckBox, 5, 1 );

        fileChooserPanel.getFileChooser().setFileSelectionMode( JFileChooser.FILES_AND_DIRECTORIES );

        // todo completion
    }

    protected void onFileSelected(
        final File file )
    {
    // todo
    //        final String mask = Single.instance( Settings.class ).IMPORT_MASK.getValue();
    //        final String[] masks = mask.split( "/" );
    //        fileImporter.importFiles( masks, Storage.getBookShelf(), file );
    }
}
