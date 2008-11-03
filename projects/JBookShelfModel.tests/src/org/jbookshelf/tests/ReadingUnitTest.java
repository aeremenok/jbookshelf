/**
 * <copyright> </copyright> $Id$
 */
package org.jbookshelf.tests;

import junit.framework.Assert;
import junit.textui.TestRunner;

import org.eclipse.emf.common.util.EList;
import org.jbookshelf.Comment;
import org.jbookshelf.JbookshelfFactory;
import org.jbookshelf.ReadingUnit;

/**
 * <!-- begin-user-doc --> A test case for the model object '<em><b>Reading Unit</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 * <li>{@link org.jbookshelf.Commentable#queryComments(java.lang.String) <em>Query Comments</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ReadingUnitTest
    extends UniqueTest
{

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static void main(
        String[] args )
    {
        TestRunner.run( ReadingUnitTest.class );
    }

    /**
     * Constructs a new Reading Unit test case with the given name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ReadingUnitTest(
        String name )
    {
        super( name );
    }

    /**
     * Tests the '{@link org.jbookshelf.Commentable#queryComments(java.lang.String) <em>Query Comments</em>}' operation.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.jbookshelf.Commentable#queryComments(java.lang.String)
     * @generated NOT
     */
    public void testQueryComments__String()
    {
        getFixture().getComments().clear();

        Comment comment1 = JbookshelfFactory.eINSTANCE.createComment();
        comment1.setTitle( "comment1" );
        Comment comment2 = JbookshelfFactory.eINSTANCE.createComment();
        comment2.setTitle( "comment2" );

        getFixture().getComments().add( comment1 );
        getFixture().getComments().add( comment2 );

        EList<Comment> comments1 = getFixture().queryComments( "comment" );
        Assert.assertNotNull( comments1 );
        Assert.assertTrue( comments1.contains( comment1 ) );
        Assert.assertTrue( comments1.contains( comment2 ) );
        Assert.assertEquals( comments1.size(), 2 );

        EList<Comment> comments2 = getFixture().queryComments( "comment1" );
        Assert.assertNotNull( comments2 );
        Assert.assertTrue( comments2.contains( comment1 ) );
        Assert.assertFalse( comments2.contains( comment2 ) );
        Assert.assertEquals( comments2.size(), 1 );
    }

    /**
     * Returns the fixture for this Reading Unit test case. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected ReadingUnit getFixture()
    {
        return (ReadingUnit) fixture;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see junit.framework.TestCase#setUp()
     * @generated
     */
    @Override
    protected void setUp()
        throws Exception
    {
        setFixture( JbookshelfFactory.eINSTANCE.createReadingUnit() );
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see junit.framework.TestCase#tearDown()
     * @generated
     */
    @Override
    protected void tearDown()
        throws Exception
    {
        setFixture( null );
    }

} // ReadingUnitTest
