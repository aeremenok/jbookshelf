package org.jbookshelf.qtgui.widgets.dialog;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.jbookshelf.model.ArchiveFile;
import org.jbookshelf.model.IndexFileFolder;
import org.jbookshelf.model.PhysicalUnit;
import org.jbookshelf.model.ReadingUnit;
import org.jbookshelf.model.SingleFile;
import org.jbookshelf.model.SingleFileFolder;
import org.jbookshelf.qtgui.widgets.ext.QDialogExt;
import org.mozilla.universalchardet.UniversalDetector;

import com.trolltech.qt.core.Qt.WindowState;
import com.trolltech.qt.gui.QMessageBox;
import com.trolltech.qt.gui.QTextEdit;
import com.trolltech.qt.gui.QVBoxLayout;
import com.trolltech.qt.gui.QWidget;

public class ReaderDialog
    extends QDialogExt
{
    private final QTextEdit       textEdit   = new QTextEdit( this );
    private final ReadingUnit     book;

    private final static String[] extensions = { "txt", "html", "htm", "shtml" };

    public static byte[] getBytesFromFile(
        File file )
        throws IOException
    {
        InputStream is = new FileInputStream( file );

        // Get the size of the file
        long length = file.length();

        if ( length > Integer.MAX_VALUE )
        {
            // File is too large
        }

        // Create the byte array to hold the data
        byte[] bytes = new byte[(int) length];

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
        QWidget parent,
        ReadingUnit book )
    {
        File file = getFile( book );
        if ( canBeOpened( file ) )
        {
            new ReaderDialog( parent, book ).show();
        }
        else
        {
            try
            {
                book.getPhysical().openUnit();
            }
            catch ( Throwable e )
            {
                e.printStackTrace();
                QMessageBox.critical( parent, "Error", "Error opening file " + file.getAbsolutePath() );
            }
        }
    }

    private static boolean canBeOpened(
        File file )
    {
        String lowerCase = file.getName().toLowerCase();
        for ( String string : extensions )
        {
            if ( lowerCase.endsWith( "." + string ) )
            {
                return true;
            }
        }
        return false;
    }

    private static File getFile(
        ReadingUnit book )
    {
        File file = null;
        PhysicalUnit physical = book.getPhysical();
        if ( physical instanceof ArchiveFile )
        {
            ArchiveFile archiveFile = (ArchiveFile) physical;
            file = archiveFile.getArchiveFile();
        }
        else if ( physical instanceof SingleFile )
        {
            SingleFile singleFile = (SingleFile) physical;
            file = singleFile.getFile();
        }
        else if ( physical instanceof SingleFileFolder )
        {
            SingleFileFolder singleFileFolder = (SingleFileFolder) physical;
            file = singleFileFolder.getSingleFile();
        }
        else if ( physical instanceof IndexFileFolder )
        {
            IndexFileFolder indexFileFolder = (IndexFileFolder) physical;
            file = indexFileFolder.getIndexFile();
        }
        return file;
    }

    private static String guessEncoding(
        File file )
    {
        UniversalDetector detector = new UniversalDetector( null );
        try
        {
            byte[] buf = new byte[4096];
            FileInputStream fis = new FileInputStream( file );

            int nread;
            while ( (nread = fis.read( buf )) > 0 && !detector.isDone() )
            {
                detector.handleData( buf, 0, nread );
            }
            detector.dataEnd();

            return detector.getDetectedCharset();
        }
        catch ( Exception e )
        {
            e.printStackTrace();
            return Charset.defaultCharset().name();
        }
        finally
        {
            detector.reset();
        }
        // todo ZIPOpener.guessEncoding()
    }

    public ReaderDialog(
        QWidget parent,
        ReadingUnit book )
    {
        super( parent );
        this.book = book;

        initComponents();
    }

    public void retranslate()
    {
    }

    private String getContent(
        File file )
    {
        try
        {
            return new String( getBytesFromFile( file ), guessEncoding( file ) );
        }
        catch ( IOException e )
        {
            e.printStackTrace();
            return "Error displaying file";
        }
    }

    private void initComponents()
    {
        setWindowTitle( book.getName() );

        setWindowState( WindowState.WindowMaximized );

        setLayout( new QVBoxLayout() );
        layout().addWidget( textEdit );
        textEdit.setReadOnly( true );
        textEdit.setText( getContent( getFile( book ) ) );
    }
}
