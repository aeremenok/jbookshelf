package org.jbookshelf.gui.tree;

import javax.swing.tree.DefaultMutableTreeNode;

import org.eclipse.emf.ecore.EReference;
import org.jbookshelf.JbookshelfPackage;
import org.jbookshelf.Unique;

public class AuthorTree
    extends CollectionTree
{

    @Override
    protected void addChildren(
        Unique unique,
        DefaultMutableTreeNode parent )
    {
        // TODO Auto-generated method stub

    }

    @Override
    protected EReference getReference()
    {
        return JbookshelfPackage.eINSTANCE.getBookShelf_Authors();
    }

}
