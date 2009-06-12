/**
 * 
 */
package org.jbookshelf.view.swinggui.widgets.panel.tab;

import javax.annotation.Nonnull;
import javax.swing.Icon;
import javax.swing.JPanel;

import org.jbookshelf.view.logic.Parameters;

/**
 * @author eav
 */
public abstract class CollectionView
    extends JPanel
{
    private Icon icon;

    public Icon getIcon()
    {
        return icon;
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
