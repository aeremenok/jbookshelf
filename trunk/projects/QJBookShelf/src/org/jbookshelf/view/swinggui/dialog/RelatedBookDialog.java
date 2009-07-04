/**
 * 
 */
package org.jbookshelf.view.swinggui.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.bushe.swing.event.EventBus;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.util.BookShelf;
import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.i18n.Translatable;
import org.jbookshelf.view.swinggui.main.MainWindow;
import org.jbookshelf.view.swinggui.multiedit.MultipleField;
import org.jbookshelf.view.swinggui.multiedit.MultipleUniqueField;
import org.xnap.commons.gui.DefaultDialog;
import org.xnap.commons.i18n.I18n;

/**
 * a dialog for book relation editing
 * 
 * @author eav 2009
 */
public class RelatedBookDialog
    extends DefaultDialog
    implements
    Translatable
{
    public static class RelatedBookEvent
    {
        private final Set<Book> books;

        public RelatedBookEvent(
            final Set<Book> books )
        {
            super();
            this.books = books;
        }

        public Set<Book> getBooks()
        {
            return this.books;
        }
    }

    private final MultipleField<Book> books = new MultipleUniqueField<Book>( Book.class )
                                            {
                                                @Override
                                                protected Book fromString(
                                                    final String text )
                                                {
                                                    final Book bookByName = BookShelf.bookByName( text );
                                                    return book.equals( bookByName )
                                                        ? null : bookByName;
                                                }
                                            };

    private final Book                book;

    public RelatedBookDialog(
        final Book book )
    {
        super( Single.instance( MainWindow.class ), BUTTON_OKAY | BUTTON_CANCEL );
        this.book = book;

        final JPanel panel = new JPanel( new BorderLayout() );
        setMainComponent( panel );
        panel.add( books, BorderLayout.CENTER );

        books.setValues( BookShelf.getBooks( book ) );

        I18N.translate( this );
        setButtonSeparatorVisible( false );
        setPreferredSize( new Dimension( 640, 480 ) );
        pack();
        setLocationRelativeTo( null );
    }

    @Override
    public boolean apply()
    {
        try
        {
            BookShelf.mergeRelatedBooks( book, books.getValues() );
            EventBus.publish( new RelatedBookEvent( book.getRelatedBooks() ) );
            return true;
        }
        catch ( final Throwable e )
        {
            return false;
        }
    }

    @Override
    public void translate(
        final I18n i18n )
    {
        setTitle( book.getName() );
        ((JComponent) books.getParent()).setBorder( new TitledBorder( i18n.tr( "Select books to be related" ) ) );
    }

}
