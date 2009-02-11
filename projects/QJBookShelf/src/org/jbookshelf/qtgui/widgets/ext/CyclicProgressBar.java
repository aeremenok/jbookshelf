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
package org.jbookshelf.qtgui.widgets.ext;

import com.trolltech.qt.gui.QProgressBar;
import com.trolltech.qt.gui.QWidget;

/**
 * resets when getting filled
 * 
 * @author eav
 */
public class CyclicProgressBar
    extends QProgressBar
{
    // todo void doJob{}: progressBar shows itself, resets and hides when job is done

    public CyclicProgressBar()
    {
        super();
        init();
    }

    public CyclicProgressBar(
        QPrivateConstructor p )
    {
        super( p );
        init();
    }

    public CyclicProgressBar(
        QWidget parent )
    {
        super( parent );
        init();
    }

    public void increment()
    {
        if ( value() == maximum() )
        {
            reset();
        }
        setValue( value() + 1 );
    }

    private void init()
    {
        setTextVisible( false );
    }
}
