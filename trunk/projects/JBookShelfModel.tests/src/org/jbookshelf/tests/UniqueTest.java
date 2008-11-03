/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jbookshelf.tests;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import org.jbookshelf.JbookshelfFactory;
import org.jbookshelf.Unique;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Unique</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class UniqueTest extends TestCase
{

    /**
     * The fixture for this Unique test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected Unique fixture = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static void main(String[] args)
    {
        TestRunner.run(UniqueTest.class);
    }

    /**
     * Constructs a new Unique test case with the given name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public UniqueTest(String name)
    {
        super(name);
    }

    /**
     * Sets the fixture for this Unique test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void setFixture(Unique fixture)
    {
        this.fixture = fixture;
    }

    /**
     * Returns the fixture for this Unique test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected Unique getFixture()
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
        setFixture(JbookshelfFactory.eINSTANCE.createUnique());
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

} //UniqueTest
