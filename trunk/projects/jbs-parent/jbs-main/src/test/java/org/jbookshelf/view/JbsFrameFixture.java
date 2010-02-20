/**
 * 
 */
package org.jbookshelf.view;

import java.awt.Frame;

import javax.swing.JDialog;

import org.apache.log4j.Logger;
import org.fest.swing.fixture.DialogFixture;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JButtonFixture;
import org.jbookshelf.view.i18n.I18N;

/**
 * @author eav 2010
 */
public class JbsFrameFixture
    extends FrameFixture
{
    @SuppressWarnings( "unused" )
    private static final Logger log = Logger.getLogger( JbsFrameFixture.class );

    public JbsFrameFixture(
        final Frame target )
    {
        super( target );
    }

    public JButtonFixture buttonByText(
        final String text )
    {
        return button( new ButtonTextMatcher( I18N.tr( text ) ) );
    }

    public <D extends JDialog> DialogFixture dialog(
        final Class<D> dialogClass )
    {
        return super.dialog( new SingletonMatcher<D>( dialogClass ) );
    }

}
