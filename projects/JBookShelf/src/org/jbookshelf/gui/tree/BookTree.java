package org.jbookshelf.gui.tree;

import javax.swing.tree.DefaultMutableTreeNode;

import org.eclipse.emf.ecore.EReference;
import org.jbookshelf.Author;
import org.jbookshelf.Category;
import org.jbookshelf.JbookshelfPackage;
import org.jbookshelf.ReadingUnit;
import org.jbookshelf.Unique;

public class BookTree
    extends CollectionTree
{
    @Override
    protected void addChildren(
        Unique unique,
        DefaultMutableTreeNode parent )
    {
        DefaultMutableTreeNode authors = new DefaultMutableTreeNode( "Authors" );
        DefaultMutableTreeNode categories = new DefaultMutableTreeNode( "Categories" );
        ReadingUnit readingUnit = (ReadingUnit) unique;

        for ( Category category : readingUnit.getCategories() )
        {
            categories.add( new DefaultMutableTreeNode( category.getName() ) );
        }
        for ( Author author : readingUnit.getAuthors() )
        {
            authors.add( new DefaultMutableTreeNode( author.getName() ) );
        }

        if ( authors.getChildCount() > 0 )
        {
            parent.add( authors );
        }
        if ( categories.getChildCount() > 0 )
        {
            parent.add( categories );
        }
    }

    @Override
    protected EReference getReference()
    {
        return JbookshelfPackage.eINSTANCE.getBookShelf_ReadingUnits();
    }

}
