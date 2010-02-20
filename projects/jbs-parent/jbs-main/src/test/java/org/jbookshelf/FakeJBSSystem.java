/**
 * 
 */
package org.jbookshelf;

import java.io.File;
import java.io.IOException;

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
        final String tempDir = tempDir();
        fakeUserHome = new File( tempDir + "/jbs-test" );
        cleanUserHome();
    }

    public void cleanUserHome()
    {
        try
        {
            FileUtils.deleteDirectory( fakeUserHome );
            fakeUserHome.mkdir();
        }
        catch ( final IOException e )
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