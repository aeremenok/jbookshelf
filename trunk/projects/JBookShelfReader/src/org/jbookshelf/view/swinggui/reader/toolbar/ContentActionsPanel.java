/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.toolbar;

import icons.IMG;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.view.swinggui.actions.TranslatableAction;
import org.jbookshelf.view.swinggui.reader.ReaderFactory;
import org.jbookshelf.view.swinggui.reader.textpanel.LayoutablePanel;

/**
 * a panel for control basic content actions
 * 
 * @author eav 2009
 */
public class ContentActionsPanel
    extends JPanel
{
    private class BookmarksAction
        extends FeatureAction
    {
        public BookmarksAction()
        {
            super( null, IMG.icon( IMG.BOOKMARKS_PNG ), ReaderFactory.BOOKMARKS );
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
            Single.instance( LayoutablePanel.class ).getCurrentPanels().changeNotesVisibility();
        }
    }

    private class ThumbnailsAction
        extends FeatureAction
    {
        public ThumbnailsAction()
        {
            super( null, IMG.icon( IMG.VIEW_PREVIEW_PNG ), ReaderFactory.THUMBNAILS );
        }
    }

    private class TOCAction
        extends FeatureAction
    {
        public TOCAction()
        {
            super( null, IMG.icon( IMG.TOC_PNG ), ReaderFactory.TOC );
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
                // todo
                //                getPropertyChangeSupport().firePropertyChange( featureName, featureName, null );
            }
            else
            {
                selectedFeature = featureName;
                // todo
                //                getPropertyChangeSupport().firePropertyChange( featureName, false, true );
            }
        }
    }

    @SuppressWarnings( "unused" )
    private static final Logger log = Logger.getLogger( ContentActionsPanel.class );

    private String              selectedFeature;

    public ContentActionsPanel()
    {
        super();
        setLayout( new BoxLayout( this, BoxLayout.X_AXIS ) );

        final ReaderFactory<?> readerFactory = Single.instance( ReaderFactory.class );
        final List<String> features = readerFactory.getFeatures();
        if ( features.contains( ReaderFactory.THUMBNAILS ) )
        {
            add( new JButton( new ThumbnailsAction() ) );
        }
        if ( features.contains( ReaderFactory.TOC ) )
        {
            add( new JButton( new TOCAction() ) );
        }
        if ( features.contains( ReaderFactory.BOOKMARKS ) )
        {
            add( new JButton( new BookmarksAction() ) );
        }
        if ( features.contains( ReaderFactory.NOTES ) )
        {
            add( new JButton( new NotesAction() ) );
        }
    }
}
