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
import org.jbookshelf.model.ArchiveFile;
import org.jbookshelf.model.ModelPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Archive File</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jbookshelf.model.impl.ArchiveFileImpl#getArchiveFile <em>Archive File</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ArchiveFileImpl
    extends PhysicalUnitImpl
    implements
        ArchiveFile
{
    /**
     * The default value of the '{@link #getArchiveFile() <em>Archive File</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getArchiveFile()
     * @generated
     * @ordered
     */
    protected static final File ARCHIVE_FILE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getArchiveFile() <em>Archive File</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getArchiveFile()
     * @generated
     * @ordered
     */
    protected File              archiveFile           = ARCHIVE_FILE_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected ArchiveFileImpl()
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
            case ModelPackage.ARCHIVE_FILE__ARCHIVE_FILE:
                return getArchiveFile();
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
            case ModelPackage.ARCHIVE_FILE__ARCHIVE_FILE:
                return ARCHIVE_FILE_EDEFAULT == null ? archiveFile != null : !ARCHIVE_FILE_EDEFAULT.equals(archiveFile);
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
            case ModelPackage.ARCHIVE_FILE__ARCHIVE_FILE:
                setArchiveFile((File)newValue);
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
            case ModelPackage.ARCHIVE_FILE__ARCHIVE_FILE:
                setArchiveFile(ARCHIVE_FILE_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public File getArchiveFile()
    {
        return archiveFile;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setArchiveFile(
        File newArchiveFile )
    {
        File oldArchiveFile = archiveFile;
        archiveFile = newArchiveFile;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.ARCHIVE_FILE__ARCHIVE_FILE, oldArchiveFile, archiveFile));
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
        result.append(" (archiveFile: ");
        result.append(archiveFile);
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
        return ModelPackage.Literals.ARCHIVE_FILE;
    }

} // ArchiveFileImpl
