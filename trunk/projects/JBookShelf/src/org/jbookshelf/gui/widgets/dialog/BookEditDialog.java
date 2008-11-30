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
package org.jbookshelf.gui.widgets.dialog;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.WindowConstants;

import org.eclipse.emf.common.util.EList;
import org.jbookshelf.controller.FileImporter;
import org.jbookshelf.controller.storage.Storage;
import org.jbookshelf.gui.Resourses;
import org.jbookshelf.gui.widgets.panel.BookPanel;
import org.jbookshelf.gui.widgets.panel.CollectionPanel;
import org.jbookshelf.gui.widgets.panel.BookPanel.Parameters;
import org.jbookshelf.model.Author;
import org.jbookshelf.model.Category;
import org.jbookshelf.model.ReadingUnit;
import org.jdesktop.swingx.VerticalLayout;

public class BookEditDialog
    extends JDialog
{
    private JButton           applyButton  = new JButton();
    private JButton           cancelButton = new JButton();
    private JLabel            headerLabel  = new JLabel();

    private BookPanel         bookPanel    = new BookPanel();

    private final ReadingUnit book;

    public BookEditDialog(
        Frame parent,
        boolean modal,
        ReadingUnit book )
    {
        super( parent, modal );
        this.book = book;
        registerComponents();
        initComponents();
        initListeners();

        bookPanel.setBook( book );
    }

    public void saveBook(
        Parameters parameters )
    {
        EList<Author> authors = Storage.getBookShelf().queryAuthors( parameters.getAuthorName() );
        if ( authors != null && authors.size() > 0 )
        { // todo what if we've found more than 1 author with equal names?
            book.getAuthors().set( 0, authors.get( 0 ) ); // todo edit multiple authors
        }
        else
        {
            Author author = book.getAuthors().get( 0 ); // todo edit multiple authors
            author.setName( parameters.getAuthorName() );
        }

        EList<Category> categories = Storage.getBookShelf().queryCategories( parameters.getCategoryName() );
        if ( categories != null && categories.size() > 0 )
        {
            book.getCategories().set( 0, categories.get( 0 ) ); // todo edit multiple categories
        }
        else
        {
            Category category = book.getCategories().get( 0 );
            category.setName( parameters.getCategoryName() ); // todo edit multiple categories
        }
        book.setName( parameters.getBookName() );

        book.setPhysical( FileImporter.createPhysicalUnit( parameters.getFile() ) );
        book.setRead( parameters.isRead() );
    }

    private void initComponents()
    {
        setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );

        headerLabel.setFont( new Font( "Tahoma", 1, 14 ) );

        JPanel contentPanel = new JPanel( new VerticalLayout() );
        add( contentPanel );

        contentPanel.add( headerLabel );
        contentPanel.add( new JSeparator() );
        contentPanel.add( bookPanel );

        JPanel buttonPanel = new JPanel();
        contentPanel.add( buttonPanel );
        buttonPanel.add( applyButton );
        buttonPanel.add( cancelButton );

        pack();
    }

    private void initListeners()
    {
        cancelButton.addActionListener( new ActionListener()
        {
            public void actionPerformed(
                ActionEvent evt )
            {
                dispose();
            }
        } );

        applyButton.addActionListener( new ActionListener()
        {
            public void actionPerformed(
                ActionEvent evt )
            {
                Parameters parameters = bookPanel.extractParameters();
                if ( parameters != null )
                {
                    saveBook( parameters );
                    CollectionPanel.getInstance().updateTree();
                    dispose();
                }
            }
        } );
    }

    private void registerComponents()
    {
        applyButton.setName( "applyButton" );
        headerLabel.setName( "headerLabel" );
        cancelButton.setName( "cancelButton" );

        Resourses.register( getClass(), applyButton );
        Resourses.register( getClass(), cancelButton );
        Resourses.register( getClass(), headerLabel );
    }
}
