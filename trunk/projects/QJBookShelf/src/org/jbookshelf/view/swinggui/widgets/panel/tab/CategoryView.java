/**
 * 
 */
package org.jbookshelf.view.swinggui.widgets.panel.tab;

import org.apache.log4j.Logger;
import org.jbookshelf.view.logic.Parameters;

/**
 * @author eav
 */
public class CategoryView
    extends CollectionView
{
    private static final Logger log = Logger.getLogger( CategoryView.class );

    public CategoryView()
    {
        super();
        setName( "Categories" );
    }

    /* (non-Javadoc)
     * @see org.jbookshelf.view.swinggui.widgets.panel.CollectionTab#search(org.jbookshelf.view.swinggui.widgets.panel.SearchParameters)
     */
    @Override
    public void search(
        final Parameters p )
    {
    // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see org.jbookshelf.view.swinggui.widgets.panel.tab.CollectionTab#buildQuery(org.jbookshelf.view.logic.Parameters)
     */
    @Override
    protected String buildQuery(
        final Parameters p )
    {
        log.debug( "buildQuery" );
        // TODO Auto-generated method stub
        return null;
    }

}
