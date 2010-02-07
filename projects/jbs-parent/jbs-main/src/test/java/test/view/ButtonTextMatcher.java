/**
 * 
 */
package test.view;

import javax.swing.JButton;

import org.apache.log4j.Logger;
import org.fest.swing.core.GenericTypeMatcher;
import org.jbookshelf.view.i18n.I18N;

/**
 * @author eav 2010
 */
public class ButtonTextMatcher
    extends GenericTypeMatcher<JButton>
{
    @SuppressWarnings( "unused" )
    private static final Logger log = Logger.getLogger( ButtonTextMatcher.class );
    private final String        translated;

    public ButtonTextMatcher(
        final String text )
    {
        super( JButton.class );
        this.translated = I18N.tr( text );
    }

    @Override
    protected boolean isMatching(
        final JButton component )
    {
        return translated.equals( component.getText() );
    }
}
