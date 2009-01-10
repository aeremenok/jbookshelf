/**
 * <copyright> This file is part of JBookShelf, http://code.google.com/p/jbookshelf/<br>
 * <br>
 * Copyright (C) 2008-2009 Andrey Yeremenok (eav1986_at_gmail_com) <br>
 * <br>
 * JBookShelf is free software; you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation; either version 3 of the License, or (at your option) any later
 * version.<br>
 * <br>
 * JBookShelf is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.<br>
 * <br>
 * You should have received a copy of the GNU General Public License along with this program. If not, see
 * <http://www.gnu.org/licenses/>. </copyright> $Id$
 */
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
