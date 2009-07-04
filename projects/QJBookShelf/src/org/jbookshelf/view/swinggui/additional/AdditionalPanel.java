/**
 * 
 */
package org.jbookshelf.view.swinggui.additional;

import icons.IMG;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.annotation.PostConstruct;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import org.bushe.swing.event.EventBus;
import org.bushe.swing.event.EventTopicSubscriber;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.view.i18n.I18N;
import org.jbookshelf.view.i18n.Translatable;
import org.jbookshelf.view.logic.BookShelfMediator;
import org.jbookshelf.view.logic.BookShelfMediator.Properties;
import org.xnap.commons.gui.EraseTextFieldAction;
import org.xnap.commons.i18n.I18n;

/**
 * displays different additional data
 * 
 * @author eav 2009
 */
public class AdditionalPanel
    extends JPanel
    implements
    EventTopicSubscriber<BookShelfMediator>,
    Translatable
{
    /**
     * add an additional data entry
     * 
     * @author eav 2009
     */
    private class AddAction
        extends AbstractAction
    {
        public AddAction()
        {
            super( null, IMG.icon( IMG.LIST_ADD_PNG ) );
            setEnabled( false );
        }

        @Override
        public void actionPerformed(
            final ActionEvent e )
        {
            final BookShelfMediator mediator = Single.instance( BookShelfMediator.class );
            getActiveTab().onAdd( mediator.getSelectedBooks().get( 0 ) );
        }
    }

    private final JTextField  searchTextField = new JTextField();
    private final JTabbedPane tabbedPane      = new JTabbedPane();
    private final AddAction   addAction       = new AddAction();

    private AdditionalTab[]   tabs;

    public AdditionalPanel()
    {
        super( new BorderLayout() );
        I18N.translate( this );
    }

    @PostConstruct
    public void init()
    {
        final Box controlPanel = Box.createHorizontalBox();
        controlPanel.add( new JButton( addAction ) );
        controlPanel.add( searchTextField );

        final JButton button = new JButton( new EraseTextFieldAction( searchTextField ) );
        button.getAction().putValue( Action.SMALL_ICON, IMG.icon( IMG.EDIT_CLEAR_LOCATIONBAR_RTL_PNG ) );
        controlPanel.add( button );

        add( controlPanel, BorderLayout.NORTH );
        add( tabbedPane, BorderLayout.CENTER );

        tabs = new AdditionalTab[]
        { Single.instance( NoteTab.class ), Single.instance( RelatedBookTab.class ) };
        for ( final AdditionalTab tab : tabs )
        {
            tabbedPane.add( tab );
        }

        EventBus.subscribe( Properties.BOOKS_SELECTED, this );

        searchTextField.addKeyListener( new KeyAdapter()
        {
            @Override
            public void keyReleased(
                final KeyEvent e )
            {
                getActiveTab().onSearch( searchTextField.getText() );
            }
        } );
    }

    @Override
    public void onEvent(
        final String topic,
        final BookShelfMediator mediator )
    {
        addAction.setEnabled( mediator.getSelectedBooks().size() == 1 );
    }

    @Override
    public void translate(
        final I18n i18n )
    {
        searchTextField.setToolTipText( i18n.tr( "Start typing to filter" ) );
    }

    private AdditionalTab getActiveTab()
    {
        return tabs[tabbedPane.getSelectedIndex()];
    }
}
