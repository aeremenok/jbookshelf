/**
 * 
 */
package test.view;

import static org.jbookshelf.controller.singleton.Single.instance;
import static org.testng.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.fest.swing.annotation.GUITest;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.DialogFixture;
import org.fest.swing.fixture.JButtonFixture;
import org.fest.swing.fixture.JTabbedPaneFixture;
import org.fest.swing.fixture.JTableFixture;
import org.fest.swing.fixture.JToolBarFixture;
import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.model.db.util.DBUtil;
import org.jbookshelf.view.swinggui.main.MainWindow;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import test.model.db.BasicTest;

/**
 * @author eav 2010
 */
@GUITest
public class BookAdditionWindowTest
    extends BasicTest
{
    @SuppressWarnings( "unused" )
    private static final Logger log = Logger.getLogger( BookAdditionWindowTest.class );

    JbsFrameFixture             window;

    @Test( dependsOnMethods =
    { "showBooksTable" } )
    public void addBook()
    {
        final JToolBarFixture toolBar = window.toolBar( "mainToolBar" );
        final JButtonFixture showAddDialogButton = toolBar.button( new ButtonTextMatcher( "Add" ) );
        showAddDialogButton.click();

        final DialogFixture dialog = window.dialog();
        // todo
        final JButtonFixture closeButton = dialog.button( new ButtonTextMatcher( "Close" ) );
        closeButton.click();

        assert !dialog.target.isVisible();
        dialog.cleanUp();
    }

    @Override
    @BeforeTest
    public void setUp()
    {
        super.setUp();
        Single.instance( DBUtil.class ).startup( testDbDir.getAbsolutePath() );
        final MainWindow mainWindow = GuiActionRunner.execute( new GuiQuery<MainWindow>()
        {
            @Override
            protected MainWindow executeInEDT()
            {
                return instance( MainWindow.class );
            }
        } );
        window = new JbsFrameFixture( mainWindow );
        window.show();
    }

    @Test
    public void showBooksTable()
    {
        final JTabbedPaneFixture tabbedPane = window.tabbedPane( "collectionViewTabbedPane" );
        assertEquals( tabbedPane.target.getSelectedIndex(), 0 );

        final JTableFixture table = window.table( "bookTable" );
        assertEquals( table.target.getRowCount(), 0 );
    }

    @Override
    @AfterTest
    public void tearDown()
    {
        window.cleanUp();
        super.tearDown();
    }
}
