/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jbookshelf.tests;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import org.jbookshelf.Commentable;
import org.jbookshelf.JbookshelfFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Commentable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 *   <li>{@link org.jbookshelf.Commentable#queryComments(java.lang.String) <em>Query Comments</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class CommentableTest extends TestCase
{

    /**
     * The fixture for this Commentable test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected Commentable fixture = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static void main(String[] args)
    {
        TestRunner.run(CommentableTest.class);
    }

    /**
     * Constructs a new Commentable test case with the given name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CommentableTest(String name)
    {
        super(name);
    }

    /**
     * Sets the fixture for this Commentable test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void setFixture(Commentable fixture)
    {
        this.fixture = fixture;
    }

    /**
     * Returns the fixture for this Commentable test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected Commentable getFixture()
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
        setFixture(JbookshelfFactory.eINSTANCE.createCommentable());
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

} //CommentableTest
