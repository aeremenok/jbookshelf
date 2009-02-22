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
import org.jbookshelf.model.IndexFileFolder;
import org.jbookshelf.model.ModelPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Index File Folder</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.jbookshelf.model.impl.IndexFileFolderImpl#getIndexFile <em>Index File</em>}</li>
 * <li>{@link org.jbookshelf.model.impl.IndexFileFolderImpl#getIndexFolder <em>Index Folder</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class IndexFileFolderImpl
    extends PhysicalUnitImpl
    implements
        IndexFileFolder
{
    /**
     * The default value of the '{@link #getIndexFile() <em>Index File</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getIndexFile()
     * @generated
     * @ordered
     */
    protected static final File INDEX_FILE_EDEFAULT   = null;

    /**
     * The cached value of the '{@link #getIndexFile() <em>Index File</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getIndexFile()
     * @generated
     * @ordered
     */
    protected File              indexFile             = INDEX_FILE_EDEFAULT;

    /**
     * The default value of the '{@link #getIndexFolder() <em>Index Folder</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getIndexFolder()
     * @generated
     * @ordered
     */
    protected static final File INDEX_FOLDER_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getIndexFolder() <em>Index Folder</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getIndexFolder()
     * @generated
     * @ordered
     */
    protected File              indexFolder           = INDEX_FOLDER_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected IndexFileFolderImpl()
    {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(
        int featureID,
        boolean resolve,
        boolean coreType )
    {
        switch (featureID)
        {
            case ModelPackage.INDEX_FILE_FOLDER__INDEX_FILE:
                return getIndexFile();
            case ModelPackage.INDEX_FILE_FOLDER__INDEX_FOLDER:
                return getIndexFolder();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(
        int featureID )
    {
        switch (featureID)
        {
            case ModelPackage.INDEX_FILE_FOLDER__INDEX_FILE:
                return INDEX_FILE_EDEFAULT == null ? indexFile != null : !INDEX_FILE_EDEFAULT.equals(indexFile);
            case ModelPackage.INDEX_FILE_FOLDER__INDEX_FOLDER:
                return INDEX_FOLDER_EDEFAULT == null ? indexFolder != null : !INDEX_FOLDER_EDEFAULT.equals(indexFolder);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(
        int featureID,
        Object newValue )
    {
        switch (featureID)
        {
            case ModelPackage.INDEX_FILE_FOLDER__INDEX_FILE:
                setIndexFile((File)newValue);
                return;
            case ModelPackage.INDEX_FILE_FOLDER__INDEX_FOLDER:
                setIndexFolder((File)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(
        int featureID )
    {
        switch (featureID)
        {
            case ModelPackage.INDEX_FILE_FOLDER__INDEX_FILE:
                setIndexFile(INDEX_FILE_EDEFAULT);
                return;
            case ModelPackage.INDEX_FILE_FOLDER__INDEX_FOLDER:
                setIndexFolder(INDEX_FOLDER_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public File getIndexFile()
    {
        return indexFile;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public File getIndexFolder()
    {
        return indexFolder;
    }

    @Override
    public void openFolder()
    {
        URIOpener.openFolder( getIndexFolder() );
    }

    @Override
    public void openUnit()
    {
        URIOpener.browseFile( getIndexFile() );
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setIndexFile(
        File newIndexFile )
    {
        File oldIndexFile = indexFile;
        indexFile = newIndexFile;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.INDEX_FILE_FOLDER__INDEX_FILE, oldIndexFile, indexFile));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setIndexFolder(
        File newIndexFolder )
    {
        File oldIndexFolder = indexFolder;
        indexFolder = newIndexFolder;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.INDEX_FILE_FOLDER__INDEX_FOLDER, oldIndexFolder, indexFolder));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString()
    {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (indexFile: ");
        result.append(indexFile);
        result.append(", indexFolder: ");
        result.append(indexFolder);
        result.append(')');
        return result.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass()
    {
        return ModelPackage.Literals.INDEX_FILE_FOLDER;
    }

} // IndexFileFolderImpl
