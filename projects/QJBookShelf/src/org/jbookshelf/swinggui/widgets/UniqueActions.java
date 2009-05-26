package org.jbookshelf.swinggui.widgets;

import images.IMG;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.swing.Action;

import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.controller.util.URIUtil;
import org.jbookshelf.i18n.I18N;
import org.jbookshelf.model.Book;
import org.jbookshelf.model.Unique;
import org.jbookshelf.qtgui.logic.BookShelfMediator;
import org.jbookshelf.qtgui.logic.UniqueSelectionListener;
import org.jbookshelf.qtgui.reader.ReaderWindow;

public class UniqueActions
    implements
    UniqueSelectionListener
{
    private class EditAction
        extends TranslatableAction
    {

        public EditAction()
        {
            super( I18N.tr( "Edit" ), IMG.icon( IMG.DOCUMENT_PROPERTIES_PNG ) );
        }

        public void actionPerformed(
            final ActionEvent e )
        {
        // TODO Auto-generated method stub
        }
    }

    private class GoogleAction
        extends TranslatableAction
    {

        public GoogleAction()
        {
            super( I18N.tr( "Google" ), IMG.icon( IMG.GOOGLE_PNG ) );
        }

        public void actionPerformed(
            final ActionEvent e )
        {
            for ( final Unique unique : mediator.getSelectedUniques() )
            {
                URIUtil.google( unique.getName() );
            }
        }
    }

    private class OpenAction
        extends TranslatableAction
    {
        public OpenAction()
        {
            super( I18N.tr( "Open" ), IMG.icon( IMG.DOCUMENT_PREVIEW_PNG ) );
        }

        public void actionPerformed(
            final ActionEvent e )
        {
            for ( final Book book : mediator.getSelectedBooks() )
            {
                ReaderWindow.open( null, book );
            }
        }
    }

    private class OpenDirAction
        extends TranslatableAction
    {
        public OpenDirAction()
        {
            super( I18N.tr( "Open Directory" ), IMG.icon( IMG.DOCUMENT_OPEN_FOLDER_PNG ) );
        }

        public void actionPerformed(
            final ActionEvent e )
        {
            for ( final Book book : mediator.getSelectedBooks() )
            {
                URIUtil.openFolder( book.getPhysical().getDirectory() );
            }
        }
    }

    private class RemoveAction
        extends TranslatableAction
    {

        public RemoveAction()
        {
            super( I18N.tr( "Remove" ), IMG.icon( IMG.LIST_REMOVE_PNG ) );
        }

        public void actionPerformed(
            final ActionEvent e )
        {
        // TODO Auto-generated method stub
        }
    }

    private final BookShelfMediator mediator      = Single.instance( BookShelfMediator.class );

    public final Action             removeAction  = new RemoveAction();
    public final Action             openAction    = new OpenAction();
    public final Action             editAction    = new EditAction();
    public final Action             openDirAction = new OpenDirAction();
    public final Action             googleAction  = new GoogleAction();

    @PostConstruct
    public void initSingleton()
    {
        mediator.addUniqueSelectionListener( this );
    }

    public void selectedUniques(
        final List<Unique> uniques )
    {
        removeAction.setEnabled( uniques.size() > 0 );
        googleAction.setEnabled( uniques.size() > 0 );

        final List<Book> books = mediator.getSelectedBooks();
        // all books and their folders can be opened
        openAction.setEnabled( books.size() > 0 );
        openDirAction.setEnabled( books.size() > 0 );
        // only a single book can be edited
        editAction.setEnabled( books.size() == 1 );
    }

}
