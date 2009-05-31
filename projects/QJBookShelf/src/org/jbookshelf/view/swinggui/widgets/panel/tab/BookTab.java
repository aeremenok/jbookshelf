/**
 * 
 */
package org.jbookshelf.view.swinggui.widgets.panel.tab;

import java.awt.BorderLayout;

import javax.swing.table.DefaultTableModel;

import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.swinggui.widgets.panel.SearchParameters;
import org.jdesktop.swingx.JXTable;

/**
 * @author eav
 */
public class BookTab
    extends CollectionTab
{
    private static class BookTableModel
        extends DefaultTableModel
    {
        private static String[] names =
                                      { I18N.tr( "Name" ), I18N.tr( "Author" ), I18N.tr( "Category" ) };

        public BookTableModel()
        {
            super( names, 0 );
        }

        /**
         * @param p
         */
        public void search(
            final SearchParameters p )
        {
        // todo
        }
    }

    private final JXTable        table = new JXTable();
    private final BookTableModel model = new BookTableModel();

    public BookTab()
    {
        super();
        setName( "Books" );
        setLayout( new BorderLayout() );
        add( table, BorderLayout.CENTER );

        table.setModel( model );
    }

    @Override
    public void search(
        final SearchParameters p )
    {
        model.search( p );
    }
}
