package org.jbookshelf.swinggui.widgets.tree;

import java.util.List;

import org.jbookshelf.i18n.I18N;
import org.jbookshelf.model.Book;
import org.jbookshelf.model.BookShelf;
import org.jbookshelf.model.Categorizable;
import org.jbookshelf.model.Category;
import org.jbookshelf.model.Unique;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;
import org.jdesktop.swingx.treetable.DefaultTreeTableModel;

public class CategoryTree
    extends CollectionTree
{
    @Override
    public void initSingleton()
    {
        super.initSingleton();
        setName( "Categories" );
        // TODO Auto-generated method stub
    }

    @Override
    protected void addChildren(
        final UniqueNode parent )
    {
        final DefaultTreeTableModel model = (DefaultTreeTableModel) getTreeTableModel();
        final DefaultMutableTreeTableNode authorNode = new DefaultMutableTreeTableNode( I18N.tr( "Authors" ) );
        final DefaultMutableTreeTableNode bookNode = new DefaultMutableTreeTableNode( I18N.tr( "Books" ) );

        model.insertNodeInto( authorNode, parent, 0 );
        model.insertNodeInto( bookNode, parent, 1 );

        final Category category = (Category) parent.getUnique();
        for ( final Categorizable categorizable : category.getCategorizables() )
        {
            final UniqueNode uniqueNode = new UniqueNode( categorizable );
            if ( categorizable instanceof Book )
            {
                model.insertNodeInto( uniqueNode, bookNode, bookNode.getChildCount() );
            }
            else
            {
                model.insertNodeInto( uniqueNode, authorNode, authorNode.getChildCount() );
            }
        }
    }

    @Override
    protected List<? extends Unique> getUniques(
        final BookShelf bookShelf )
    {
        return bookShelf.getCategories();
    }

}
