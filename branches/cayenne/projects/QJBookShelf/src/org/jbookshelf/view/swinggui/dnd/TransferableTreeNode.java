/**
 * 
 */
package org.jbookshelf.view.swinggui.dnd;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;

import javax.swing.tree.TreePath;

/**
 * copied from {@link "http://www.java2s.com/Code/Java/Swing-JFC/DnDdraganddropJTreecode.htm"}<br>
 * A Transferable TreePath to be used with Drag & Drop applications.
 */
public class TransferableTreeNode
    implements
    Transferable
{
    public static DataFlavor TREE_PATH_FLAVOR = new DataFlavor( TreePath.class, "Tree Path" );

    private final DataFlavor flavors[]        =
                                              { TREE_PATH_FLAVOR };
    private final TreePath   path;

    public TransferableTreeNode(
        final TreePath tp )
    {
        path = tp;
    }

    public synchronized Object getTransferData(
        final DataFlavor flavor )
        throws UnsupportedFlavorException
    {
        if ( isDataFlavorSupported( flavor ) )
        {
            return path;
        }
        throw new UnsupportedFlavorException( flavor );
    }

    public synchronized DataFlavor[] getTransferDataFlavors()
    {
        return flavors;
    }

    public boolean isDataFlavorSupported(
        final DataFlavor flavor )
    {
        return flavor.getRepresentationClass() == TreePath.class;
    }
}