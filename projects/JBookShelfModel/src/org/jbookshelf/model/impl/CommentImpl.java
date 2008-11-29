/**
 * <copyright> </copyright> $Id$
 */
package org.jbookshelf.model.impl;

import java.util.Date;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.jbookshelf.model.Comment;
import org.jbookshelf.model.Commentable;
import org.jbookshelf.model.ModelPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Comment</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jbookshelf.model.impl.CommentImpl#getContent <em>Content</em>}</li>
 *   <li>{@link org.jbookshelf.model.impl.CommentImpl#getCreationDate <em>Creation Date</em>}</li>
 *   <li>{@link org.jbookshelf.model.impl.CommentImpl#getSubject <em>Subject</em>}</li>
 *   <li>{@link org.jbookshelf.model.impl.CommentImpl#getTitle <em>Title</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CommentImpl
    extends EObjectImpl
    implements
        Comment
{
    /**
     * The default value of the '{@link #getContent() <em>Content</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getContent()
     * @generated
     * @ordered
     */
    protected static final String CONTENT_EDEFAULT       = null;

    /**
     * The cached value of the '{@link #getContent() <em>Content</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getContent()
     * @generated
     * @ordered
     */
    protected String              content                = CONTENT_EDEFAULT;

    /**
     * The default value of the '{@link #getCreationDate() <em>Creation Date</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCreationDate()
     * @generated
     * @ordered
     */
    protected static final Date   CREATION_DATE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getCreationDate() <em>Creation Date</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCreationDate()
     * @generated
     * @ordered
     */
    protected Date                creationDate           = CREATION_DATE_EDEFAULT;

    /**
     * The default value of the '{@link #getTitle() <em>Title</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTitle()
     * @generated
     * @ordered
     */
    protected static final String TITLE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTitle() <em>Title</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTitle()
     * @generated
     * @ordered
     */
    protected String title = TITLE_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected CommentImpl()
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
            case ModelPackage.COMMENT__CONTENT:
                return getContent();
            case ModelPackage.COMMENT__CREATION_DATE:
                return getCreationDate();
            case ModelPackage.COMMENT__SUBJECT:
                return getSubject();
            case ModelPackage.COMMENT__TITLE:
                return getTitle();
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
            case ModelPackage.COMMENT__CONTENT:
                return CONTENT_EDEFAULT == null ? content != null : !CONTENT_EDEFAULT.equals(content);
            case ModelPackage.COMMENT__CREATION_DATE:
                return CREATION_DATE_EDEFAULT == null ? creationDate != null : !CREATION_DATE_EDEFAULT.equals(creationDate);
            case ModelPackage.COMMENT__SUBJECT:
                return getSubject() != null;
            case ModelPackage.COMMENT__TITLE:
                return TITLE_EDEFAULT == null ? title != null : !TITLE_EDEFAULT.equals(title);
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
            case ModelPackage.COMMENT__CONTENT:
                setContent((String)newValue);
                return;
            case ModelPackage.COMMENT__CREATION_DATE:
                setCreationDate((Date)newValue);
                return;
            case ModelPackage.COMMENT__SUBJECT:
                setSubject((Commentable)newValue);
                return;
            case ModelPackage.COMMENT__TITLE:
                setTitle((String)newValue);
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
            case ModelPackage.COMMENT__CONTENT:
                setContent(CONTENT_EDEFAULT);
                return;
            case ModelPackage.COMMENT__CREATION_DATE:
                setCreationDate(CREATION_DATE_EDEFAULT);
                return;
            case ModelPackage.COMMENT__SUBJECT:
                setSubject((Commentable)null);
                return;
            case ModelPackage.COMMENT__TITLE:
                setTitle(TITLE_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public String getContent()
    {
        return content;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public Date getCreationDate()
    {
        return creationDate;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public Commentable getSubject()
    {
        if (eContainerFeatureID != ModelPackage.COMMENT__SUBJECT) return null;
        return (Commentable)eContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetSubject(Commentable newSubject, NotificationChain msgs)
    {
        msgs = eBasicSetContainer((InternalEObject)newSubject, ModelPackage.COMMENT__SUBJECT, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setContent(
        String newContent )
    {
        String oldContent = content;
        content = newContent;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.COMMENT__CONTENT, oldContent, content));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setCreationDate(
        Date newCreationDate )
    {
        Date oldCreationDate = creationDate;
        creationDate = newCreationDate;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.COMMENT__CREATION_DATE, oldCreationDate, creationDate));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setSubject(
        Commentable newSubject )
    {
        if (newSubject != eInternalContainer() || (eContainerFeatureID != ModelPackage.COMMENT__SUBJECT && newSubject != null))
        {
            if (EcoreUtil.isAncestor(this, newSubject))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newSubject != null)
                msgs = ((InternalEObject)newSubject).eInverseAdd(this, ModelPackage.COMMENTABLE__COMMENTS, Commentable.class, msgs);
            msgs = basicSetSubject(newSubject, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.COMMENT__SUBJECT, newSubject, newSubject));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTitle(String newTitle)
    {
        String oldTitle = title;
        title = newTitle;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.COMMENT__TITLE, oldTitle, title));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
    {
        switch (featureID)
        {
            case ModelPackage.COMMENT__SUBJECT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetSubject((Commentable)otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
    {
        switch (featureID)
        {
            case ModelPackage.COMMENT__SUBJECT:
                return basicSetSubject(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs)
    {
        switch (eContainerFeatureID)
        {
            case ModelPackage.COMMENT__SUBJECT:
                return eInternalContainer().eInverseRemove(this, ModelPackage.COMMENTABLE__COMMENTS, Commentable.class, msgs);
        }
        return super.eBasicRemoveFromContainerFeature(msgs);
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
        result.append(" (content: ");
        result.append(content);
        result.append(", creationDate: ");
        result.append(creationDate);
        result.append(", title: ");
        result.append(title);
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
        return ModelPackage.Literals.COMMENT;
    }

} // CommentImpl
