package org.util.storage;

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
import org.jbookshelf.BookShelf;
import org.jbookshelf.JbookshelfFactory;
import org.jbookshelf.JbookshelfPackage;

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
            BookShelf bookShelf = JbookshelfFactory.eINSTANCE.createBookShelf();
            backupCollection( bookShelf, externalFile );
            return bookShelf;
        }

        ResourceSet resourceSet = new ResourceSetImpl();

        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put( Registry.DEFAULT_EXTENSION,
            new XMLResourceFactoryImpl() );

        resourceSet.getPackageRegistry().put( JbookshelfPackage.eNS_URI, JbookshelfPackage.eINSTANCE );

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
