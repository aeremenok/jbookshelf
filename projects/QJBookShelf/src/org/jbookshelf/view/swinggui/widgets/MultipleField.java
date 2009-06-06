package org.jbookshelf.view.swinggui.widgets;

import images.IMG;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.jbookshelf.model.db.BookShelf;
import org.jbookshelf.model.db.Unique;
import org.jdesktop.swingx.JXTable;
import org.xnap.commons.gui.Builder;

/**
 * allows to specify multiple values
 * 
 * @author eav 2009
 * @param <T> value type
 */
public class MultipleField<T extends Unique>
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
            final String text = field.getText();
            if ( !"".equals( text ) )
            {
                model.addValue( BookShelf.getOrAddUnique( clazz, text ) );
                field.setText( "" );
            }
        }
    }

    private final ListTableModel<T> model = new ListTableModel<T>();
    private final JTextField        field = new JTextField();
    private final Class<T>          clazz;

    public MultipleField(
        final Class<T> clazz )
    {
        super( new BorderLayout() );
        this.clazz = clazz;

        field.setEditable( true );

        final Box horizontalBox = Box.createHorizontalBox();
        horizontalBox.add( field );
        horizontalBox.add( new JButton( new AddAction() ) );
        add( horizontalBox, BorderLayout.NORTH );

        final JXTable table = new JXTable( model );
        add( new JScrollPane( table ), BorderLayout.CENTER );

        table.setTableHeader( null );
        table.setPreferredScrollableViewportSize( new Dimension( 0, 50 ) );
        table.getColumn( 1 ).setMaxWidth( 40 );

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
                    if ( col == 1 )
                    {
                        model.removeRow( table.rowAtPoint( e.getPoint() ) );
                    }
                }
            }
        } );

        // add name completion
        Builder.addCompletion( field, new UniqueCompletionModel( clazz ) );
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

}
