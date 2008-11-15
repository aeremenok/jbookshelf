package org.jbookshelf.gui.tree;

import javax.swing.tree.DefaultMutableTreeNode;

import org.eclipse.emf.ecore.EReference;
import org.jbookshelf.Author;
import org.jbookshelf.Category;
import org.jbookshelf.JbookshelfPackage;
import org.jbookshelf.ReadingUnit;
import org.jbookshelf.Unique;

public class AuthorTree
    extends CollectionTree
{
    @Override
    protected void addChildren(
        Unique unique,
        DefaultMutableTreeNode parent )
    {
        DefaultMutableTreeNode books = new DefaultMutableTreeNode( "Books" );
        DefaultMutableTreeNode categories = new DefaultMutableTreeNode( "Categories" );
        Author author = (Author) unique;

        for ( ReadingUnit book : author.getReadingUnits() )
        {
            books.add( new DefaultMutableTreeNode( book.getName() ) );
        }
        for ( Category category : author.getCategories() )
        {
            categories.add( new DefaultMutableTreeNode( category.getName() ) );
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
        return JbookshelfPackage.eINSTANCE.getBookShelf_Authors();
    }

}