/**
 * 
 */
package org.jbookshelf.controller.util;

import javax.swing.JFileChooser;

/**
 * @author eav 2010
 */
public class JBSSystem
{
    public String javaHome()
    {
        return System.getProperty( "java.home" );
    }

    public String tempDir()
    {
        return System.getProperty( "java.io.tmpdir" );
    }

    public String userHome()
    {
        return System.getProperty( "user.home" );
    }

    public void exit(
        final int status )
    {
        System.exit( status );
    }

    public String myDocs()
    {
        return new JFileChooser().getFileSystemView().getDefaultDirectory().getAbsolutePath();
    }
}
