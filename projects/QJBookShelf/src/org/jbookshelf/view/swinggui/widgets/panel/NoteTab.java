/**
 * 
 */
package org.jbookshelf.view.swinggui.widgets.panel;

import org.apache.log4j.Logger;
import org.jbookshelf.model.db.Book;
import org.jbookshelf.view.i18n.I18N;

/**
 * @author eav 2009
 */
public class NoteTab
    extends AdditionalTab
{
    private static final Logger log = Logger.getLogger( NoteTab.class );

    public NoteTab()
    {
        super();
        setName( I18N.tr( "Notes" ) );
    }

    /* (non-Javadoc)
     * @see org.jbookshelf.view.swinggui.widgets.panel.AdditionalTab#onAdd(org.jbookshelf.model.db.Book)
     */
    @Override
    public void onAdd(
        final Book book )
    {
        log.debug( "onAdd" );
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see org.jbookshelf.view.swinggui.widgets.panel.AdditionalTab#onRemove()
     */
    @Override
    public void onRemove()
    {
        log.debug( "onRemove" );
        // TODO Auto-generated method stub

    }
}
