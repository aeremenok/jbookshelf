/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.toolbar;

import icons.IMG;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JToolBar;

import org.jbookshelf.view.swinggui.actions.TranslatableAction;
import org.jbookshelf.view.swinggui.dialog.book.BookEditDialog;
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
    private class EditBookAction
        extends TranslatableAction
    {
        public EditBookAction()
        {
            super( null, IMG.icon( IMG.DOCUMENT_PROPERTIES_PNG ) );
        }

        @Override
        public void actionPerformed(
            final ActionEvent e )
        {
            new BookEditDialog( readerWindow.getBook() ).setVisible( true );
        }
    }

    private final ReaderWindow        readerWindow;

    private final Scalator            scalator            = new Scalator( 50, 200, 50, 100 );
    private final Paginator           paginator           = new Paginator( this );
    private final TextFinder          textFinder          = new TextFinder();
    private final ContentActionsPanel contentActionsPanel = new ContentActionsPanel();

    public ReaderToolBar(
        final ReaderWindow readerWindow )
    {
        this.readerWindow = readerWindow;
        add( contentActionsPanel );
        addSeparator();
        add( scalator );
        addSeparator();
        add( paginator );
        addSeparator();
        add( textFinder );
        addSeparator();
        add( new WrapperPanel( new JButton( new EditBookAction() ) ) );
    }

    /**
     * @return the contentActionsPanel
     */
    public ContentActionsPanel getContentActionsPanel()
    {
        return this.contentActionsPanel;
    }

    /**
     * @return the paginator
     */
    public Paginator getPaginator()
    {
        return paginator;
    }

    /**
     * @return the readerWindow
     */
    public ReaderWindow getReaderWindow()
    {
        return this.readerWindow;
    }

    /**
     * @return the scalator
     */
    public Scalator getScalator()
    {
        return scalator;
    }

    /**
     * @return the text finder
     */
    public TextFinder getTextFinder()
    {
        return textFinder;
    }
}