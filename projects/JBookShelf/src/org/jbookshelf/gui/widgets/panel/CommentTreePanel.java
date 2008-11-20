package org.jbookshelf.gui.widgets.panel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.text.JTextComponent;
import javax.swing.tree.DefaultMutableTreeNode;

import org.jbookshelf.Comment;
import org.jbookshelf.Commentable;
import org.jbookshelf.JbookshelfFactory;
import org.jbookshelf.Unique;
import org.jbookshelf.gui.RelatedPanel;

public class CommentTreePanel
    extends SearchableTreePanel
    implements
        ActionListener
{
    private class CommentNode
        extends DefaultMutableTreeNode
    {
        private final Comment comment;

        public CommentNode(
            Comment comment )
        {
            super( comment.getTitle() );
            this.comment = comment;
        }

        public Comment getComment()
        {
            return comment;
        }
    }

    private JTextArea              commentTextArea    = new JTextArea();
    private JScrollPane            textAreaScrollPane = new JScrollPane( commentTextArea );
    private JTextField             titleTextField     = new JTextField();
    private JLabel                 dateLabel          = new JLabel();
    private DefaultMutableTreeNode root               = new DefaultMutableTreeNode();
    private JTree                  commentTree        = new JTree( root );
    private JScrollPane            treeScrollPane     = new JScrollPane( commentTree );
    private JPanel                 headerPanel        = new JPanel( new BorderLayout() );
    private JButton                submitButton       = new JButton();

    private Commentable            selectedCommentable;

    public CommentTreePanel()
    {
        super();
        initComponents();
    }

    public void actionPerformed(
        ActionEvent e )
    {
        Comment comment = ((CommentNode) commentTree.getLastSelectedPathComponent()).getComment();
        comment.setContent( commentTextArea.getText() );
        comment.setTitle( titleTextField.getText() );
    }

    public void nothingSelected()
    {
        textAreaScrollPane.setVisible( false );
        headerPanel.setVisible( false );

        root.removeAllChildren();
        commentTree.setRootVisible( false );
        updateUI();
    }

    @Override
    public void onAdd()
    {
        Comment comment = JbookshelfFactory.eINSTANCE.createComment();
        selectedCommentable.getComments().add( comment );
        Date value = new Date();
        comment.setCreationDate( value );
        comment.setTitle( "comment" + value.toLocaleString() );
        comment.setContent( "" );

        CommentNode node = new CommentNode( comment );
        root.add( node );
        commentTree.updateUI();

        commentTree.setSelectionRow( root.getChildCount() - 1 );
        editComment( comment );
    }

    @Override
    public void onKeyTyped(
        KeyEvent evt )
    {
        String text = ((JTextComponent) evt.getSource()).getText();
        if ( text.equals( "" ) )
        {
            drawComments( selectedCommentable.getComments() );
        }
        else
        {
            drawComments( selectedCommentable.queryComments( text ) );
        }
    }

    @Override
    public void onRemove()
    {
        CommentNode commentNode = (CommentNode) commentTree.getLastSelectedPathComponent();
        selectedCommentable.getComments().remove( commentNode.getComment() );

        root.remove( commentNode );
        commentTree.updateUI();

        commentTree.setSelectionRow( 0 );
    }

    public void selectedUnique(
        Unique unique )
    {
        textAreaScrollPane.setVisible( true );
        headerPanel.setVisible( true );

        selectedCommentable = (Commentable) unique;
        drawComments( selectedCommentable.getComments() );
    }

    private void drawComments(
        List<Comment> comments )
    {
        root.removeAllChildren();

        for ( Comment comment : comments )
        {
            CommentNode parent = new CommentNode( comment );
            root.add( parent );
        }

        commentTree.expandRow( 0 );
        commentTree.setRootVisible( false );

        updateUI();
    }

    private void editComment(
        Comment comment )
    {
        dateLabel.setText( comment.getCreationDate().toLocaleString() );
        dateLabel.repaint();
        titleTextField.setText( comment.getTitle() );
        commentTextArea.setText( comment.getContent() );

        submitButton.setEnabled( true );
    }

    private void initComponents()
    {
        submitButton.setText( "v" );
        submitButton.setEnabled( false );

        dateLabel.setText( "Created " );
        commentTextArea.setColumns( 20 );
        commentTextArea.setRows( 5 );

        add( headerPanel );
        headerPanel.add( dateLabel, BorderLayout.WEST );
        headerPanel.add( titleTextField, BorderLayout.CENTER );
        headerPanel.add( submitButton, BorderLayout.EAST );

        add( textAreaScrollPane );
        add( treeScrollPane );

        commentTree.addMouseListener( new MouseAdapter()
        {
            @SuppressWarnings( "synthetic-access" )
            @Override
            public void mousePressed(
                MouseEvent e )
            {
                RelatedPanel.getInstance().focusGained( null );
                CommentNode commentNode = (CommentNode) commentTree.getLastSelectedPathComponent();
                editComment( commentNode.getComment() );
            }
        } );

        submitButton.addActionListener( this );

        nothingSelected();

        // todo check focus and notify RelatedPanel
    }
}
