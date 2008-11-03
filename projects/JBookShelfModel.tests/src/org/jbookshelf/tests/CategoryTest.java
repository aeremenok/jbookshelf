/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jbookshelf.tests;

import junit.textui.TestRunner;

import org.jbookshelf.Category;
import org.jbookshelf.JbookshelfFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Category</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class CategoryTest extends UniqueTest
{

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static void main(String[] args)
    {
        TestRunner.run(CategoryTest.class);
    }

    /**
     * Constructs a new Category test case with the given name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CategoryTest(String name)
    {
        super(name);
    }

    /**
     * Returns the fixture for this Category test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected Category getFixture()
    {
        return (Category)fixture;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see junit.framework.TestCase#setUp()
     * @generated
     */
    @Override
    protected void setUp() throws Exception
    {
        setFixture(JbookshelfFactory.eINSTANCE.createCategory());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see junit.framework.TestCase#tearDown()
     * @generated
     */
    @Override
    protected void tearDown() throws Exception
    {
        setFixture(null);
    }

} //CategoryTest
