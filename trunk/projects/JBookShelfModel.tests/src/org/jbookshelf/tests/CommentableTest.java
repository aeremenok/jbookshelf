/**
 * <copyright> </copyright> $Id$
 */
package org.jbookshelf.tests;

import junit.framework.Assert;

import org.eclipse.emf.common.util.EList;
import org.jbookshelf.model.Comment;
import org.jbookshelf.model.Commentable;
import org.jbookshelf.model.ModelFactory;

/**
 * <!-- begin-user-doc --> A test case for the model object '<em><b>Commentable</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 * <li>{@link org.jbookshelf.model.Commentable#queryComments(java.lang.String) <em>Query Comments</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public abstract class CommentableTest
    extends UniqueTest
{

    /**
     * Constructs a new Commentable test case with the given name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public CommentableTest(
        String name )
    {
        super( name );
    }

    /**
     * Tests the '{@link org.jbookshelf.model.Commentable#queryComments(java.lang.String) <em>Query Comments</em>}'
     * operation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.jbookshelf.model.Commentable#queryComments(java.lang.String)
     * @generated NOT
     */
    public void testQueryComments__String()
    {
        getFixture().getComments().clear();

        Comment comment1 = ModelFactory.eINSTANCE.createComment();
        comment1.setTitle( "comment1" );
        Comment comment2 = ModelFactory.eINSTANCE.createComment();
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
     * Returns the fixture for this Commentable test case. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected Commentable getFixture()
    {
        return (Commentable) fixture;
    }

} // CommentableTest