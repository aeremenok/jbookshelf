package org.jbookshelf.gui.tree;

import javax.swing.tree.DefaultMutableTreeNode;

import org.eclipse.emf.ecore.EReference;
import org.jbookshelf.Categorizable;
import org.jbookshelf.Category;
import org.jbookshelf.JbookshelfPackage;
import org.jbookshelf.ReadingUnit;
import org.jbookshelf.Unique;

public class CategoryTree
    extends CollectionTree
{
    @Override
    protected void addChildren(
        Unique unique,
        DefaultMutableTreeNode parent )
    {
        DefaultMutableTreeNode authors = new DefaultMutableTreeNode( "Authors" );
        DefaultMutableTreeNode books = new DefaultMutableTreeNode( "Books" );
        Category category = (Category) unique;
        for ( Categorizable categorizable : category.getCategorizables() )
        {
            if ( categorizable instanceof ReadingUnit )
            {
                books.add( new DefaultMutableTreeNode( categorizable.getName() ) );
            }
            else
            {
                authors.add( new DefaultMutableTreeNode( categorizable.getName() ) );
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
