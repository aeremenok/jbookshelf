/**
 * 
 */
package org.jbookshelf.view.swinggui.collection.tab;

import javax.annotation.Nonnull;
import javax.annotation.PostConstruct;
import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.view.logic.Parameters;
import org.jbookshelf.view.swinggui.SetReadItem;
import org.jbookshelf.view.swinggui.actions.UniqueActions;
import org.xnap.commons.gui.util.PopupListener;

/**
 * @author eav
 */
public abstract class CollectionView
    extends JPanel
{
    protected class CollectionPopupListener
        extends PopupListener
    {
        public CollectionPopupListener()
        {
            super( menu );
        }
    }

    private final JPopupMenu menu = new JPopupMenu();

    private Icon             icon;

    public Icon getIcon()
    {
        return icon;
    }

    @PostConstruct
    public void initMenu()
    {
        final UniqueActions actions = Single.instance( UniqueActions.class );
        menu.add( actions.openAction );
        menu.add( actions.openDirAction );
        menu.addSeparator();
        menu.add( actions.editAction );
        menu.add( actions.removeAction );
        menu.addSeparator();
        menu.add( actions.googleAction );
        menu.addSeparator();
        menu.add( new SetReadItem() );
    }

    /**
     * display search result
     * 
     * @param p search parameters
     */
    public abstract void search(
        @Nonnull Parameters p );

    public void setIcon(
        final Icon icon )
    {
        this.icon = icon;
    }

    /**
     * build query to execute in database
     * 
     * @param p search parameters
     * @return query text
     */
    @Nonnull
    protected abstract String buildQuery(
        @Nonnull Parameters p );
}
