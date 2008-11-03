/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jbookshelf.tests;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import org.jbookshelf.Categorizable;
import org.jbookshelf.JbookshelfFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Categorizable</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class CategorizableTest extends TestCase
{

    /**
     * The fixture for this Categorizable test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected Categorizable fixture = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static void main(String[] args)
    {
        TestRunner.run(CategorizableTest.class);
    }

    /**
     * Constructs a new Categorizable test case with the given name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CategorizableTest(String name)
    {
        super(name);
    }

    /**
     * Sets the fixture for this Categorizable test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void setFixture(Categorizable fixture)
    {
        this.fixture = fixture;
    }

    /**
     * Returns the fixture for this Categorizable test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected Categorizable getFixture()
    {
        return fixture;
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
        setFixture(JbookshelfFactory.eINSTANCE.createCategorizable());
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

} //CategorizableTest
