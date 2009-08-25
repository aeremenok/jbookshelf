/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.toolbar;

import icons.IMG;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeSupport;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.Icon;
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
        extends FeatureAction
    {
        public BookmarksAction()
        {
            super( null, IMG.icon( IMG.BOOKMARKS_PNG ), Features.BOOKMARKS );
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
        extends FeatureAction
    {
        public ThumbnailsAction()
        {
            super( null, IMG.icon( IMG.VIEW_PREVIEW_PNG ), Features.THUMBNAILS );
        }
    }

    private class TOCAction
        extends FeatureAction
    {
        public TOCAction()
        {
            super( null, IMG.icon( IMG.TOC_PNG ), Features.TOC );
        }
    }

    protected abstract class FeatureAction
        extends TranslatableAction
    {
        protected final String featureName;

        public FeatureAction(
            final String name,
            final Icon icon,
            final String featureName )
        {
            super( name, icon );
            this.featureName = featureName;
        }

        @Override
        public void actionPerformed(
            final ActionEvent e )
        {
            if ( featureName.equals( selectedFeature ) )
            {
                selectedFeature = null;
                getPropertyChangeSupport().firePropertyChange( featureName, featureName, null );
            }
            else
            {
                selectedFeature = featureName;
                getPropertyChangeSupport().firePropertyChange( featureName, false, true );
            }
        }
    }

    @SuppressWarnings( "unused" )
    private static final Logger         log                   = Logger.getLogger( ContentActionsPanel.class );

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport( this );
    private String                      selectedFeature;

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
