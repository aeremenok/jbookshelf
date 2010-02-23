/**
 * 
 */
package org.jbookshelf.controller.settings;

import static org.jbookshelf.controller.singleton.Single.destroy;
import static org.jbookshelf.controller.singleton.Single.instance;
import static test.env.JBSAssert.assertFileEquals;
import static test.env.JBSAssert.assertFileNotSame;

import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.controller.util.JBSSystem;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import test.env.FakeJBSSystem;
import test.env.TestEnvironment;

/**
 * @author eav 2010
 */
public class SettingsTest
{
    @BeforeClass
    public void setUp()
    {
        instance( TestEnvironment.class ).setUp();
        Single.setImplementation( JBSSystem.class, FakeJBSSystem.class );
    }

    @Test
    public void firstLaunch()
    {
        final Settings settings = instance( Settings.class );
        final JBSSystem system = instance( JBSSystem.class );

        assertFileEquals( settings.JBS_DIR.getValue(), system.userHome() + "/.jbookshelf" );
        assertFileEquals( settings.WORKSPACE_DIR.getValue(), system.myDocs() );
    }

    @Test( dependsOnMethods = "firstLaunch" )
    public void modifySettings()
    {
        final Settings settings = instance( Settings.class );
        final JBSSystem system = instance( JBSSystem.class );

        settings.WORKSPACE_DIR.setValue( system.tempDir() );
        settings.save();
    }

    @Test( dependsOnMethods = "modifySettings" )
    public void readAgain()
    {
        destroy( Settings.class );

        final JBSSystem system = instance( JBSSystem.class );
        final Settings settings = instance( Settings.class );

        final String wspDir = settings.WORKSPACE_DIR.getValue();

        assertFileNotSame( wspDir, system.myDocs() );
        assertFileEquals( wspDir, system.tempDir() );
    }

    @Test( dependsOnMethods = "readAgain" )
    public void hardReset()
    {
        destroy( Settings.class );
        final FakeJBSSystem system = (FakeJBSSystem) instance( JBSSystem.class );
        system.cleanUserHome();

        firstLaunch();
    }
}
