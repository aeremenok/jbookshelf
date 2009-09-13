/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.toolbar;

import icons.IMG;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.view.swinggui.actions.TranslatableAction;
import org.jbookshelf.view.swinggui.reader.ReaderFactory;
import org.jbookshelf.view.swinggui.reader.textpanel.LayoutablePanel;
import org.jbookshelf.view.swinggui.reader.textpanel.navigate.ContentNavigator;

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
            super( IMG.icon( IMG.BOOKMARKS_PNG ), ReaderFactory.BOOKMARKS, tr( "Show global bookmarks" ) );
        }
    }

    private class NotesAction
        extends TranslatableAction
    {
        public NotesAction()
        {
            super( IMG.icon( IMG.KNOTES_PNG ), tr( "Show local notes" ) );
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
            super( IMG.icon( IMG.VIEW_PREVIEW_PNG ), ReaderFactory.THUMBNAILS, tr( "Show thumbnails" ) );
        }
    }

    protected abstract class FeatureAction
        extends TranslatableAction
    {
        protected final String featureName;

        public FeatureAction(
            final Icon icon,
            final String featureName,
            final String desc )
        {
            super( icon, desc );
            this.featureName = featureName;
        }

        @Override
        public void actionPerformed(
            final ActionEvent e )
        {
            selectedFeature = featureName.equals( selectedFeature )
                ? null : featureName;
            Single.instance( ContentNavigator.class ).show( selectedFeature );
        }
    }

    @SuppressWarnings( "unused" )
    private static final Logger log = Logger.getLogger( ContentActionsPanel.class );

    private String              selectedFeature;

    @PostConstruct
    public void init()
    {
        setLayout( new BoxLayout( this, BoxLayout.X_AXIS ) );

        final ReaderFactory<?> readerFactory = Single.instance( ReaderFactory.class );
        final List<String> features = readerFactory.getFeatures();
        if ( features.contains( ReaderFactory.THUMBNAILS ) )
        {
            add( new JButton( new ThumbnailsAction() ) );
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
