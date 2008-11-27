package org.jbookshelf.gui;

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

import org.jbookshelf.Author;
import org.jbookshelf.Category;
import org.jbookshelf.ReadingUnit;
import org.jbookshelf.gui.BookPanel.Parameters;
import org.jdesktop.swingx.VerticalLayout;
import org.util.FileImporter;

public class BookEditDialog
    extends JDialog
{
    private final ReadingUnit book;
    private JButton           applyButton;
    private BookPanel         bookPanel;
    private JButton           cancelButton;
    private JLabel            headerLabel;

    public BookEditDialog(
        Frame parent,
        boolean modal,
        ReadingUnit book )
    {
        super( parent, modal );
        this.book = book;
        initComponents();
        initListeners();

        bookPanel.setBook( book );
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

    public boolean saveBook(
        Parameters parameters )
    {
        Author author = book.getAuthors().get( 0 ); // todo edit multiple authors
        author.setName( parameters.getBookName() );
        Category category = book.getCategories().get( 0 );
        category.setName( parameters.getCategoryName() ); // todo edit multiple categories
        book.setName( parameters.getBookName() );

        book.setPhysical( FileImporter.createPhysicalUnit( parameters.getFile() ) );
        book.setRead( parameters.isRead() );

        return true;
    }

    private void initComponents()
    {
        headerLabel = new JLabel();
        cancelButton = new JButton();
        applyButton = new JButton();
        bookPanel = new BookPanel();

        setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );

        headerLabel.setFont( new Font( "Tahoma", 1, 14 ) );

        registerComponents();

        headerLabel.setText( Resourses.getString( getClass(), headerLabel ) );
        cancelButton.setText( Resourses.getString( getClass(), cancelButton ) );
        applyButton.setText( Resourses.getString( getClass(), applyButton ) );

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
                    dispose();
                }
            }
        } );
    }
}
