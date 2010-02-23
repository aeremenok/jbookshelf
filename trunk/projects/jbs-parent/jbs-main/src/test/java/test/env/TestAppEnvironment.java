/**
 * 
 */
package test.env;

import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.controller.util.JBSSystem;
import org.jbookshelf.view.swinggui.main.Application;

/**
 * @author eav 2010
 */
public class TestAppEnvironment
    extends TestDBEnvironment
{
    @Override
    public void setUp()
    {
        Single.setImplementation( JBSSystem.class, FakeJBSSystem.class );
        Application.INSTANCE.start();
    }

    @Override
    public void tearDown()
    {
        Application.INSTANCE.stop();
    }
}
