/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.toolbar;

import icons.IMG;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.jbookshelf.view.swinggui.actions.TranslatableAction;

/**
 * a panel for control basic content actions
 * 
 * @author eav 2009
 */
public class ContentActionsPanel
    extends JPanel
{
    private class BookmarksAction
        extends TranslatableAction
    {
        public BookmarksAction()
        {
            super( null, IMG.icon( IMG.BOOKMARKS_PNG ) );
        }

        @Override
        public void actionPerformed(
            final ActionEvent e )
        {
        // todo
        }
    }

    private class NotesAction
        extends TranslatableAction
    {
        public NotesAction()
        {
            super( null, IMG.icon( IMG.KNOTES_PNG ) );
        }

        @Override
        public void actionPerformed(
            final ActionEvent e )
        {
        // todo
        }
    }

    private class ThumbnailsAction
        extends TranslatableAction
    {
        public ThumbnailsAction()
        {
            super( null, IMG.icon( IMG.VIEW_PREVIEW_PNG ) );
        }

        @Override
        public void actionPerformed(
            final ActionEvent e )
        {
        // todo
        }
    }

    private class TOCAction
        extends TranslatableAction
    {
        public TOCAction()
        {
            super( null, IMG.icon( IMG.TOC_PNG ) );
        }

        @Override
        public void actionPerformed(
            final ActionEvent e )
        {
        // todo
        }
    }

    @SuppressWarnings( "unused" )
    private static final Logger log = Logger.getLogger( ContentActionsPanel.class );

    public ContentActionsPanel(
        final List<String> features )
    {
        super();
        setLayout( new BoxLayout( this, BoxLayout.X_AXIS ) );
        if ( features.contains( Features.THUMBNAILS ) )
        {
            add( new JButton( new ThumbnailsAction() ) );
        }
        if ( features.contains( Features.TOC ) )
        {
            add( new JButton( new TOCAction() ) );
        }
        if ( features.contains( Features.BOOKMARKS ) )
        {
            add( new JButton( new BookmarksAction() ) );
        }
        if ( features.contains( Features.NOTES ) )
        {
            add( new JButton( new NotesAction() ) );
        }
    }

}
