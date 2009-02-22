/**
 * <copyright> </copyright> $Id$
 */
package org.jbookshelf.model.tests;

import junit.textui.TestRunner;

import org.jbookshelf.model.Book;
import org.jbookshelf.model.ModelFactory;

/**
 * <!-- begin-user-doc --> A test case for the model object '<em><b>Book</b></em>'. <!-- end-user-doc -->
 * 
 * @generated
 */
public class BookTest
    extends CategorizableTest
{

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static void main(
        String[] args )
    {
        TestRunner.run( BookTest.class );
    }

    /**
     * Constructs a new Book test case with the given name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public BookTest(
        String name )
    {
        super( name );
    }

    /**
     * Returns the fixture for this Book test case. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected Book getFixture()
    {
        return (Book) fixture;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see junit.framework.TestCase#setUp()
     * @generated
     */
    @Override
    protected void setUp()
    {
        setFixture( ModelFactory.eINSTANCE.createBook() );
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see junit.framework.TestCase#tearDown()
     * @generated
     */
    @Override
    protected void tearDown()
    {
        setFixture( null );
    }

} // BookTest
