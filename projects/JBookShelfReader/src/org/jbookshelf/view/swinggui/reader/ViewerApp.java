/**
 * 
 */
package org.jbookshelf.view.swinggui.reader;

import java.awt.EventQueue;
import java.io.File;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.log4j.PropertyConfigurator;
import org.jbookshelf.controller.settings.Settings;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.util.HibernateUtil;
import org.jbookshelf.view.swinggui.main.MainWindow;
import org.jbookshelf.view.swinggui.reader.html.HTMLReaderFactory;
import org.jbookshelf.view.swinggui.reader.pdf.PDFReaderFactory;
import org.jbookshelf.view.swinggui.reader.rtf.RTFReaderFactory;
import org.jbookshelf.view.swinggui.reader.txt.PlainTextReaderFactory;
import org.jbookshelf.view.swinggui.widget.LookAndFeelComboBoxModel;

/**
 * @author eav 2009
 */
public class ViewerApp
{
    @SuppressWarnings( "unchecked" )
    public static void main(
        final String[] args )
    {
        PropertyConfigurator.configure( MainWindow.class.getResource( "log4j.properties" ) );
        HibernateUtil.main( args );

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
        final File file = args.length > 2
            ? new File( args[2] ) : null;

        final ReaderFactory factory;
        if ( Viewer.TXT.equals( type ) )
        {
            factory = new PlainTextReaderFactory();
        }
        else if ( Viewer.PDF.equals( type ) )
        {
            factory = new PDFReaderFactory();
        }
        else if ( Viewer.RTF.equals( type ) )
        {
            factory = new RTFReaderFactory();
        }
        else
        //if ( Viewer.HTML.equals( type ) )
        {
            factory = new HTMLReaderFactory();
        }

        final ReaderWindow readerWindow = new ReaderWindow( id, file, factory );
        EventQueue.invokeLater( new Runnable()
        {
            @Override
            public void run()
            {
                readerWindow.setVisible( true );
            }
        } );
    }
}
