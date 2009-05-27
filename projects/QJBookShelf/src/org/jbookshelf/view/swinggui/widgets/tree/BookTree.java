package org.jbookshelf.view.swinggui.widgets.tree;

import org.jbookshelf.model.db.Book;
import org.jbookshelf.view.i18n.I18N;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;
import org.jdesktop.swingx.treetable.DefaultTreeTableModel;

public class BookTree
    extends CollectionTree
{

    @Override
    public void initSingleton()
    {
        super.initSingleton();
        setName( "Books" );
        // TODO Auto-generated method stub
    }

    @Override
    protected void addChildren(
        final UniqueNode parent )
    {
        final DefaultTreeTableModel model = (DefaultTreeTableModel) getTreeTableModel();
        final DefaultMutableTreeTableNode authorNode = new DefaultMutableTreeTableNode( I18N.tr( "Authors" ) );
        final DefaultMutableTreeTableNode categoryNode = new DefaultMutableTreeTableNode( I18N.tr( "Categories" ) );

        model.insertNodeInto( authorNode, parent, 0 );
        model.insertNodeInto( categoryNode, parent, 1 );

        final Book book = (Book) parent.getUnique();
        //        for ( int i = 0; i < book.getAuthors().size(); i++ )
        //        {
        //            final Author author = book.getAuthors().get( i );
        //            model.insertNodeInto( new UniqueNode( author ), authorNode, i );
        //        }
        //        for ( int i = 0; i < book.getCategories().size(); i++ )
        //        {
        //            final Category category = book.getCategories().get( i );
        //            model.insertNodeInto( new UniqueNode( category ), categoryNode, i );
        //        }
    }

    //    @Override
    //    protected List<? extends Unique> getUniques(
    //        final BookShelf bookShelf )
    //    {
    //        return bookShelf.getReadingUnits();
    //    }

}
