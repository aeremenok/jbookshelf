/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.toolbar;

import icons.IMG;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.jbookshelf.view.swinggui.actions.TranslatableAction;

/**
 * @author eav 2009
 */
public class Layouter
    extends JPanel
{
    public static enum PageLayout
    {
        ONE_PAGE,
        TWO_PAGES;

        public static final PageLayout DEFAULT_LAYOUT = ONE_PAGE;
    }

    private class LayoutAction
        extends TranslatableAction
    {
        public LayoutAction()
        {
            super( null, IMG.icon( IMG.RIGHT_NEW_PNG ) );
        }

        @Override
        public void actionPerformed(
            final ActionEvent e )
        {
            // change the layout
            final boolean isOne = getPageLayout() == PageLayout.ONE_PAGE;
            setPageLayout( isOne
                ? PageLayout.TWO_PAGES : PageLayout.ONE_PAGE );
            putValue( SMALL_ICON, IMG.icon( isOne
                ? IMG.RIGHT_CLOSE_PNG : IMG.RIGHT_NEW_PNG ) );
        }
    }

    private PageLayout pageLayout = PageLayout.DEFAULT_LAYOUT;

    public Layouter()
    {
        super( new BorderLayout() );
        final LayoutAction a = new LayoutAction();
        add( new JButton( a ) );
    }

    /**
     * @return the pageLayout
     */
    public PageLayout getPageLayout()
    {
        return this.pageLayout;
    }

    /**
     * @param pageLayout the pageLayout to set
     */
    public void setPageLayout(
        final PageLayout pageLayout )
    {
        this.pageLayout = pageLayout;
        firePropertyChange( Features.LAYOUT, null, pageLayout );
    }
}
