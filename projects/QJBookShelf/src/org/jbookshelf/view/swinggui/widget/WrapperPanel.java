/**
 * 
 */
package org.jbookshelf.view.swinggui.widget;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * @author eav 2009
 */
public class WrapperPanel
    extends JPanel
{
    private final JComponent component;

    public WrapperPanel(
        final JComponent component )
    {
        super( new BorderLayout() );
        this.component = component;
        add( component, BorderLayout.CENTER );
    }

    /**
     * @return the component
     */
    public JComponent getComponent()
    {
        return this.component;
    }
}
