package org.jbookshelf.view.swinggui.widgets.panel;

import images.IMG;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.annotation.PostConstruct;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.logic.Translatable;
import org.jbookshelf.view.logic.Translator;
import org.jbookshelf.view.swinggui.widgets.TranslatableAction;
import org.jbookshelf.view.swinggui.widgets.tree.AuthorTree;
import org.jbookshelf.view.swinggui.widgets.tree.BookTree;
import org.jbookshelf.view.swinggui.widgets.tree.CategoryTree;
import org.jbookshelf.view.swinggui.widgets.tree.CollectionTree;

public class CollectionPanel
    extends JPanel
    implements
    Translatable,
    ChangeListener
{
    private class ClearAction
        extends TranslatableAction
    {
        public ClearAction()
        {
            super( I18N.tr( "Clear" ), IMG.icon( IMG.EDIT_CLEAR_LOCATIONBAR_RTL_PNG ) );
        }

        public void actionPerformed(
            final ActionEvent e )
        {
            searchTextField.setText( "" );
            updateActiveTree();
        }
    }

    private class SearchAction
        extends TranslatableAction
    {
        public SearchAction()
        {
            super( I18N.tr( "Search" ), IMG.icon( IMG.EDIT_FIND_PNG ) );
        }

        public void actionPerformed(
            final ActionEvent e )
        {
        //            final String text = searchTextField.getText();
        //            final CollectionTree tree = getActiveTree();
        //            if ( tree instanceof BookTree )
        //            {
        //                Boolean isRead = null;
        //                final int index = isReadComboBox.getSelectedIndex();
        //                if ( index == 1 )
        //                {
        //                    isRead = true;
        //                }
        //                else if ( index == 2 )
        //                {
        //                    isRead = false;
        //                }
        //                tree.showResult( Storage.getBookShelf().queryUnits( text, isRead ) );
        //            }
        //            else if ( tree instanceof AuthorTree )
        //            {
        //                tree.showResult( Storage.getBookShelf().queryAuthors( text ) );
        //            }
        //            else
        //            // (tree instanceof CategoryTree)
        //            {
        //                tree.showResult( Storage.getBookShelf().queryCategories( text ) );
        //            }
        }
    }

    private final SearchAction searchAction    = new SearchAction();
    private final ClearAction  clearAction     = new ClearAction();

    private final JTabbedPane  viewTabbedPane  = new JTabbedPane();
    private final JTextField   searchTextField = new JTextField( 50 );
    private final JComboBox    isReadComboBox  = new JComboBox();

    private CollectionTree[]   trees;

    @PostConstruct
    public void initSingleton()
    {
        initComponents();
        Translator.addTranslatable( this );
        updateActiveTree();
    }

    public void retranslate()
    {
        isReadComboBox.removeAllItems();
        isReadComboBox.addItem( I18N.tr( "All" ) );
        isReadComboBox.addItem( I18N.tr( "Read" ) );
        isReadComboBox.addItem( I18N.tr( "Unread" ) );

        searchTextField.setToolTipText( I18N.tr( "Type here your search query" ) );

        clearAction.retranslate();
        searchAction.retranslate();

        for ( int i = 0; i < trees.length; i++ )
        {
            viewTabbedPane.setTitleAt( i, I18N.tr( trees[i].getName() ) );
        }
    }

    public void stateChanged(
        final ChangeEvent e )
    {
        isReadComboBox.setEnabled( getActiveTree() instanceof BookTree );
        updateActiveTree();
    }

    public void updateActiveTree()
    {
        getActiveTree().update();
    }

    private CollectionTree getActiveTree()
    {
        return trees[viewTabbedPane.getSelectedIndex()];
    }

    private void initComponents()
    {
        setLayout( new BorderLayout() );

        final Box searchBox = Box.createHorizontalBox();
        searchBox.add( searchTextField );

        final JButton button = new JButton( clearAction );
        button.setHideActionText( true );
        searchBox.add( button );

        searchBox.add( isReadComboBox );
        searchBox.add( new JButton( searchAction ) );

        add( searchBox, BorderLayout.NORTH );
        add( viewTabbedPane, BorderLayout.CENTER );

        final BookTree bookTree = Single.instance( BookTree.class );
        final AuthorTree authorTree = Single.instance( AuthorTree.class );
        final CategoryTree categoryTree = Single.instance( CategoryTree.class );
        trees = new CollectionTree[]
        { bookTree, authorTree, categoryTree };
        for ( final CollectionTree tree : trees )
        {
            viewTabbedPane.add( new JScrollPane( tree ) );
        }

        viewTabbedPane.addChangeListener( this );
    }
}
