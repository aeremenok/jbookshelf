package org.jbookshelf.qtgui.reader;

import org.jbookshelf.model.Citation;
import org.jbookshelf.model.Comment;
import org.jbookshelf.qtgui.widgets.ext.QGroupBoxExt;

import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QTextBrowser;

/**
 * displays comments and citations
 * 
 * @author eav
 */
public class CitationPanel
    extends QGroupBoxExt
{
    private class CitationWidget
        extends QTextBrowser
    {
        public CitationWidget(
            final String text )
        {
            super();
            setText( text );
            setReadOnly( true );
        }
    }

    private final ReaderWindow readerWindow;

    public CitationPanel(
        final ReaderWindow readerWindow )
    {
        this.readerWindow = readerWindow;
        setTitle( tr( "Citations" ) );
        setLayout( new QGridLayout() );
        setVisible( false );
    }

    public void viewComments()
    {
        setVisible( !isVisible() );
        if ( isVisible() )
        { // refresh comments
            final QGridLayout layout = (QGridLayout) layout();
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
                    layout.addWidget( new CitationWidget( cit.getCitation() ) );
                }
                else
                {
                    layout.addWidget( new CitationWidget( comment.getContent() ) );
                }
            }
        }
    }

}
