package org.jbookshelf.qtgui.widgets.tree;

import org.jbookshelf.model.ReadingUnit;
import org.jbookshelf.model.Unique;
import org.jbookshelf.qtgui.ToolBar;

import com.trolltech.qt.gui.QAction;
import com.trolltech.qt.gui.QMenu;

public class CollectionTreeMenu
    extends QMenu
{
    private Unique unique;

    public CollectionTreeMenu(
        UniqueNode uniqueNode )
    {
        unique = uniqueNode.getUnique();

        if ( unique instanceof ReadingUnit )
        {
            addAction( ToolBar.getInstance().getOpenAction() );
            addAction( ToolBar.getInstance().getEditAction() );
        }

        addAction( ToolBar.getInstance().getRemoveAction() );

        if ( unique instanceof ReadingUnit )
        {
            addSeparator();
            addAction( isReadAction( (ReadingUnit) unique ) );
        }
    }

    private QAction isReadAction(
        ReadingUnit book )
    {
        QAction isReadAction = new QAction( tr( "Is Read" ), this );
        isReadAction.setCheckable( true );
        isReadAction.setChecked( book.isRead() );

        isReadAction.toggled.connect( this, "setRead()" );

        return isReadAction;
    }

    @SuppressWarnings( "unused" )
    private void setRead()
    {
        ReadingUnit book = (ReadingUnit) unique;
        book.setRead( !book.isRead() );
    }
}
