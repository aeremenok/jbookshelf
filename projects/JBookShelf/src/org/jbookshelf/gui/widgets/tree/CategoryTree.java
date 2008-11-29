package org.jbookshelf.gui.widgets.tree;

import javax.swing.tree.DefaultMutableTreeNode;

import org.eclipse.emf.ecore.EReference;
import org.jbookshelf.model.Categorizable;
import org.jbookshelf.model.Category;
import org.jbookshelf.model.ModelPackage;
import org.jbookshelf.model.ReadingUnit;

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
        return ModelPackage.eINSTANCE.getBookShelf_Categories();
    }
}
