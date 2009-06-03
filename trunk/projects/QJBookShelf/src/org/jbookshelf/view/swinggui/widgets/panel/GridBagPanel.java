package org.jbookshelf.view.swinggui.widgets.panel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class GridBagPanel
    extends JPanel
{
    private final GridBagConstraints constraints = new GridBagConstraints();

    public GridBagPanel()
    {
        super( new GridBagLayout() );

        constraints.fill = GridBagConstraints.BOTH;
        constraints.ipadx = 10;
        constraints.ipady = 10;
    }

    public GridBagConstraints add(
        final JComponent component,
        final int row,
        final int col )
    {
        return add( component, row, col, 1, 1 );
    }

    public GridBagConstraints add(
        final JComponent component,
        final int row,
        final int col,
        final int rowspan,
        final int colspan )
    {
        constraints.gridx = col;
        constraints.gridy = row;
        constraints.gridwidth = colspan;
        constraints.gridheight = rowspan;
        constraints.weightx = 1;
        constraints.weighty = 1;
        add( component, constraints );
        return constraints;
    }

    public GridBagConstraints getConstraints()
    {
        return constraints;
    }
}
