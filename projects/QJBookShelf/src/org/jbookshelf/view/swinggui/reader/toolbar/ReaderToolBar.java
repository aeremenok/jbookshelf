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

import org.jbookshelf.controller.util.URIUtil;
import org.jbookshelf.model.db.Book;
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
            final Book book = readerWindow.getBook();
            URIUtil.openDir( book.getPhysicalBook().getFile().getParentFile() );
        }
    }

    protected final ReaderWindow  readerWindow;
    protected final ProgressBar   progressBar = new ProgressBar();

    protected ContentActionsPanel contentActionsPanel;
    protected Scalator            scalator;
    protected Layouter            layouter    = new Layouter();
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

        add( new WrapperPanel( new JButton( new EditBookAction() ) ) );
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

    public ReaderWindow getReaderWindow()
    {
        return this.readerWindow;
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
        if ( features.contains( Features.NOTES ) || features.contains( Features.BOOKMARKS )
            || features.contains( Features.THUMBNAILS ) )
        {
            addComponent( contentActionsPanel = new ContentActionsPanel() );
            contentActionsPanel.addPropertyChangeListener( readerWindow );
        }
        if ( features.contains( Features.SCALING ) )
        {
            addComponent( scalator = new Scalator( 50, 200, 25, 100 ) );
            scalator.addPropertyChangeListener( readerWindow );
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
            textFinder.addPropertyChangeListener( readerWindow );
        }
        if ( features.contains( Features.CHARSET ) )
        {
            addComponent( charsetChooser = new CharsetChooser() );
            final String charsetName = readerWindow.getBook().getPhysicalBook().getCharsetName();
            charsetChooser.setCharset( charsetName );
            charsetChooser.addPropertyChangeListener( readerWindow );
        }
        if ( features.contains( Features.FONT ) )
        {
            addComponent( fontChooser = new FontChooser() );
            fontChooser.addPropertyChangeListener( readerWindow );
        }

        paginator.addPropertyChangeListener( readerWindow );
        layouter.addPropertyChangeListener( readerWindow );
    }
}
