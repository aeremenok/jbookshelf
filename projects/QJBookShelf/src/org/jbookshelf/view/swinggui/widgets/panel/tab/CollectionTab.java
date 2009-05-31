/**
 * 
 */
package org.jbookshelf.view.swinggui.widgets.panel.tab;

import javax.swing.JPanel;

import org.jbookshelf.view.swinggui.widgets.panel.SearchParameters;

/**
 * @author eav
 */
public abstract class CollectionTab
    extends JPanel
{

    public abstract void search(
        SearchParameters p );
}
