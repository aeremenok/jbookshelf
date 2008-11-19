package org.jbookshelf.gui.widgets.panel;

import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import org.jbookshelf.gui.logic.UniqueSelectionListener;
import org.jdesktop.swingx.VerticalLayout;

public abstract class SearchableTreePanel
    extends JPanel
    implements
        UniqueSelectionListener
{
    public SearchableTreePanel()
    {
        super( new VerticalLayout() );
    }

    public abstract void onAdd();

    public abstract void onRemove();

    public abstract void onKeyTyped(
        KeyEvent evt );
}
