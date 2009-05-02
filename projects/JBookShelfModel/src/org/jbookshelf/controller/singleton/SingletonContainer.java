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

import java.util.HashMap;
import java.util.Map;

/**
 * maps singleton classes to their instances and probably implementations
 * 
 * @author eav
 */
public class SingletonContainer
{
    private final Map<Class<? extends Singleton>, Singleton>                  instances       =
                                                                                                  new HashMap<Class<? extends Singleton>, Singleton>();

    private final Map<Class<? extends Singleton>, Class<? extends Singleton>> implementations =
                                                                                                  new HashMap<Class<? extends Singleton>, Class<? extends Singleton>>();

    /**
     * get an instance of a specified class
     * 
     * @param <T> instance class literal
     * @param clazz instance class
     * @return instance
     */
    @SuppressWarnings( "unchecked" )
    public <T extends Singleton> T instance(
        final Class<T> clazz )
    {
        try
        {
            Singleton singleton = instances.get( clazz );
            if ( singleton == null )
            {
                final Class<? extends Singleton> impl = implementations.get( clazz );
                if ( impl != null )
                {
                    singleton = impl.newInstance();
                }
                else
                {
                    singleton = clazz.newInstance();
                }
                instances.put( clazz, singleton );
                singleton.initSingleton();
            }
            return (T) singleton;
        }
        catch ( final Exception e )
        {
            e.printStackTrace();
            throw new Error( e );
        }
    }

    /**
     * set implementation of a basic class
     * 
     * @param <B> basic class literal
     * @param <D> derived class literal
     * @param abstractClass basic class
     * @param concreteClass derived class
     */
    public <B extends Singleton, D extends B> void setImplementation(
        final Class<B> abstractClass,
        final Class<D> concreteClass )
    {
        implementations.put( abstractClass, concreteClass );
    }
}
