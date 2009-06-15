/**
 * 
 */
package org.jbookshelf.view.swinggui.widgets.panel;

import org.apache.log4j.Logger;
import org.jbookshelf.view.i18n.I18N;

/**
 * @author eav 2009
 */
public class RelatedBookTab
    extends AdditionalTab
{
    private static final Logger log = Logger.getLogger( RelatedBookTab.class );

    public RelatedBookTab()
    {
        super();
        setName( I18N.tr( "Related Books" ) );
    }

}
