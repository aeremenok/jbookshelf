/**
 * 
 */
package org.jbookshelf.view.swinggui.widgets.panel;

import org.apache.log4j.Logger;
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
}
