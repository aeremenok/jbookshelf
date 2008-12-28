/**
 * <copyright> This file is part of JBookShelf, http://code.google.com/p/jbookshelf/<br>
 * <br>
 * Copyright (C) 2008 Andrey Yeremenok (eav1986_at_gmail_com) <br>
 * <br>
 * JBookShelf is free software; you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation; either version 3 of the License, or (at your option) any later
 * version.<br>
 * <br>
 * JBookShelf is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.<br>
 * <br>
 * You should have received a copy of the GNU General Public License along with this program. If not, see
 * <http://www.gnu.org/licenses/>. </copyright> $Id$
 */
package org.jbookshelf.qtgui.widgets.treepanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

import org.jbookshelf.model.Comment;
import org.jbookshelf.model.Commentable;
import org.jbookshelf.model.Unique;
import org.jbookshelf.qtgui.widgets.panel.RelatedPanel;

public class CommentTreePanel
    extends SearchableTreePanel
    implements
        ActionListener
{
    private class CommentNode
    // extends DefaultMutableTreeNode
    {
        private final Comment comment;

        public CommentNode(
            Comment comment )
        {
            // super( comment.getTitle() );
            this.comment = comment;
        }

        public Comment getComment()
        {
            return comment;
        }
    }

    private static final SimpleDateFormat format = new SimpleDateFormat( "dd.MM.yy HH:mm" );

    // private final DefaultMutableTreeNode root = new DefaultMutableTreeNode();
    // private final JTree commentTree = new JTree( root );
    //
    // private final JTextArea commentTextArea = new JTextArea();
    // private final JTextField titleTextField = new JTextField();
    // private final JLabel dateLabel = new JLabel();
    //
    // private final JPanel editPanel = new JPanel( new VerticalLayout() )
    // {
    // @SuppressWarnings( "synthetic-access" )
    // @Override
    // public void setVisible(
    // boolean flag )
    // {
    // super.setVisible( flag );
    // refresh();
    // }
    // };

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
        // CommentNode node = (CommentNode) commentTree.getLastSelectedPathComponent();
        // Comment comment = node.getComment();
        // comment.setContent( commentTextArea.getText() );
        // comment.setTitle( titleTextField.getText() );
        //
        // node.setUserObject( comment.getTitle() );
        // commentTree.updateUI();
    }

    public void nothingSelected()
    {
        // selectedCommentable = null;
        //
        // root.removeAllChildren();
        // commentTree.setRootVisible( false );
        // commentTree.updateUI();
        //
        // editPanel.setVisible( false );
        // relatedPanel.focusLost();
    }

    @Override
    public void onAdd()
    {
        // Comment comment = ModelFactory.eINSTANCE.createComment();
        // selectedCommentable.getComments().add( comment );
        // Date value = new Date();
        // comment.setCreationDate( value );
        // comment.setTitle( "comment" + format.format( value ) );
        // comment.setContent( "" );
        //
        // root.add( new CommentNode( comment ) );
        // commentTree.updateUI();
        //
        // commentTree.setSelectionRow( root.getChildCount() - 1 );
        // editComment( comment );
    }

    @Override
    public void search(
        String text )
    {
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
        // CommentNode commentNode = (CommentNode) commentTree.getLastSelectedPathComponent();
        // selectedCommentable.getComments().remove( commentNode.getComment() );
        //
        // int row = root.getIndex( commentNode );
        // root.remove( commentNode );
        // commentTree.updateUI();
        //
        // if ( row > 1 )
        // {
        // commentTree.setSelectionRow( row - 1 );
        // }
        // else
        // {
        // relatedPanel.focusLost();
        // }
    }

    public void selectedUnique(
        Unique unique )
    {
        // editPanel.setVisible( false );
        // relatedPanel.focusLost();
        //
        // selectedCommentable = (Commentable) unique;
        // drawComments( selectedCommentable.getComments() );
    }

    private void drawComments(
        List<Comment> comments )
    {
        // root.removeAllChildren();
        // commentTree.setRootVisible( true );
        //
        // for ( Comment comment : comments )
        // {
        // root.add( new CommentNode( comment ) );
        // }
        //
        // commentTree.expandRow( 0 );
        // commentTree.setRootVisible( false );
        //
        // commentTree.updateUI();
    }

    private void editComment(
        Comment comment )
    {
        // dateLabel.setText( "Created " + format.format( comment.getCreationDate() ) );
        // titleTextField.setText( comment.getTitle() );
        // commentTextArea.setText( comment.getContent() );
        //
        // editPanel.setVisible( true );
    }

    private void initComponents()
    {
        // JButton submitButton = new JButton();
        // submitButton.addActionListener( this );
        // submitButton.setIcon( createIcon( "/org/jbookshelf/gui/images/dialog-ok-apply.png" ) );
        //
        // JPanel headerPanel = new JPanel( new BorderLayout() );
        // headerPanel.add( dateLabel, BorderLayout.WEST );
        // headerPanel.add( titleTextField, BorderLayout.CENTER );
        // headerPanel.add( submitButton, BorderLayout.EAST );
        //
        // commentTextArea.setColumns( 20 );
        // commentTextArea.setRows( 5 );
        //
        // editPanel.add( headerPanel );
        // editPanel.add( new JScrollPane( commentTextArea ) );
        //
        // add( editPanel, BorderLayout.NORTH );
        // add( new JScrollPane( commentTree ), BorderLayout.CENTER );
        //
        // commentTree.addTreeSelectionListener( new TreeSelectionListener()
        // {
        // @SuppressWarnings( "synthetic-access" )
        // public void valueChanged(
        // TreeSelectionEvent e )
        // {
        // CommentNode commentNode = (CommentNode) commentTree.getLastSelectedPathComponent();
        // if ( commentNode != null )
        // {
        // editComment( commentNode.getComment() );
        // }
        // relatedPanel.focusGained();
        // }
        // } );
        //
        // nothingSelected();
    }

    private void refresh()
    {
        // updateUI();
        // editPanel.updateUI();
    }
}
