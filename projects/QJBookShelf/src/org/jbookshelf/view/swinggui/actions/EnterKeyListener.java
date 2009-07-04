package org.jbookshelf.view.swinggui.actions;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Action;

/**
 * performs an action when the enter key is released
 * 
 * @author eav 2009
 */
public class EnterKeyListener
    extends KeyAdapter
{
    private final Action action;

    public EnterKeyListener(
        final Action action )
    {
        this.action = action;
    }

    @Override
    public void keyReleased(
        final KeyEvent e )
    {
        if ( e.getKeyCode() == KeyEvent.VK_ENTER )
        {
            action.actionPerformed( null );
        }
    }
}