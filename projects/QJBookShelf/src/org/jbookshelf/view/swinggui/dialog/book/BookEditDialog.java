/**
 * 
 */
package org.jbookshelf.view.swinggui.dialog.book;

import javax.swing.Action;
import javax.swing.JDialog;
import javax.swing.border.TitledBorder;

import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.BookShelf;
import org.jbookshelf.model.db.api.spec.IBook;
import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.i18n.Translatable;
import org.jbookshelf.view.logic.Parameters;
import org.jbookshelf.view.swinggui.collection.CollectionPanel;
import org.jbookshelf.view.swinggui.main.MainWindow;
import org.xnap.commons.gui.DefaultDialog;
import org.xnap.commons.i18n.I18n;

/**
 * a dialog to edit existing {@link IBook}s
 * 
 * @author eav 2009
 */
public class BookEditDialog
    extends DefaultDialog
    implements
    Translatable
{
    private final BookPanel bookPanel = new BookPanel();
    private final IBook     book;

    public BookEditDialog(
        final IBook book )
    {
        super( Single.instance( MainWindow.class ), BUTTON_OKAY | BUTTON_CANCEL );
        this.book = book;
        init();
    }

    public BookEditDialog(
        final JDialog dialog,
        final IBook book )
    {
        super( dialog, BUTTON_OKAY | BUTTON_CANCEL );
        this.book = book;
        init();
    }

    @Override
    public boolean apply()
    {
        final Parameters parameters = bookPanel.extractParameters();
        if ( parameters != null )
        {
            if ( book.getId() == null )
            { // let the FileImportDialog deal with result  
                BookPanel.changeBook( book, parameters );
            }
            else
            { // general addition, merge a book
                BookPanel.changeBook( book, parameters );
                BookShelf.mergeBook( book );
                // todo if BookView is active update only one row 
                Single.instance( CollectionPanel.class ).updateActiveView();
            }
            return true;
        }
        return false;
    }

    @Override
    public void translate(
        final I18n i18n )
    {
        setTitle( i18n.tr( "Edit book" ) );
        bookPanel.setBorder( new TitledBorder( i18n.tr( "Edit book properties" ) ) );
        getCancelAction().putValue( Action.NAME, i18n.tr( "Cancel" ) );
    }

    private void init()
    {
        bookPanel.setBook( book );

        I18N.translate( this );

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
