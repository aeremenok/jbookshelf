package org.jbookshelf.view.swinggui.widgets;

import images.IMG;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;

import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.controller.util.URIUtil;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.BookShelf;
import org.jbookshelf.model.db.Unique;
import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.logic.BookShelfMediator;
import org.jbookshelf.view.logic.BookShelfMediator.Properties;
import org.jbookshelf.view.qtgui.reader.ReaderWindow;
import org.jbookshelf.view.swinggui.widgets.panel.CollectionPanel;

public class UniqueActions
{
    private class EditAction
        extends TranslatableAction
        implements
        PropertyChangeListener
    {
        public EditAction()
        {
            super( I18N.tr( "Edit" ), IMG.icon( IMG.DOCUMENT_PROPERTIES_PNG ) );
            mediator.addPropertyChangeListener( Properties.BOOKS_SELECTED, this );
        }

        public void actionPerformed(
            final ActionEvent e )
        {
        // TODO Auto-generated method stub
        }

        @Override
        public void propertyChange(
            final PropertyChangeEvent evt )
        {
            setEnabled( mediator.getSelectedBooks().size() == 1 );
        }
    }

    private class GoogleAction
        extends TranslatableAction
        implements
        PropertyChangeListener
    {

        public GoogleAction()
        {
            super( I18N.tr( "Google" ), IMG.icon( IMG.GOOGLE_PNG ) );
            mediator.addPropertyChangeListener( Properties.UNIQUES_SELECTED, this );
        }

        public void actionPerformed(
            final ActionEvent e )
        {
            for ( final Unique unique : mediator.getSelectedUniques() )
            {
                URIUtil.google( unique.getName() );
            }
        }

        @Override
        public void propertyChange(
            final PropertyChangeEvent evt )
        {
            setEnabled( mediator.getSelectedUniques().size() > 0 );
        }
    }

    private class OpenAction
        extends TranslatableAction
        implements
        PropertyChangeListener
    {
        public OpenAction()
        {
            super( I18N.tr( "Open" ), IMG.icon( IMG.DOCUMENT_PREVIEW_PNG ) );
            mediator.addPropertyChangeListener( Properties.BOOKS_SELECTED, this );
        }

        public void actionPerformed(
            final ActionEvent e )
        {
            for ( final Book book : mediator.getSelectedBooks() )
            {
                ReaderWindow.open( null, book );
            }
        }

        @Override
        public void propertyChange(
            final PropertyChangeEvent evt )
        {
            setEnabled( mediator.getSelectedBooks().size() > 0 );
        }
    }

    private class OpenDirAction
        extends TranslatableAction
        implements
        PropertyChangeListener
    {
        public OpenDirAction()
        {
            super( I18N.tr( "Open Directory" ), IMG.icon( IMG.DOCUMENT_OPEN_FOLDER_PNG ) );
            mediator.addPropertyChangeListener( Properties.BOOKS_SELECTED, this );
        }

        public void actionPerformed(
            final ActionEvent e )
        {
            for ( final Book book : mediator.getSelectedBooks() )
            {
                //                URIUtil.openFolder( book.getPhysical().getDirectory() );
            }
        }

        @Override
        public void propertyChange(
            final PropertyChangeEvent evt )
        {
            setEnabled( mediator.getSelectedBooks().size() > 0 );
        }
    }

    private class RemoveAction
        extends TranslatableAction
        implements
        PropertyChangeListener
    {
        public RemoveAction()
        {
            super( I18N.tr( "Remove" ), IMG.icon( IMG.LIST_REMOVE_PNG ) );
            mediator.addPropertyChangeListener( Properties.UNIQUES_SELECTED, this );
        }

        public void actionPerformed(
            final ActionEvent e )
        {
            BookShelf.remove( mediator.getSelectedUniques() );
            Single.instance( CollectionPanel.class ).updateActiveView();
        }

        @Override
        public void propertyChange(
            final PropertyChangeEvent evt )
        {
            setEnabled( mediator.getSelectedUniques().size() > 0 );
        }
    }

    private final BookShelfMediator mediator      = Single.instance( BookShelfMediator.class );

    public final Action             removeAction  = new RemoveAction();
    public final Action             openAction    = new OpenAction();
    public final Action             editAction    = new EditAction();
    public final Action             openDirAction = new OpenDirAction();
    public final Action             googleAction  = new GoogleAction();
}
