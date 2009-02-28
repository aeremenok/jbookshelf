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
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.jbookshelf.model.ModelPackage;
import org.jbookshelf.model.PhysicalUnit;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Physical Unit</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jbookshelf.model.impl.PhysicalUnitImpl#getFile <em>File</em>}</li>
 *   <li>{@link org.jbookshelf.model.impl.PhysicalUnitImpl#getDirectory <em>Directory</em>}</li>
 *   <li>{@link org.jbookshelf.model.impl.PhysicalUnitImpl#getViewer <em>Viewer</em>}</li>
 *   <li>{@link org.jbookshelf.model.impl.PhysicalUnitImpl#getCharset <em>Charset</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PhysicalUnitImpl
    extends EObjectImpl
    implements
        PhysicalUnit
{
    /**
     * The default value of the '{@link #getFile() <em>File</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see #getFile()
     * @generated
     * @ordered
     */
    protected static final File FILE_EDEFAULT      = null;
    /**
     * The cached value of the '{@link #getFile() <em>File</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see #getFile()
     * @generated
     * @ordered
     */
    protected File              file               = FILE_EDEFAULT;
    /**
     * The default value of the '{@link #getDirectory() <em>Directory</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getDirectory()
     * @generated
     * @ordered
     */
    protected static final File DIRECTORY_EDEFAULT = null;
    /**
     * The cached value of the '{@link #getDirectory() <em>Directory</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getDirectory()
     * @generated
     * @ordered
     */
    protected File              directory          = DIRECTORY_EDEFAULT;

    /**
     * The default value of the '{@link #getViewer() <em>Viewer</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getViewer()
     * @generated
     * @ordered
     */
    protected static final String VIEWER_EDEFAULT = null;
    /**
     * The cached value of the '{@link #getViewer() <em>Viewer</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getViewer()
     * @generated
     * @ordered
     */
    protected String viewer = VIEWER_EDEFAULT;
    /**
     * The default value of the '{@link #getCharset() <em>Charset</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCharset()
     * @generated
     * @ordered
     */
    protected static final String CHARSET_EDEFAULT = null;
    /**
     * The cached value of the '{@link #getCharset() <em>Charset</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCharset()
     * @generated
     * @ordered
     */
    protected String charset = CHARSET_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected PhysicalUnitImpl()
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
            case ModelPackage.PHYSICAL_UNIT__FILE:
                return getFile();
            case ModelPackage.PHYSICAL_UNIT__DIRECTORY:
                return getDirectory();
            case ModelPackage.PHYSICAL_UNIT__VIEWER:
                return getViewer();
            case ModelPackage.PHYSICAL_UNIT__CHARSET:
                return getCharset();
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
            case ModelPackage.PHYSICAL_UNIT__FILE:
                return FILE_EDEFAULT == null ? file != null : !FILE_EDEFAULT.equals(file);
            case ModelPackage.PHYSICAL_UNIT__DIRECTORY:
                return DIRECTORY_EDEFAULT == null ? directory != null : !DIRECTORY_EDEFAULT.equals(directory);
            case ModelPackage.PHYSICAL_UNIT__VIEWER:
                return VIEWER_EDEFAULT == null ? viewer != null : !VIEWER_EDEFAULT.equals(viewer);
            case ModelPackage.PHYSICAL_UNIT__CHARSET:
                return CHARSET_EDEFAULT == null ? charset != null : !CHARSET_EDEFAULT.equals(charset);
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
            case ModelPackage.PHYSICAL_UNIT__FILE:
                setFile((File)newValue);
                return;
            case ModelPackage.PHYSICAL_UNIT__DIRECTORY:
                setDirectory((File)newValue);
                return;
            case ModelPackage.PHYSICAL_UNIT__VIEWER:
                setViewer((String)newValue);
                return;
            case ModelPackage.PHYSICAL_UNIT__CHARSET:
                setCharset((String)newValue);
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
            case ModelPackage.PHYSICAL_UNIT__FILE:
                setFile(FILE_EDEFAULT);
                return;
            case ModelPackage.PHYSICAL_UNIT__DIRECTORY:
                setDirectory(DIRECTORY_EDEFAULT);
                return;
            case ModelPackage.PHYSICAL_UNIT__VIEWER:
                setViewer(VIEWER_EDEFAULT);
                return;
            case ModelPackage.PHYSICAL_UNIT__CHARSET:
                setCharset(CHARSET_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public File getDirectory()
    {
        return directory;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public File getFile()
    {
        return file;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setDirectory(
        File newDirectory )
    {
        File oldDirectory = directory;
        directory = newDirectory;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.PHYSICAL_UNIT__DIRECTORY, oldDirectory, directory));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getViewer()
    {
        return viewer;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setViewer(String newViewer)
    {
        String oldViewer = viewer;
        viewer = newViewer;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.PHYSICAL_UNIT__VIEWER, oldViewer, viewer));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getCharset()
    {
        return charset;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCharset(String newCharset)
    {
        String oldCharset = charset;
        charset = newCharset;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.PHYSICAL_UNIT__CHARSET, oldCharset, charset));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setFile(
        File newFile )
    {
        File oldFile = file;
        file = newFile;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.PHYSICAL_UNIT__FILE, oldFile, file));
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
        result.append(" (file: ");
        result.append(file);
        result.append(", directory: ");
        result.append(directory);
        result.append(", viewer: ");
        result.append(viewer);
        result.append(", charset: ");
        result.append(charset);
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
        return ModelPackage.Literals.PHYSICAL_UNIT;
    }

} // PhysicalUnitImpl
