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
import org.jbookshelf.gui.ToolBar;
import org.jbookshelf.gui.logic.SoucesUniqueSelection;
import org.jbookshelf.gui.logic.UniqueSelectionListener;
import org.jbookshelf.gui.widgets.panel.RelatedPanel;
import org.jbookshelf.model.BookShelf;
import org.jbookshelf.model.Unique;

public abstract class CollectionTree
    extends JTree
    implements
        SoucesUniqueSelection
{
    private List<UniqueSelectionListener> listeners = new ArrayList<UniqueSelectionListener>();

    protected DefaultMutableTreeNode      root      = new DefaultMutableTreeNode();

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

    public void addSelectionListener(
        UniqueSelectionListener listener )
    {
        listeners.add( listener );
    }

    public void removeSelectedItem()
    {
        UniqueNode uniqueNode = (UniqueNode) getLastSelectedPathComponent();
        uniqueNode.removeFromParent();
        updateUI();
    }

    public void removeSelectionListener(
        UniqueSelectionListener listener )
    {
        listeners.remove( listener );
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

    @SuppressWarnings( "unchecked" )
    public void update(
        BookShelf bookShelf )
    {
        showResult( (EList<Unique>) bookShelf.eGet( getReference() ) );
    }

    private void fireNothingSelected()
    {
        for ( UniqueSelectionListener listener : listeners )
        {
            listener.nothingSelected();
        }
    }

    private void fireSelectedUnique(
        Unique unique )
    {
        for ( UniqueSelectionListener listener : listeners )
        {
            listener.selectedUnique( unique );
        }
    }

    protected abstract void addChildren(
        UniqueNode parent );

    protected abstract EReference getReference();
}
