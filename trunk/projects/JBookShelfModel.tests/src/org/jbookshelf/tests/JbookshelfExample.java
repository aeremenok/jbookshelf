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
package org.jbookshelf.tests;

import java.io.File;
import java.io.IOException;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.jbookshelf.model.Author;
import org.jbookshelf.model.ModelFactory;
import org.jbookshelf.model.ModelPackage;

/**
 * <!-- begin-user-doc --> A sample utility for the '<em><b>jbookshelf</b></em>' package. <!-- end-user-doc -->
 * 
 * @generated
 */
public class JbookshelfExample
{
    /**
     * <!-- begin-user-doc --> Load all the argument file paths or URIs as instances of the model. <!-- end-user-doc -->
     * 
     * @param args the file paths or URIs.
     * @generated
     */
    public static void main(
        String[] args )
    {
        // Create a resource set to hold the resources.
        //
        ResourceSet resourceSet = new ResourceSetImpl();

        // Register the appropriate resource factory to handle all file extensions.
        //
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(
            Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl() );

        // Register the package to ensure it is available during loading.
        //
        resourceSet.getPackageRegistry().put( ModelPackage.eNS_URI, ModelPackage.eINSTANCE );

        // If there are no arguments, emit an appropriate usage message.
        //
        if ( args.length == 0 )
        {
            System.out.println( "Enter a list of file paths or URIs that have content like this:" );
            try
            {
                Resource resource = resourceSet.createResource( URI.createURI( "http:///My.jbookshelf" ) );
                Author root = ModelFactory.eINSTANCE.createAuthor();
                resource.getContents().add( root );
                resource.save( System.out, null );
            }
            catch ( IOException exception )
            {
                exception.printStackTrace();
            }
        }
        else
        {
            // Iterate over all the arguments.
            //
            for ( int i = 0; i < args.length; ++i )
            {
                // Construct the URI for the instance file.
                // The argument is treated as a file path only if it denotes an existing file.
                // Otherwise, it's directly treated as a URL.
                //
                File file = new File( args[i] );
                URI uri = file.isFile() ? URI.createFileURI( file.getAbsolutePath() ) : URI.createURI( args[0] );

                try
                {
                    // Demand load resource for this file.
                    //
                    Resource resource = resourceSet.getResource( uri, true );
                    System.out.println( "Loaded " + uri );

                    // Validate the contents of the loaded resource.
                    //
                    for ( EObject eObject : resource.getContents() )
                    {
                        Diagnostic diagnostic = Diagnostician.INSTANCE.validate( eObject );
                        if ( diagnostic.getSeverity() != Diagnostic.OK )
                        {
                            printDiagnostic( diagnostic, "" );
                        }
                    }
                }
                catch ( RuntimeException exception )
                {
                    System.out.println( "Problem loading " + uri );
                    exception.printStackTrace();
                }
            }
        }
    }

    /**
     * <!-- begin-user-doc --> Prints diagnostics with indentation. <!-- end-user-doc -->
     * 
     * @param diagnostic the diagnostic to print.
     * @param indent the indentation for printing.
     * @generated
     */
    protected static void printDiagnostic(
        Diagnostic diagnostic,
        String indent )
    {
        System.out.print( indent );
        System.out.println( diagnostic.getMessage() );
        for ( Diagnostic child : diagnostic.getChildren() )
        {
            printDiagnostic( child, indent + "  " );
        }
    }

} // JbookshelfExample
