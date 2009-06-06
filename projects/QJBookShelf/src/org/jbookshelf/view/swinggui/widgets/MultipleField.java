package org.jbookshelf.view.swinggui.widgets;

import images.IMG;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;

import javax.annotation.Nonnull;
import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXTable;

/**
 * allows to specify multiple values
 * 
 * @author eav 2009
 * @param <T> value type ( {@link String} by default )
 */
public class MultipleField<T>
    extends JPanel
{
    private final class AddAction
        extends AbstractAction
    {
        private AddAction()
        {
            super( null, IMG.icon( IMG.LIST_ADD_SMALL_PNG ) );
        }

        @Override
        public void actionPerformed(
            final ActionEvent e )
        {
            addValue();
        }
    }

    protected final ListTableModel<T> model = new ListTableModel<T>();
    protected final JXTable           table = new JXTable( model );
    protected final JTextField        field = new JTextField();

    public MultipleField()
    {
        super( new BorderLayout() );
        initComponents();
        initListeners();
    }

    public void clear()
    {
        model.clear();
    }

    public Collection<T> getValues()
    {
        return model.getValues();
    }

    public void setValues(
        final Collection<T> objects )
    {
        model.setValues( objects );
    }

    /**
     * 
     */
    private void addValue()
    {
        final String text = field.getText();
        if ( !"".equals( text ) )
        {
            model.addValue( fromString( text ) );
            field.setText( "" );
        }
    }

    private void initComponents()
    {
        final Box horizontalBox = Box.createHorizontalBox();
        horizontalBox.add( field );
        horizontalBox.add( new JButton( new AddAction() ) );
        add( horizontalBox, BorderLayout.NORTH );

        add( new JScrollPane( table ), BorderLayout.CENTER );

        table.setTableHeader( null );
        table.setPreferredScrollableViewportSize( new Dimension( 0, 50 ) );
        table.getColumn( 1 ).setMaxWidth( 40 );
    }

    private void initListeners()
    {
        // allow deletion of rows
        table.addMouseListener( new MouseAdapter()
        {
            @Override
            public void mouseClicked(
                final MouseEvent e )
            {
                if ( e.getButton() == MouseEvent.BUTTON1 )
                {
                    final int col = table.columnAtPoint( e.getPoint() );
                    if ( col == 1 || e.getClickCount() == 2 )
                    {
                        model.removeRow( table.rowAtPoint( e.getPoint() ) );
                    }
                }
            }
        } );

        field.addKeyListener( new KeyAdapter()
        {
            @Override
            public void keyReleased(
                final KeyEvent e )
            {
                if ( e.getKeyCode() == KeyEvent.VK_ENTER )
                {
                    addValue();
                }
            }
        } );
    }

    /**
     * convert string to value
     * 
     * @param text source {@link String}
     * @return dest value
     */
    @SuppressWarnings( "unchecked" )
    protected T fromString(
        @Nonnull final String text )
    {
        return (T) text;
    }
}
