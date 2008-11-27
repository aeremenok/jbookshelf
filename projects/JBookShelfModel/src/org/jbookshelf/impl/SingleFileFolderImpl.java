/**
 * <copyright> </copyright> $Id$
 */
package org.jbookshelf.impl;

import java.io.File;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.jbookshelf.JbookshelfPackage;
import org.jbookshelf.SingleFileFolder;
import org.util.URIOpener;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Single File Folder</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.jbookshelf.impl.SingleFileFolderImpl#getFolder <em>Folder</em>}</li>
 * <li>{@link org.jbookshelf.impl.SingleFileFolderImpl#getSingleFile <em>Single File</em>}</li>
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
     * The default value of the '{@link #getFolder() <em>Folder</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getFolder()
     * @generated
     * @ordered
     */
    protected static final File FOLDER_EDEFAULT      = null;

    /**
     * The cached value of the '{@link #getFolder() <em>Folder</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getFolder()
     * @generated
     * @ordered
     */
    protected File              folder               = FOLDER_EDEFAULT;

    /**
     * The default value of the '{@link #getSingleFile() <em>Single File</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getSingleFile()
     * @generated
     * @ordered
     */
    protected static final File SINGLE_FILE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSingleFile() <em>Single File</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getSingleFile()
     * @generated
     * @ordered
     */
    protected File              singleFile           = SINGLE_FILE_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected SingleFileFolderImpl()
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
            case JbookshelfPackage.SINGLE_FILE_FOLDER__FOLDER:
                return getFolder();
            case JbookshelfPackage.SINGLE_FILE_FOLDER__SINGLE_FILE:
                return getSingleFile();
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
            case JbookshelfPackage.SINGLE_FILE_FOLDER__FOLDER:
                return FOLDER_EDEFAULT == null ? folder != null : !FOLDER_EDEFAULT.equals(folder);
            case JbookshelfPackage.SINGLE_FILE_FOLDER__SINGLE_FILE:
                return SINGLE_FILE_EDEFAULT == null ? singleFile != null : !SINGLE_FILE_EDEFAULT.equals(singleFile);
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
            case JbookshelfPackage.SINGLE_FILE_FOLDER__FOLDER:
                setFolder((File)newValue);
                return;
            case JbookshelfPackage.SINGLE_FILE_FOLDER__SINGLE_FILE:
                setSingleFile((File)newValue);
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
            case JbookshelfPackage.SINGLE_FILE_FOLDER__FOLDER:
                setFolder(FOLDER_EDEFAULT);
                return;
            case JbookshelfPackage.SINGLE_FILE_FOLDER__SINGLE_FILE:
                setSingleFile(SINGLE_FILE_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public File getFolder()
    {
        return folder;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
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
     * @generated
     */
    public void setFolder(
        File newFolder )
    {
        File oldFolder = folder;
        folder = newFolder;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, JbookshelfPackage.SINGLE_FILE_FOLDER__FOLDER, oldFolder, folder));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setSingleFile(
        File newSingleFile )
    {
        File oldSingleFile = singleFile;
        singleFile = newSingleFile;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, JbookshelfPackage.SINGLE_FILE_FOLDER__SINGLE_FILE, oldSingleFile, singleFile));
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
        result.append(" (folder: ");
        result.append(folder);
        result.append(", singleFile: ");
        result.append(singleFile);
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
        return JbookshelfPackage.Literals.SINGLE_FILE_FOLDER;
    }

} // SingleFileFolderImpl