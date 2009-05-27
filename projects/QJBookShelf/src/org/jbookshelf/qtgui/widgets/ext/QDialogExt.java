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

import org.jbookshelf.view.logic.Translatable;
import org.jbookshelf.view.logic.Translator;

import com.trolltech.qt.core.Qt.WindowFlags;
import com.trolltech.qt.core.Qt.WindowType;
import com.trolltech.qt.gui.QDialog;
import com.trolltech.qt.gui.QWidget;

/**
 * {@link QDialog} with tricky translation policy
 * 
 * @author eav
 */
public abstract class QDialogExt
    extends QDialog
    implements
        Translatable
{
    public QDialogExt()
    {
        super();
    }

    public QDialogExt(
        QPrivateConstructor p )
    {
        super( p );
    }

    public QDialogExt(
        QWidget parent )
    {
        super( parent );
    }

    public QDialogExt(
        QWidget parent,
        WindowFlags f )
    {
        super( parent, f );
    }

    public QDialogExt(
        QWidget parent,
        WindowType... f )
    {
        super( parent, f );
    }

    @Override
    public String tr(
        String source )
    {
        return Translator.tr( getClass().getName(), source );
    }
}
