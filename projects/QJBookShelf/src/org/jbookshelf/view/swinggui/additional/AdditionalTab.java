/**
 * 
 */
package org.jbookshelf.view.swinggui.additional;

import java.util.regex.Pattern;

import javax.swing.JPanel;

import org.jbookshelf.model.db.api.spec.IBook;
import org.jdesktop.swingx.decorator.PatternFilter;

/**
 * displays some kind of additional data
 * 
 * @author eav 2009
 */
public abstract class AdditionalTab
    extends JPanel
{
    protected final PatternFilter filter = new PatternFilter( ".*", 0, 0 );

    /**
     * data entry added to the book
     * 
     * @param book a book to add entry
     */
    public abstract void onAdd(
        final IBook book );

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
