package org.jbookshelf.view.swinggui.multiedit;

import icons.IMG;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.jbookshelf.view.i18n.Translatable;
import org.jbookshelf.view.swinggui.actions.EnterKeyListener;
import org.jbookshelf.view.swinggui.widget.ChangeDocumentListener;
import org.jdesktop.swingx.JXTable;
import org.xnap.commons.i18n.I18n;

/**
 * allows to specify multiple values
 * 
 * @author eav 2009
 * @param <T> value type ( {@link String} by default )
 */
public class MultipleField<T>
    extends JPanel
    implements
    Translatable
{
    private final class AddAction
        extends AbstractAction
    {
        private AddAction()
        {
            super( null, IMG.icon( IMG.LIST_ADD_PNG ) );
        }

        @Override
        public void actionPerformed(
            final ActionEvent e )
        {
            final String text = field.getText();
            if ( !"".equals( text ) )
            {
                final T fromString = fromString( text );
                if ( fromString != null )
                {
                    model.addValue( fromString );
                    field.setText( "" );
                }
            }
        }
    }

    protected final ListTableModel<T> model     = new ListTableModel<T>();
    protected final JXTable           table     = new JXTable( model );
    protected final JTextField        field     = new JTextField();

    private final AddAction           addAction = new AddAction();

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

    public List<T> getValues()
    {
        return model.getValues();
    }

    public void setValues(
        final Collection<T> objects )
    {
        model.setValues( objects );
    }

    @Override
    public void translate(
        final I18n i18n )
    {
        field.setToolTipText( i18n.tr( "Type here to begin addition" ) );
    }

    private void initComponents()
    {
        final Box horizontalBox = Box.createHorizontalBox();
        horizontalBox.add( field );
        horizontalBox.add( new JButton( addAction ) );
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

        field.addKeyListener( new EnterKeyListener( addAction ) );
        field.getDocument().addDocumentListener( new ChangeDocumentListener( field )
        {
            @Override
            public void onChange(
                final String newText )
            {
                addAction.setEnabled( !"".equals( newText ) );
            }
        } );
        addAction.setEnabled( false );
    }

    /**
     * convert string to value
     * 
     * @param text source {@link String}
     * @return dest value
     */
    @SuppressWarnings( "unchecked" )
    protected T fromString(
        final String text )
    { // can be overriden
        return (T) text;
    }
}
