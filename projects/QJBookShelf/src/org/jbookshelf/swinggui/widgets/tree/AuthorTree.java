package org.jbookshelf.swinggui.widgets.tree;

import java.util.List;

import org.jbookshelf.i18n.I18N;
import org.jbookshelf.model.Author;
import org.jbookshelf.model.Book;
import org.jbookshelf.model.BookShelf;
import org.jbookshelf.model.Category;
import org.jbookshelf.model.Unique;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;
import org.jdesktop.swingx.treetable.DefaultTreeTableModel;

public class AuthorTree
    extends CollectionTree
{
    @Override
    public void initSingleton()
    {
        super.initSingleton();
        setName( "Authors" );
        // TODO Auto-generated method stub
    }

    @Override
    protected void addChildren(
        final UniqueNode parent )
    {
        final DefaultTreeTableModel model = (DefaultTreeTableModel) getTreeTableModel();
        final DefaultMutableTreeTableNode bookNode = new DefaultMutableTreeTableNode( I18N.tr( "Books" ) );
        final DefaultMutableTreeTableNode categoryNode = new DefaultMutableTreeTableNode( I18N.tr( "Categories" ) );

        model.insertNodeInto( bookNode, parent, 0 );
        model.insertNodeInto( categoryNode, parent, 1 );

        final Author author = (Author) parent.getUnique();
        for ( int i = 0; i < author.getBooks().size(); i++ )
        {
            final Book book = author.getBooks().get( i );
            model.insertNodeInto( new UniqueNode( book ), bookNode, i );
        }
        for ( int i = 0; i < author.getCategories().size(); i++ )
        {
            final Category category = author.getCategories().get( i );
            model.insertNodeInto( new UniqueNode( category ), categoryNode, i );
        }
    }

    @Override
    protected List<? extends Unique> getUniques(
        final BookShelf bookShelf )
    {
        return bookShelf.getAuthors();
    }
}
