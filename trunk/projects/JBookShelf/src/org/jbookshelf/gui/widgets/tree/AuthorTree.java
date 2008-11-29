package org.jbookshelf.gui.widgets.tree;

import javax.swing.tree.DefaultMutableTreeNode;

import org.eclipse.emf.ecore.EReference;
import org.jbookshelf.model.Author;
import org.jbookshelf.model.Category;
import org.jbookshelf.model.ModelPackage;
import org.jbookshelf.model.ReadingUnit;

public class AuthorTree
    extends CollectionTree
{
    @Override
    protected void addChildren(
        UniqueNode parent )
    {
        DefaultMutableTreeNode books = new DefaultMutableTreeNode( "Books" );
        DefaultMutableTreeNode categories = new DefaultMutableTreeNode( "Categories" );
        Author author = (Author) parent.getUnique();

        for ( ReadingUnit book : author.getReadingUnits() )
        {
            books.add( new UniqueNode( book ) );
        }
        for ( Category category : author.getCategories() )
        {
            categories.add( new UniqueNode( category ) );
        }

        if ( books.getChildCount() > 0 )
        {
            parent.add( books );
        }
        if ( categories.getChildCount() > 0 )
        {
            parent.add( categories );
        }
    }

    @Override
    protected EReference getReference()
    {
        return ModelPackage.eINSTANCE.getBookShelf_Authors();
    }

}
