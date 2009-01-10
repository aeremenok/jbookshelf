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
package org.jbookshelf.model.impl;

import java.io.File;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.jbookshelf.controller.URIOpener;
import org.jbookshelf.model.ModelPackage;
import org.jbookshelf.model.SingleFileFolder;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Single File Folder</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.jbookshelf.model.impl.SingleFileFolderImpl#getFolder <em>Folder</em>}</li>
 * <li>{@link org.jbookshelf.model.impl.SingleFileFolderImpl#getSingleFile <em>Single File</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class SingleFileFolderImpl
    extends PhysicalUnitImpl
    implements
        SingleFileFolder
{
    /**
     * The default value of the '{@link #getFolder() <em>Folder</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getFolder()
     * @generated
     * @ordered
     */
    protected static final File FOLDER_EDEFAULT      = null;

    /**
     * The cached value of the '{@link #getFolder() <em>Folder</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getFolder()
     * @generated
     * @ordered
     */
    protected File              folder               = FOLDER_EDEFAULT;

    /**
     * The default value of the '{@link #getSingleFile() <em>Single File</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getSingleFile()
     * @generated
     * @ordered
     */
    protected static final File SINGLE_FILE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSingleFile() <em>Single File</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getSingleFile()
     * @generated
     * @ordered
     */
    protected File              singleFile           = SINGLE_FILE_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected SingleFileFolderImpl()
    {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(
        int featureID,
        boolean resolve,
        boolean coreType )
    {
        switch ( featureID )
        {
            case ModelPackage.SINGLE_FILE_FOLDER__FOLDER:
                return getFolder();
            case ModelPackage.SINGLE_FILE_FOLDER__SINGLE_FILE:
                return getSingleFile();
        }
        return super.eGet( featureID, resolve, coreType );
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean eIsSet(
        int featureID )
    {
        switch ( featureID )
        {
            case ModelPackage.SINGLE_FILE_FOLDER__FOLDER:
                return FOLDER_EDEFAULT == null ? folder != null : !FOLDER_EDEFAULT.equals( folder );
            case ModelPackage.SINGLE_FILE_FOLDER__SINGLE_FILE:
                return SINGLE_FILE_EDEFAULT == null ? singleFile != null : !SINGLE_FILE_EDEFAULT.equals( singleFile );
        }
        return super.eIsSet( featureID );
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eSet(
        int featureID,
        Object newValue )
    {
        switch ( featureID )
        {
            case ModelPackage.SINGLE_FILE_FOLDER__FOLDER:
                setFolder( (File) newValue );
                return;
            case ModelPackage.SINGLE_FILE_FOLDER__SINGLE_FILE:
                setSingleFile( (File) newValue );
                return;
        }
        super.eSet( featureID, newValue );
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eUnset(
        int featureID )
    {
        switch ( featureID )
        {
            case ModelPackage.SINGLE_FILE_FOLDER__FOLDER:
                setFolder( FOLDER_EDEFAULT );
                return;
            case ModelPackage.SINGLE_FILE_FOLDER__SINGLE_FILE:
                setSingleFile( SINGLE_FILE_EDEFAULT );
                return;
        }
        super.eUnset( featureID );
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public File getFolder()
    {
        return folder;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public File getSingleFile()
    {
        return singleFile;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    @Override
    public void openFolder()
    {
        URIOpener.openFolder( getFolder() );
    }

    @Override
    public void openUnit()
    {
        URIOpener.browseFile( getSingleFile() );
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setFolder(
        File newFolder )
    {
        File oldFolder = folder;
        folder = newFolder;
        if ( eNotificationRequired() )
        {
            eNotify( new ENotificationImpl( this, Notification.SET, ModelPackage.SINGLE_FILE_FOLDER__FOLDER, oldFolder,
                folder ) );
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setSingleFile(
        File newSingleFile )
    {
        File oldSingleFile = singleFile;
        singleFile = newSingleFile;
        if ( eNotificationRequired() )
        {
            eNotify( new ENotificationImpl( this, Notification.SET, ModelPackage.SINGLE_FILE_FOLDER__SINGLE_FILE,
                oldSingleFile, singleFile ) );
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String toString()
    {
        if ( eIsProxy() )
        {
            return super.toString();
        }

        StringBuffer result = new StringBuffer( super.toString() );
        result.append( " (folder: " );
        result.append( folder );
        result.append( ", singleFile: " );
        result.append( singleFile );
        result.append( ')' );
        return result.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass()
    {
        return ModelPackage.Literals.SINGLE_FILE_FOLDER;
    }

} // SingleFileFolderImpl
