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

/**
 * @author eav
 */
public class RelatedPanel
    extends JPanel
    implements
        UniqueSelectionListener,
        FocusListener
{
    private static final RelatedPanel instance        = new RelatedPanel();

    private SearchableTreePanel[]     searchableTreePanels;
    private JButton                   addButton       = new JButton();
    private JTabbedPane               tabbedPane      = new JTabbedPane();
    private JButton                   removeButton    = new JButton();
    private JTextField                searchTextField = new JTextField();

    private SearchableTreePanel       commentPanel    = new CommentTreePanel( this );
    private SearchableTreePanel       relatedPanel    = new RelatedTreePanel( this );

    public static RelatedPanel getInstance()
    {
        return instance;
    }

    public RelatedPanel()
    {
        super( new BorderLayout() );
        initComponents();
        initListeners();
    }

    public void focusGained(
        FocusEvent e )
    {
        removeButton.setEnabled( true );
    }

    public void focusLost(
        FocusEvent e )
    {
        if ( e == null || !removeButton.equals( e.getComponent() ) )
        {
            removeButton.setEnabled( false );
        }
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

    public void nothingSelected()
    {
        getSelectedTreePanel().nothingSelected();
    }

    public void selectedUnique(
        Unique unique )
    {
        getSelectedTreePanel().selectedUnique( unique );
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
        add( panel, BorderLayout.NORTH );
        JPanel buttonPanel = new JPanel();
        panel.add( buttonPanel, BorderLayout.WEST );
        buttonPanel.add( addButton );
        buttonPanel.add( removeButton );

        panel.add( searchTextField, BorderLayout.CENTER );

        add( tabbedPane, BorderLayout.CENTER );
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
}
