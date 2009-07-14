/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.toolbar;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.nio.charset.Charset;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 * @author eav 2009
 */
public class CharsetChooser
    extends JPanel
{
    public static final String CURRENT_CHARSET = "CURRENT_CHARSET";
    private final JComboBox    comboBox        = new JComboBox();

    public CharsetChooser()
    {
        super( new BorderLayout() );
        add( comboBox, BorderLayout.CENTER );
        for ( final String name : Charset.availableCharsets().keySet() )
        {
            comboBox.addItem( name );
        }

        comboBox.addItemListener( new ItemListener()
        {
            @Override
            public void itemStateChanged(
                final ItemEvent e )
            {
                if ( e.getStateChange() == ItemEvent.SELECTED )
                {
                    EventQueue.invokeLater( new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            firePropertyChange( CURRENT_CHARSET, null, getCharset() );
                        }
                    } );
                }
            }
        } );

        comboBox.setEditable( true );
        AutoCompleteDecorator.decorate( comboBox );
    }

    public Charset getCharset()
    {
        return Charset.availableCharsets().get( comboBox.getSelectedItem() );
    }

    public void setCharset(
        final Charset charset )
    {
        setCharset( charset.name() );
    }

    public void setCharset(
        final String charsetName )
    {
        comboBox.setSelectedItem( charsetName );
    }
}