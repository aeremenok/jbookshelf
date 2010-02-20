/**
 * 
 */
package org.jbookshelf;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jbookshelf.view.swinggui.main.MainWindow;

/**
 * @author eav 2010
 */
public class TestEnvironment
{
    private static final Logger log;
    static
    {
        PropertyConfigurator.configure( MainWindow.class.getResource( "log4j.properties" ) );
        log = Logger.getLogger( TestEnvironment.class );
    }

    public void setUp()
    {
        log.debug( "env set up" );
    }

    public void tearDown()
    {}
}
