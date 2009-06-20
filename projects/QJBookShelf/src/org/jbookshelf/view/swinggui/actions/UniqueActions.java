package org.jbookshelf.view.swinggui.actions;

import icons.IMG;

import java.awt.event.ActionEvent;

import javax.annotation.PostConstruct;
import javax.swing.Action;
import javax.swing.JOptionPane;

import org.bushe.swing.event.EventBus;
import org.bushe.swing.event.EventTopicSubscriber;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.controller.util.URIUtil;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.BookShelf;
import org.jbookshelf.model.db.Unique;
import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.logic.BookShelfMediator;
import org.jbookshelf.view.logic.SafeWorker;
import org.jbookshelf.view.logic.BookShelfMediator.Properties;
import org.jbookshelf.view.qtgui.reader.Viewer;
import org.jbookshelf.view.swinggui.ProgressBar;
import org.jbookshelf.view.swinggui.collection.CollectionPanel;
import org.jbookshelf.view.swinggui.dialog.book.BookEditDialog;
import org.jbookshelf.view.swinggui.main.MainWindow;

public class UniqueActions
{
    private class EditAction
        extends TranslatableAction
    {
        public EditAction()
        {
            super( I18N.tr( "Edit" ), IMG.icon( IMG.DOCUMENT_PROPERTIES_PNG, 32 ) );
        }

        public void actionPerformed(
            final ActionEvent e )
        {
            new BookEditDialog( mediator.getSelectedBooks().get( 0 ) ).setVisible( true );
        }
    }

    private class GoogleAction
        extends TranslatableAction
    {
        public GoogleAction()
        {
            super( I18N.tr( "Google" ), IMG.icon( IMG.GOOGLE_PNG, 32 ) );
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
            super( I18N.tr( "Open" ), IMG.icon( IMG.DOCUMENT_PREVIEW_PNG, 32 ) );
        }

        public void actionPerformed(
            final ActionEvent e )
        {
            for ( final Book book : mediator.getSelectedBooks() )
            {
                Single.instance( Viewer.class ).open( book );
            }
        }
    }

    private class OpenDirAction
        extends TranslatableAction
    {
        public OpenDirAction()
        {
            super( I18N.tr( "Open Directory" ), IMG.icon( IMG.DOCUMENT_OPEN_FOLDER_PNG, 32 ) );
        }

        public void actionPerformed(
            final ActionEvent e )
        {
            for ( final Book book : mediator.getSelectedBooks() )
            {
                URIUtil.openFolder( book.getPhysicalBook().getFile().getParentFile() );
            }
        }
    }

    private class RemoveAction
        extends TranslatableAction
    {
        public RemoveAction()
        {
            super( I18N.tr( "Remove" ), IMG.icon( IMG.LIST_REMOVE_PNG, 32 ) );
        }

        public void actionPerformed(
            final ActionEvent e )
        {
            final MainWindow window = Single.instance( MainWindow.class );
            if ( JOptionPane.showConfirmDialog( window, I18N.tr( "Remove selected?" ), "", JOptionPane.YES_NO_OPTION ) == JOptionPane.YES_OPTION )
            {
                Single.instance( ProgressBar.class ).invoke( new SafeWorker<Object, Object>()
                {
                    @Override
                    protected Object doInBackground()
                    {
                        BookShelf.remove( mediator.getSelectedUniques() );
                        return null;
                    }

                    @Override
                    protected void doneSafe()
                    {
                        Single.instance( CollectionPanel.class ).updateActiveView();
                    }

                } );
            }
        }
    }

    private final BookShelfMediator mediator      = Single.instance( BookShelfMediator.class );
    public final Action             removeAction  = new RemoveAction();
    public final Action             openAction    = new OpenAction();
    public final Action             editAction    = new EditAction();
    public final Action             openDirAction = new OpenDirAction();
    public final Action             googleAction  = new GoogleAction();

    @PostConstruct
    public void init()
    {
        EventBus.subscribeStrongly( Properties.UNIQUES_SELECTED, new EventTopicSubscriber<BookShelfMediator>()
        {
            @Override
            public void onEvent(
                final String string,
                final BookShelfMediator mediator )
            {
                final boolean manyUniques = mediator.getSelectedUniques().size() > 0;
                removeAction.setEnabled( manyUniques );
                googleAction.setEnabled( manyUniques );
            }
        } );
        EventBus.subscribeStrongly( Properties.BOOKS_SELECTED, new EventTopicSubscriber<BookShelfMediator>()
        {
            @Override
            public void onEvent(
                final String string,
                final BookShelfMediator mediator )
            {
                final boolean manyBooks = mediator.getSelectedBooks().size() > 0;
                openDirAction.setEnabled( manyBooks );
                openAction.setEnabled( manyBooks );

                editAction.setEnabled( mediator.getSelectedBooks().size() == 1 );
            }
        } );
    }
}
