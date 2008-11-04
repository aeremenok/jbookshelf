/**
 * <copyright> </copyright> $Id$
 */
package org.jbookshelf.impl;

import java.io.File;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.jbookshelf.ArchiveFile;
import org.jbookshelf.JbookshelfPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Archive File</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.jbookshelf.impl.ArchiveFileImpl#getArchiveFile <em>Archive File</em>}</li>
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
     * The default value of the '{@link #getArchiveFile() <em>Archive File</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getArchiveFile()
     * @generated
     * @ordered
     */
    protected static final File ARCHIVE_FILE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getArchiveFile() <em>Archive File</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getArchiveFile()
     * @generated
     * @ordered
     */
    protected File              archiveFile           = ARCHIVE_FILE_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected ArchiveFileImpl()
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
            case JbookshelfPackage.ARCHIVE_FILE__ARCHIVE_FILE:
                return getArchiveFile();
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
            case JbookshelfPackage.ARCHIVE_FILE__ARCHIVE_FILE:
                return ARCHIVE_FILE_EDEFAULT == null ? archiveFile != null : !ARCHIVE_FILE_EDEFAULT
                    .equals( archiveFile );
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
            case JbookshelfPackage.ARCHIVE_FILE__ARCHIVE_FILE:
                setArchiveFile( (File) newValue );
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
            case JbookshelfPackage.ARCHIVE_FILE__ARCHIVE_FILE:
                setArchiveFile( ARCHIVE_FILE_EDEFAULT );
                return;
        }
        super.eUnset( featureID );
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public File getArchiveFile()
    {
        return archiveFile;
    }

    @Override
    public void openUnit()
    {
        // todo
        super.openUnit();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setArchiveFile(
        File newArchiveFile )
    {
        File oldArchiveFile = archiveFile;
        archiveFile = newArchiveFile;
        if ( eNotificationRequired() )
        {
            eNotify( new ENotificationImpl( this, Notification.SET, JbookshelfPackage.ARCHIVE_FILE__ARCHIVE_FILE,
                oldArchiveFile, archiveFile ) );
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
        result.append( " (archiveFile: " );
        result.append( archiveFile );
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
        return JbookshelfPackage.Literals.ARCHIVE_FILE;
    }

} // ArchiveFileImpl
