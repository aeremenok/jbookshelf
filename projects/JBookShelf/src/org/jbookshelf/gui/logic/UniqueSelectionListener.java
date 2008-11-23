package org.jbookshelf.gui.logic;

import org.jbookshelf.Unique;

public interface UniqueSelectionListener
{
    void selectedUnique(
        Unique unique );

    void nothingSelected();
}
