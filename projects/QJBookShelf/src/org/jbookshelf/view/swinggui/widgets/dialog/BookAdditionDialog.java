package org.jbookshelf.view.swinggui.widgets.dialog;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.border.TitledBorder;

import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.BookShelf;
import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.logic.Parameters;
import org.jbookshelf.view.logic.Translatable;
import org.jbookshelf.view.logic.Translator;
import org.jbookshelf.view.swinggui.widgets.panel.BookPanel;
import org.jbookshelf.view.swinggui.widgets.panel.CollectionPanel;
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

    @Override
    public boolean apply()
    {
        final Parameters parameters = bookPanel.extractParameters();
        if ( parameters != null )
        {
            final Book book = BookPanel.changeBook( new Book(), parameters );
            BookShelf.persistBook( book );
            bookPanel.clear();
            Single.instance( CollectionPanel.class ).updateActiveView();

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
