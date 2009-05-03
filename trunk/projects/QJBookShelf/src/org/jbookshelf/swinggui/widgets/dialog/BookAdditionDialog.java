package org.jbookshelf.swinggui.widgets.dialog;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class BookAdditionDialog
    extends JDialog
{

    public BookAdditionDialog(
        final JFrame owner )
    {
        super( owner );
        setModal( true );
    }

}
