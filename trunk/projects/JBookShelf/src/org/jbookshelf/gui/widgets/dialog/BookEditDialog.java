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

import org.jbookshelf.Author;
import org.jbookshelf.Category;
import org.jbookshelf.ReadingUnit;
import org.jbookshelf.gui.Resourses;
import org.jbookshelf.gui.widgets.panel.BookPanel;
import org.jbookshelf.gui.widgets.panel.BookPanel.Parameters;
import org.jdesktop.swingx.VerticalLayout;
import org.util.FileImporter;

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
