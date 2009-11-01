/**
 * 
 */
package org.jbookshelf.view.swinggui.widget;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * simply wraps components to a center of {@link JPanel} with {@link BorderLayout}
 * 
 * @author eav 2009
 */
public class WrapperPanel
    extends JPanel
{
    private final JComponent component;

    public WrapperPanel(
        final JComponent component )
    {
        this( component, false );
    }

    public WrapperPanel(
        final JComponent component,
        final boolean scroll )
    {
        super( new BorderLayout() );
        this.component = component;
        if ( scroll )
        {
            add( new JScrollPane( component ), BorderLayout.CENTER );
        }
        else
        {
            add( component, BorderLayout.CENTER );
        }
    }

    /**
     * @return the component
     */
    public JComponent getComponent()
    {
        return this.component;
    }
}
