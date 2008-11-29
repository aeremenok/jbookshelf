package org.jbookshelf.tests;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.jbookshelf.controller.NameParser;

public class NameParserTest
    extends TestCase
{
    public final void testParse()
    {
        NameParser nameParser = new NameParser( "[%a]-%b    %c." );
        nameParser.parse( "[a]-b    c." );
        Assert.assertEquals( "a", nameParser.getAuthorName() );
        Assert.assertEquals( "b", nameParser.getBookName() );
        Assert.assertEquals( "c", nameParser.getCategoryName() );
        nameParser.parse( "[Author]-%Book    ]-Category." );
        Assert.assertEquals( "Author", nameParser.getAuthorName() );
        Assert.assertEquals( "%Book", nameParser.getBookName() );
        Assert.assertEquals( "]-Category", nameParser.getCategoryName() );

        NameParser nameParser1 = new NameParser( "%a %b %c" );
        nameParser1.parse( "Author Book Category" );
        Assert.assertEquals( "Author", nameParser1.getAuthorName() );
        Assert.assertEquals( "Book", nameParser1.getBookName() );
        Assert.assertEquals( "Category", nameParser1.getCategoryName() );
    }
}
