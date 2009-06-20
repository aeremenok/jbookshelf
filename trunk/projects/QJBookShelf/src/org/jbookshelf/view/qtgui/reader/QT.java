/**
 * 
 */
package org.jbookshelf.view.qtgui.reader;

import org.jbookshelf.view.logic.JBookShelfConstants;
import org.jbookshelf.view.swinggui.main.MainWindow;

import com.trolltech.qt.core.QCoreApplication;
import com.trolltech.qt.gui.QApplication;

/**
 * @author eav 2009
 */
public class QT
    implements
    JBookShelfConstants
{
    public void invoke(
        final Runnable runnable )
    {
        // todo allow opening multiple instances
        new Thread( new Runnable()
        {
            @Override
            public void run()
            {
                QApplication.initialize( MainWindow.args );
                QCoreApplication.setApplicationVersion( MainWindow.VERSION );
                QCoreApplication.setApplicationName( MainWindow.APP_NAME );
                runnable.run();
                QApplication.exec();
            }
        } ).start();
        // todo fire events
    }
}
