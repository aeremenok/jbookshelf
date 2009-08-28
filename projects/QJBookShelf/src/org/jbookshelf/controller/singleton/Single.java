/**
 * <copyright> This file is part of JBookShelf, http://code.google.com/p/jbookshelf/<br>
 * <br>
 * Copyright (C) 2008-2009-2009 Andrey Yeremenok (eav1986_at_gmail_com) <br>
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
package org.jbookshelf.controller.singleton;

/**
 * static {@link SingletonContainer} delegate
 * 
 * @author eav
 */
public abstract class Single
{
    private static final SingletonContainer container = new SingletonContainer();

    public static <T> T instance(
        final Class<T> clazz )
    {
        return container.instance( clazz );
    }

    public static <T> T newInstance(
        final Class<T> clazz )
    {
        return Single.container.newInstance( clazz );
    }

    public static <B, D extends B> void setImplementation(
        final Class<B> basicClass,
        final Class<D> derivedClass )
    {
        container.setImplementation( basicClass, derivedClass );
    }
}
