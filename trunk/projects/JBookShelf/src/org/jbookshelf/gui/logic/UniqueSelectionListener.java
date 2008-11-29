package org.jbookshelf.gui.logic;

import org.jbookshelf.model.Unique;

public interface UniqueSelectionListener
{
    void nothingSelected();

    void selectedUnique(
        Unique unique );
}
