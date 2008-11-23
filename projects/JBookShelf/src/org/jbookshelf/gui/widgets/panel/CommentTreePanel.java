package org.jbookshelf.gui.widgets.panel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
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
import org.jdesktop.swingx.VerticalLayout;

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

    private static final SimpleDateFormat format             = new SimpleDateFormat( "dd.MM.yy HH:mm" );

    private JTextArea                     commentTextArea    = new JTextArea();
    private JScrollPane                   textAreaScrollPane = new JScrollPane( commentTextArea );
    private JTextField                    titleTextField     = new JTextField();
    private JLabel                        dateLabel          = new JLabel();
    private DefaultMutableTreeNode        root               = new DefaultMutableTreeNode();
    private JTree                         commentTree        = new JTree( root );
    private JScrollPane                   treeScrollPane     = new JScrollPane( commentTree );
    private JPanel                        editPanel          = new JPanel( new VerticalLayout() )
                                                             {
                                                                 @SuppressWarnings( "synthetic-access" )
                                                                 @Override
                                                                 public void setVisible(
                                                                     boolean flag )
                                                                 {
                                                                     super.setVisible( flag );
                                                                     refresh();
                                                                 }
                                                             };
    private JPanel                        headerPanel        = new JPanel( new BorderLayout() );
    private JButton                       submitButton       = new JButton();

    private Commentable                   selectedCommentable;

    public CommentTreePanel(
        RelatedPanel relatedPanel )
    {
        super( relatedPanel );
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
        selectedCommentable = null;

        root.removeAllChildren();
        commentTree.setRootVisible( false );
        commentTree.updateUI();

        editPanel.setVisible( false );
        relatedPanel.focusLost( null );
    }

    private void refresh()
    {
        updateUI();
        editPanel.updateUI();
        treeScrollPane.updateUI();
    }

    @Override
    public void onAdd()
    {
        Comment comment = JbookshelfFactory.eINSTANCE.createComment();
        selectedCommentable.getComments().add( comment );
        Date value = new Date();
        comment.setCreationDate( value );
        comment.setTitle( "comment" + format.format( value ) );
        comment.setContent( "" );

        root.add( new CommentNode( comment ) );
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

        int row = root.getIndex( commentNode );
        root.remove( commentNode );
        commentTree.updateUI();

        if ( row > 1 )
        {
            commentTree.setSelectionRow( row - 1 );
        }
        else
        {
            relatedPanel.focusLost( null );
        }
    }

    public void selectedUnique(
        Unique unique )
    {
        editPanel.setVisible( false );
        relatedPanel.focusLost( null );

        selectedCommentable = (Commentable) unique;
        drawComments( selectedCommentable.getComments() );
    }

    private void drawComments(
        List<Comment> comments )
    {
        root.removeAllChildren();
        commentTree.setRootVisible( true );

        for ( Comment comment : comments )
        {
            root.add( new CommentNode( comment ) );
        }

        commentTree.expandRow( 0 );
        commentTree.setRootVisible( false );

        commentTree.updateUI();
    }

    private void editComment(
        Comment comment )
    {
        dateLabel.setText( "Created " + format.format( comment.getCreationDate() ) );
        titleTextField.setText( comment.getTitle() );
        commentTextArea.setText( comment.getContent() );

        editPanel.setVisible( true );
    }

    private void initComponents()
    {
        submitButton.setText( "v" );

        commentTextArea.setColumns( 20 );
        commentTextArea.setRows( 5 );

        add( editPanel, BorderLayout.NORTH );
        editPanel.add( headerPanel );
        headerPanel.add( dateLabel, BorderLayout.WEST );
        headerPanel.add( titleTextField, BorderLayout.CENTER );
        headerPanel.add( submitButton, BorderLayout.EAST );

        editPanel.add( textAreaScrollPane );
        add( treeScrollPane, BorderLayout.CENTER );

        commentTree.addMouseListener( new MouseAdapter()
        {
            @SuppressWarnings( "synthetic-access" )
            @Override
            public void mousePressed(
                MouseEvent e )
            {
                CommentNode commentNode = (CommentNode) commentTree.getLastSelectedPathComponent();
                if ( commentNode != null )
                {
                    editComment( commentNode.getComment() );
                }
                relatedPanel.focusGained( null );
            }
        } );

        submitButton.addActionListener( this );

        nothingSelected();

        // todo check focus and notify RelatedPanel
    }
}
