package org.jbookshelf.swinggui.widgets.panel;

import images.IMG;

import javax.swing.Action;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.jbookshelf.controller.singleton.Singletons;
import org.jbookshelf.controller.storage.Storage;
import org.jbookshelf.i18n.I18N;
import org.jbookshelf.model.Book;
import org.jbookshelf.model.BookShelf;
import org.jbookshelf.qtgui.logic.Translatable;
import org.jbookshelf.qtgui.logic.Translator;
import org.jbookshelf.qtgui.widgets.completion.CompletionDictionary;
import org.jbookshelf.swinggui.widgets.MultipleField;
import org.xnap.commons.gui.Builder;
import org.xnap.commons.gui.FileChooserPanel;
import org.xnap.commons.gui.completion.DefaultCompletionModel;

public class BookPanel
    extends GridBagPanel
    implements
        Translatable
{
    private final JLabel           bookLabel        = new JLabel();
    private final JLabel           authorLabel      = new JLabel();
    private final JLabel           categoryLabel    = new JLabel();
    private final JLabel           viewerLabel      = new JLabel();
    private final JLabel           fileLabel        = new JLabel();

    private final JTextField       bookTextField    = new JTextField( 50 );
    private final JCheckBox        isReadCheckBox   = new JCheckBox();
    private final JComboBox        viewerComboBox   = new JComboBox();

    private final MultipleField    authorField      = new MultipleField( 50 );
    private final MultipleField    categoryField    = new MultipleField( 50 );

    private final FileChooserPanel fileChooserPanel = new FileChooserPanel( 50 );

    public static void changeBook(
        final Book book,
        final BookParameters parameters )
    {
        // TODO Auto-generated method stub

    }

    public BookPanel()
    {
        super();
        Translator.addTranslatable( this );
        initComponents();
    }

    public void clear(
        final boolean b )
    {
        // TODO Auto-generated method stub
    }

    public BookParameters extractParameters()
    {
        // TODO Auto-generated method stub
        return null;
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

        // adding autocompletion
        final BookShelf bookShelf = Storage.getBookShelf();
        final CompletionDictionary dictionary = Singletons.instance( CompletionDictionary.class );
        Builder.addCompletion( bookTextField, new DefaultCompletionModel( dictionary.getCompletions().toArray() ) );

        Builder.addCompletion( authorField, new DefaultCompletionModel( bookShelf.getAuthors().toArray() ) );
        Builder.addCompletion( categoryField, new DefaultCompletionModel( bookShelf.getCategories().toArray() ) );

        // todo smaller icon
        fileChooserPanel.getFileChooserAction().putValue( Action.SMALL_ICON, IMG.icon( IMG.DOCUMENT_OPEN_FOLDER_PNG ) );
    }
}
