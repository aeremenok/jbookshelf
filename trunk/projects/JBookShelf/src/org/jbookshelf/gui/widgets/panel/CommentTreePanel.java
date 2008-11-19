package org.jbookshelf.gui.widgets.panel;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;

public class CommentTreePanel
    extends SearchableTreePanel
{
    private JTextArea   commentTextArea    = new JTextArea();
    private JScrollPane textAreaScrollPane = new JScrollPane( commentTextArea );
    private JTextField  titleTextField     = new JTextField();
    private JLabel      dateLabel          = new JLabel();
    private JTree       commentTree        = new JTree();
    private JScrollPane treeScrollPane     = new JScrollPane( commentTree );

    public CommentTreePanel()
    {
        super();
        initComponents();
    }

    protected void initComponents()
    {
        // todo
        String text = "01.01.2008/12-12";
        dateLabel.setText( text );
        commentTextArea.setColumns( 20 );
        commentTextArea.setRows( 5 );

        JPanel panel = new JPanel( new BorderLayout() );
        add( panel );
        panel.add( dateLabel, BorderLayout.WEST );
        panel.add( titleTextField, BorderLayout.CENTER );

        add( textAreaScrollPane );
        add( treeScrollPane );
    }
}
