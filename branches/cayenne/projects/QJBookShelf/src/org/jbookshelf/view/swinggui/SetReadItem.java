/**
 * 
 */
package org.jbookshelf.view.swinggui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JPopupMenu;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import org.bushe.swing.event.EventBus;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.BookShelf;
import org.jbookshelf.model.db.api.spec.IBook;
import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.logic.BookShelfMediator;

/**
 * a menu item to edit {@link IBook#getLastRead()} property
 * 
 * @author eav 2009
 */
public class SetReadItem
    extends JCheckBox
{
    private List<IBook> selectedBooks = new ArrayList<IBook>();

    public SetReadItem(
        final JPopupMenu menu )
    {
        super( I18N.tr( "Is read" ) );

        this.addItemListener( new ItemListener()
        {
            @Override
            public void itemStateChanged(
                final ItemEvent e )
            {
                menu.setVisible( false );
                updateBook();
            }
        } );

        menu.addPopupMenuListener( new PopupMenuListener()
        {
            @Override
            public void popupMenuCanceled(
                final PopupMenuEvent e )
            {}

            @Override
            public void popupMenuWillBecomeInvisible(
                final PopupMenuEvent e )
            {}

            @Override
            public void popupMenuWillBecomeVisible(
                final PopupMenuEvent e )
            {
                updateSelection();
            }
        } );
    }

    public void updateSelection()
    {
        selectedBooks = Single.instance( BookShelfMediator.class ).getSelectedBooks();
        setEnabled( selectedBooks.size() > 0 );

        boolean allRead = true;
        final Iterator<IBook> iterator = selectedBooks.iterator();
        while ( iterator.hasNext() && allRead )
        {
            final IBook book = iterator.next();
            allRead &= book.isRead();
        }
        setSelected( allRead && isEnabled() );
    }

    private void updateBook()
    {
        Single.instance( ProgressBar.class ).invoke( new Runnable()
        {
            @Override
            public void run()
            {
                for ( final IBook book : selectedBooks )
                {
                    book.setRead( isSelected() );
                    BookShelf.mergeNote( book.getLastRead() );
                    EventBus.publish( book.getLastRead() );
                }
            }
        } );
    }
}