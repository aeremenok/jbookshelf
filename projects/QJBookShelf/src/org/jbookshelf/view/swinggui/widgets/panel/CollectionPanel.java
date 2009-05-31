package org.jbookshelf.view.swinggui.widgets.panel;

import images.IMG;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.annotation.PostConstruct;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.logic.Translatable;
import org.jbookshelf.view.logic.Translator;
import org.jbookshelf.view.swinggui.widgets.TranslatableAction;
import org.jbookshelf.view.swinggui.widgets.panel.tab.AuthorTab;
import org.jbookshelf.view.swinggui.widgets.panel.tab.BookTab;
import org.jbookshelf.view.swinggui.widgets.panel.tab.CategoryTab;
import org.jbookshelf.view.swinggui.widgets.panel.tab.CollectionTab;

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
            final String text = searchTextField.getText();
            final int selectedIndex = isReadComboBox.getSelectedIndex();
            final Boolean isRead = selectedIndex == 0
                ? null : selectedIndex == 1
                    ? true : false;
            getActiveTree().search( new SearchParameters( isRead, searchContent.isSelected(), text ) );
        }
    }

    private final SearchAction searchAction    = new SearchAction();
    private final ClearAction  clearAction     = new ClearAction();

    private final JTabbedPane  viewTabbedPane  = new JTabbedPane();
    private final JTextField   searchTextField = new JTextField( 50 );
    private final JComboBox    isReadComboBox  = new JComboBox();
    private final JCheckBox    searchContent   = new JCheckBox();

    private CollectionTab[]    tabs;

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
        searchContent.setText( I18N.tr( "Search content" ) );

        clearAction.retranslate();
        searchAction.retranslate();

        for ( int i = 0; i < tabs.length; i++ )
        {
            viewTabbedPane.setTitleAt( i, I18N.tr( tabs[i].getName() ) );
        }
    }

    public void stateChanged(
        final ChangeEvent e )
    {
        final boolean isBook = getActiveTree() instanceof BookTab;
        isReadComboBox.setEnabled( isBook );
        searchContent.setEnabled( isBook );

        updateActiveTree();
    }

    public void updateActiveTree()
    {
        searchAction.actionPerformed( null );
    }

    private CollectionTab getActiveTree()
    {
        return tabs[viewTabbedPane.getSelectedIndex()];
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
        searchBox.add( searchContent );
        searchBox.add( new JButton( searchAction ) );

        add( searchBox, BorderLayout.NORTH );
        add( viewTabbedPane, BorderLayout.CENTER );

        final BookTab bookTab = Single.instance( BookTab.class );
        final AuthorTab authorTab = Single.instance( AuthorTab.class );
        final CategoryTab categoryTab = Single.instance( CategoryTab.class );
        tabs = new CollectionTab[]
        { bookTab, authorTab, categoryTab };
        for ( final CollectionTab tab : tabs )
        {
            viewTabbedPane.add( tab );
        }

        viewTabbedPane.addChangeListener( this );
    }
}
