/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.textview.pagelayout;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.Bookmark;
import org.jbookshelf.view.swinggui.reader.navigation.notes.NotesPanel;
import org.jbookshelf.view.swinggui.reader.textview.ContentRenderer;
import org.jbookshelf.view.swinggui.reader.toolbar.Paginator;

/**
 * displays two pages: left and right
 * 
 * @author eav 2009
 * @param <PageType> displayed page type
 */
public class TwoPageLayout<PageType>
    extends PageLayout<PageType>
{
    private final ContentRenderer<PageType> leftContentPanel;
    private final ContentRenderer<PageType> rightContentPanel;

    private final NotesPanel                   leftNotesPanel  = new NotesPanel();
    private final NotesPanel                   rightNotesPanel = new NotesPanel();

    @SuppressWarnings( "unchecked" )
    public TwoPageLayout()
    {
        super();

        leftContentPanel = Single.newInstance( ContentRenderer.class );
        rightContentPanel = Single.newInstance( ContentRenderer.class );

        final JSplitPane splitPane = new JSplitPane();
        add( splitPane, BorderLayout.CENTER );

        final JPanel left = new JPanel( new BorderLayout() );
        left.add( leftContentPanel, BorderLayout.CENTER );
        left.add( leftNotesPanel, BorderLayout.EAST );

        final JPanel right = new JPanel( new BorderLayout() );
        right.add( rightContentPanel, BorderLayout.CENTER );
        right.add( rightNotesPanel, BorderLayout.EAST );

        splitPane.setLeftComponent( left );
        splitPane.setRightComponent( right );
        splitPane.setResizeWeight( 0.5 );

        rightNotesPanel.setPageOffset( 1 );
    }

    @Override
    public void changeNotesVisibility()
    {
        leftNotesPanel.setVisible( !leftNotesPanel.isVisible() );
        rightNotesPanel.setVisible( !rightNotesPanel.isVisible() );
    }

    @Override
    public Bookmark createBookmark()
    {
        return leftContentPanel.createNote( null );
    }

    @Override
    public void displayPages(
        final PageType... pages )
    {
        leftContentPanel.displayContent( pages[0] );
        rightContentPanel.displayContent( pages[1] );
    }

    @Override
    public void goTo(
        final Bookmark bookmark )
    {
        Single.instance( Paginator.class ).setNewPage( bookmark.getPage() );
    }

    @Override
    public void highlightText(
        final String text )
    {
        leftContentPanel.highlightText( text );
        rightContentPanel.highlightText( text );
    }

    @Override
    public void scale(
        final int scalePercentage )
    {
        leftContentPanel.scale( scalePercentage );
        rightContentPanel.scale( scalePercentage );
    }

    @Override
    public void useReaderFont(
        final Font font )
    {
        leftContentPanel.useFont( font );
        rightContentPanel.useFont( font );
    }
}
