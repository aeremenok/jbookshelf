/**
 * 
 */
package org.jbookshelf.view.swinggui.widgets.panel;

import java.util.regex.Pattern;

import javax.swing.JPanel;

import org.jbookshelf.model.db.Book;
import org.jdesktop.swingx.decorator.PatternFilter;

/**
 * @author eav 2009
 */
public abstract class AdditionalTab
    extends JPanel
{
    protected final PatternFilter filter = new PatternFilter( ".*", 0, 0 );

    public abstract void onAdd(
        final Book book );

    public void onSearch(
        final String text )
    {
        if ( text == null )
        {
            filter.setPattern( ".*", 0 );
        }
        else
        {
            filter.setPattern( text + ".*", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE );
        }
    }
}
