/**
 * 
 */
package org.jbookshelf.view.swinggui.widgets.panel;

import javax.swing.JPanel;

import org.jbookshelf.model.db.Book;

/**
 * @author eav 2009
 */
public abstract class AdditionalTab
    extends JPanel
{
    public abstract void onAdd(
        final Book book );
}
