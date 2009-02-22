/**
 * <copyright> </copyright> $Id$
 */
package org.jbookshelf.model.tests;

import junit.framework.TestCase;

import junit.textui.TestRunner;
import org.jbookshelf.model.ModelFactory;
import org.jbookshelf.model.PhysicalUnit;

/**
 * <!-- begin-user-doc --> A test case for the model object '<em><b>Physical Unit</b></em>'. <!-- end-user-doc -->
 * @generated
 */
public class PhysicalUnitTest
    extends TestCase
{

    /**
     * The fixture for this Physical Unit test case.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected PhysicalUnit fixture = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static void main(String[] args)
    {
        TestRunner.run(PhysicalUnitTest.class);
    }

    /**
     * Constructs a new Physical Unit test case with the given name.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public PhysicalUnitTest(
        String name )
    {
        super(name);
    }

    /**
     * Returns the fixture for this Physical Unit test case.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected PhysicalUnit getFixture()
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
        setFixture(ModelFactory.eINSTANCE.createPhysicalUnit());
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

    /**
     * Sets the fixture for this Physical Unit test case.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected void setFixture(
        PhysicalUnit fixture )
    {
        this.fixture = fixture;
    }

} // PhysicalUnitTest
