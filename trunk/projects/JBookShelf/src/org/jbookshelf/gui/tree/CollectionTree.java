package org.jbookshelf.gui.tree;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EReference;
import org.jbookshelf.BookShelf;
import org.jbookshelf.Unique;
import org.jbookshelf.gui.ToolBar;

public abstract class CollectionTree
    extends JTree
{
    protected class UniqueNode
        extends DefaultMutableTreeNode
    {
        private Unique unique;

        public UniqueNode(
            Unique unique )
        {
            super( unique.getName() );
            this.unique = unique;
        }

        public Unique getUnique()
        {
            return unique;
        }
    }

    protected DefaultMutableTreeNode root = new DefaultMutableTreeNode();

    public CollectionTree()
    {
        super();

        ((DefaultTreeModel) getModel()).setRoot( root );
        setExpandsSelectedPaths( true );

        getSelectionModel().setSelectionMode( TreeSelectionModel.SINGLE_TREE_SELECTION );
        setShowsRootHandles( false );

        addTreeSelectionListener( new TreeSelectionListener()
        {
            public void valueChanged(
                TreeSelectionEvent e )
            {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) getLastSelectedPathComponent();
                if ( node == null )
                {
                    ToolBar.getInstance().nothingSelected();
                }
                else
                {
                    if ( node instanceof UniqueNode )
                    {
                        UniqueNode uniqueNode = (UniqueNode) node;
                        ToolBar.getInstance().selectedUnique( uniqueNode.getUnique() );
                    }
                    else
                    {
                        ToolBar.getInstance().nothingSelected();
                    }
                }
            }
        } );

        ToolBar.getInstance().nothingSelected();
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
            UniqueNode parent = new UniqueNode( unique );
            root.add( parent );
            addChildren( parent );
        }

        expandRow( 0 );
        setRootVisible( false );
    }

    protected abstract void addChildren(
        UniqueNode parent );
}
