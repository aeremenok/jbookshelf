package org.jbookshelf.swinggui.widgets.panel;

import java.io.File;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.jbookshelf.controller.importer.FileImporter;
import org.jbookshelf.controller.singleton.Singletons;
import org.jbookshelf.controller.storage.Storage;
import org.jbookshelf.i18n.I18N;
import org.jbookshelf.model.Author;
import org.jbookshelf.model.Book;
import org.jbookshelf.model.BookShelf;
import org.jbookshelf.model.Category;
import org.jbookshelf.model.PhysicalUnit;
import org.jbookshelf.model.Unique;
import org.jbookshelf.qtgui.logic.Translatable;
import org.jbookshelf.qtgui.logic.Translator;
import org.jbookshelf.qtgui.widgets.completion.CompletionDictionary;
import org.jbookshelf.settings.Settings;
import org.jbookshelf.swinggui.widgets.FileChooserPanelExt;
import org.jbookshelf.swinggui.widgets.MultipleField;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.autocomplete.ObjectToStringConverter;
import org.xnap.commons.gui.Builder;
import org.xnap.commons.gui.FileChooserPanel;
import org.xnap.commons.gui.completion.DefaultCompletionModel;

public class BookPanel
    extends GridBagPanel
    implements
        Translatable
{
    private static class UniqueToStringConverter
        extends ObjectToStringConverter
    {
        @Override
        public String getPreferredStringForItem(
            final Object item )
        {
            if ( item instanceof Unique )
            {
                return ((Unique) item).getName();
            }
            return item != null ? item.toString() : null;
        }
    }

    private final static ObjectToStringConverter CONVERTER        = new UniqueToStringConverter();

    private final JLabel                         bookLabel        = new JLabel();
    private final JLabel                         authorLabel      = new JLabel();
    private final JLabel                         categoryLabel    = new JLabel();
    private final JLabel                         viewerLabel      = new JLabel();

    private final JLabel                         fileLabel        = new JLabel();
    private final JTextField                     bookTextField    = new JTextField( 50 );
    private final JCheckBox                      isReadCheckBox   = new JCheckBox();

    private final JComboBox                      viewerComboBox   = new JComboBox();

    private final MultipleField                  authorField      = new MultipleField( 50 );
    private final MultipleField                  categoryField    = new MultipleField( 50 );

    private final FileChooserPanel               fileChooserPanel = new FileChooserPanelExt( 50 )
                                                                  {
                                                                      @Override
                                                                      protected void fileSelected(
                                                                          final File file )
                                                                      {
                                                                          super.fileSelected( file );
                                                                          onFileSelected( file );
                                                                      }
                                                                  };

    private final FileImporter                   fileImporter     = new FileImporter()
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

    public static Book changeBook(
        final Book book,
        final BookParameters parameters )
    {
        // todo reflective
        book.getAuthors().clear();
        for ( final String name : parameters.getAuthorNames() )
        {
            Author author;
            final String trim = name.trim();
            final List<Author> authors = Storage.getBookShelf().queryAuthors( trim );
            if ( authors.size() > 0 )
            { // todo what if we've found more than 1 author with equal names?
                author = authors.get( 0 );
            }
            else
            {
                author = Storage.getBookShelf().addAuthor( trim );
            }
            book.getAuthors().add( author );
        }

        book.getCategories().clear();
        for ( final String name : parameters.getCategoryNames() )
        {
            Category category;
            final String trim = name.trim();
            final List<Category> authors = Storage.getBookShelf().queryCategories( trim );
            if ( authors.size() > 0 )
            { // todo what if we've found more than 1 author with equal names?
                category = authors.get( 0 );
            }
            else
            {
                category = Storage.getBookShelf().addCategory( trim );
            }
            book.getCategories().add( category );
        }

        book.setName( parameters.getBookName() );

        PhysicalUnit physical = book.getPhysical();
        if ( physical == null || !physical.getFile().equals( parameters.getFile() ) )
        {
            physical = FileImporter.createPhysicalUnit( parameters.getFile() );
        }
        physical.setViewer( parameters.getViewer() );

        book.setPhysical( physical );
        book.setRead( parameters.isRead() ? 1 : 0 );

        return book;
    }

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

        final String authorNames = authorField.getText();
        if ( authorNames.equals( "" ) )
        {
            final String tr = I18N.tr( "Author name not specified" );
            JOptionPane.showMessageDialog( this, tr, title, JOptionPane.ERROR_MESSAGE );
            return null;
        }

        final String categoryNames = categoryField.getText();
        if ( categoryNames.equals( "" ) )
        {
            final String tr = I18N.tr( "Category name not specified" );
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
        final String viewer =
            viewerIndex == 0 ? null : viewerIndex == 1 ? PhysicalUnit.INTERNAL_VIEWER : PhysicalUnit.SYSTEM_VIEWER;
        return new BookParameters( bookName, authorNames.split( "," ), categoryNames.split( "," ), file, isRead, viewer );
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
        authorField.setValue( book.getAuthors() );
        categoryField.setValue( book.getCategories() );

        // display file of the physical unit
        fileChooserPanel.setFile( book.getPhysical().getFile() );

        // display whether is read
        isReadCheckBox.setSelected( book.getRead() == 1 );

        // display viewer
        final String viewer = book.getPhysical().getViewer();
        final int index = viewer == null ? 0 : PhysicalUnit.INTERNAL_VIEWER.equals( viewer ) ? 1 : 2;
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

        // todo better completion
        final BookShelf bookShelf = Storage.getBookShelf();
        final CompletionDictionary dictionary = Singletons.instance( CompletionDictionary.class );
        Builder.addCompletion( bookTextField, new DefaultCompletionModel( dictionary.getCompletionArray() ) );

        AutoCompleteDecorator.decorate( authorField, bookShelf.getAuthors(), false, CONVERTER );
        AutoCompleteDecorator.decorate( categoryField, bookShelf.getCategories(), false, CONVERTER );
    }

    protected void onFileSelected(
        final File file )
    {
        final String mask = Singletons.instance( Settings.class ).IMPORT_MASK.getValue();
        final String[] masks = mask.split( "/" );
        fileImporter.importFiles( masks, Storage.getBookShelf(), file );
    }
}
