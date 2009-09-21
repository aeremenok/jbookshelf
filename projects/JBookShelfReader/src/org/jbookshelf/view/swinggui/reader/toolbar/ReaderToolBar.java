/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.toolbar;

import icons.IMG;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.swing.JButton;
import javax.swing.JToolBar;

import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.controller.util.URIUtil;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.view.swinggui.ProgressBar;
import org.jbookshelf.view.swinggui.actions.TranslatableAction;
import org.jbookshelf.view.swinggui.reader.ReaderSpecific;
import org.jbookshelf.view.swinggui.reader.ReaderWindow;
import org.jbookshelf.view.swinggui.widget.WrapperPanel;

/**
 * reader window toolbar, dispatches viewer actions
 * 
 * @author eav 2009
 */
public class ReaderToolBar
    extends JToolBar
{
    private class OpenDirAction
        extends TranslatableAction
    {
        public OpenDirAction()
        {
            super( IMG.icon( IMG.DOCUMENT_OPEN_FOLDER_PNG ), tr( "Open book directory" ) );
        }

        @Override
        public void actionPerformed(
            final ActionEvent e )
        {
            final Book book = Single.instance( ReaderWindow.class ).getBook();
            URIUtil.openDir( book.getPhysicalBook().getFile().getParentFile() );
        }
    }

    @PostConstruct
    public void init()
    {
        initFeatures();

        add( new WrapperPanel( new JButton( new OpenDirAction() ) ) );
        addSeparator();
        add( new WrapperPanel( Single.instance( ProgressBar.class ) ) );
    }

    private void addComponent(
        final Component comp )
    {
        add( comp );
        addSeparator();
    }

    private void initFeatures()
    {
        final ReaderSpecific<?> readerFactory = Single.instance( ReaderSpecific.class );
        final List<String> features = readerFactory.getSupportedFeatures();

        if ( features.contains( ReaderSpecific.NOTES ) || features.contains( ReaderSpecific.BOOKMARKS )
            || features.contains( ReaderSpecific.THUMBNAILS ) )
        {
            addComponent( Single.instance( ContentActionsPanel.class ) );
        }
        if ( features.contains( ReaderSpecific.SCALING ) )
        {
            addComponent( Single.instance( Scalator.class ) );
        }
        if ( features.contains( ReaderSpecific.LAYOUT ) )
        {
            addComponent( Single.instance( Layouter.class ) );
        }
        if ( features.contains( ReaderSpecific.PAGING ) )
        {
            addComponent( Single.instance( Paginator.class ) );
        }
        if ( features.contains( ReaderSpecific.SEARCH ) )
        {
            addComponent( Single.instance( TextFinder.class ) );
        }
        if ( features.contains( ReaderSpecific.CHARSET ) )
        {
            addComponent( Single.instance( CharsetChooser.class ) );
        }
        if ( features.contains( ReaderSpecific.FONT ) )
        {
            addComponent( Single.instance( FontChooser.class ) );
        }
        if ( features.contains( ReaderSpecific.BROWSER ) )
        {
            addComponent( Single.instance( BrowserNavigator.class ) );
        }
    }
}
