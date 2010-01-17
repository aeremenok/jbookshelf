/**
 * 
 */
package org.jbookshelf.view.swinggui.reader;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.log4j.PropertyConfigurator;
import org.jbookshelf.controller.settings.Settings;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.util.DBUtil;
import org.jbookshelf.view.swinggui.main.MainWindow;
import org.jbookshelf.view.swinggui.reader.navigation.thumbnails.Thumbnail;
import org.jbookshelf.view.swinggui.reader.textview.ContentRenderer;
import org.jbookshelf.view.swinggui.reader.types.html.HTMLReaderSpecific;
import org.jbookshelf.view.swinggui.reader.types.html.HTMLRenderer;
import org.jbookshelf.view.swinggui.reader.types.pdf.PDFReaderSpecific;
import org.jbookshelf.view.swinggui.reader.types.pdf.PDFRenderer;
import org.jbookshelf.view.swinggui.reader.types.pdf.PDFThumbnail;
import org.jbookshelf.view.swinggui.reader.types.rtf.RTFReaderSpecific;
import org.jbookshelf.view.swinggui.reader.types.rtf.RTFRenderer;
import org.jbookshelf.view.swinggui.reader.types.txt.TXTReaderSpecific;
import org.jbookshelf.view.swinggui.reader.types.txt.TXTRenderer;
import org.jbookshelf.view.swinggui.widget.LookAndFeelComboBoxModel;

/**
 * properly initializes and starts the reader
 * 
 * @author eav 2009
 */
public class ViewerApp
{
    public static void main(
        final String[] args )
    {
        PropertyConfigurator.configure( MainWindow.class.getResource( "log4j.properties" ) );
        final String jbsDir = Single.instance( Settings.class ).JBS_DIR.getValue();
        Single.instance( DBUtil.class ).startup( jbsDir );
        try
        {
            final String laf = Single.instance( Settings.class ).LAF.getValue();
            UIManager.setLookAndFeel( LookAndFeelComboBoxModel.fromName( laf ) );
        }
        catch ( final UnsupportedLookAndFeelException e )
        {}

        final Long id = Long.valueOf( args[0] );
        final String type = args[1];

        // todo do not wait for db call - open immediately
        //        final File file = args.length > 2
        //            ? new File( args[2] ) : null;

        if ( Viewer.TXT.equals( type ) )
        {
            Single.setImplementation( ReaderSpecific.class, TXTReaderSpecific.class );
            Single.setImplementation( ContentRenderer.class, TXTRenderer.class );
        }
        else if ( Viewer.PDF.equals( type ) )
        {
            Single.setImplementation( ReaderSpecific.class, PDFReaderSpecific.class );
            Single.setImplementation( ContentRenderer.class, PDFRenderer.class );
            Single.setImplementation( Thumbnail.class, PDFThumbnail.class );
        }
        else if ( Viewer.RTF.equals( type ) )
        {
            Single.setImplementation( ReaderSpecific.class, RTFReaderSpecific.class );
            Single.setImplementation( ContentRenderer.class, RTFRenderer.class );
        }
        else
        //if ( Viewer.HTML.equals( type ) )
        {
            Single.setImplementation( ReaderSpecific.class, HTMLReaderSpecific.class );
            Single.setImplementation( ContentRenderer.class, HTMLRenderer.class );
        }

        EventQueue.invokeLater( new Runnable()
        {
            @Override
            public void run()
            {
                final ReaderWindow readerWindow = Single.instance( ReaderWindow.class );
                readerWindow.setVisible( true );
                readerWindow.loadAndDisplayBook( id );
            }
        } );
    }
}
