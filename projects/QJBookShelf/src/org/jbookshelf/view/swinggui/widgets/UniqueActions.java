package org.jbookshelf.view.swinggui.widgets;

import images.IMG;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.annotation.PostConstruct;
import javax.swing.Action;
import javax.swing.JOptionPane;

import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.controller.util.URIUtil;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.BookShelf;
import org.jbookshelf.model.db.Unique;
import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.logic.BookShelfMediator;
import org.jbookshelf.view.logic.BookShelfMediator.Properties;
import org.jbookshelf.view.qtgui.reader.ReaderWindow;
import org.jbookshelf.view.swinggui.MainWindow;
import org.jbookshelf.view.swinggui.widgets.dialog.BookEditDialog;
import org.jbookshelf.view.swinggui.widgets.panel.CollectionPanel;

public class UniqueActions
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
            new BookEditDialog( mediator.getSelectedBooks().get( 0 ) ).setVisible( true );
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
                //                URIUtil.openFolder( book.getPhysical().getDirectory() );
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
            final MainWindow window = Single.instance( MainWindow.class );
            if ( JOptionPane.showConfirmDialog( window, I18N.tr( "Remove selected?" ), "", JOptionPane.YES_NO_OPTION ) == JOptionPane.YES_OPTION )
            {
                BookShelf.remove( mediator.getSelectedUniques() );
                Single.instance( CollectionPanel.class ).updateActiveView();
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
        mediator.addPropertyChangeListener( Properties.UNIQUES_SELECTED, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(
                final PropertyChangeEvent evt )
            {
                final boolean manyUniques = mediator.getSelectedUniques().size() > 0;
                removeAction.setEnabled( manyUniques );
                googleAction.setEnabled( manyUniques );

            }
        } );

        mediator.addPropertyChangeListener( Properties.BOOKS_SELECTED, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(
                final PropertyChangeEvent evt )
            {
                final boolean manyBooks = mediator.getSelectedBooks().size() > 0;
                openDirAction.setEnabled( manyBooks );
                openAction.setEnabled( manyBooks );

                editAction.setEnabled( mediator.getSelectedBooks().size() == 1 );
            }
        } );
    }
}
