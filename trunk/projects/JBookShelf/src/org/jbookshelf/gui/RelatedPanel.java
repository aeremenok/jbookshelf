/*
 * RelatedPanel.java Created on 15 Ноябрь 2008 г., 12:42
 */

package org.jbookshelf.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import org.jbookshelf.Unique;
import org.jbookshelf.gui.logic.UniqueSelectionListener;
import org.jbookshelf.gui.widgets.panel.CommentTreePanel;
import org.jbookshelf.gui.widgets.panel.RelatedTreePanel;
import org.jbookshelf.gui.widgets.panel.SearchableTreePanel;
import org.jdesktop.swingx.VerticalLayout;

/**
 * @author eav
 */
public class RelatedPanel
    extends JPanel
    implements
        UniqueSelectionListener,
        FocusListener
{
    private static RelatedPanel   instance;
    private SearchableTreePanel[] searchableTreePanels;
    private JButton               addButton       = new JButton();
    private SearchableTreePanel   commentPanel    = new CommentTreePanel();
    private JTabbedPane           tabbedPane      = new JTabbedPane();
    private SearchableTreePanel   relatedPanel    = new RelatedTreePanel();
    private JButton               removeButton    = new JButton();
    private JTextField            searchTextField = new JTextField();

    public RelatedPanel()
    {
        super( new VerticalLayout() );
        initComponents();
        initListeners();
    }

    public SearchableTreePanel[] getSearchableTreePanels()
    {
        if ( searchableTreePanels == null )
        {
            searchableTreePanels = new SearchableTreePanel[] { commentPanel, relatedPanel };
        }
        return searchableTreePanels;
    }

    public SearchableTreePanel getSelectedTreePanel()
    {
        return getSearchableTreePanels()[tabbedPane.getSelectedIndex()];
    }

    private void addButtonActionPerformed(
        @SuppressWarnings( "unused" ) ActionEvent evt )
    {
        getSelectedTreePanel().onAdd();
    }

    private void initComponents()
    {
        ResourceBundle bundle = ResourceBundle.getBundle( "org/jbookshelf/gui/Bundle" ); // NOI18N

        tabbedPane.addTab( bundle.getString( "RelatedPanel.commentPanel.TabConstraints.tabTitle" ), commentPanel ); // NOI18N
        tabbedPane.addTab( bundle.getString( "RelatedPanel.relatedPanel.TabConstraints.tabTitle" ), relatedPanel ); // NOI18N

        searchTextField.setText( bundle.getString( "RelatedPanel.searchTextField.text" ) ); // NOI18N
        addButton.setText( bundle.getString( "RelatedPanel.addButton.text" ) ); // NOI18N
        removeButton.setText( bundle.getString( "RelatedPanel.removeButton.text" ) ); // NOI18N

        removeButton.setEnabled( false );

        JPanel panel = new JPanel( new BorderLayout() );
        add( panel );
        JPanel buttonPanel = new JPanel();
        panel.add( buttonPanel, BorderLayout.WEST );
        buttonPanel.add( addButton );
        buttonPanel.add( removeButton );

        panel.add( searchTextField, BorderLayout.CENTER );

        add( tabbedPane );
    }

    private void initListeners()
    {
        searchTextField.addKeyListener( new KeyAdapter()
        {
            @SuppressWarnings( "synthetic-access" )
            @Override
            public void keyTyped(
                java.awt.event.KeyEvent evt )
            {
                searchTextFieldKeyTyped( evt );
            }
        } );
        removeButton.addActionListener( new ActionListener()
        {
            @SuppressWarnings( "synthetic-access" )
            public void actionPerformed(
                ActionEvent evt )
            {
                removeButtonActionPerformed( evt );
            }
        } );
        addButton.addActionListener( new ActionListener()
        {
            @SuppressWarnings( "synthetic-access" )
            public void actionPerformed(
                ActionEvent evt )
            {
                addButtonActionPerformed( evt );
            }
        } );
    }

    private void removeButtonActionPerformed(
        @SuppressWarnings( "unused" ) ActionEvent evt )
    {
        getSelectedTreePanel().onRemove();
    }

    private void searchTextFieldKeyTyped(
        KeyEvent evt )
    {
        getSelectedTreePanel().onKeyTyped( evt );
    }

    public static RelatedPanel getInstance()
    {
        if ( instance == null )
        {
            instance = new RelatedPanel();
        }
        return instance;
    }

    public void nothingSelected()
    {
        getSelectedTreePanel().nothingSelected();
    }

    public void selectedUnique(
        Unique unique )
    {
        getSelectedTreePanel().selectedUnique( unique );
    }

    public void focusGained(
        FocusEvent e )
    {
        removeButton.setEnabled( true );
    }

    public void focusLost(
        FocusEvent e )
    {
        removeButton.setEnabled( false );
    }
}
