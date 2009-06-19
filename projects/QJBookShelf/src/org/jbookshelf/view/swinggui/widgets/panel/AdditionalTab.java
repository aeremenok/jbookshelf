/**
 * 
 */
package org.jbookshelf.view.swinggui.widgets.panel;

import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.jbookshelf.model.db.Book;

/**
 * @author eav 2009
 */
public abstract class AdditionalTab
    extends JPanel
{
    private static final Logger log = Logger.getLogger( AdditionalTab.class );

    public abstract void onAdd(
        final Book book );

    public abstract void onRemove();
}
