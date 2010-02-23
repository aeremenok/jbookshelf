/**
 * 
 */
package org.jbookshelf.controller.util;

import static org.jbookshelf.controller.singleton.Single.instance;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.env.TestEnvironment;

/**
 * @author eav 2010
 */
public class FileUtilTest
{
    @SuppressWarnings( "unused" )
    private static final Logger log = Logger.getLogger( FileUtilTest.class );

    @BeforeClass
    public void setUp()
    {
        instance( TestEnvironment.class ).setUp();
    }

    @SuppressWarnings( "unchecked" )
    @DataProvider
    public Iterator encodedFiles()
        throws URISyntaxException
    {
        final File dir = new File( getClass().getResource( "./test-files/" ).toURI() );
        final Collection<File> listFiles = FileUtils.listFiles( dir, new String[]
        { "txt" }, false );
        final List<Object[]> list = new ArrayList<Object[]>();
        for ( final File file : listFiles )
        {
            list.add( new Object[]
            { file, FilenameUtils.getBaseName( file.getName() ) } );
        }
        return list.iterator();
    }

    @Test( dataProvider = "encodedFiles" )
    public void guessFileEncoding(
        final File file,
        final String expectedEncoding )
    {
        final String encoding = FileUtil.guessFileEncoding( file );
        assertEquals( Charset.forName( encoding ), Charset.forName( expectedEncoding ) );
    }

    @AfterClass
    public void tearDown()
    {
        instance( TestEnvironment.class ).tearDown();
    }
}
