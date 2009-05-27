package org.jbookshelf.view.swinggui.widgets.dialog;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.border.TitledBorder;

import org.jbookshelf.controller.importer.FileImporter;
import org.jbookshelf.model.db.PhysicalBook;
import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.logic.Translatable;
import org.jbookshelf.view.logic.Translator;
import org.jbookshelf.view.swinggui.widgets.panel.BookPanel;
import org.jbookshelf.view.swinggui.widgets.panel.BookParameters;
import org.xnap.commons.gui.DefaultDialog;

public class BookAdditionDialog
    extends DefaultDialog
    implements
    Translatable
{
    private final BookPanel bookPanel = new BookPanel();

    public BookAdditionDialog(
        final JFrame owner )
    {
        super( owner, BUTTON_OKAY | BUTTON_APPLY | BUTTON_CANCEL );
        Translator.addTranslatable( this );
        initComponents();

        pack();
        setLocationRelativeTo( null );
    }

    public void addBookToCollection(
        final BookParameters parameters )
    {
        final PhysicalBook physicalUnit = FileImporter.createPhysicalUnit( parameters.getFile() );
        //        final Book book = Storage.getBookShelf().addBook( parameters.getBookName(), null, null, physicalUnit );
        //        BookPanel.changeBook( book, parameters );
        //
        //        Single.instance( CollectionPanel.class ).updateTree();
    }

    @Override
    public boolean apply()
    {
        final BookParameters parameters = bookPanel.extractParameters();
        if ( parameters != null )
        {
            addBookToCollection( parameters );
            bookPanel.clear();
            return true;
        }
        return false;
    }

    public void retranslate()
    {
        setTitle( I18N.tr( "Add Book" ) );
        getOkayAction().putValue( Action.NAME, I18N.tr( "Add and close" ) );
        getApplyAction().putValue( Action.NAME, I18N.tr( "Add and continue" ) );
        getCancelAction().putValue( Action.NAME, I18N.tr( "Close" ) );
        bookPanel.setBorder( new TitledBorder( I18N.tr( "Add book to collection" ) ) );
    }

    private void initComponents()
    {
        setModal( true );
        setMainComponent( bookPanel );
        setButtonSeparatorVisible( false );
    }

    @Override
    protected void cancelled()
    {
        close();
    }
}
