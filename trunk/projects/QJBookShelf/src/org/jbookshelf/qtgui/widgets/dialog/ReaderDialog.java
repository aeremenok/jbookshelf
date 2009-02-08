package org.jbookshelf.qtgui.widgets.dialog;

import org.jbookshelf.model.ArchiveFile;
import org.jbookshelf.model.IndexFileFolder;
import org.jbookshelf.model.PhysicalUnit;
import org.jbookshelf.model.ReadingUnit;
import org.jbookshelf.model.SingleFile;
import org.jbookshelf.model.SingleFileFolder;
import org.jbookshelf.qtgui.widgets.ext.QDialogExt;

import com.trolltech.qt.core.QUrl;
import com.trolltech.qt.gui.QVBoxLayout;
import com.trolltech.qt.gui.QWidget;
import com.trolltech.qt.webkit.QWebView;

public class ReaderDialog
    extends QDialogExt
{
    private QWebView          webView = new QWebView( this );
    private final ReadingUnit book;

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

    private void initComponents()
    {
        setWindowTitle( book.getName() );

        setLayout( new QVBoxLayout() );
        layout().addWidget( webView );

        String url = "";
        PhysicalUnit physical = book.getPhysical();
        if ( physical instanceof ArchiveFile )
        {
            ArchiveFile archiveFile = (ArchiveFile) physical;
            url = archiveFile.getArchiveFile().getAbsolutePath();
        }
        else if ( physical instanceof SingleFile )
        {
            SingleFile singleFile = (SingleFile) physical;
            url = singleFile.getFile().getAbsolutePath();
        }
        else if ( physical instanceof SingleFileFolder )
        {
            SingleFileFolder singleFileFolder = (SingleFileFolder) physical;
            url = singleFileFolder.getSingleFile().getAbsolutePath();
        }
        else if ( physical instanceof IndexFileFolder )
        {
            IndexFileFolder indexFileFolder = (IndexFileFolder) physical;
            url = indexFileFolder.getIndexFile().getAbsolutePath();
        }
        System.out.println( "url=" + url );
        webView.load( new QUrl( url ) );
    }

}
