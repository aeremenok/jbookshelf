/*
 * SettingsDialog.java Created on 31 Октябрь 2008 г., 22:43
 */
package org.jbookshelf.gui;

import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 * @author eav
 */
public class SettingsDialog
    extends javax.swing.JDialog
{
    /** Creates new form SettingsDialog */
    public SettingsDialog(
        java.awt.Frame parent,
        boolean modal )
    {
        super( parent, modal );
        initComponents();
        registerComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings( "unchecked" )
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        lafComboBox = new javax.swing.JComboBox();
        langComboBox = new javax.swing.JComboBox();

        setDefaultCloseOperation( javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
        setName( "Form" ); // NOI18N

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle( "org/jbookshelf/gui/Bundle" ); // NOI18N
        jButton1.setText( bundle.getString( "SettingsDialog.jButton1.text" ) ); // NOI18N
        jButton1.setName( "jButton1" ); // NOI18N

        jButton3.setText( bundle.getString( "SettingsDialog.jButton3.text" ) ); // NOI18N
        jButton3.setName( "jButton3" ); // NOI18N

        jButton4.setText( bundle.getString( "SettingsDialog.jButton4.text" ) ); // NOI18N
        jButton4.setName( "jButton4" ); // NOI18N

        jButton5.setText( bundle.getString( "SettingsDialog.jButton5.text" ) ); // NOI18N
        jButton5.setName( "jButton5" ); // NOI18N

        jLabel1.setFont( new java.awt.Font( "Dialog", 1, 18 ) );
        jLabel1.setText( bundle.getString( "SettingsDialog.jLabel1.text" ) ); // NOI18N
        jLabel1.setName( "jLabel1" ); // NOI18N

        jSeparator1.setName( "jSeparator1" ); // NOI18N

        jLabel2.setText( bundle.getString( "SettingsDialog.jLabel2.text" ) ); // NOI18N
        jLabel2.setName( "jLabel2" ); // NOI18N

        jLabel3.setText( bundle.getString( "SettingsDialog.jLabel3.text" ) ); // NOI18N
        jLabel3.setName( "jLabel3" ); // NOI18N

        jLabel4.setText( bundle.getString( "SettingsDialog.jLabel4.text" ) ); // NOI18N
        jLabel4.setName( "jLabel4" ); // NOI18N

        jLabel5.setText( bundle.getString( "SettingsDialog.jLabel5.text" ) ); // NOI18N
        jLabel5.setName( "jLabel5" ); // NOI18N

        jLabel6.setText( bundle.getString( "SettingsDialog.jLabel6.text" ) ); // NOI18N
        jLabel6.setName( "jLabel6" ); // NOI18N

        jTextField1.setText( bundle.getString( "SettingsDialog.jTextField1.text" ) ); // NOI18N
        jTextField1.setName( "jTextField1" ); // NOI18N

        jTextField2.setText( bundle.getString( "SettingsDialog.jTextField2.text" ) ); // NOI18N
        jTextField2.setName( "jTextField2" ); // NOI18N

        jButton2.setText( bundle.getString( "SettingsDialog.jButton2.text" ) ); // NOI18N
        jButton2.setName( "jButton2" ); // NOI18N

        jTextField3.setText( bundle.getString( "SettingsDialog.jTextField3.text" ) ); // NOI18N
        jTextField3.setName( "jTextField3" ); // NOI18N

        jButton6.setText( bundle.getString( "SettingsDialog.jButton6.text" ) ); // NOI18N
        jButton6.setName( "jButton6" ); // NOI18N

        lafComboBox.setModel( new javax.swing.DefaultComboBoxModel( getLAFs() ) );
        lafComboBox.setName( "lafComboBox" ); // NOI18N
        lafComboBox.addActionListener( new java.awt.event.ActionListener()
        {
            public void actionPerformed(
                java.awt.event.ActionEvent evt )
            {
                lafComboBoxActionPerformed( evt );
            }
        } );

        langComboBox.setModel( new javax.swing.DefaultComboBoxModel( new String[] { "Russian", "English" } ) );
        langComboBox.setName( "langComboBox" ); // NOI18N
        langComboBox.addActionListener( new java.awt.event.ActionListener()
        {
            public void actionPerformed(
                java.awt.event.ActionEvent evt )
            {
                langComboBoxActionPerformed( evt );
            }
        } );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout( getContentPane() );
        getContentPane().setLayout( layout );
        layout.setHorizontalGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING ).addGroup(
            layout.createSequentialGroup().addComponent( jLabel1 ).addContainerGap() ).addComponent( jSeparator1,
            javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE ).addGroup(
            layout.createSequentialGroup().addContainerGap().addComponent( jButton5 ).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED ).addComponent( jButton1 ).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED, 215, Short.MAX_VALUE ).addComponent( jButton4 )
                .addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED ).addComponent( jButton3 )
                .addContainerGap() ).addGroup(
            layout.createSequentialGroup().addContainerGap().addGroup(
                layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING ).addComponent( jLabel6 )
                    .addComponent( jLabel5 ).addComponent( jLabel4 ).addComponent( jLabel3 ).addComponent( jLabel2 ) )
                .addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED ).addGroup(
                    layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING ).addComponent( jTextField1,
                        javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE ).addGroup(
                        javax.swing.GroupLayout.Alignment.TRAILING,
                        layout.createSequentialGroup().addGroup(
                            layout.createParallelGroup( javax.swing.GroupLayout.Alignment.TRAILING ).addComponent(
                                langComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, 379, Short.MAX_VALUE )
                                .addComponent( lafComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, 379,
                                    Short.MAX_VALUE ).addComponent( jTextField3,
                                    javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE,
                                    379, Short.MAX_VALUE ).addComponent( jTextField2,
                                    javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE ) ).addPreferredGap(
                            javax.swing.LayoutStyle.ComponentPlacement.RELATED ).addGroup(
                            layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING ).addComponent(
                                jButton6 ).addComponent( jButton2 ) ) ) ).addContainerGap() ) );
        layout.setVerticalGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING ).addGroup(
            javax.swing.GroupLayout.Alignment.TRAILING,
            layout.createSequentialGroup().addComponent( jLabel1 ).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED ).addComponent( jSeparator1,
                javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE ).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED ).addGroup(
                layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE ).addComponent( jLabel2 )
                    .addComponent( langComboBox, javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE ) ).addGap( 18,
                18, 18 ).addGroup(
                layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE ).addComponent( jLabel3 )
                    .addComponent( lafComboBox, javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE ) ).addGap( 18,
                18, 18 ).addGroup(
                layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE ).addComponent( jLabel4 )
                    .addComponent( jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE ).addComponent(
                        jButton6 ) ).addGap( 18, 18, 18 ).addGroup(
                layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE ).addComponent( jLabel5 )
                    .addComponent( jButton2 ).addComponent( jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE ) ).addGap( 18,
                18, 18 ).addGroup(
                layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE ).addComponent( jLabel6 )
                    .addComponent( jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE ) ).addGap( 18,
                18, 18 ).addGroup(
                layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE ).addComponent( jButton5 )
                    .addComponent( jButton1 ).addComponent( jButton3 ).addComponent( jButton4 ) ).addContainerGap() ) );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lafComboBoxActionPerformed(
        java.awt.event.ActionEvent evt )
    {// GEN-FIRST:event_lafComboBoxActionPerformed
        try
        {
            UIManager.setLookAndFeel( lafClassNames.get( lafComboBox.getSelectedItem() ) );
            SwingUtilities.updateComponentTreeUI( this );
            pack();
        }
        catch ( Exception ex )
        {
            throw new Error( ex );
        }
    }// GEN-LAST:event_lafComboBoxActionPerformed

    private void langComboBoxActionPerformed(
        java.awt.event.ActionEvent evt )
    {// GEN-FIRST:event_langComboBoxActionPerformed
        ResourceBundle bundle;
        if ( langComboBox.getSelectedItem().toString().equals( "Russian" ) )
        {
            bundle = ResourceBundle.getBundle( "org/jbookshelf/gui/Bundle", new Locale( "ru", "RU" ) );
        }
        else
        {
            bundle = ResourceBundle.getBundle( "org/jbookshelf/gui/Bundle", new Locale( "en", "US" ) );
        }

        for ( Component component : components )
        {
            String text = bundle.getString( "SettingsDialog." + component.getName() + ".text" );
            if ( component instanceof JButton )
            {
                ((JButton) component).setText( text );
            }
            else if ( component instanceof JLabel )
            {
                ((JLabel) component).setText( text );
            }
        }
    }// GEN-LAST:event_langComboBoxActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(
        String args[] )
    {
        java.awt.EventQueue.invokeLater( new Runnable()
        {
            @Override
            public void run()
            {
                SettingsDialog dialog = new SettingsDialog( new javax.swing.JFrame(), true );
                dialog.addWindowListener( new java.awt.event.WindowAdapter()
                {
                    @Override
                    public void windowClosing(
                        java.awt.event.WindowEvent e )
                    {
                        System.exit( 0 );
                    }
                } );
                dialog.setVisible( true );
            }
        } );
    }

    /**
     * stores {@link LookAndFeel} classNames
     */
    private static Map<String, String> lafClassNames = new HashMap<String, String>();

    /**
     * @return {@link LookAndFeel} names for combobox
     */
    private static String[] getLAFs()
    {
        LookAndFeelInfo[] installed = UIManager.getInstalledLookAndFeels();
        String[] lafs = new String[installed.length];
        for ( int i = 0; i < installed.length; i++ )
        {
            LookAndFeelInfo lookAndFeelInfo = installed[i];
            lafs[i] = lookAndFeelInfo.getName();
            lafClassNames.put( lafs[i], lookAndFeelInfo.getClassName() );
        }
        return lafs;
    }

    /**
     * stores component names to iterate while changing locale
     */
    private List<Component> components = new ArrayList<Component>();

    /**
     * register components to be localized
     */
    private void registerComponents()
    {
        components.add( jButton1 );
        components.add( jButton2 );
        components.add( jButton3 );
        components.add( jButton4 );
        components.add( jButton5 );
        components.add( jButton6 );

        components.add( jLabel1 );
        components.add( jLabel2 );
        components.add( jLabel3 );
        components.add( jLabel4 );
        components.add( jLabel5 );
        components.add( jLabel6 );
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton    jButton1;
    private javax.swing.JButton    jButton2;
    private javax.swing.JButton    jButton3;
    private javax.swing.JButton    jButton4;
    private javax.swing.JButton    jButton5;
    private javax.swing.JButton    jButton6;
    private javax.swing.JLabel     jLabel1;
    private javax.swing.JLabel     jLabel2;
    private javax.swing.JLabel     jLabel3;
    private javax.swing.JLabel     jLabel4;
    private javax.swing.JLabel     jLabel5;
    private javax.swing.JLabel     jLabel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JComboBox  lafComboBox;
    private javax.swing.JComboBox  langComboBox;
    // End of variables declaration//GEN-END:variables
}
