/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.txt;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.nio.charset.Charset;

import org.jbookshelf.model.db.PhysicalBook;
import org.jbookshelf.model.db.util.BookShelf;
import org.jbookshelf.view.swinggui.reader.ReaderWindow;
import org.jbookshelf.view.swinggui.reader.toolbar.CharsetChooser;
import org.jbookshelf.view.swinggui.reader.toolbar.ReaderToolBar;
import org.jbookshelf.view.swinggui.widget.FontChooser;

/**
 * @author eav 2009
 */
public class PlainTextToolBar
    extends ReaderToolBar
{
    private CharsetChooser charsetChooser;
    private FontChooser    fontChooser;

    public PlainTextToolBar(
        final ReaderWindow readerWindow )
    {
        super( readerWindow );
    }

    /**
     * @return the charsetChooser
     */
    public CharsetChooser getCharsetChooser()
    {
        return this.charsetChooser;
    }

    /**
     * @return the fontChooser
     */
    public FontChooser getFontChooser()
    {
        return this.fontChooser;
    }

    @Override
    protected void init()
    {
        super.init();
        charsetChooser = new CharsetChooser();
        addSeparator();
        add( charsetChooser );

        charsetChooser.setCharset( readerWindow.getBook().getPhysicalBook().getCharsetName() );

        charsetChooser.addPropertyChangeListener( CharsetChooser.CURRENT_CHARSET, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(
                final PropertyChangeEvent evt )
            {
                final Charset charset = (Charset) evt.getNewValue();
                final PhysicalBook physicalBook = readerWindow.getBook().getPhysicalBook();
                physicalBook.setCharsetName( charset.name() );
                BookShelf.updatePhysical( physicalBook );
                readerWindow.reloadContent();
            }
        } );

        fontChooser = new FontChooser();
        addSeparator();
        add( fontChooser );
    }
}
