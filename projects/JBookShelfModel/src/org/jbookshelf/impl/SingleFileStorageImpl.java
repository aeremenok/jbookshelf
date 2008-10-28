/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jbookshelf.impl;

import java.io.File;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.jbookshelf.JbookshelfPackage;
import org.jbookshelf.SingleFileStorage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Single File Storage</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jbookshelf.impl.SingleFileStorageImpl#getCollectionStorageFile <em>Collection Storage File</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SingleFileStorageImpl extends BookShelfStorageImpl implements SingleFileStorage
{
    /**
     * The default value of the '{@link #getCollectionStorageFile() <em>Collection Storage File</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCollectionStorageFile()
     * @generated
     * @ordered
     */
    protected static final File COLLECTION_STORAGE_FILE_EDEFAULT = null;
    /**
     * The cached value of the '{@link #getCollectionStorageFile() <em>Collection Storage File</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCollectionStorageFile()
     * @generated
     * @ordered
     */
    protected File collectionStorageFile = COLLECTION_STORAGE_FILE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SingleFileStorageImpl()
    {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass()
    {
        return JbookshelfPackage.Literals.SINGLE_FILE_STORAGE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public File getCollectionStorageFile()
    {
        return collectionStorageFile;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCollectionStorageFile(File newCollectionStorageFile)
    {
        File oldCollectionStorageFile = collectionStorageFile;
        collectionStorageFile = newCollectionStorageFile;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, JbookshelfPackage.SINGLE_FILE_STORAGE__COLLECTION_STORAGE_FILE, oldCollectionStorageFile, collectionStorageFile));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void backupCollection(File externalFile)
    {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void restoreCollection(File externalFile)
    {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType)
    {
        switch (featureID)
        {
            case JbookshelfPackage.SINGLE_FILE_STORAGE__COLLECTION_STORAGE_FILE:
                return getCollectionStorageFile();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue)
    {
        switch (featureID)
        {
            case JbookshelfPackage.SINGLE_FILE_STORAGE__COLLECTION_STORAGE_FILE:
                setCollectionStorageFile((File)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID)
    {
        switch (featureID)
        {
            case JbookshelfPackage.SINGLE_FILE_STORAGE__COLLECTION_STORAGE_FILE:
                setCollectionStorageFile(COLLECTION_STORAGE_FILE_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID)
    {
        switch (featureID)
        {
            case JbookshelfPackage.SINGLE_FILE_STORAGE__COLLECTION_STORAGE_FILE:
                return COLLECTION_STORAGE_FILE_EDEFAULT == null ? collectionStorageFile != null : !COLLECTION_STORAGE_FILE_EDEFAULT.equals(collectionStorageFile);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString()
    {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (collectionStorageFile: ");
        result.append(collectionStorageFile);
        result.append(')');
        return result.toString();
    }

} //SingleFileStorageImpl
