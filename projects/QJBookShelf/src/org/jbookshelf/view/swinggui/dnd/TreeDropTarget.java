/**
 * 
 */
package org.jbookshelf.view.swinggui.dnd;

import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetContext;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import org.apache.log4j.Logger;

/**
 * copied from {@link "http://www.java2s.com/Code/Java/Swing-JFC/DnDdraganddropJTreecode.htm"}<br>
 * A quick DropTarget that's looking for drops from draggable JTrees.
 */
public class TreeDropTarget
    implements
    DropTargetListener
{
    private static final Logger log = Logger.getLogger( TreeDropTarget.class );
    @SuppressWarnings( "unused" )
    private final DropTarget    target;

    public TreeDropTarget(
        final JTree tree )
    {
        target = new DropTarget( tree, this );
    }

    public void dragEnter(
        final DropTargetDragEvent dtde )
    {
        final TreeNode node = getNodeForEvent( dtde );
        if ( node.isLeaf() )
        {
            dtde.rejectDrag();
        }
        else
        {
            dtde.acceptDrag( dtde.getDropAction() );
        }
    }

    public void dragExit(
        final DropTargetEvent dte )
    {}

    public void dragOver(
        final DropTargetDragEvent dtde )
    {
        final TreeNode node = getNodeForEvent( dtde );
        if ( node.isLeaf() )
        {
            dtde.rejectDrag();
        }
        else
        {
            dtde.acceptDrag( dtde.getDropAction() );
        }
    }

    public void drop(
        final DropTargetDropEvent dtde )
    {
        final Point pt = dtde.getLocation();
        final DropTargetContext dtc = dtde.getDropTargetContext();
        final JTree tree = (JTree) dtc.getComponent();
        final TreePath parentpath = tree.getClosestPathForLocation( pt.x, pt.y );
        final DefaultMutableTreeNode parent = (DefaultMutableTreeNode) parentpath.getLastPathComponent();
        if ( parent.isLeaf() )
        {
            dtde.rejectDrop();
            return;
        }

        try
        {
            final Transferable tr = dtde.getTransferable();
            final DataFlavor[] flavors = tr.getTransferDataFlavors();
            for ( int i = 0; i < flavors.length; i++ )
            {
                if ( tr.isDataFlavorSupported( flavors[i] ) )
                {
                    dtde.acceptDrop( dtde.getDropAction() );
                    final TreePath p = (TreePath) tr.getTransferData( flavors[i] );
                    final DefaultMutableTreeNode node = (DefaultMutableTreeNode) p.getLastPathComponent();
                    final DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
                    dropDataChange( parent, node, model );
                    dtde.dropComplete( true );
                    return;
                }
            }
            dtde.rejectDrop();
        }
        catch ( final Exception e )
        {
            log.error( e, e );
            dtde.rejectDrop();
        }
    }

    public void dropActionChanged(
        final DropTargetDragEvent dtde )
    {}

    /*
     * Drop Event Handlers
     */
    private TreeNode getNodeForEvent(
        final DropTargetDragEvent dtde )
    {
        final Point p = dtde.getLocation();
        final DropTargetContext dtc = dtde.getDropTargetContext();
        final JTree tree = (JTree) dtc.getComponent();
        final TreePath path = tree.getClosestPathForLocation( p.x, p.y );
        return (TreeNode) path.getLastPathComponent();
    }

    protected void dropDataChange(
        final DefaultMutableTreeNode parent,
        final DefaultMutableTreeNode node,
        final DefaultTreeModel model )
    {
        model.insertNodeInto( node, parent, 0 );
    }
}