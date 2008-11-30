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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.WindowConstants;

import org.jbookshelf.controller.FileImporter;
import org.jbookshelf.controller.storage.Storage;
import org.jbookshelf.gui.Resourses;
import org.jbookshelf.gui.widgets.panel.BookPanel;
import org.jbookshelf.gui.widgets.panel.CollectionPanel;
import org.jbookshelf.gui.widgets.panel.BookPanel.Parameters;
import org.jbookshelf.model.Author;
import org.jbookshelf.model.Category;
import org.jbookshelf.model.PhysicalUnit;
import org.jbookshelf.model.ReadingUnit;
import org.jdesktop.swingx.VerticalLayout;

/**
 * @author eav
 */
public class BookAdditionDialog
    extends JDialog
{
    private JButton   addNCloseButton    = new JButton();
    private JButton   addNContinueButton = new JButton();
    private JButton   cancelButton       = new JButton();

    private JLabel    headerLabel        = new JLabel();

    private BookPanel bookPanel          = new BookPanel();

    public BookAdditionDialog(
        java.awt.Frame parent,
        boolean modal )
    {
        super( parent, modal );
        registerComponents();
        initComponents();
        initListeners();
    }

    public void addBook(
        Parameters parameters )
    {
        Author author = Storage.getBookShelf().addAuthor( parameters.getAuthorName() );
        Category category = Storage.getBookShelf().addCategory( parameters.getCategoryName() );
        PhysicalUnit physicalUnit = FileImporter.createPhysicalUnit( parameters.getFile() );
        ReadingUnit unit =
            Storage.getBookShelf().addReadingUnit( parameters.getBookName(), author, category, physicalUnit );
        unit.setRead( parameters.isRead() );

        CollectionPanel.getInstance().updateTree();

        JOptionPane.showMessageDialog( this, parameters.getBookName() + " added" );
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
        buttonPanel.add( addNContinueButton );
        buttonPanel.add( addNCloseButton );
        buttonPanel.add( cancelButton );

        pack();
    }

    private void initListeners()
    {
        addNCloseButton.addActionListener( new ActionListener()
        {
            public void actionPerformed(
                ActionEvent evt )
            {
                Parameters parameters = bookPanel.extractParameters();
                if ( parameters != null )
                {
                    addBook( parameters );
                    dispose();
                }
            }
        } );
        addNContinueButton.addActionListener( new ActionListener()
        {
            public void actionPerformed(
                ActionEvent evt )
            {
                Parameters parameters = bookPanel.extractParameters();
                if ( parameters != null )
                {
                    addBook( parameters );
                    bookPanel.clear();
                }
            }
        } );
        cancelButton.addActionListener( new ActionListener()
        {
            public void actionPerformed(
                ActionEvent evt )
            {
                dispose();
            }
        } );
    }

    private void registerComponents()
    {
        cancelButton.setName( "cancelButton" );
        headerLabel.setName( "headerLabel" );
        addNContinueButton.setName( "addNContinueButton" );
        addNCloseButton.setName( "addNCloseButton" );

        Resourses.register( getClass(), addNCloseButton );
        Resourses.register( getClass(), addNContinueButton );
        Resourses.register( getClass(), cancelButton );
        Resourses.register( getClass(), headerLabel );
        Resourses.register( getClass(), headerLabel );
    }
}
