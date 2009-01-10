/**
 * <copyright> This file is part of JBookShelf, http://code.google.com/p/jbookshelf/<br>
 * <br>
 * Copyright (C) 2008-2009 Andrey Yeremenok (eav1986_at_gmail_com) <br>
 * <br>
 * JBookShelf is free software; you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation; either version 3 of the License, or (at your option) any later
 * version.<br>
 * <br>
 * JBookShelf is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.<br>
 * <br>
 * You should have received a copy of the GNU General Public License along with this program. If not, see
 * <http://www.gnu.org/licenses/>. </copyright>
 */
package org.jbookshelf.qtgui.widgets.menu;

import org.jbookshelf.qtgui.widgets.ext.QMenuExt;
import org.jbookshelf.qtgui.widgets.panel.RelatedPanel;

import com.trolltech.qt.gui.QAction;
import com.trolltech.qt.gui.QPushButton;

/**
 * popups over related panel's trees
 * 
 * @author eav
 */
public class RelatedTreeMenu
    extends QMenuExt
{
    public RelatedTreeMenu(
        RelatedPanel relatedPanel )
    {
        buttonToAction( relatedPanel.getRemoveButton() );
    }

    private QAction buttonToAction(
        QPushButton button )
    {
        // extract some text from button
        String text = button.text();
        if ( "".equals( text ) )
        {
            text = button.toolTip();
        }

        QAction action = addAction( button.icon(), text );
        // action causes button to press
        action.triggered.connect( button.released );

        return action;
    }
}
