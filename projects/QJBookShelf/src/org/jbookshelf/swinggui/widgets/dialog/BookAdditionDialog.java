package org.jbookshelf.swinggui.widgets.dialog;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.jbookshelf.controller.importer.FileImporter;
import org.jbookshelf.controller.singleton.Singletons;
import org.jbookshelf.controller.storage.Storage;
import org.jbookshelf.i18n.I18N;
import org.jbookshelf.model.Book;
import org.jbookshelf.model.PhysicalUnit;
import org.jbookshelf.qtgui.logic.Translatable;
import org.jbookshelf.qtgui.logic.Translator;
import org.jbookshelf.qtgui.widgets.panel.CollectionPanel;
import org.jbookshelf.swinggui.widgets.StretchAction;
import org.jbookshelf.swinggui.widgets.panel.BookPanel;
import org.jbookshelf.swinggui.widgets.panel.BookParameters;

public class BookAdditionDialog
    extends JDialog
    implements
        Translatable
{
    private class AddNCloseAction
        extends StretchAction
    {
        public void actionPerformed(
            final ActionEvent e )
        {
            final BookParameters parameters = bookPanel.extractParameters();
            if ( parameters != null )
            {
                addBook( parameters );
                closeAction.actionPerformed( e );
            }
        }
    }

    private class AddNContinueAction
        extends StretchAction
    {
        public void actionPerformed(
            final ActionEvent e )
        {
            final BookParameters parameters = bookPanel.extractParameters();
            if ( parameters != null )
            {
                addBook( parameters );
                bookPanel.clear( false );
            }
        }
    }

    private class CloseAction
        extends StretchAction
    {
        public void actionPerformed(
            final ActionEvent e )
        {
            dispose();
        }
    }

    private final BookPanel bookPanel          = new BookPanel();

    private final Action    addNCloseAction    = new AddNCloseAction();
    private final Action    addNContinueAction = new AddNContinueAction();
    private final Action    closeAction        = new CloseAction();

    public BookAdditionDialog(
        final JFrame owner )
    {
        super( owner );
        initComponents();
        Translator.addTranslatable( this );

        pack();
        setLocationRelativeTo( null );
    }

    public void addBook(
        final BookParameters parameters )
    {
        final PhysicalUnit physicalUnit = FileImporter.createPhysicalUnit( parameters.getFile() );
        final Book book = Storage.getBookShelf().addBook( parameters.getBookName(), null, null, physicalUnit );
        BookPanel.changeBook( book, parameters );

        Singletons.instance( CollectionPanel.class ).updateTree();
    }

    public void retranslate()
    {
        setTitle( I18N.tr( "Add Book" ) );
        addNContinueAction.putValue( Action.NAME, I18N.tr( "Add and continue" ) );
        addNCloseAction.putValue( Action.NAME, I18N.tr( "Add and close" ) );
        closeAction.putValue( Action.NAME, I18N.tr( "Close" ) );
    }

    private void initComponents()
    {
        setModal( true );

        setContentPane( new JPanel( new BorderLayout() ) );
        add( bookPanel, BorderLayout.CENTER );
        bookPanel.setBorder( new TitledBorder( I18N.tr( "Add book to collection" ) ) );

        final Box box = Box.createHorizontalBox();
        box.add( new JButton( addNContinueAction ) );
        box.add( new JButton( addNCloseAction ) );
        box.add( new JButton( closeAction ) );
        add( box, BorderLayout.SOUTH );
    }
}
