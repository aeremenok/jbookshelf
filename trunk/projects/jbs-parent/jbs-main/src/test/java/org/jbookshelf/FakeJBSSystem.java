/**
 * 
 */
package org.jbookshelf;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.jbookshelf.controller.util.JBSSystem;

public class FakeJBSSystem
    extends JBSSystem
{
    private static final Logger log = Logger.getLogger( FakeJBSSystem.class );
    private final File          fakeUserHome;

    public FakeJBSSystem()
    {
        super();
        fakeUserHome = new File( tempDir() + "/jbs-test" );
        cleanUserHome();
    }

    public void cleanUserHome()
    {
        try
        {
            FileUtils.deleteDirectory( fakeUserHome );
            fakeUserHome.mkdir();
            log.debug( "cleansed " + fakeUserHome );
        }
        catch ( final Exception e )
        {
            log.error( e, e );
            throw new Error( e );
        }
    }

    @Override
    public String userHome()
    {
        return fakeUserHome.getAbsolutePath();
    }
}