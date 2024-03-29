/**
 * 
 */
package org.jbookshelf.view;

import static org.jbookshelf.controller.singleton.Single.instance;
import static org.testng.Assert.assertEquals;

import javax.swing.JTable;

import org.apache.log4j.Logger;
import org.fest.swing.annotation.GUITest;
import org.fest.swing.fixture.DialogFixture;
import org.fest.swing.fixture.JButtonFixture;
import org.fest.swing.fixture.JTabbedPaneFixture;
import org.fest.swing.fixture.JTableFixture;
import org.jbookshelf.view.swinggui.main.MainWindow;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import test.env.TestAppEnvironment;

/**
 * @author eav 2010
 */
@GUITest
public class BookAdditionWindowTest
{
    @SuppressWarnings( "unused" )
    private static final Logger log = Logger.getLogger( BookAdditionWindowTest.class );

    private JbsFrameFixture     window;

    @BeforeTest
    public void setUp()
    {
        instance( TestAppEnvironment.class ).setUp();
        window = new JbsFrameFixture( instance( MainWindow.class ) );
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

    @Test( dependsOnMethods = "showBooksTable" )
    public void addBook()
    {
        final JButtonFixture showAddDialogButton = window.button( new ButtonTextMatcher( "Add" ) );
        showAddDialogButton.click();

        final DialogFixture dialog = window.dialog();

        final String bookName = "book1";
        dialog.textBox( "bookTextField" ).setText( bookName );

        final JButtonFixture closeButton = dialog.button( new ButtonTextMatcher( "Add and close" ) );
        closeButton.click();

        final JTableFixture table = window.table( "bookTable" );
        final JTable targetTable = table.target;
        assertEquals( targetTable.getRowCount(), 1 );
        assertEquals( targetTable.getValueAt( 0, 0 ), bookName );
    }

    @AfterTest
    public void tearDown()
    {
        window.cleanUp();
        instance( TestAppEnvironment.class ).tearDown();
    }
}
