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
import org.jbookshelf.view.swinggui.main.MainWindow;
import org.jbookshelf.view.swinggui.reader.textpanel.ReaderContentPanel;
import org.jbookshelf.view.swinggui.reader.textpanel.navigate.ThumbnailPanel;
import org.jbookshelf.view.swinggui.reader.toolbar.ReaderToolBar;
import org.jbookshelf.view.swinggui.reader.types.html.HTMLReaderFactory;
import org.jbookshelf.view.swinggui.reader.types.html.HTMLReaderPanel;
import org.jbookshelf.view.swinggui.reader.types.html.HTMLToolBar;
import org.jbookshelf.view.swinggui.reader.types.pdf.PDFPanel;
import org.jbookshelf.view.swinggui.reader.types.pdf.PDFReaderFactory;
import org.jbookshelf.view.swinggui.reader.types.pdf.PDFThumbnailPanel;
import org.jbookshelf.view.swinggui.reader.types.pdf.PDFToolBar;
import org.jbookshelf.view.swinggui.reader.types.rtf.RTFPanel;
import org.jbookshelf.view.swinggui.reader.types.rtf.RTFReaderFactory;
import org.jbookshelf.view.swinggui.reader.types.rtf.RTFToolBar;
import org.jbookshelf.view.swinggui.reader.types.txt.PlainTextReaderFactory;
import org.jbookshelf.view.swinggui.reader.types.txt.PlainTextToolBar;
import org.jbookshelf.view.swinggui.reader.types.txt.PlaintTextPanel;
import org.jbookshelf.view.swinggui.widget.LookAndFeelComboBoxModel;

/**
 * @author eav 2009
 */
public class ViewerApp
{
    public static void main(
        final String[] args )
    {
        PropertyConfigurator.configure( MainWindow.class.getResource( "log4j.properties" ) );

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
            Single.setImplementation( ReaderFactory.class, PlainTextReaderFactory.class );
            Single.setImplementation( ReaderContentPanel.class, PlaintTextPanel.class );
            Single.setImplementation( ReaderToolBar.class, PlainTextToolBar.class );
        }
        else if ( Viewer.PDF.equals( type ) )
        {
            Single.setImplementation( ReaderFactory.class, PDFReaderFactory.class );
            Single.setImplementation( ReaderContentPanel.class, PDFPanel.class );
            Single.setImplementation( ThumbnailPanel.class, PDFThumbnailPanel.class );
            Single.setImplementation( ReaderToolBar.class, PDFToolBar.class );
        }
        else if ( Viewer.RTF.equals( type ) )
        {
            Single.setImplementation( ReaderFactory.class, RTFReaderFactory.class );
            Single.setImplementation( ReaderContentPanel.class, RTFPanel.class );
            Single.setImplementation( ReaderToolBar.class, RTFToolBar.class );
        }
        else
        //if ( Viewer.HTML.equals( type ) )
        {
            Single.setImplementation( ReaderFactory.class, HTMLReaderFactory.class );
            Single.setImplementation( ReaderContentPanel.class, HTMLReaderPanel.class );
            Single.setImplementation( ReaderToolBar.class, HTMLToolBar.class );
        }

        EventQueue.invokeLater( new Runnable()
        {
            @Override
            public void run()
            {
                final ReaderWindow readerWindow = Single.instance( ReaderWindow.class );
                readerWindow.setVisible( true );
                readerWindow.setBook( id );
            }
        } );
    }
}
