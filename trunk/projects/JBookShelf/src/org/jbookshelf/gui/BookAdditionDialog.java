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
    private JButton    addNCloseButton;
    private JButton    addNContinueButton;
    private JPanel     bookPanel;
    private JButton    cancelButton;
    private JLabel     headerLabel;
    private JSeparator jSeparator1;

    public BookAdditionDialog(
        java.awt.Frame parent,
        boolean modal )
    {
        super( parent, modal );
        initComponents();
        registerComponents();
        initListeners();
    }

    private void registerComponents()
    {
        Resourses.register( getClass(), addNCloseButton );
        Resourses.register( getClass(), addNContinueButton );
        Resourses.register( getClass(), cancelButton );
        Resourses.register( getClass(), headerLabel );
        Resourses.register( getClass(), headerLabel );
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

    public BookPanel getBookPanel()
    {
        return (BookPanel) bookPanel;
    }

    private void initComponents()
    {
        headerLabel = new JLabel();
        jSeparator1 = new JSeparator();
        cancelButton = new JButton();
        addNContinueButton = new JButton();
        addNCloseButton = new JButton();
        bookPanel = new BookPanel();

        setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );

        headerLabel.setFont( new Font( "Tahoma", 1, 14 ) );

        cancelButton.setName( "cancelButton" );
        headerLabel.setName( "headerLabel" );
        addNContinueButton.setName( "addNContinueButton" );
        addNCloseButton.setName( "addNCloseButton" );

        headerLabel.setText( Resourses.getString( getClass(), headerLabel ) );
        cancelButton.setText( Resourses.getString( getClass(), cancelButton ) );
        addNContinueButton.setText( Resourses.getString( getClass(), addNContinueButton ) );
        addNCloseButton.setText( Resourses.getString( getClass(), addNCloseButton ) );

        JPanel contentPanel = new JPanel( new VerticalLayout() );
        add( contentPanel );

        contentPanel.add( headerLabel );
        contentPanel.add( jSeparator1 );
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
                Parameters parameters = getBookPanel().extractParameters();
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
                Parameters parameters = getBookPanel().extractParameters();
                if ( parameters != null )
                {
                    addBook( parameters );
                    getBookPanel().clear();
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
}
