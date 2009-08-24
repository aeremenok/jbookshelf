/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.toolbar;

import icons.IMG;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeSupport;
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
    implements
    Features
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
            getPropertyChangeSupport().firePropertyChange( Features.BOOKMARKS, false, true );
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
            getPropertyChangeSupport().firePropertyChange( Features.NOTES, false, true );
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
            getPropertyChangeSupport().firePropertyChange( Features.THUMBNAILS, false, true );
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
            getPropertyChangeSupport().firePropertyChange( Features.TOC, false, true );
        }
    }

    @SuppressWarnings( "unused" )
    private static final Logger         log                   = Logger.getLogger( ContentActionsPanel.class );

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport( this );

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

    @Override
    public PropertyChangeSupport getPropertyChangeSupport()
    {
        return propertyChangeSupport;
    }
}
