package org.jbookshelf.qtgui.reader;

import org.jbookshelf.model.Citation;
import org.jbookshelf.model.Comment;

import com.trolltech.qt.gui.QLabel;
import com.trolltech.qt.gui.QVBoxLayout;
import com.trolltech.qt.gui.QWidget;

/**
 * displays comments and citations
 * 
 * @author eav
 */
public class CitationPanel
    extends QWidget
{
    private final ReaderWindow readerWindow;

    public CitationPanel(
        final ReaderWindow readerWindow )
    {
        this.readerWindow = readerWindow;

        setLayout( new QVBoxLayout() );
        setVisible( false );
    }

    public void viewComments()
    {
        setVisible( !isVisible() );
        if ( isVisible() )
        { // refresh comments
            final QVBoxLayout layout = (QVBoxLayout) layout();
            final int count = layout.count();
            for ( int i = 0; i < count; i++ )
            {
                layout.removeItem( layout.itemAt( 0 ) );
            }

            for ( final Comment comment : readerWindow.getBook().getComments() )
            {
                if ( comment instanceof Citation )
                {
                    final Citation cit = (Citation) comment;
                    layout.addWidget( new QLabel( cit.getCitation() + "" ) );
                }
                else
                {
                    layout.addWidget( new QLabel( comment.getContent() + "" ) );
                }
            }
        }
    }

}
