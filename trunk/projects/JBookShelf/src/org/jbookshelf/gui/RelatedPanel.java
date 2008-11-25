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
        UniqueSelectionListener
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

    public void focusGained()
    {
        removeButton.setEnabled( true );
    }

    public void focusLost()
    {
        removeButton.setEnabled( false );
    }

    public SearchableTreePanel[] getSearchableTreePanels()
    {
        if ( searchableTreePanels == null )
        {
            searchableTreePanels = new SearchableTreePanel[] { commentPanel, relatedPanel };
        }
        return searchableTreePanels;
    }

    public SearchableTreePanel getActiveTreePanel()
    {
        return getSearchableTreePanels()[tabbedPane.getSelectedIndex()];
    }

    public void nothingSelected()
    {
        for ( SearchableTreePanel panel : getSearchableTreePanels() )
        {
            panel.nothingSelected();
        }
    }

    public void selectedUnique(
        Unique unique )
    {
        for ( SearchableTreePanel panel : getSearchableTreePanels() )
        {
            panel.selectedUnique( unique );
        }
    }

    private void initComponents()
    {
        tabbedPane.addTab( Resourses.getSpecificString( "RelatedPanel.commentPanel.TabConstraints.tabTitle" ),
            commentPanel );
        tabbedPane.addTab( Resourses.getSpecificString( "RelatedPanel.relatedPanel.TabConstraints.tabTitle" ),
            relatedPanel );

        searchTextField.setText( Resourses.getString( "RelatedPanel.searchTextField" ) );

        addButton.setIcon( Resourses.createIcon( "list-add-small.png" ) );
        removeButton.setIcon( Resourses.createIcon( "list-remove-small.png" ) );

        removeButton.setEnabled( false );

        JPanel panel = new JPanel( new BorderLayout() );
        JPanel buttonPanel = new JPanel();

        panel.add( buttonPanel, BorderLayout.WEST );
        panel.add( searchTextField, BorderLayout.CENTER );

        buttonPanel.add( addButton );
        buttonPanel.add( removeButton );

        add( panel, BorderLayout.NORTH );
        add( tabbedPane, BorderLayout.CENTER );
    }

    private void initListeners()
    {
        searchTextField.addFocusListener( new FocusListener()
        {
            @SuppressWarnings( "synthetic-access" )
            public void focusGained(
                FocusEvent e )
            {
                searchTextField.setText( "" );
            }

            @SuppressWarnings( "synthetic-access" )
            public void focusLost(
                FocusEvent e )
            {
                searchTextField.setText( Resourses.getString( "RelatedPanel.searchTextField" ) );
            }
        } );

        searchTextField.addKeyListener( new KeyAdapter()
        {
            @Override
            public void keyReleased(
                KeyEvent e )
            {
                getActiveTreePanel().onKeyReleased( e );
            }
        } );
        removeButton.addActionListener( new ActionListener()
        {
            public void actionPerformed(
                ActionEvent evt )
            {
                getActiveTreePanel().onRemove();
            }
        } );
        addButton.addActionListener( new ActionListener()
        {
            public void actionPerformed(
                ActionEvent evt )
            {
                getActiveTreePanel().onAdd();
            }
        } );
    }
}
