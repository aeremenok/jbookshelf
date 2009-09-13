/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.textpanel.navigate;

import icons.IMG;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.view.swinggui.actions.TranslatableAction;
import org.jbookshelf.view.swinggui.reader.BookContent;
import org.jbookshelf.view.swinggui.reader.ReaderWindow;

/**
 * @author eav 2009
 */
public class ThumbnailPanel
    extends JPanel
{
    protected class DownAction
        extends TranslatableAction
    {
        public DownAction()
        {
            super( IMG.icon( IMG.ARROW_DOWN_PNG ), tr( "Down" ) );
        }

        @Override
        public void actionPerformed(
            final ActionEvent e )
        {
            for ( int i = 0; i < pageNumbers.length; i++ )
            {
                pageNumbers[i] += pageNumbers.length;
            }
            updatePageNumbers();
        }
    }

    protected class UpAction
        extends TranslatableAction
    {
        public UpAction()
        {
            super( IMG.icon( IMG.ARROW_UP_PNG ), tr( "Up" ) );
        }

        @Override
        public void actionPerformed(
            final ActionEvent e )
        {
            for ( int i = 0; i < pageNumbers.length; i++ )
            {
                pageNumbers[i] -= pageNumbers.length;
            }
            updatePageNumbers();
        }
    }

    @SuppressWarnings( "unused" )
    private static final Logger     log         = Logger.getLogger( ThumbnailPanel.class );
    protected final Action          upAction    = new UpAction();
    protected final Action          downAction  = new DownAction();

    protected final List<Thumbnail> thumbnails  = new ArrayList<Thumbnail>();

    protected Integer[]             pageNumbers =
                                                { 0, 1, 2, 3 };

    public ThumbnailPanel()
    {
        super( new BorderLayout() );

        add( new JButton( upAction ), BorderLayout.NORTH );

        final Box verticalBox = Box.createVerticalBox();
        add( verticalBox, BorderLayout.CENTER );
        for ( int i = 0; i < pageNumbers.length; i++ )
        {
            final Thumbnail thumbnail = Single.newInstance( Thumbnail.class );
            verticalBox.add( thumbnail );
            thumbnails.add( thumbnail );
        }

        add( new JButton( downAction ), BorderLayout.SOUTH );

        updatePageNumbers();
    }

    protected void updatePageNumbers()
    {
        final BookContent bookContent = Single.instance( ReaderWindow.class ).getBookContent();

        upAction.setEnabled( pageNumbers[0] > 0 );
        downAction.setEnabled( pageNumbers[pageNumbers.length - 1] < bookContent.getPageCount() );

        for ( int i = 0; i < thumbnails.size(); i++ )
        {
            thumbnails.get( i ).setPageNumber( pageNumbers[i] );
        }
    }
}
