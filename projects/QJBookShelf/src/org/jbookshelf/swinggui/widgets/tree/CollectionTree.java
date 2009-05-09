package org.jbookshelf.swinggui.widgets.tree;

import java.util.List;

import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeWillExpandListener;

import org.jbookshelf.controller.singleton.Singleton;
import org.jbookshelf.controller.storage.Storage;
import org.jbookshelf.model.BookShelf;
import org.jbookshelf.model.Unique;
import org.jbookshelf.qtgui.logic.Translatable;
import org.jbookshelf.qtgui.logic.Translator;
import org.jdesktop.swingx.JXTreeTable;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;
import org.jdesktop.swingx.treetable.DefaultTreeTableModel;
import org.jdesktop.swingx.treetable.MutableTreeTableNode;

public abstract class CollectionTree
    extends JXTreeTable
    implements
        Singleton,
        Translatable,
        TreeWillExpandListener
{
    public void initSingleton()
    {
        Translator.addTranslatable( this );
        setRootVisible( false );
        setSortable( true );
        addTreeWillExpandListener( this );
        ((DefaultTreeTableModel) getTreeTableModel()).setRoot( new DefaultMutableTreeTableNode() );
    }

    public void retranslate()
    {
    }

    public void showResult(
        final List<? extends Unique> list )
    {
        clearSelection();

        final DefaultTreeTableModel model = (DefaultTreeTableModel) getTreeTableModel();
        final MutableTreeTableNode root = (MutableTreeTableNode) model.getRoot();

        for ( int i = 0; i < root.getChildCount(); i++ )
        {
            root.remove( 0 );
        }

        for ( int i = 0; i < list.size(); i++ )
        {
            model.insertNodeInto( new UniqueNode( list.get( i ), false ), root, i );
        }
    }

    public void treeWillCollapse(
        final TreeExpansionEvent event )
    {
    }

    public void treeWillExpand(
        final TreeExpansionEvent event )
    {
        final Object object = event.getPath().getLastPathComponent();
        System.out.println( object );
        if ( object instanceof UniqueNode )
        {
            addChildren( (UniqueNode) object );
        }
    }

    public void update()
    {
        showResult( getUniques( Storage.getBookShelf() ) );
    }

    protected abstract void addChildren(
        final UniqueNode parent );

    protected abstract List<? extends Unique> getUniques(
        final BookShelf bookShelf );

}
