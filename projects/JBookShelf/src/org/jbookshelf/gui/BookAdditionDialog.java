/*
 * BookAdditionDialog.java Created on 1 Ноябрь 2008 г., 16:35
 */

package org.jbookshelf.gui;

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

import org.jbookshelf.Author;
import org.jbookshelf.Category;
import org.jbookshelf.PhysicalUnit;
import org.jbookshelf.ReadingUnit;
import org.jbookshelf.gui.BookPanel.Parameters;
import org.jdesktop.swingx.VerticalLayout;
import org.util.FileImporter;
import org.util.storage.Storage;

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
