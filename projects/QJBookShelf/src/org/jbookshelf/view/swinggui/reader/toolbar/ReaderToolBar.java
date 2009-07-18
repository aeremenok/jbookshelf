/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.toolbar;

import icons.IMG;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JToolBar;

import org.jbookshelf.view.swinggui.ProgressBar;
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
    protected class EditBookAction
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

    protected final ReaderWindow  readerWindow;
    protected final ProgressBar   progressBar = new ProgressBar();

    protected ContentActionsPanel contentActionsPanel;
    protected Scalator            scalator;
    protected Layouter            layouter;
    protected Paginator           paginator   = new Paginator();
    protected TextFinder          textFinder;
    protected CharsetChooser      charsetChooser;
    protected FontChooser         fontChooser;

    public ReaderToolBar(
        final ReaderWindow readerWindow,
        final String... featureNames )
    {
        super();
        this.readerWindow = readerWindow;

        initFeatures( Arrays.asList( featureNames ) );

        addComponent( new WrapperPanel( new JButton( new EditBookAction() ) ) );
        add( new WrapperPanel( progressBar ) );

    }

    public void addComponent(
        final Component comp )
    {
        add( comp );
        addSeparator();
        comp.addPropertyChangeListener( readerWindow );
    }

    /**
     * @return the charsetChooser
     */
    public CharsetChooser getCharsetChooser()
    {
        return this.charsetChooser;
    }

    /**
     * @return the contentActionsPanel
     */
    public ContentActionsPanel getContentActionsPanel()
    {
        return this.contentActionsPanel;
    }

    /**
     * @return the fontChooser
     */
    public FontChooser getFontChooser()
    {
        return this.fontChooser;
    }

    /**
     * @return the layouter
     */
    public Layouter getLayouter()
    {
        return this.layouter;
    }

    /**
     * @return the paginator
     */
    public Paginator getPaginator()
    {
        return paginator;
    }

    /**
     * @return the progressBar
     */
    public ProgressBar getProgressBar()
    {
        return this.progressBar;
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

    /**
     * @param features
     */
    private void initFeatures(
        final List<String> features )
    {
        if ( features.contains( Features.NOTES ) || features.contains( Features.BOOKMARKS )
            || features.contains( Features.THUMBNAILS ) )
        {
            addComponent( contentActionsPanel = new ContentActionsPanel() );
        }
        if ( features.contains( Features.SCALING ) )
        {
            addComponent( scalator = new Scalator( 50, 200, 50, 100 ) );
        }
        if ( features.contains( Features.LAYOUT ) )
        {
            addComponent( layouter = new Layouter() );
        }
        if ( features.contains( Features.PAGING ) )
        {
            addComponent( paginator );
        }
        if ( features.contains( Features.SEARCH ) )
        {
            addComponent( textFinder = new TextFinder() );
        }
        if ( features.contains( Features.CHARSET ) )
        {
            addComponent( charsetChooser = new CharsetChooser() );
            final String charsetName = readerWindow.getBook().getPhysicalBook().getCharsetName();
            charsetChooser.setCharset( charsetName );
        }
        if ( features.contains( Features.FONT ) )
        {
            addComponent( fontChooser = new FontChooser() );
        }
    }
}
