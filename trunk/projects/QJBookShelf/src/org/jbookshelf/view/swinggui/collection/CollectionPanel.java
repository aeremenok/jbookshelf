package org.jbookshelf.view.swinggui.collection;

import icons.IMG;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.annotation.PostConstruct;
import javax.swing.Action;
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
import org.jbookshelf.view.i18n.Translatable;
import org.jbookshelf.view.logic.BookShelfMediator;
import org.jbookshelf.view.logic.Parameters;
import org.jbookshelf.view.logic.Parameters.Keys;
import org.jbookshelf.view.swinggui.actions.EnterKeyListener;
import org.jbookshelf.view.swinggui.actions.TranslatableAction;
import org.jbookshelf.view.swinggui.collection.tab.AuthorView;
import org.jbookshelf.view.swinggui.collection.tab.CategoryView;
import org.jbookshelf.view.swinggui.collection.tab.CollectionView;
import org.jbookshelf.view.swinggui.collection.tab.book.BookView;
import org.xnap.commons.gui.EraseTextFieldAction;
import org.xnap.commons.i18n.I18n;

public class CollectionPanel
    extends JPanel
    implements
    Translatable,
    ChangeListener
{
    private class SearchAction
        extends TranslatableAction
    {
        public SearchAction()
        {
            super( "Search", IMG.icon( IMG.EDIT_FIND_PNG, 16 ) );
        }

        public void actionPerformed(
            final ActionEvent e )
        {
            Single.instance( BookShelfMediator.class ).nothingSelected();
            final int selectedIndex = isReadComboBox.getSelectedIndex();
            final Boolean isRead = selectedIndex == 0
                ? null : selectedIndex == 1
                    ? true : false;

            final Parameters parameters = new Parameters();
            parameters.put( Keys.SEARCH_TEXT, searchTextField.getText() );
            parameters.put( Keys.SEARCH_IS_READ, isRead );
            parameters.put( Keys.SEARCH_CONTENT, searchContent.isSelected() );

            getActiveTab().search( parameters );
        }
    }

    private final SearchAction searchAction    = new SearchAction();

    private final JTabbedPane  viewTabbedPane  = new JTabbedPane();
    private final JTextField   searchTextField = new JTextField( 50 );
    private final JComboBox    isReadComboBox  = new JComboBox();
    private final JCheckBox    searchContent   = new JCheckBox();

    private CollectionView[]   tabs;

    @PostConstruct
    public void initSingleton()
    {
        initComponents();
        I18N.translate( this );
        updateActiveView();
        searchContent.setEnabled( false );
    }

    public void setResultCount(
        final int count )
    {
        final int selectedIndex = viewTabbedPane.getSelectedIndex();
        viewTabbedPane.setTitleAt( selectedIndex, tabs[selectedIndex].getName() + " (" + count + ") " );
    }

    public void stateChanged(
        final ChangeEvent e )
    {
        final boolean isBook = getActiveTab() instanceof BookView;
        isReadComboBox.setEnabled( isBook );
        // todo search content
        //        searchContent.setEnabled( isBook );

        updateActiveView();
    }

    public void translate(
        final I18n i18n )
    {
        isReadComboBox.removeAllItems();
        isReadComboBox.addItem( i18n.tr( "All" ) );
        isReadComboBox.addItem( i18n.tr( "Read" ) );
        isReadComboBox.addItem( i18n.tr( "Unread" ) );

        searchTextField.setToolTipText( i18n.tr( "Type here your search query" ) );
        searchContent.setText( i18n.tr( "Search content" ) );

        for ( int i = 0; i < tabs.length; i++ )
        {
            viewTabbedPane.setTitleAt( i, i18n.tr( tabs[i].getName() ) );
        }
    }

    public void updateActiveView()
    {
        searchAction.actionPerformed( null );
    }

    private CollectionView getActiveTab()
    {
        return tabs[viewTabbedPane.getSelectedIndex()];
    }

    private void initComponents()
    {
        setLayout( new BorderLayout() );

        final Box searchBox = Box.createHorizontalBox();
        searchBox.add( searchTextField );

        final JButton button = new JButton( new EraseTextFieldAction( searchTextField ) );
        button.getAction().putValue( Action.SMALL_ICON, IMG.icon( IMG.EDIT_CLEAR_LOCATIONBAR_RTL_PNG ) );
        searchBox.add( button );

        searchBox.add( isReadComboBox );
        searchBox.add( searchContent );
        searchBox.add( new JButton( searchAction ) );

        add( searchBox, BorderLayout.NORTH );
        add( viewTabbedPane, BorderLayout.CENTER );

        final BookView bookTab = Single.instance( BookView.class );
        final AuthorView authorTab = Single.instance( AuthorView.class );
        final CategoryView categoryTab = Single.instance( CategoryView.class );
        tabs = new CollectionView[]
        { bookTab, authorTab, categoryTab };
        for ( final CollectionView tab : tabs )
        {
            viewTabbedPane.addTab( tab.getName(), tab.getIcon(), tab );
        }

        viewTabbedPane.addChangeListener( this );
        searchTextField.addKeyListener( new EnterKeyListener( searchAction ) );
    }
}
