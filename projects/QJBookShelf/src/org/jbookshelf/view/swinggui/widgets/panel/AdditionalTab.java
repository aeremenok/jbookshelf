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

    public void onAdd(
        final Book book )
    {
        log.debug( "onAdd" );
        // TODO Auto-generated method stub

    }

    /**
     * 
     */
    public void onRemove()
    {
        log.debug( "onRemove" );
        // TODO Auto-generated method stub

    }
}
