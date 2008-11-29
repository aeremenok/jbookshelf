package org.jbookshelf.gui.widgets.treepanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.text.JTextComponent;
import javax.swing.tree.DefaultMutableTreeNode;

import org.jbookshelf.Comment;
import org.jbookshelf.Commentable;
import org.jbookshelf.JbookshelfFactory;
import org.jbookshelf.Unique;
import org.jbookshelf.gui.widgets.panel.RelatedPanel;
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

    private static final SimpleDateFormat format          = new SimpleDateFormat( "dd.MM.yy HH:mm" );

    private final DefaultMutableTreeNode  root            = new DefaultMutableTreeNode();
    private final JTree                   commentTree     = new JTree( root );

    private final JTextArea               commentTextArea = new JTextArea();
    private final JTextField              titleTextField  = new JTextField();
    private final JLabel                  dateLabel       = new JLabel();

    private final JPanel                  editPanel       = new JPanel( new VerticalLayout() )
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
        CommentNode node = (CommentNode) commentTree.getLastSelectedPathComponent();
        Comment comment = node.getComment();
        comment.setContent( commentTextArea.getText() );
        comment.setTitle( titleTextField.getText() );

        node.setUserObject( comment.getTitle() );
        commentTree.updateUI();
    }

    public void nothingSelected()
    {
        selectedCommentable = null;

        root.removeAllChildren();
        commentTree.setRootVisible( false );
        commentTree.updateUI();

        editPanel.setVisible( false );
        relatedPanel.focusLost();
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
    public void onKeyReleased(
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
            relatedPanel.focusLost();
        }
    }

    public void selectedUnique(
        Unique unique )
    {
        editPanel.setVisible( false );
        relatedPanel.focusLost();

        selectedCommentable = (Commentable) unique;
        drawComments( selectedCommentable.getComments() );
    }

    private ImageIcon createIcon(
        String name )
    {
        return new ImageIcon( getClass().getResource( name ) );
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
        JButton submitButton = new JButton();
        submitButton.addActionListener( this );
        submitButton.setIcon( createIcon( "/org/jbookshelf/gui/images/dialog-ok-apply.png" ) );

        JPanel headerPanel = new JPanel( new BorderLayout() );
        headerPanel.add( dateLabel, BorderLayout.WEST );
        headerPanel.add( titleTextField, BorderLayout.CENTER );
        headerPanel.add( submitButton, BorderLayout.EAST );

        commentTextArea.setColumns( 20 );
        commentTextArea.setRows( 5 );

        editPanel.add( headerPanel );
        editPanel.add( new JScrollPane( commentTextArea ) );

        add( editPanel, BorderLayout.NORTH );
        add( new JScrollPane( commentTree ), BorderLayout.CENTER );

        commentTree.addTreeSelectionListener( new TreeSelectionListener()
        {
            @SuppressWarnings( "synthetic-access" )
            public void valueChanged(
                TreeSelectionEvent e )
            {
                CommentNode commentNode = (CommentNode) commentTree.getLastSelectedPathComponent();
                if ( commentNode != null )
                {
                    editComment( commentNode.getComment() );
                }
                relatedPanel.focusGained();
            }
        } );

        nothingSelected();
    }

    private void refresh()
    {
        updateUI();
        editPanel.updateUI();
    }
}
