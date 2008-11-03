/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jbookshelf.tests;

import junit.textui.TestRunner;

import org.jbookshelf.Author;
import org.jbookshelf.JbookshelfFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Author</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 *   <li>{@link org.jbookshelf.Commentable#queryComments(java.lang.String) <em>Query Comments</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class AuthorTest extends UniqueTest
{

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static void main(String[] args)
    {
        TestRunner.run(AuthorTest.class);
    }

    /**
     * Constructs a new Author test case with the given name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AuthorTest(String name)
    {
        super(name);
    }

    /**
     * Returns the fixture for this Author test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected Author getFixture()
    {
        return (Author)fixture;
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
        setFixture(JbookshelfFactory.eINSTANCE.createAuthor());
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

} //AuthorTest
