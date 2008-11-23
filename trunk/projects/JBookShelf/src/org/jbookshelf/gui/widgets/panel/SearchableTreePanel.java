package org.jbookshelf.gui.widgets.panel;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import org.jbookshelf.gui.RelatedPanel;
import org.jbookshelf.gui.logic.UniqueSelectionListener;

public abstract class SearchableTreePanel
    extends JPanel
    implements
        UniqueSelectionListener
{
    protected final RelatedPanel relatedPanel;

    public SearchableTreePanel(
        RelatedPanel relatedPanel )
    {
        super( new BorderLayout() );
        this.relatedPanel = relatedPanel;
    }

    public abstract void onAdd();

    public abstract void onKeyReleased(
        KeyEvent evt );

    public abstract void onRemove();
}
