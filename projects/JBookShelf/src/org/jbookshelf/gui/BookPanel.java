/*
 * BookPanel.java Created on 16 Ноябрь 2008 г., 23:01
 */

package org.jbookshelf.gui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

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
    extends javax.swing.JPanel
{
    private ObjectToStringConverter converter = new ObjectToStringConverter()
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

        public boolean isRead()
        {
            return isRead;
        }

        public String getBookName()
        {
            return bookName;
        }

        public String getAuthorName()
        {
            return authorName;
        }

        public String getCategoryName()
        {
            return categoryName;
        }

        public File getFile()
        {
            return file;
        }
    }

    /** Creates new form BookPanel */
    public BookPanel()
    {
        super();
        initComponents();

        initCustomComponents();

        registerComponents();

        setVisible( true );
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {
        bookTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        authorTextField = new javax.swing.JTextField();
        categoryTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        fileTextField = new javax.swing.JTextField();
        chooseButton = new javax.swing.JButton();
        isReadCheckBox = new javax.swing.JCheckBox();

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle( "org/jbookshelf/gui/Bundle" ); // NOI18N
        bookTextField.setText( bundle.getString( "BookPanel.bookTextField.text" ) ); // NOI18N

        jLabel3.setText( bundle.getString( "BookPanel.jLabel3.text" ) ); // NOI18N

        jLabel4.setText( bundle.getString( "BookPanel.jLabel4.text" ) ); // NOI18N

        authorTextField.setText( bundle.getString( "BookPanel.authorTextField.text" ) ); // NOI18N

        categoryTextField.setText( bundle.getString( "BookPanel.categoryTextField.text" ) ); // NOI18N

        jLabel5.setText( bundle.getString( "BookPanel.jLabel5.text" ) ); // NOI18N

        jLabel2.setText( bundle.getString( "BookPanel.jLabel2.text" ) ); // NOI18N

        fileTextField.setText( bundle.getString( "BookPanel.fileTextField.text" ) ); // NOI18N

        chooseButton.setText( bundle.getString( "BookPanel.chooseButton.text" ) ); // NOI18N
        chooseButton.addActionListener( new java.awt.event.ActionListener()
        {
            public void actionPerformed(
                java.awt.event.ActionEvent evt )
            {
                chooseButtonActionPerformed( evt );
            }
        } );

        isReadCheckBox.setText( bundle.getString( "BookPanel.isReadCheckBox.text" ) ); // NOI18N
        isReadCheckBox.setHorizontalTextPosition( javax.swing.SwingConstants.LEADING );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout( this );
        this.setLayout( layout );
        layout.setHorizontalGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING ).addGroup(
            layout.createSequentialGroup().addContainerGap().addGroup(
                layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING ).addComponent( isReadCheckBox )
                    .addGroup(
                        layout.createSequentialGroup().addGroup(
                            layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING ).addComponent(
                                jLabel5 ).addComponent( jLabel4 ).addComponent( jLabel3 ).addComponent( jLabel2 ) )
                            .addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED ).addGroup(
                                layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING ).addGroup(
                                    javax.swing.GroupLayout.Alignment.TRAILING,
                                    layout.createSequentialGroup().addComponent( fileTextField,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE ).addPreferredGap(
                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED )
                                        .addComponent( chooseButton ) ).addComponent( authorTextField,
                                    javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE ).addComponent(
                                    bookTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE )
                                    .addComponent( categoryTextField, javax.swing.GroupLayout.Alignment.TRAILING,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE ) ) ) ).addGap( 16,
                16, 16 ) ) );
        layout.setVerticalGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING ).addGroup(
            layout.createSequentialGroup().addContainerGap().addGroup(
                layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE ).addComponent( jLabel3 )
                    .addComponent( bookTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE ) )
                .addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED ).addGroup(
                    layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE ).addComponent( jLabel4 )
                        .addComponent( authorTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE ) )
                .addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED ).addGroup(
                    layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE ).addComponent( jLabel5 )
                        .addComponent( categoryTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE ) )
                .addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED ).addGroup(
                    layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE ).addComponent( jLabel2 )
                        .addComponent( chooseButton ).addComponent( fileTextField,
                            javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                            javax.swing.GroupLayout.PREFERRED_SIZE ) ).addPreferredGap(
                    javax.swing.LayoutStyle.ComponentPlacement.RELATED ).addComponent( isReadCheckBox )
                .addContainerGap( javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE ) ) );
    }// </editor-fold>//GEN-END:initComponents

    private void chooseButtonActionPerformed(
        @SuppressWarnings( "unused" ) java.awt.event.ActionEvent evt )
    {// GEN-FIRST:event_chooseButtonActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        if ( fileChooser.showOpenDialog( this ) == JFileChooser.APPROVE_OPTION )
        {
            fileTextField.setText( fileChooser.getSelectedFile().getAbsolutePath() );
        }
    }// GEN-LAST:event_chooseButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField authorTextField;
    private javax.swing.JTextField bookTextField;
    private javax.swing.JTextField categoryTextField;
    private javax.swing.JButton    chooseButton;
    private javax.swing.JTextField fileTextField;
    private javax.swing.JCheckBox  isReadCheckBox;
    private javax.swing.JLabel     jLabel2;
    private javax.swing.JLabel     jLabel3;
    private javax.swing.JLabel     jLabel4;
    private javax.swing.JLabel     jLabel5;

    // End of variables declaration//GEN-END:variables

    private void registerComponents()
    {
        components.add( bookTextField );
        components.add( authorTextField );
        components.add( categoryTextField );
        components.add( fileTextField );
    }

    private void initCustomComponents()
    {
        AutoCompleteDecorator.decorate( authorTextField, Storage.getBookShelf().getAuthors(), false, converter );
        AutoCompleteDecorator.decorate( categoryTextField, Storage.getBookShelf().getCategories(), false, converter );
        // todo better autocomplete and comma separated values
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

    private List<JComponent> components = new ArrayList<JComponent>();

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
}