package org.jbookshelf.gui.widgets.tree;

import javax.swing.tree.DefaultMutableTreeNode;

import org.eclipse.emf.ecore.EReference;
import org.jbookshelf.model.Author;
import org.jbookshelf.model.Category;
import org.jbookshelf.model.ModelPackage;
import org.jbookshelf.model.ReadingUnit;

public class BookTree
    extends CollectionTree
{
    @Override
    protected void addChildren(
        UniqueNode parent )
    {
        DefaultMutableTreeNode authors = new DefaultMutableTreeNode( "Authors" );
        DefaultMutableTreeNode categories = new DefaultMutableTreeNode( "Categories" );
        ReadingUnit readingUnit = (ReadingUnit) parent.getUnique();

        for ( Category category : readingUnit.getCategories() )
        {
            categories.add( new UniqueNode( category ) );
        }
        for ( Author author : readingUnit.getAuthors() )
        {
            authors.add( new UniqueNode( author ) );
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
        return ModelPackage.eINSTANCE.getBookShelf_ReadingUnits();
    }

}