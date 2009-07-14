/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.txt;

import icons.IMG;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JPanel;

import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.i18n.Translatable;
import org.jbookshelf.view.swinggui.actions.TranslatableAction;
import org.xnap.commons.gui.DefaultDialog;
import org.xnap.commons.i18n.I18n;

import com.connectina.swing.fontchooser.JFontChooser;

/**
 * @author eav 2009
 */
public class FontChooser
    extends JPanel
{
    private static class FontChooserDialog
        extends DefaultDialog
        implements
        Translatable
    {
        protected JFontChooser fontChooser;

        public FontChooserDialog(
            final Font font )
        {
            super( BUTTON_OKAY | BUTTON_CANCEL | BUTTON_DEFAULTS );
            fontChooser = new JFontChooser( font );
            setMainComponent( fontChooser );
            setButtonSeparatorVisible( false );
            I18N.translate( this );
            pack();
            setLocationRelativeTo( null );
        }

        @Override
        public void translate(
            final I18n i18n )
        {
            setTitle( i18n.tr( "Choose font" ) );
            getCancelAction().putValue( Action.NAME, i18n.tr( "Cancel" ) );
            getDefaultsAction().putValue( Action.NAME, i18n.tr( "Defaults" ) );
        }

        @Override
        protected void defaults()
        { // todo dirty hack, but fontChooser.setSelectedFont() takes no effect... 
            fontChooser = new JFontChooser( DEFAULT_FONT );
            setMainComponent( fontChooser );
        }
    }

    private class ShowChooserAction
        extends TranslatableAction
    {
        public ShowChooserAction()
        {
            super( null, IMG.icon( IMG.FONT_PNG ) );
        }

        @Override
        public void actionPerformed(
            final ActionEvent e )
        {
            final FontChooserDialog dialog = new FontChooserDialog( selectedFont )
            {
                @Override
                public boolean apply()
                {
                    selectedFont = fontChooser.getSelectedFont();
                    FontChooser.this.firePropertyChange( FONT_SELECTED, null, selectedFont );
                    return true;
                }
            };
            dialog.setVisible( true );
        }
    }

    public final static int    INITIAL_SIZE  = 20;
    public final static Font   DEFAULT_FONT;
    public final static String FONT_SELECTED = "FONT_SELECTED";
    static
    {
        final Font oldFont = new JEditorPane().getFont();
        DEFAULT_FONT = new Font( oldFont.getName(), oldFont.getStyle(), INITIAL_SIZE );
    }

    private Font               selectedFont  = DEFAULT_FONT;

    public FontChooser()
    {
        super( new BorderLayout() );
        add( new JButton( new ShowChooserAction() ) );
        // todo remember font in settings files
    }

    /**
     * @return the selectedFont
     */
    public Font getSelectedFont()
    {
        return this.selectedFont;
    }

    /**
     * @param selectedFont the selectedFont to set
     */
    public void setSelectedFont(
        final Font selectedFont )
    {
        this.selectedFont = selectedFont;
    }
}
