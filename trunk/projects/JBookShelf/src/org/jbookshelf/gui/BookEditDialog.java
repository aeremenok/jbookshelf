package org.jbookshelf.gui;

import javax.swing.JDialog;
import javax.swing.JPanel;

import org.jbookshelf.Author;
import org.jbookshelf.Category;
import org.jbookshelf.PhysicalUnit;
import org.jbookshelf.ReadingUnit;
import org.jbookshelf.gui.BookPanel.Parameters;
import org.jdesktop.swingx.VerticalLayout;
import org.util.FileImporter;

public class BookEditDialog
    extends JDialog
{
    private final ReadingUnit book;

    public BookEditDialog(
        java.awt.Frame parent,
        boolean modal,
        ReadingUnit book )
    {
        super( parent, modal );
        this.book = book;
        initComponents();
        getBookPanel().setBook( book );
    }

    private void initComponents()
    {
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        cancelButton = new javax.swing.JButton();
        applyButton = new javax.swing.JButton();
        bookPanel = new BookPanel();

        setDefaultCloseOperation( javax.swing.WindowConstants.DISPOSE_ON_CLOSE );

        jLabel1.setFont( new java.awt.Font( "Tahoma", 1, 14 ) );
        jLabel1.setText( "Add book to Collection" );

        cancelButton.setText( "Cancel" );
        cancelButton.addActionListener( new java.awt.event.ActionListener()
        {
            @SuppressWarnings( "synthetic-access" )
            public void actionPerformed(
                java.awt.event.ActionEvent evt )
            {
                cancelButtonActionPerformed( evt );
            }
        } );

        applyButton.setText( "Apply" );
        applyButton.addActionListener( new java.awt.event.ActionListener()
        {
            @SuppressWarnings( "synthetic-access" )
            public void actionPerformed(
                java.awt.event.ActionEvent evt )
            {
                applyButtonActionPerformed( evt );
            }
        } );

        JPanel contentPanel = new JPanel( new VerticalLayout() );
        add( contentPanel );

        contentPanel.add( jLabel1 );
        contentPanel.add( jSeparator1 );
        contentPanel.add( bookPanel );
        JPanel buttonPanel = new JPanel();
        contentPanel.add( buttonPanel );
        buttonPanel.add( applyButton );
        buttonPanel.add( cancelButton );

        pack();
    }

    private void cancelButtonActionPerformed(
        @SuppressWarnings( "unused" ) java.awt.event.ActionEvent evt )
    {
        dispose();
    }

    private void applyButtonActionPerformed(
        @SuppressWarnings( "unused" ) java.awt.event.ActionEvent evt )
    {
        Parameters parameters = getBookPanel().extractParameters();
        if ( parameters != null )
        {
            saveBook( parameters );
            dispose();
        }
    }

    public boolean saveBook(
        Parameters parameters )
    {
        Author author = book.getAuthors().get( 0 ); // todo edit multiple authors
        author.setName( parameters.getBookName() );
        Category category = book.getCategories().get( 0 );
        category.setName( parameters.getCategoryName() ); // todo edit multiple categories
        book.setName( parameters.getBookName() );

        PhysicalUnit physicalUnit = FileImporter.createPhysicalUnit( parameters.getFile() );
        book.setPhysical( physicalUnit );

        book.setRead( parameters.isRead() );

        return true;
    }

    public BookPanel getBookPanel()
    {
        return (BookPanel) bookPanel;
    }

    private javax.swing.JButton    applyButton;
    private javax.swing.JPanel     bookPanel;
    private javax.swing.JButton    cancelButton;
    private javax.swing.JLabel     jLabel1;
    private javax.swing.JSeparator jSeparator1;
}
