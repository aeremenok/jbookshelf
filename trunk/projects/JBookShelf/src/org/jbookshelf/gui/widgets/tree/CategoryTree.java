package org.jbookshelf.gui.widgets.tree;

import javax.swing.tree.DefaultMutableTreeNode;

import org.eclipse.emf.ecore.EReference;
import org.jbookshelf.Categorizable;
import org.jbookshelf.Category;
import org.jbookshelf.JbookshelfPackage;
import org.jbookshelf.ReadingUnit;

public class CategoryTree
    extends CollectionTree
{
    @Override
    protected void addChildren(
        UniqueNode parent )
    {
        DefaultMutableTreeNode authors = new DefaultMutableTreeNode( "Authors" );
        DefaultMutableTreeNode books = new DefaultMutableTreeNode( "Books" );
        Category category = (Category) parent.getUnique();
        for ( Categorizable categorizable : category.getCategorizables() )
        {
            UniqueNode node = new UniqueNode( categorizable );
            if ( categorizable instanceof ReadingUnit )
            {
                books.add( node );
            }
            else
            {
                authors.add( node );
            }
        }

        if ( books.getChildCount() > 0 )
        {
            parent.add( books );
        }
        if ( authors.getChildCount() > 0 )
        {
            parent.add( authors );
        }
    }

    @Override
    protected EReference getReference()
    {
        return JbookshelfPackage.eINSTANCE.getBookShelf_Categories();
    }
}
