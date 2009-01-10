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
 * <http://www.gnu.org/licenses/>. </copyright> $Id$
 */
package org.jbookshelf.controller.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.Resource.Factory.Registry;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.jbookshelf.model.BookShelf;
import org.jbookshelf.model.ModelFactory;
import org.jbookshelf.model.ModelPackage;

public abstract class StorageImpl
{
    public void backupCollection(
        BookShelf bookShelf,
        File externalFile )
    {
        if ( !externalFile.exists() )
        {
            try
            {
                externalFile.createNewFile();
            }
            catch ( IOException e )
            {
                throw new Error( externalFile.getAbsolutePath(), e );
            }
        }

        ResourceSet resourceSet = new ResourceSetImpl();

        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(
            Resource.Factory.Registry.DEFAULT_EXTENSION, new XMLResourceFactoryImpl() );

        URI fileURI = URI.createFileURI( externalFile.getAbsolutePath() );

        Resource resource = resourceSet.createResource( fileURI );

        resource.getContents().add( bookShelf );

        try
        {
            resource.save( Collections.EMPTY_MAP );
        }
        catch ( IOException e )
        {
            throw new Error( e );
        }
    }

    public abstract BookShelf loadCollection();

    public BookShelf restoreCollection(
        File externalFile )
    {
        if ( !externalFile.exists() )
        {
            BookShelf bookShelf = ModelFactory.eINSTANCE.createBookShelf();
            backupCollection( bookShelf, externalFile );
            return bookShelf;
        }

        ResourceSet resourceSet = new ResourceSetImpl();

        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put( Registry.DEFAULT_EXTENSION,
            new XMLResourceFactoryImpl() );

        resourceSet.getPackageRegistry().put( ModelPackage.eNS_URI, ModelPackage.eINSTANCE );

        URI fileURI = URI.createFileURI( externalFile.getAbsolutePath() );

        Resource resource = resourceSet.getResource( fileURI, true );

        try
        {
            resource.load( new FileInputStream( externalFile ), Collections.EMPTY_MAP );
            return (BookShelf) resource.getContents().get( 0 );
        }
        catch ( IOException e )
        {
            throw new Error( e );
        }
    }

    public abstract void saveCollection(
        BookShelf bookShelf );
}
