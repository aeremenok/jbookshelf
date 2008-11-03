/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jbookshelf.tests;

import junit.textui.TestRunner;

import org.jbookshelf.JbookshelfFactory;
import org.jbookshelf.ReadingUnit;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Reading Unit</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 *   <li>{@link org.jbookshelf.Commentable#queryComments(java.lang.String) <em>Query Comments</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class ReadingUnitTest extends UniqueTest
{

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static void main(String[] args)
    {
        TestRunner.run(ReadingUnitTest.class);
    }

    /**
     * Constructs a new Reading Unit test case with the given name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ReadingUnitTest(String name)
    {
        super(name);
    }

    /**
     * Returns the fixture for this Reading Unit test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected ReadingUnit getFixture()
    {
        return (ReadingUnit)fixture;
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
        setFixture(JbookshelfFactory.eINSTANCE.createReadingUnit());
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
     * Tests the '{@link org.jbookshelf.Commentable#queryComments(java.lang.String) <em>Query Comments</em>}' operation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.jbookshelf.Commentable#queryComments(java.lang.String)
     * @generated
     */
    public void testQueryComments__String()
    {
        // TODO: implement this operation test method
        // Ensure that you remove @generated or mark it @generated NOT
        fail();
    }

} //ReadingUnitTest
