package org.jbookshelf.gui.tree;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EReference;
import org.jbookshelf.BookShelf;
import org.jbookshelf.Unique;

public abstract class CollectionTree
    extends JTree
{
    protected DefaultMutableTreeNode root = new DefaultMutableTreeNode();

    public CollectionTree()
    {
        super();

        ((DefaultTreeModel) getModel()).setRoot( root );
        // todo expand after updating
        // setRootVisible( false );
        setExpandsSelectedPaths( true );

        getSelectionModel().setSelectionMode( TreeSelectionModel.SINGLE_TREE_SELECTION );
        setShowsRootHandles( false );
    }

    protected abstract EReference getReference();

    @SuppressWarnings( "unchecked" )
    public void update(
        BookShelf bookShelf )
    {
        root.removeAllChildren();

        EList<Unique> uniques = (EList<Unique>) bookShelf.eGet( getReference() );
        for ( Unique unique : uniques )
        {
            DefaultMutableTreeNode parent = new DefaultMutableTreeNode( unique.getName() );
            root.add( parent );
            addChildren( unique, parent );
        }
    }

    protected abstract void addChildren(
        Unique unique,
        DefaultMutableTreeNode parent );
}
