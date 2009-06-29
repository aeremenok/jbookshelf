/**
 * 
 */
package org.jbookshelf.view.swinggui.reader;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.log4j.Logger;

import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 * @author eav 2009
 */
public class ReaderWindow
    extends JFrame
{
    @SuppressWarnings( "unused" )
    private static final Logger log = Logger.getLogger( ReaderWindow.class );

    public static void main(
        final String[] args )
    {
        try
        {
            UIManager.setLookAndFeel( new NimbusLookAndFeel() );
            new ReaderWindow().setVisible( true );
        }
        catch ( final UnsupportedLookAndFeelException e )
        {
            e.printStackTrace();
        }
    }

    private final ReaderToolBar      toolBar           = new ReaderToolBar( this );
    private final ReaderContentPanel leftContentPanel  = new ReaderContentPanel( this );
    private final ReaderContentPanel rightContentPanel = new ReaderContentPanel( this );

    public ReaderWindow()
    {
        super();
        setContentPane( new JPanel( new BorderLayout() ) );

        add( toolBar, BorderLayout.NORTH );

        final JSplitPane splitPane = new JSplitPane();
        add( splitPane );
        splitPane.setLeftComponent( leftContentPanel );
        splitPane.setRightComponent( rightContentPanel );

        pack();
        setExtendedState( Frame.MAXIMIZED_BOTH );
    }

}
