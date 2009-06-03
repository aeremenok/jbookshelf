package org.jbookshelf.view.swinggui.widgets;

import images.IMG;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import org.jbookshelf.model.db.BookShelf;
import org.jbookshelf.model.db.Unique;
import org.jbookshelf.view.i18n.I18N;
import org.jdesktop.swingx.JXTable;

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
            model.addValue( BookShelf.getUnique( clazz, combo.getSelectedItem().toString() ) );
        }
    }

    private static class ListTableModel<V>
        extends DefaultTableModel
    {
        private final List<V>         values = new ArrayList<V>();

        private static final String[] names  =
                                             { I18N.tr( "Name" ), "" };

        public ListTableModel()
        {
            super( names, 0 );
        }

        public void addValue(
            final V value )
        {
            values.add( value );
            fireTableDataChanged();
        }

        public void clear()
        {
            values.clear();
            fireTableDataChanged();
        }

        @Override
        public int getRowCount()
        {
            return values != null
                ? values.size() : 0;
        }

        @Override
        public Object getValueAt(
            final int row,
            final int column )
        {
            switch ( column )
            {
                case 0:
                    return values.get( row );
                case 1:
                    return "-";
            }
            return super.getValueAt( row, column );
        }

        public Collection<V> getValues()
        {
            return this.values;
        }

        @Override
        public boolean isCellEditable(
            final int row,
            final int column )
        {
            return false;
        }

        public void setValues(
            final Collection<V> objects )
        {
            values.clear();
            values.addAll( objects );
            fireTableDataChanged();
        }
    }

    private final ListTableModel<T> model = new ListTableModel<T>();

    private final JComboBox         combo = new JComboBox();

    private final Class<T>          clazz;

    public MultipleField(
        final Class<T> clazz )
    {
        super( new BorderLayout() );
        this.clazz = clazz;

        combo.setEditable( true );

        final Box horizontalBox = Box.createHorizontalBox();
        horizontalBox.add( combo );
        horizontalBox.add( new JButton( new AddAction() ) );
        add( horizontalBox, BorderLayout.NORTH );

        final JXTable table = new JXTable( model );
        add( new JScrollPane( table ), BorderLayout.CENTER );

        table.setPreferredScrollableViewportSize( new Dimension( 0, 50 ) );
        table.getColumn( 1 ).setMaxWidth( 40 );
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
