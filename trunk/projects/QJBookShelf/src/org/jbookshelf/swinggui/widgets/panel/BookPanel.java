package org.jbookshelf.swinggui.widgets.panel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jbookshelf.model.Book;

public class BookPanel
    extends JPanel
{
    private final JLabel     bookLabel     = new JLabel();
    private final JLabel     authorLabel   = new JLabel();
    private final JLabel     categoryLabel = new JLabel();
    private final JLabel     viewerLabel   = new JLabel();
    private final JLabel     fileLabel     = new JLabel();

    private final JTextField bookTextField = new JTextField();

    public static void changeBook(
        final Book book,
        final BookParameters parameters )
    {
        // TODO Auto-generated method stub

    }

    public BookPanel()
    {
        super( new GridBagLayout() );

    }

    public void clear(
        final boolean b )
    {
        // TODO Auto-generated method stub
    }

    public BookParameters extractParameters()
    {
        // TODO Auto-generated method stub
        return null;
    }
}
