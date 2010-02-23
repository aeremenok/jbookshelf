package org.jbookshelf.view.swinggui.dialog.book;

import java.io.File;


import javax.swing.Action;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.border.TitledBorder;

import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.util.BookShelf;
import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.i18n.Translatable;
import org.jbookshelf.view.logic.Parameters;
import org.jbookshelf.view.swinggui.collection.CollectionPanel;
import org.jbookshelf.view.swinggui.dialog.importer.FileImportDialog;
import org.jbookshelf.view.swinggui.main.MainWindow;
import org.xnap.commons.gui.DefaultDialog;
import org.xnap.commons.i18n.I18n;

/**
 * a dialog to create new {@link Book}s
 * 
 * @author eav 2009
 */
public class BookAdditionDialog
    extends DefaultDialog
    implements
    Translatable
{
    private final BookPanel bookPanel = new BookPanel();

    /**
     * created book
     */
    
    private Book            result;

    public BookAdditionDialog()
    {
        super( Single.instance( MainWindow.class ), BUTTON_OKAY | BUTTON_APPLY | BUTTON_CANCEL );
        init();
    }

    public BookAdditionDialog(
        final JDialog parent,
        final File file )
    {
        super( parent, BUTTON_OKAY | BUTTON_CANCEL );
        init();
        bookPanel.setFile( file );
    }

    public BookAdditionDialog(
        final JFrame parent,
        final File file )
    {
        super( parent, BUTTON_OKAY | BUTTON_CANCEL );
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

    
    public Book getResult()
    {
        return this.result;
    }

    public void translate(
        final I18n i18n )
    {
        setTitle( i18n.tr( "Add Book" ) );
        getOkayAction().putValue( Action.NAME, i18n.tr( "Add and close" ) );
        getApplyAction().putValue( Action.NAME, i18n.tr( "Add and continue" ) );
        getCancelAction().putValue( Action.NAME, i18n.tr( "Close" ) );
        bookPanel.setBorder( new TitledBorder( i18n.tr( "Add book to collection" ) ) );
    }

    private void init()
    {
        setName( "BookAdditionDialog" );
        I18N.translate( this );

        setModal( true );
        setMainComponent( bookPanel );
        setButtonSeparatorVisible( false );

        pack();
        setLocationByPlatform( true );
    }

    @Override
    protected void cancelled()
    {
        close();
    }
}
