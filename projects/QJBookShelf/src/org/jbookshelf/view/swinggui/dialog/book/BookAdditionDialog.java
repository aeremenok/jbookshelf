package org.jbookshelf.view.swinggui.dialog.book;

import java.io.File;

import javax.swing.Action;
import javax.swing.border.TitledBorder;

import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.util.BookShelf;
import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.i18n.Translatable;
import org.jbookshelf.view.i18n.Translator;
import org.jbookshelf.view.logic.Parameters;
import org.jbookshelf.view.swinggui.collection.CollectionPanel;
import org.jbookshelf.view.swinggui.dialog.importer.FileImportDialog;
import org.jbookshelf.view.swinggui.main.MainWindow;
import org.xnap.commons.gui.DefaultDialog;

import com.sun.istack.internal.Nullable;

public class BookAdditionDialog
    extends DefaultDialog
    implements
    Translatable
{
    private final BookPanel bookPanel = new BookPanel();

    /**
     * created book
     */
    @Nullable
    private Book            result;

    public BookAdditionDialog()
    {
        super( Single.instance( MainWindow.class ), BUTTON_OKAY | BUTTON_APPLY | BUTTON_CANCEL );
        init();
    }

    public BookAdditionDialog(
        final FileImportDialog dialog,
        final File file )
    {
        super( dialog, BUTTON_OKAY | BUTTON_CANCEL );
        init();
        bookPanel.setFile( file );
    }

    @Override
    public boolean apply()
    {
        final Parameters parameters = bookPanel.extractParameters();
        if ( parameters != null )
        {
            final Book book = BookPanel.changeBook( new Book(), parameters );
            if ( getParent() instanceof FileImportDialog )
            { // let the dialog deal with result
                result = book;
            }
            else
            { // general addition, persist a new book
                BookShelf.persistBook( book );
                bookPanel.clear();
                Single.instance( CollectionPanel.class ).updateActiveView();
            }
            return true;
        }
        return false;
    }

    /**
     * @return the result
     */
    @Nullable
    public Book getResult()
    {
        return this.result;
    }

    public void retranslate()
    {
        setTitle( I18N.tr( "Add Book" ) );
        getOkayAction().putValue( Action.NAME, I18N.tr( "Add and close" ) );
        getApplyAction().putValue( Action.NAME, I18N.tr( "Add and continue" ) );
        getCancelAction().putValue( Action.NAME, I18N.tr( "Close" ) );
        bookPanel.setBorder( new TitledBorder( I18N.tr( "Add book to collection" ) ) );
    }

    private void init()
    {
        Translator.addTranslatable( this );

        setModal( true );
        setMainComponent( bookPanel );
        setButtonSeparatorVisible( false );

        pack();
        setLocationRelativeTo( null );
    }

    @Override
    protected void cancelled()
    {
        close();
    }
}
