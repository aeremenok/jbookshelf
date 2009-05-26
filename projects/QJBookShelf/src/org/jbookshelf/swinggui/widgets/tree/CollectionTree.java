package org.jbookshelf.swinggui.widgets.tree;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.swing.ListSelectionModel;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeWillExpandListener;

import org.jbookshelf.controller.storage.Storage;
import org.jbookshelf.model.BookShelf;
import org.jbookshelf.model.Unique;
import org.jbookshelf.qtgui.logic.Translatable;
import org.jbookshelf.qtgui.logic.Translator;
import org.jdesktop.swingx.JXTreeTable;
import org.jdesktop.swingx.treetable.AbstractMutableTreeTableNode;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;
import org.jdesktop.swingx.treetable.DefaultTreeTableModel;
import org.jdesktop.swingx.treetable.MutableTreeTableNode;

public abstract class CollectionTree
    extends JXTreeTable
    implements
    Translatable,
    TreeWillExpandListener,
    TreeSelectionListener
{
    @PostConstruct
    public void initSingleton()
    {
        Translator.addTranslatable( this );
        setRootVisible( false );
        setSortable( true );
        addTreeWillExpandListener( this );
        addTreeSelectionListener( this );
        setSelectionMode( ListSelectionModel.MULTIPLE_INTERVAL_SELECTION );
    }

    public void retranslate()
    {}

    public void showResult(
        final List<? extends Unique> list )
    {
        clearSelection();

        final DefaultTreeTableModel model = (DefaultTreeTableModel) getTreeTableModel();
        // fixme memory leakage?
        final MutableTreeTableNode root = new DefaultMutableTreeTableNode();
        model.setRoot( root );

        for ( int i = 0; i < list.size(); i++ )
        {
            model.insertNodeInto( new UniqueNode( list.get( i ), false ), root, i );
        }
    }

    public void treeWillCollapse(
        final TreeExpansionEvent event )
    {}

    public void treeWillExpand(
        final TreeExpansionEvent event )
    {
        final Object object = event.getPath().getLastPathComponent();
        if ( object instanceof UniqueNode && ((AbstractMutableTreeTableNode) object).getChildCount() == 0 )
        {
            addChildren( (UniqueNode) object );
        }
    }

    public void update()
    {
        showResult( getUniques( Storage.getBookShelf() ) );
    }

    public void valueChanged(
        final TreeSelectionEvent e )
    {
    // todo
    }

    protected abstract void addChildren(
        final UniqueNode parent );

    protected abstract List<? extends Unique> getUniques(
        final BookShelf bookShelf );

}
