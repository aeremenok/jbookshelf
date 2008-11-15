/**
 * <copyright> </copyright> $Id$
 */
package org.jbookshelf.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.jbookshelf.Author;
import org.jbookshelf.BookShelf;
import org.jbookshelf.Category;
import org.jbookshelf.JbookshelfFactory;
import org.jbookshelf.JbookshelfPackage;
import org.jbookshelf.PhysicalUnit;
import org.jbookshelf.ReadingUnit;
import org.jbookshelf.Unique;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Book Shelf</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jbookshelf.impl.BookShelfImpl#getAuthors <em>Authors</em>}</li>
 *   <li>{@link org.jbookshelf.impl.BookShelfImpl#getCategories <em>Categories</em>}</li>
 *   <li>{@link org.jbookshelf.impl.BookShelfImpl#getReadingUnits <em>Reading Units</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BookShelfImpl
    extends EObjectImpl
    implements
        BookShelf
{
    /**
     * The cached value of the '{@link #getAuthors() <em>Authors</em>}' containment reference list.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #getAuthors()
     * @generated
     * @ordered
     */
    protected EList<Author>      authors;
    /**
     * The cached value of the '{@link #getCategories() <em>Categories</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getCategories()
     * @generated
     * @ordered
     */
    protected EList<Category>    categories;
    /**
     * The cached value of the '{@link #getReadingUnits() <em>Reading Units</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getReadingUnits()
     * @generated
     * @ordered
     */
    protected EList<ReadingUnit> readingUnits;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected BookShelfImpl()
    {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public Author addAuthor(
        String name )
    {
        Author author = JbookshelfFactory.eINSTANCE.createAuthor();
        author.setName( name );

        if ( getAuthors().add( author ) )
        {
            return author;
        }

        return getAuthorByName( name );
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public Category addCategory(
        String name )
    {
        Category category = JbookshelfFactory.eINSTANCE.createCategory();
        category.setName( name );

        if ( getCategories().add( category ) )
        {
            return category;
        }

        return getCategoryByName( name );
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public ReadingUnit addReadingUnit(
        String name,
        Author author,
        Category category,
        PhysicalUnit physicalUnit )
    {
        ReadingUnit readingUnit = JbookshelfFactory.eINSTANCE.createReadingUnit();

        readingUnit.setName( name );
        readingUnit.setPhysical( physicalUnit );
        readingUnit.getAuthors().add( author );
        readingUnit.getCategories().add( category );

        if ( getReadingUnits().add( readingUnit ) )
        {
            return readingUnit;
        }

        return getReadingUnitByName( name );
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
            case JbookshelfPackage.BOOK_SHELF__AUTHORS:
                return getAuthors();
            case JbookshelfPackage.BOOK_SHELF__CATEGORIES:
                return getCategories();
            case JbookshelfPackage.BOOK_SHELF__READING_UNITS:
                return getReadingUnits();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(
        InternalEObject otherEnd,
        int featureID,
        NotificationChain msgs )
    {
        switch (featureID)
        {
            case JbookshelfPackage.BOOK_SHELF__AUTHORS:
                return ((InternalEList<?>)getAuthors()).basicRemove(otherEnd, msgs);
            case JbookshelfPackage.BOOK_SHELF__CATEGORIES:
                return ((InternalEList<?>)getCategories()).basicRemove(otherEnd, msgs);
            case JbookshelfPackage.BOOK_SHELF__READING_UNITS:
                return ((InternalEList<?>)getReadingUnits()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
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
            case JbookshelfPackage.BOOK_SHELF__AUTHORS:
                return authors != null && !authors.isEmpty();
            case JbookshelfPackage.BOOK_SHELF__CATEGORIES:
                return categories != null && !categories.isEmpty();
            case JbookshelfPackage.BOOK_SHELF__READING_UNITS:
                return readingUnits != null && !readingUnits.isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings( "unchecked" )
    @Override
    public void eSet(
        int featureID,
        Object newValue )
    {
        switch (featureID)
        {
            case JbookshelfPackage.BOOK_SHELF__AUTHORS:
                getAuthors().clear();
                getAuthors().addAll((Collection<? extends Author>)newValue);
                return;
            case JbookshelfPackage.BOOK_SHELF__CATEGORIES:
                getCategories().clear();
                getCategories().addAll((Collection<? extends Category>)newValue);
                return;
            case JbookshelfPackage.BOOK_SHELF__READING_UNITS:
                getReadingUnits().clear();
                getReadingUnits().addAll((Collection<? extends ReadingUnit>)newValue);
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
            case JbookshelfPackage.BOOK_SHELF__AUTHORS:
                getAuthors().clear();
                return;
            case JbookshelfPackage.BOOK_SHELF__CATEGORIES:
                getCategories().clear();
                return;
            case JbookshelfPackage.BOOK_SHELF__READING_UNITS:
                getReadingUnits().clear();
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EList<Author> getAuthors()
    {
        if (authors == null)
        {
            authors = new EObjectContainmentEList<Author>(Author.class, this, JbookshelfPackage.BOOK_SHELF__AUTHORS);
        }
        return authors;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EList<Category> getCategories()
    {
        if (categories == null)
        {
            categories = new EObjectContainmentEList<Category>(Category.class, this, JbookshelfPackage.BOOK_SHELF__CATEGORIES);
        }
        return categories;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EList<ReadingUnit> getReadingUnits()
    {
        if (readingUnits == null)
        {
            readingUnits = new EObjectContainmentEList<ReadingUnit>(ReadingUnit.class, this, JbookshelfPackage.BOOK_SHELF__READING_UNITS);
        }
        return readingUnits;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public EList<Author> queryAuthors(
        String query )
    {
        EList<Author> result = new BasicEList<Author>();

        for ( Author author : getAuthors() )
        {
            if ( author.getName().contains( query ) )
            {
                result.add( author );
            }
        }

        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public EList<Category> queryCategories(
        String query )
    {
        EList<Category> result = new BasicEList<Category>();

        for ( Category category : getCategories() )
        {
            if ( category.getName().contains( query ) )
            {
                result.add( category );
            }
        }

        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public EList<Unique> queryUniques(
        String query )
    {
        EList<Unique> result = new BasicEList<Unique>();

        result.addAll( queryAuthors( query ) );
        result.addAll( queryCategories( query ) );
        result.addAll( queryUnits( query, null ) );

        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public EList<ReadingUnit> queryUnits(
        String query,
        Boolean isRead )
    {
        EList<ReadingUnit> result = new BasicEList<ReadingUnit>();

        if ( isRead == null )
        {
            for ( ReadingUnit unit : getReadingUnits() )
            {
                if ( unit.getName().contains( query ) )
                {
                    result.add( unit );
                }
            }
        }
        else
        {
            for ( ReadingUnit unit : getReadingUnits() )
            {
                if ( isRead.booleanValue() == unit.isRead() && unit.getName().contains( query ) )
                {
                    result.add( unit );
                }
            }
        }

        return result;
    }

    private Author getAuthorByName(
        String name )
    {
        // todo optimize
        for ( Author author : getAuthors() )
        {
            if ( author.getName().equals( name ) )
            {
                return author;
            }
        }
        return null;
    }

    private Category getCategoryByName(
        String name )
    {
        // todo optimize
        for ( Category category : getCategories() )
        {
            if ( category.getName().equals( name ) )
            {
                return category;
            }
        }
        return null;
    }

    private ReadingUnit getReadingUnitByName(
        String name )
    {
        // todo optimize
        for ( ReadingUnit readingUnit : getReadingUnits() )
        {
            if ( readingUnit.getName().equals( name ) )
            {
                return readingUnit;
            }
        }

        return null;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass()
    {
        return JbookshelfPackage.Literals.BOOK_SHELF;
    }

} // BookShelfImpl
