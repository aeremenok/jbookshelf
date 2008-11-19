package org.jbookshelf.gui.widgets.tree;

import java.util.ArrayList;
import java.util.List;

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
import org.jbookshelf.gui.RelatedPanel;
import org.jbookshelf.gui.ToolBar;
import org.jbookshelf.gui.logic.SoucesUniqueSelection;
import org.jbookshelf.gui.logic.UniqueSelectionListener;

public abstract class CollectionTree
    extends JTree
    implements
        SoucesUniqueSelection
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

    private List<UniqueSelectionListener> listeners = new ArrayList<UniqueSelectionListener>();

    public void addSelectionListener(
        UniqueSelectionListener listener )
    {
        listeners.add( listener );
    }

    public void removeSelectionListener(
        UniqueSelectionListener listener )
    {
        listeners.remove( listener );
    }

    protected DefaultMutableTreeNode root = new DefaultMutableTreeNode();

    public CollectionTree()
    {
        super();

        ((DefaultTreeModel) getModel()).setRoot( root );
        setExpandsSelectedPaths( true );

        getSelectionModel().setSelectionMode( TreeSelectionModel.SINGLE_TREE_SELECTION );
        setShowsRootHandles( false );

        addSelectionListener( ToolBar.getInstance() );
        addSelectionListener( RelatedPanel.getInstance() );

        addTreeSelectionListener( new TreeSelectionListener()
        {
            @SuppressWarnings( "synthetic-access" )
            public void valueChanged(
                TreeSelectionEvent e )
            {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) getLastSelectedPathComponent();
                if ( node == null )
                {
                    fireNothingSelected();
                }
                else
                {
                    if ( node instanceof UniqueNode )
                    {
                        fireSelectedUnique( ((UniqueNode) node).getUnique() );
                    }
                    else
                    {
                        fireNothingSelected();
                    }
                }
            }

        } );

        ToolBar.getInstance().nothingSelected();
    }

    private void fireSelectedUnique(
        Unique unique )
    {
        for ( UniqueSelectionListener listener : listeners )
        {
            listener.selectedUnique( unique );
        }
    }

    private void fireNothingSelected()
    {
        for ( UniqueSelectionListener listener : listeners )
        {
            listener.nothingSelected();
        }
    }

    protected abstract EReference getReference();

    @SuppressWarnings( "unchecked" )
    public void update(
        BookShelf bookShelf )
    {
        showResult( (EList<Unique>) bookShelf.eGet( getReference() ) );
    }

    protected abstract void addChildren(
        UniqueNode parent );

    public void removeSelectedItem()
    {
        UniqueNode uniqueNode = (UniqueNode) getLastSelectedPathComponent();
        uniqueNode.removeFromParent();
        updateUI();
    }

    public void showResult(
        EList<? extends Unique> uniques )
    {
        root.removeAllChildren();

        for ( Unique unique : uniques )
        {
            UniqueNode parent = new UniqueNode( unique );
            root.add( parent );
            addChildren( parent );
        }

        expandRow( 0 );
        setRootVisible( false );

        updateUI();
    }
}
