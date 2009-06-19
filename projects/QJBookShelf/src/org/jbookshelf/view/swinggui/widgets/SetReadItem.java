/**
 * 
 */
package org.jbookshelf.view.swinggui.widgets;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.JCheckBoxMenuItem;

import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.BookShelf;
import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.logic.BookShelfMediator;
import org.jbookshelf.view.logic.BookShelfMediator.Properties;

public class SetReadItem
    extends JCheckBoxMenuItem
    implements
    PropertyChangeListener,
    ItemListener
{
    public SetReadItem()
    {
        super( I18N.tr( "Is read" ) );
        addItemListener( this );
        Single.instance( BookShelfMediator.class ).addPropertyChangeListener( Properties.BOOKS_SELECTED, this );
    }

    @Override
    public void itemStateChanged(
        final ItemEvent e )
    {
        final List<Book> selectedBooks = Single.instance( BookShelfMediator.class ).getSelectedBooks();
        for ( final Book book : selectedBooks )
        {
            book.setRead( isSelected()
                ? 1f : 0f );
            BookShelf.updateIsRead( book );
        }
    }

    @Override
    public void propertyChange(
        final PropertyChangeEvent evt )
    {
        final List<Book> selectedBooks = Single.instance( BookShelfMediator.class ).getSelectedBooks();
        setEnabled( selectedBooks.size() > 0 );

        boolean allRead = true;
        final Iterator<Book> iterator = selectedBooks.iterator();
        while ( iterator.hasNext() && allRead )
        {
            final Book book = iterator.next();
            allRead &= book.getRead() == 1f;
        }
        setSelected( allRead && isEnabled() );
    }
}