/**
 * 
 */
package org.jbookshelf.view;

import javax.swing.JButton;

import org.fest.swing.core.GenericTypeMatcher;
import org.jbookshelf.view.i18n.I18N;

/**
 * @author eav 2010
 */
public class ButtonTextMatcher
    extends GenericTypeMatcher<JButton>
{
    private final String translatedText;
    private final String originalText;

    public ButtonTextMatcher(
        final String orgginalText )
    {
        super( JButton.class );
        this.originalText = orgginalText;
        this.translatedText = I18N.tr( orgginalText );
    }

    @Override
    protected boolean isMatching(
        final JButton component )
    {
        final String text = component.getText();
        return translatedText.equals( text ) || originalText.equals( text );
    }
}
