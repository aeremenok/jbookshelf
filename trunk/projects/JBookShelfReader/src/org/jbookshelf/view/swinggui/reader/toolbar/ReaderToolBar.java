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

import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.controller.util.URIUtil;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.view.swinggui.ProgressBar;
import org.jbookshelf.view.swinggui.actions.TranslatableAction;
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
    protected class OpenDirAction
        extends TranslatableAction
    {
        public OpenDirAction()
        {
            super( null, IMG.icon( IMG.DOCUMENT_OPEN_FOLDER_PNG ) );
        }

        @Override
        public void actionPerformed(
            final ActionEvent e )
        {
            final ReaderWindow readerWindow = Single.instance( ReaderWindow.class );
            final Book book = readerWindow.getBook();
            URIUtil.openDir( book.getPhysicalBook().getFile().getParentFile() );
        }
    }

    protected final ProgressBar   progressBar = new ProgressBar();

    protected Layouter            layouter    = new Layouter();
    protected Paginator           paginator   = new Paginator();
    protected ContentActionsPanel contentActionsPanel;
    protected Scalator            scalator;
    protected TextFinder          textFinder;
    protected CharsetChooser      charsetChooser;
    protected FontChooser         fontChooser;
    private List<String>          features;

    public ReaderToolBar(
        final String... featureNames )
    {
        super();

        initFeatures( Arrays.asList( featureNames ) );

        add( new WrapperPanel( new JButton( new OpenDirAction() ) ) );
        addSeparator();
        add( new WrapperPanel( progressBar ) );

    }

    public void addComponent(
        final Component comp )
    {
        add( comp );
        addSeparator();
    }

    public CharsetChooser getCharsetChooser()
    {
        return this.charsetChooser;
    }

    public ContentActionsPanel getContentActionsPanel()
    {
        return this.contentActionsPanel;
    }

    public List<String> getFeatures()
    {
        return this.features;
    }

    public FontChooser getFontChooser()
    {
        return this.fontChooser;
    }

    public Layouter getLayouter()
    {
        return this.layouter;
    }

    public Paginator getPaginator()
    {
        return paginator;
    }

    public ProgressBar getProgressBar()
    {
        return this.progressBar;
    }

    public Scalator getScalator()
    {
        return scalator;
    }

    public TextFinder getTextFinder()
    {
        return textFinder;
    }

    private void initFeatures(
        final List<String> features )
    {
        this.features = features;

        final ReaderWindow readerWindow = Single.instance( ReaderWindow.class );
        if ( features.contains( Features.NOTES ) || features.contains( Features.BOOKMARKS )
            || features.contains( Features.THUMBNAILS ) )
        {
            addComponent( contentActionsPanel = new ContentActionsPanel( features ) );
            contentActionsPanel.getPropertyChangeSupport().addPropertyChangeListener( readerWindow );
        }
        if ( features.contains( Features.SCALING ) )
        {
            addComponent( scalator = new Scalator( 50, 200, 25, 100 ) );
            scalator.getPropertyChangeSupport().addPropertyChangeListener( readerWindow );
        }
        if ( features.contains( Features.LAYOUT ) )
        {
            addComponent( layouter );
        }
        if ( features.contains( Features.PAGING ) )
        {
            addComponent( paginator );
        }
        if ( features.contains( Features.SEARCH ) )
        {
            addComponent( textFinder = new TextFinder() );
            textFinder.getPropertyChangeSupport().addPropertyChangeListener( readerWindow );
        }
        if ( features.contains( Features.CHARSET ) )
        {
            addComponent( charsetChooser = new CharsetChooser() );
            // todo set after changing a book
            //            final String charsetName = readerWindow.getBook().getPhysicalBook().getCharsetName();
            //            charsetChooser.setCharset( charsetName );
            charsetChooser.getPropertyChangeSupport().addPropertyChangeListener( readerWindow );
        }
        if ( features.contains( Features.FONT ) )
        {
            addComponent( fontChooser = new FontChooser() );
            fontChooser.getPropertyChangeSupport().addPropertyChangeListener( readerWindow );
        }

        paginator.getPropertyChangeSupport().addPropertyChangeListener( readerWindow );
        layouter.getPropertyChangeSupport().addPropertyChangeListener( readerWindow );
    }
}
