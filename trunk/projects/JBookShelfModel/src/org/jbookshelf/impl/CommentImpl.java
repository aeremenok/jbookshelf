/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jbookshelf.impl;

import java.sql.Date;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.jbookshelf.Comment;
import org.jbookshelf.Commentable;
import org.jbookshelf.JbookshelfPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Comment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jbookshelf.impl.CommentImpl#getContent <em>Content</em>}</li>
 *   <li>{@link org.jbookshelf.impl.CommentImpl#getCreationDate <em>Creation Date</em>}</li>
 *   <li>{@link org.jbookshelf.impl.CommentImpl#getSubject <em>Subject</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CommentImpl extends EObjectImpl implements Comment
{
    /**
     * The default value of the '{@link #getContent() <em>Content</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getContent()
     * @generated
     * @ordered
     */
    protected static final String CONTENT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getContent() <em>Content</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getContent()
     * @generated
     * @ordered
     */
    protected String content = CONTENT_EDEFAULT;

    /**
     * The default value of the '{@link #getCreationDate() <em>Creation Date</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCreationDate()
     * @generated
     * @ordered
     */
    protected static final Date CREATION_DATE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getCreationDate() <em>Creation Date</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCreationDate()
     * @generated
     * @ordered
     */
    protected Date creationDate = CREATION_DATE_EDEFAULT;

    /**
     * The cached value of the '{@link #getSubject() <em>Subject</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSubject()
     * @generated
     * @ordered
     */
    protected Commentable subject;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected CommentImpl()
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
        return JbookshelfPackage.Literals.COMMENT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getContent()
    {
        return content;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setContent(String newContent)
    {
        String oldContent = content;
        content = newContent;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, JbookshelfPackage.COMMENT__CONTENT, oldContent, content));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Date getCreationDate()
    {
        return creationDate;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCreationDate(Date newCreationDate)
    {
        Date oldCreationDate = creationDate;
        creationDate = newCreationDate;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, JbookshelfPackage.COMMENT__CREATION_DATE, oldCreationDate, creationDate));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Commentable getSubject()
    {
        if (subject != null && subject.eIsProxy())
        {
            InternalEObject oldSubject = (InternalEObject)subject;
            subject = (Commentable)eResolveProxy(oldSubject);
            if (subject != oldSubject)
            {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, JbookshelfPackage.COMMENT__SUBJECT, oldSubject, subject));
            }
        }
        return subject;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Commentable basicGetSubject()
    {
        return subject;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSubject(Commentable newSubject)
    {
        Commentable oldSubject = subject;
        subject = newSubject;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, JbookshelfPackage.COMMENT__SUBJECT, oldSubject, subject));
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
            case JbookshelfPackage.COMMENT__CONTENT:
                return getContent();
            case JbookshelfPackage.COMMENT__CREATION_DATE:
                return getCreationDate();
            case JbookshelfPackage.COMMENT__SUBJECT:
                if (resolve) return getSubject();
                return basicGetSubject();
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
            case JbookshelfPackage.COMMENT__CONTENT:
                setContent((String)newValue);
                return;
            case JbookshelfPackage.COMMENT__CREATION_DATE:
                setCreationDate((Date)newValue);
                return;
            case JbookshelfPackage.COMMENT__SUBJECT:
                setSubject((Commentable)newValue);
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
            case JbookshelfPackage.COMMENT__CONTENT:
                setContent(CONTENT_EDEFAULT);
                return;
            case JbookshelfPackage.COMMENT__CREATION_DATE:
                setCreationDate(CREATION_DATE_EDEFAULT);
                return;
            case JbookshelfPackage.COMMENT__SUBJECT:
                setSubject((Commentable)null);
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
            case JbookshelfPackage.COMMENT__CONTENT:
                return CONTENT_EDEFAULT == null ? content != null : !CONTENT_EDEFAULT.equals(content);
            case JbookshelfPackage.COMMENT__CREATION_DATE:
                return CREATION_DATE_EDEFAULT == null ? creationDate != null : !CREATION_DATE_EDEFAULT.equals(creationDate);
            case JbookshelfPackage.COMMENT__SUBJECT:
                return subject != null;
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
        result.append(" (content: ");
        result.append(content);
        result.append(", creationDate: ");
        result.append(creationDate);
        result.append(')');
        return result.toString();
    }

} //CommentImpl
