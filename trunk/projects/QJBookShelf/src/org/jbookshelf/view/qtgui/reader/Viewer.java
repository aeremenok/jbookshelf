/**
 * 
 */
package org.jbookshelf.view.qtgui.reader;

import java.io.File;

import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.controller.util.URIUtil;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.model.db.PhysicalBook;
import org.jbookshelf.view.swinggui.MainWindow;
import org.jbookshelf.view.swinggui.widgets.ProgressBar;
import org.jbookshelf.view.swinggui.widgets.SafeWorker;

import com.trolltech.qt.core.QCoreApplication;
import com.trolltech.qt.gui.QApplication;

/**
 * @author eav 2009
 */
public class Viewer
{
    private final static String[] extensions =
                                             { "txt", "html", "htm", "shtml"
                                             // todo import rich text
                                             // , "doc", "odt", "rtf"
                                             };

    /**
     * get a file with book content
     * 
     * @param book a book to open
     * @return a file with book content
     */
    public File getFile(
        final Book book )
    {
        final PhysicalBook physical = book.getPhysicalBook();
        //        if ( physical instanceof ArchiveFile )
        //        {
        //            final ArchiveFile archiveFile = (ArchiveFile) physical;
        //            if ( archiveFile.getArchiveFile() == null || !archiveFile.getArchiveFile().exists() )
        //            { // unpack and remember the file
        //                final QMessageBox messageBox = new QMessageBox( Single.instance( MainWindow.class ) );
        //                messageBox.setWindowTitle( "Unpacking. Please wait..." );
        //                messageBox.show();
        //
        //                final File zippedFileToOpen = ZIPUtil.getZippedFileToOpen( archiveFile.getFile() );
        //                archiveFile.setArchiveFile( zippedFileToOpen );
        //
        //                messageBox.hide();
        //            }
        //            return archiveFile.getArchiveFile();
        //        }

        return physical.getFile();
    }

    public void open(
        final Book book )
    {
        // todo separate cases
        Single.instance( ProgressBar.class ).invoke( new SafeWorker<File, Object>()
        {
            @Override
            protected File doInBackground()
            {
                final File file = getFile( book );
                // define which viewer to use
                String viewer = book.getPhysicalBook().getViewer();
                if ( viewer == null )
                {
                    viewer = isSupported( file )
                        ? PhysicalBook.INTERNAL_VIEWER : PhysicalBook.SYSTEM_VIEWER;
                    book.getPhysicalBook().setViewer( viewer );
                }

                return file;
            }

            @Override
            protected void doneSafe()
            {
                final String viewer = book.getPhysicalBook().getViewer();
                if ( PhysicalBook.INTERNAL_VIEWER.equals( viewer ) )
                { // internal
                    new Thread()
                    {
                        @Override
                        public void run()
                        {
                            QApplication.initialize( MainWindow.args );
                            QCoreApplication.setApplicationVersion( MainWindow.VERSION );
                            QCoreApplication.setApplicationName( MainWindow.APP_NAME );
                            new ReaderWindow( book ).show();
                            QApplication.exec();
                        }
                    }.start();
                }
                else
                { // system default
                    URIUtil.browseFile( getQuiet() );
                }
            }
        } );
    }

    /**
     * @param file file to check
     * @return is this file supported by the internal viewer
     */
    private boolean isSupported(
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
}
