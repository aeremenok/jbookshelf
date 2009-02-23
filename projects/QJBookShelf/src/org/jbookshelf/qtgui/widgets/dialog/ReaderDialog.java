package org.jbookshelf.qtgui.widgets.dialog;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.Charset;

import org.jbookshelf.controller.URIOpener;
import org.jbookshelf.controller.ZIPHandler;
import org.jbookshelf.model.ArchiveFile;
import org.jbookshelf.model.Book;
import org.jbookshelf.model.PhysicalUnit;
import org.jbookshelf.qtgui.logic.JBookShelfConstants;
import org.jbookshelf.qtgui.widgets.ext.QDialogExt;
import org.mozilla.universalchardet.UniversalDetector;

import com.trolltech.qt.core.Qt.WindowState;
import com.trolltech.qt.gui.QAction;
import com.trolltech.qt.gui.QCloseEvent;
import com.trolltech.qt.gui.QIcon;
import com.trolltech.qt.gui.QMessageBox;
import com.trolltech.qt.gui.QScrollBar;
import com.trolltech.qt.gui.QTextBrowser;
import com.trolltech.qt.gui.QToolBar;
import com.trolltech.qt.gui.QVBoxLayout;
import com.trolltech.qt.gui.QWidget;

public class ReaderDialog
    extends QDialogExt
    implements
        JBookShelfConstants
{
    private final QTextBrowser    textEdit   = new QTextBrowser( this );
    private final QAction         backward   = new QAction( new QIcon( ICONPATH + "go-previous.png" ), "", this );
    private final QAction         forward    = new QAction( new QIcon( ICONPATH + "go-next.png" ), "", this );

    private final Book            book;

    private final static String[] extensions = { "txt", "html", "htm", "shtml" };

    public static byte[] getBytesFromFile(
        final File file )
        throws IOException
    {
        final InputStream is = new FileInputStream( file );

        // Get the size of the file
        final long length = file.length();

        if ( length > Integer.MAX_VALUE )
        {
            // todo break into parts?
            throw new IOException( "File is too large: " + file.getAbsolutePath() );
        }

        // Create the byte array to hold the data
        final byte[] bytes = new byte[(int) length];

        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while ( offset < bytes.length && (numRead = is.read( bytes, offset, bytes.length - offset )) >= 0 )
        {
            offset += numRead;
        }

        // Ensure all the bytes have been read in
        if ( offset < bytes.length )
        {
            throw new IOException( "Could not completely read file " + file.getName() );
        }

        // Close the input stream and return bytes
        is.close();
        return bytes;
    }

    public static void open(
        final QWidget parent,
        final Book book )
    {
        try
        {
            final File file = getFile( book );
            if ( canBeOpened( file ) )
            {
                new ReaderDialog( parent, book ).show();
            }
            else
            {
                URIOpener.browseFile( file );
            }
        }
        catch ( final Throwable e )
        {
            e.printStackTrace();
            QMessageBox
                .critical( parent, "Error", "Error opening book " + book.getName() + "\n\n" + printThrowable( e ) );
        }
    }

    private static boolean canBeOpened(
        final File file )
    {
        final String lowerCase = file.getName().toLowerCase();
        for ( final String string : extensions )
        {
            if ( lowerCase.endsWith( "." + string ) )
            {
                return true;
            }
        }
        return false;
    }

    private static File getFile(
        final Book book )
    {
        final PhysicalUnit physical = book.getPhysical();
        if ( physical instanceof ArchiveFile )
        {
            final ArchiveFile archiveFile = (ArchiveFile) physical;
            if ( archiveFile.getArchiveFile() == null )
            { // unpack and remember the file
                // todo waiting dialog
                final File zippedFileToOpen = ZIPHandler.getZippedFileToOpen( archiveFile.getFile() );
                archiveFile.setArchiveFile( zippedFileToOpen );
            }
            return archiveFile.getArchiveFile();
        }

        return physical.getFile();
    }

    private static String guessEncoding(
        final File file )
    {
        final UniversalDetector detector = new UniversalDetector( null );
        try
        {
            final byte[] buf = new byte[4096];
            final FileInputStream fis = new FileInputStream( file );

            int nread;
            while ( (nread = fis.read( buf )) > 0 && !detector.isDone() )
            {
                detector.handleData( buf, 0, nread );
            }
            detector.dataEnd();

            return detector.getDetectedCharset();
        }
        catch ( final Exception e )
        {
            e.printStackTrace();
        }
        finally
        {
            detector.reset();
        }
        return Charset.defaultCharset().name();
    }

    private static String printThrowable(
        final Throwable e )
    {
        final StringWriter stringWriter = new StringWriter();
        e.printStackTrace( new PrintWriter( stringWriter ) );
        return stringWriter.toString();
    }

    public ReaderDialog(
        final QWidget parent,
        final Book book )
    {
        super( parent );
        this.book = book;

        initComponents();
        initListeners();
    }

    public void retranslate()
    {
        backward.setText( tr( "Backward" ) );
        forward.setText( tr( "Forward" ) );
    }

    private String getContent(
        final File file )
    {
        try
        {
            return new String( getBytesFromFile( file ), guessEncoding( file ) );
        }
        catch ( final IOException e )
        {
            e.printStackTrace();
            return tr( "Error displaying file " ) + file.getAbsolutePath() + "\n\n" + printThrowable( e );
        }
    }

    private void initComponents()
    {
        setWindowTitle( book.getName() );

        setWindowState( WindowState.WindowMaximized );

        setLayout( new QVBoxLayout() );
        final QToolBar toolBar = new QToolBar( this );
        layout().addWidget( toolBar );
        layout().addWidget( textEdit );

        toolBar.addAction( backward );
        toolBar.addAction( forward );

        textEdit.setReadOnly( true );
        textEdit.setText( getContent( getFile( book ) ) );
    }

    private void initListeners()
    {
        // wait for the size to be stabilized to scroll
        textEdit.verticalScrollBar().rangeChanged.connect( this, "rangeChanged(Integer,Integer)" );

        textEdit.backwardAvailable.connect( backward, "setEnabled(boolean)" );
        textEdit.forwardAvailable.connect( forward, "setEnabled(boolean)" );
        backward.triggered.connect( textEdit, "backward()" );
        forward.triggered.connect( textEdit, "forward()" );

        backward.setEnabled( textEdit.isBackwardAvailable() );
        forward.setEnabled( textEdit.isForwardAvailable() );
    }

    @SuppressWarnings( "unused" )
    private void rangeChanged(
        final Integer min,
        final Integer max )
    {
        // todo apply only at first change
        final QScrollBar scrollBar = textEdit.verticalScrollBar();
        scrollBar.setSliderPosition( (int) (book.getRead() * max) );
    }

    @Override
    protected void closeEvent(
        final QCloseEvent arg__1 )
    {
        // save current position
        final QScrollBar scrollBar = textEdit.verticalScrollBar();
        book.setRead( (float) scrollBar.sliderPosition() / (float) scrollBar.maximum() );

        super.closeEvent( arg__1 );
    }
}
