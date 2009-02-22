/**
 * <copyright> </copyright> $Id$
 */
package org.jbookshelf.model.tests;

import junit.framework.TestCase;
import junit.textui.TestRunner;

import org.jbookshelf.model.Comment;
import org.jbookshelf.model.ModelFactory;

/**
 * <!-- begin-user-doc --> A test case for the model object '<em><b>Comment</b></em>'. <!-- end-user-doc -->
 * 
 * @generated
 */
public class CommentTest
    extends TestCase
{

    /**
     * The fixture for this Comment test case. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected Comment fixture = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static void main(
        String[] args )
    {
        TestRunner.run( CommentTest.class );
    }

    /**
     * Constructs a new Comment test case with the given name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public CommentTest(
        String name )
    {
        super( name );
    }

    /**
     * Returns the fixture for this Comment test case. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected Comment getFixture()
    {
        return fixture;
    }

    /**
     * Sets the fixture for this Comment test case. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void setFixture(
        Comment fixture )
    {
        this.fixture = fixture;
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
        setFixture( ModelFactory.eINSTANCE.createComment() );
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

} // CommentTest
