/**
 * 
 */
package org.jbookshelf.view.swinggui.dnd;

import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragGestureRecognizer;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

/**
 * copied from {@link "http://www.java2s.com/Code/Java/Swing-JFC/DnDdraganddropJTreecode.htm"}<br>
 * A drag source wrapper for a JTree. This class can be used to make a rearrangeable DnD tree with the
 * TransferableTreeNode class as the transfer data type.
 */
public class TreeDragSource
    implements
    DragSourceListener,
    DragGestureListener
{
    private final DragSource            source;

    private TransferableTreeNode        transferable;

    private DefaultMutableTreeNode      oldNode;

    private final JTree                 sourceTree;

    @SuppressWarnings( "unused" )
    private final DragGestureRecognizer recognizer;

    public TreeDragSource(
        final JTree tree,
        final int actions )
    {
        sourceTree = tree;
        source = new DragSource();
        recognizer = source.createDefaultDragGestureRecognizer( sourceTree, actions, this );
    }

    public void dragDropEnd(
        final DragSourceDropEvent dsde )
    {
        /*
         * to support move only... 
         */
        if ( dsde.getDropSuccess() )
        {
            ((DefaultTreeModel) sourceTree.getModel()).removeNodeFromParent( oldNode );
        }
    }

    /*
     * Drag Event Handlers
     */
    public void dragEnter(
        final DragSourceDragEvent dsde )
    {}

    public void dragExit(
        final DragSourceEvent dse )
    {}

    /*
     * Drag Gesture Handler
     */
    public void dragGestureRecognized(
        final DragGestureEvent dge )
    {
        System.out.println( "TreeDragSource.dragGestureRecognized()" );
        final TreePath path = sourceTree.getSelectionPath();
        if ( path == null || path.getPathCount() <= 1 )
        {
            // We can't move the root node or an empty selection
            return;
        }
        oldNode = (DefaultMutableTreeNode) path.getLastPathComponent();
        transferable = new TransferableTreeNode( path );
        source.startDrag( dge, DragSource.DefaultMoveNoDrop, transferable, this );
    }

    public void dragOver(
        final DragSourceDragEvent dsde )
    {}

    public void dropActionChanged(
        final DragSourceDragEvent dsde )
    {
        System.out.println( "Action: " + dsde.getDropAction() );
        System.out.println( "Target Action: " + dsde.getTargetActions() );
        System.out.println( "User Action: " + dsde.getUserAction() );
    }
}
