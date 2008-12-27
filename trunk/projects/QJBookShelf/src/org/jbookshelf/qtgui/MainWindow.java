package org.jbookshelf.qtgui;

import com.trolltech.qt.gui.QAction;
import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QMainWindow;
import com.trolltech.qt.gui.QMenu;
import com.trolltech.qt.gui.QMessageBox;
import com.trolltech.qt.gui.QWidget;

public class MainWindow
    extends QMainWindow
{
    private QMenu   fileMenu;
    private QMenu   helpMenu;

    private QAction exitAct;
    private QAction aboutAct;
    private QAction aboutQtJambiAct;

    public static void main(
        String[] args )
    {
        QApplication.initialize( args );

        MainWindow testMainWindow = new MainWindow( null );
        testMainWindow.show();

        QApplication.exec();
    }

    public MainWindow(
        QWidget parent )
    {
        super( parent );
        createActions();
        createMenus();
    }

    private void createActions()
    {
        exitAct = new QAction( tr( "E&xit" ), this );
        exitAct.setShortcut( tr( "Ctrl+Q" ) );
        exitAct.setStatusTip( tr( "Exit the application" ) );
        exitAct.triggered.connect( this, "close()" );

        aboutAct = new QAction( tr( "&About" ), this );
        aboutAct.setStatusTip( tr( "Show the application's About box" ) );
        aboutAct.triggered.connect( this, "about()" );

        aboutQtJambiAct = new QAction( tr( "About &Qt Jambi" ), this );
        aboutQtJambiAct.setStatusTip( tr( "Show the Qt Jambi's About box" ) );
        aboutQtJambiAct.triggered.connect( QApplication.instance(), "aboutQtJambi()" );
    }

    private void createMenus()
    {
        fileMenu = menuBar().addMenu( tr( "&File" ) );
        fileMenu.addAction( exitAct );

        helpMenu = menuBar().addMenu( tr( "&Help" ) );
        helpMenu.addAction( aboutAct );
        helpMenu.addAction( aboutQtJambiAct );
    }

    protected void about()
    {
        QMessageBox.information( this, "Info", "It's your turn now :-)" );
    }
}
