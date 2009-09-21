/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.toolbar;

import icons.IMG;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.view.swinggui.actions.TranslatableAction;
import org.jbookshelf.view.swinggui.reader.ReaderWindow;

/**
 * @author eav 2009
 */
public class PageLayoutSwitcher
    extends JPanel
{
    public static enum PageLayoutType
    {
        ONE_PAGE,
        TWO_PAGES;

        public static final PageLayoutType DEFAULT_LAYOUT = ONE_PAGE;
    }

    private class LayoutAction
        extends TranslatableAction
    {
        public LayoutAction()
        {
            super( IMG.icon( IMG.RIGHT_NEW_PNG ), tr( "Switch page layout" ) );
        }

        @Override
        public void actionPerformed(
            final ActionEvent e )
        {
            // change the layout
            final boolean isOne = getCurrentLayout() == PageLayoutType.ONE_PAGE;
            setPageLayout( isOne
                ? PageLayoutType.TWO_PAGES : PageLayoutType.ONE_PAGE );
            putValue( SMALL_ICON, IMG.icon( isOne
                ? IMG.RIGHT_CLOSE_PNG : IMG.RIGHT_NEW_PNG ) );
        }
    }

    private PageLayoutType currentLayout = PageLayoutType.DEFAULT_LAYOUT;

    public PageLayoutSwitcher()
    {
        super( new BorderLayout() );
        add( new JButton( new LayoutAction() ) );
    }

    public PageLayoutType getCurrentLayout()
    {
        return this.currentLayout;
    }

    public void setPageLayout(
        final PageLayoutType pageLayout )
    {
        this.currentLayout = pageLayout;
        Single.instance( ReaderWindow.class ).switchPageLayout();
    }
}
