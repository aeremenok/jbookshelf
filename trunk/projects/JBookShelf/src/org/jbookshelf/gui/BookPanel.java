/*
 * BookPanel.java Created on 16 Ноябрь 2008 г., 23:01
 */

package org.jbookshelf.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

import org.jbookshelf.ArchiveFile;
import org.jbookshelf.Author;
import org.jbookshelf.Category;
import org.jbookshelf.IndexFileFolder;
import org.jbookshelf.PhysicalUnit;
import org.jbookshelf.ReadingUnit;
import org.jbookshelf.SingleFile;
import org.jbookshelf.SingleFileFolder;
import org.jbookshelf.Unique;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.autocomplete.ObjectToStringConverter;
import org.util.storage.Storage;

/**
 * @author eav
 */
public class BookPanel
    extends JPanel
{
    public class Parameters
    {
        private final String  bookName;
        private final String  authorName;
        private final String  categoryName;
        private final File    file;
        private final boolean isRead;

        public Parameters(
            String bookName,
            String authorName,
            String categoryName,
            File file,
            boolean isRead )
        {
            this.bookName = bookName;
            this.authorName = authorName;
            this.categoryName = categoryName;
            this.file = file;
            this.isRead = isRead;
        }

        public String getAuthorName()
        {
            return authorName;
        }

        public String getBookName()
        {
            return bookName;
        }

        public String getCategoryName()
        {
            return categoryName;
        }

        public File getFile()
        {
            return file;
        }

        public boolean isRead()
        {
            return isRead;
        }
    }

    private ObjectToStringConverter converter         = new ObjectToStringConverter()
                                                      {
                                                          @Override
                                                          public String getPreferredStringForItem(
                                                              Object object )
                                                          {
                                                              if ( object == null )
                                                              {
                                                                  return null;
                                                              }

                                                              if ( object instanceof Unique )
                                                              {
                                                                  return ((Unique) object).getName();
                                                              }

                                                              return object.toString();
                                                          }
                                                      };

    private JLabel                  authorLabel       = new JLabel();
    private JLabel                  categoryLabel     = new JLabel();
    private JLabel                  fileLabel         = new JLabel();
    private JLabel                  nameLabel         = new JLabel();

    private JCheckBox               isReadCheckBox    = new JCheckBox();
    private JButton                 chooseButton      = new JButton();

    private JTextField              authorTextField   = new JTextField();
    private JTextField              bookTextField     = new JTextField();
    private JTextField              categoryTextField = new JTextField();
    private JTextField              fileTextField     = new JTextField();

    private List<JComponent>        components        = new ArrayList<JComponent>();

    public BookPanel()
    {
        super();
        initComponents();
        initListeners();

        initCustomComponents();

        registerComponents();

        setVisible( true );
    }

    public void clear()
    {
        for ( JComponent component : components )
        {
            if ( component instanceof JTextField )
            {
                ((JTextField) component).setText( "" );
            }
        }
        isReadCheckBox.setSelected( false );
    }

    public Parameters extractParameters()
    {
        String bookName = bookTextField.getText();
        if ( bookName.equals( "" ) )
        {
            JOptionPane.showMessageDialog( this, "Book name not specified", "Error", JOptionPane.ERROR_MESSAGE );
            return null;
        }

        String authorName = authorTextField.getText();
        if ( authorName.equals( "" ) )
        {
            JOptionPane.showMessageDialog( this, "Author name not specified", "Error", JOptionPane.ERROR_MESSAGE );
            return null;
        }

        String categoryName = categoryTextField.getText();
        if ( categoryName.equals( "" ) )
        {
            JOptionPane.showMessageDialog( this, "Category name not specified", "Error", JOptionPane.ERROR_MESSAGE );
            return null;
        }

        File file = new File( fileTextField.getText() );
        if ( !file.exists() )
        {
            JOptionPane.showMessageDialog( this, "File " + file.getName() + " does not exist", "Error",
                JOptionPane.ERROR_MESSAGE );
            return null;
        }

        boolean isRead = isReadCheckBox.isSelected();

        return new Parameters( bookName, authorName, categoryName, file, isRead );
    }

    public void setBook(
        ReadingUnit book )
    {
        bookTextField.setText( book.getName() );
        Author author = book.getAuthors().get( 0 ); // todo multiple authors
        authorTextField.setText( author.getName() );
        Category category = book.getCategories().get( 0 ); // todo multiple categories
        categoryTextField.setText( category.getName() );

        String fileName;
        PhysicalUnit physical = book.getPhysical();
        if ( physical instanceof SingleFile )
        {
            SingleFile singleFile = (SingleFile) physical;
            fileName = singleFile.getFile().getAbsolutePath();
        }
        else if ( physical instanceof ArchiveFile )
        {
            ArchiveFile archiveFile = (ArchiveFile) physical;
            fileName = archiveFile.getArchiveFile().getAbsolutePath();
        }
        else if ( physical instanceof SingleFileFolder )
        {
            SingleFileFolder singleFileFolder = (SingleFileFolder) physical;
            fileName = singleFileFolder.getFolder().getAbsolutePath();
        }
        else if ( physical instanceof IndexFileFolder )
        {
            IndexFileFolder indexFileFolder = (IndexFileFolder) physical;
            fileName = indexFileFolder.getIndexFolder().getAbsolutePath();
        }
        else
        {
            throw new Error( physical.toString() );
        }
        fileTextField.setText( fileName );

        isReadCheckBox.setSelected( book.isRead() );
    }

    private void initComponents()
    {
        isReadCheckBox.setHorizontalTextPosition( SwingConstants.LEADING );

        // setLayout( new GridLayout( 5, 2, 10, 10 ) );
        // add( nameLabel );
        // add( bookTextField );
        // add( authorLabel );
        // add( authorTextField );
        // add( categoryLabel );
        // add( categoryTextField );
        // add( fileLabel );
        // JPanel panel = new JPanel( new BorderLayout() );
        // panel.add( fileTextField, BorderLayout.CENTER );
        // panel.add( chooseButton, BorderLayout.EAST );
        // add( panel );
        // add( isReadCheckBox );

        GroupLayout layout = new GroupLayout( this );
        this.setLayout( layout );
        layout.setHorizontalGroup( layout.createParallelGroup( GroupLayout.Alignment.LEADING ).addGroup(
            layout.createSequentialGroup().addContainerGap().addGroup(
                layout.createParallelGroup( GroupLayout.Alignment.LEADING ).addComponent( isReadCheckBox ).addGroup(
                    layout.createSequentialGroup().addGroup(
                        layout.createParallelGroup( GroupLayout.Alignment.LEADING ).addComponent( categoryLabel )
                            .addComponent( authorLabel ).addComponent( nameLabel ).addComponent( fileLabel ) )
                        .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED ).addGroup(
                            layout.createParallelGroup( GroupLayout.Alignment.LEADING ).addGroup(
                                GroupLayout.Alignment.TRAILING,
                                layout.createSequentialGroup().addComponent( fileTextField, GroupLayout.DEFAULT_SIZE,
                                    209, Short.MAX_VALUE ).addPreferredGap( LayoutStyle.ComponentPlacement.RELATED )
                                    .addComponent( chooseButton ) ).addComponent( authorTextField,
                                GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE ).addComponent( bookTextField,
                                GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE ).addComponent( categoryTextField,
                                GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE ) ) ) )
                .addGap( 16, 16, 16 ) ) );
        layout.setVerticalGroup( layout.createParallelGroup( GroupLayout.Alignment.LEADING ).addGroup(
            layout.createSequentialGroup().addContainerGap().addGroup(
                layout.createParallelGroup( GroupLayout.Alignment.BASELINE ).addComponent( nameLabel ).addComponent(
                    bookTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE ) )
                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED ).addGroup(
                    layout.createParallelGroup( GroupLayout.Alignment.BASELINE ).addComponent( authorLabel )
                        .addComponent( authorTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                            GroupLayout.PREFERRED_SIZE ) ).addPreferredGap( LayoutStyle.ComponentPlacement.RELATED )
                .addGroup(
                    layout.createParallelGroup( GroupLayout.Alignment.BASELINE ).addComponent( categoryLabel )
                        .addComponent( categoryTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                            GroupLayout.PREFERRED_SIZE ) ).addPreferredGap( LayoutStyle.ComponentPlacement.RELATED )
                .addGroup(
                    layout.createParallelGroup( GroupLayout.Alignment.BASELINE ).addComponent( fileLabel )
                        .addComponent( chooseButton ).addComponent( fileTextField, GroupLayout.PREFERRED_SIZE,
                            GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE ) ).addPreferredGap(
                    LayoutStyle.ComponentPlacement.RELATED ).addComponent( isReadCheckBox ).addContainerGap(
                    GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE ) ) );
    }

    private void initCustomComponents()
    {
        AutoCompleteDecorator.decorate( authorTextField, Storage.getBookShelf().getAuthors(), false, converter );
        AutoCompleteDecorator.decorate( categoryTextField, Storage.getBookShelf().getCategories(), false, converter );
        // todo better autocomplete and comma separated values
    }

    private void initListeners()
    {
        chooseButton.addActionListener( new ActionListener()
        {
            public void actionPerformed(
                ActionEvent evt )
            {
                JFileChooser fileChooser = new JFileChooser();
                if ( fileChooser.showOpenDialog( BookPanel.this ) == JFileChooser.APPROVE_OPTION )
                {
                    fileTextField.setText( fileChooser.getSelectedFile().getAbsolutePath() );
                }
            }
        } );
    }

    private void registerComponents()
    {
        components.add( bookTextField );
        components.add( authorTextField );
        components.add( categoryTextField );
        components.add( fileTextField );

        nameLabel.setName( "nameLabel" );
        authorLabel.setName( "authorLabel" );
        categoryLabel.setName( "categoryLabel" );
        isReadCheckBox.setName( "isReadCheckBox" );
        fileLabel.setName( "fileLabel" );
        chooseButton.setName( "chooseButton" );

        Resourses.register( getClass(), nameLabel );
        Resourses.register( getClass(), authorLabel );
        Resourses.register( getClass(), categoryLabel );
        Resourses.register( getClass(), isReadCheckBox );
        Resourses.register( getClass(), fileLabel );
        Resourses.register( getClass(), chooseButton );
    }
}
