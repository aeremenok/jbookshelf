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

import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.view.swinggui.reader.ReaderWindow;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 * @author eav 2009
 */
public class CharsetChooser
    extends JPanel
    implements
    ItemListener
{
    private final JComboBox comboBox = new JComboBox();

    public CharsetChooser()
    {
        super( new BorderLayout() );
        add( comboBox, BorderLayout.CENTER );
        for ( final String name : Charset.availableCharsets().keySet() )
        {
            comboBox.addItem( name );
        }

        comboBox.addItemListener( this );

        comboBox.setEditable( true );
        AutoCompleteDecorator.decorate( comboBox );
    }

    public Charset getCharset()
    {
        return Charset.availableCharsets().get( comboBox.getSelectedItem() );
    }

    @Override
    public void itemStateChanged(
        final ItemEvent e )
    {
        if ( ItemEvent.SELECTED == e.getStateChange() )
        {
            EventQueue.invokeLater( new Runnable()
            {
                @Override
                public void run()
                {
                    Single.instance( ReaderWindow.class ).useCharset( getCharset() );
                }
            } );
        }
    }

    public void setCharset(
        final Charset charset )
    {
        setCharset( charset.name() );
    }

    public void setCharset(
        final String charsetName )
    {
        comboBox.removeItemListener( this );
        comboBox.setSelectedItem( charsetName );
        comboBox.addItemListener( this );
    }
}