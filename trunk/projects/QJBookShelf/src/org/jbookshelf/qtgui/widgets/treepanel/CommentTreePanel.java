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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.jbookshelf.model.Comment;
import org.jbookshelf.model.Commentable;
import org.jbookshelf.model.ModelFactory;
import org.jbookshelf.model.Unique;
import org.jbookshelf.qtgui.JBookShelfConstants;
import org.jbookshelf.qtgui.widgets.panel.RelatedPanel;

import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QIcon;
import com.trolltech.qt.gui.QLabel;
import com.trolltech.qt.gui.QLineEdit;
import com.trolltech.qt.gui.QPlainTextEdit;
import com.trolltech.qt.gui.QPushButton;
import com.trolltech.qt.gui.QTreeWidget;
import com.trolltech.qt.gui.QTreeWidgetItem;
import com.trolltech.qt.gui.QVBoxLayout;
import com.trolltech.qt.gui.QWidget;

public class CommentTreePanel
    extends SearchableTreePanel
    implements
        JBookShelfConstants
{
    private class CommentNode
        extends QTreeWidgetItem
    {
        private final Comment comment;

        public CommentNode(
            Comment comment )
        {
            setText( 0, comment.getTitle() );
            this.comment = comment;
        }

        public Comment getComment()
        {
            return comment;
        }
    }

    private static final SimpleDateFormat format          = new SimpleDateFormat( "dd.MM.yy HH:mm" );

    private QTreeWidgetItem               root;
    private final QTreeWidget             commentTree     = new QTreeWidget();

    private final QPlainTextEdit          commentTextArea = new QPlainTextEdit();
    private final QLineEdit               titleTextField  = new QLineEdit();
    private final QLabel                  dateLabel       = new QLabel();

    private final QWidget                 editPanel       = new QWidget();

    private Commentable                   selectedCommentable;

    private QPushButton                   submitButton    = new QPushButton();

    public CommentTreePanel(
        RelatedPanel relatedPanel )
    {
        super( relatedPanel );
        initComponents();
        initListeners();
    }

    public void nothingSelected()
    {
        selectedCommentable = null;

        cleanRoot();

        editPanel.setVisible( false );
        relatedPanel.focusLost();
    }

    @Override
    public void onAdd()
    {
        Comment comment = ModelFactory.eINSTANCE.createComment();
        selectedCommentable.getComments().add( comment );
        Date value = new Date();
        comment.setCreationDate( value );
        comment.setTitle( "comment" + format.format( value ) );
        comment.setContent( "" );

        root.addChild( new CommentNode( comment ) );

        commentTree.setCurrentItem( root.child( root.childCount() - 1 ) );
        editComment( comment );
    }

    @Override
    public void onRemove()
    {
        CommentNode commentNode = (CommentNode) commentTree.selectedItems().get( 0 );
        selectedCommentable.getComments().remove( commentNode.getComment() );

        int row = root.indexOfChild( commentNode );
        root.removeChild( commentNode );

        if ( row > 1 )
        {
            commentTree.setCurrentItem( root.child( row - 1 ) );
        }
        else
        {
            relatedPanel.focusLost();
        }
    }

    public void saveComment()
    {
        CommentNode node = (CommentNode) commentTree.selectedItems().get( 0 );
        Comment comment = node.getComment();
        comment.setContent( commentTextArea.toPlainText() );
        comment.setTitle( titleTextField.text() );

        node.setText( 0, comment.getTitle() );
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

    public void selectedUnique(
        Unique unique )
    {
        editPanel.setVisible( false );
        relatedPanel.focusLost();

        selectedCommentable = (Commentable) unique;
        drawComments( selectedCommentable.getComments() );
    }

    private void cleanRoot()
    {
        for ( int i = 0; i < root.childCount(); i++ )
        {
            root.removeChild( root.child( i ) );
        }
    }

    private void drawComments(
        List<Comment> comments )
    {
        cleanRoot();
        for ( Comment comment : comments )
        {
            root.addChild( new CommentNode( comment ) );
        }
        root.setExpanded( true );
    }

    private void editComment(
        Comment comment )
    {
        dateLabel.setText( tr( "Created " ) + format.format( comment.getCreationDate() ) );
        titleTextField.setText( comment.getTitle() );
        commentTextArea.setPlainText( comment.getContent() );

        editPanel.setVisible( true );
    }

    private void initComponents()
    {
        root = commentTree.invisibleRootItem();
        commentTree.header().hide();

        QGridLayout editLayout = new QGridLayout();
        editPanel.setLayout( editLayout );
        editLayout.addWidget( dateLabel, 0, 0 );
        editLayout.addWidget( titleTextField, 0, 1 );
        editLayout.addWidget( submitButton, 0, 2 );

        editLayout.addWidget( commentTextArea, 1, 0, 1, 3 );

        setLayout( new QVBoxLayout() );
        layout().addWidget( editPanel );
        layout().addWidget( commentTree );

        submitButton.setIcon( new QIcon( ICONPATH + "dialog-ok-apply.png" ) );

        nothingSelected();
    }

    private void initListeners()
    {
        commentTree.itemSelectionChanged.connect( this, "onItemSelection()" );
        submitButton.released.connect( this, "saveComment()" );
    }

    @SuppressWarnings( "unused" )
    private void onItemSelection()
    {
        CommentNode commentNode = (CommentNode) commentTree.selectedItems().get( 0 );
        if ( commentNode != null )
        {
            editComment( commentNode.getComment() );
        }
        relatedPanel.focusGained();
    }
}
