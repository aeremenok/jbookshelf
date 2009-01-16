package org.jbookshelf.qtgui.widgets;

import org.jbookshelf.qtgui.widgets.ext.QFileDialogExt;

import com.trolltech.qt.core.Qt.WindowFlags;
import com.trolltech.qt.core.Qt.WindowType;
import com.trolltech.qt.gui.QHBoxLayout;
import com.trolltech.qt.gui.QLineEdit;
import com.trolltech.qt.gui.QPushButton;
import com.trolltech.qt.gui.QWidget;
import com.trolltech.qt.gui.QFileDialog.FileMode;

public class FilePathEdit
    extends QWidget
{
    private final QLineEdit   edit    = new QLineEdit( this );
    private final QPushButton button  = new QPushButton( this );
    private FileMode          mode    = FileMode.AnyFile;
    private String            caption = "";

    public FilePathEdit()
    {
        super();
        initComponents();
    }

    public FilePathEdit(
        QPrivateConstructor p )
    {
        super( p );
        initComponents();
    }

    public FilePathEdit(
        QWidget parent )
    {
        super( parent );
        initComponents();
    }

    public FilePathEdit(
        QWidget parent,
        WindowFlags f )
    {
        super( parent, f );
        initComponents();
    }

    public FilePathEdit(
        QWidget parent,
        WindowType... f )
    {
        super( parent, f );
        initComponents();
    }

    public void setCaption(
        String caption )
    {
        this.caption = caption;
    }

    public void setFileMode(
        FileMode mode )
    {
        this.mode = mode;
    }

    public void setText(
        String text )
    {
        edit.setText( text );
    }

    public String text()
    {
        return edit.text();
    }

    @SuppressWarnings( "unused" )
    private void chooseFile()
    {
        QFileDialogExt fileDialog = new QFileDialogExt( this, caption )
        {
            @Override
            protected void filesSelected()
            {
                setText( selectedFiles().get( 0 ) );
            }
        };
        fileDialog.selectFile( text() );
        fileDialog.setFileMode( mode );
        fileDialog.show();
    }

    private void initComponents()
    {
        setLayout( new QHBoxLayout() );
        layout().addWidget( edit );
        layout().addWidget( button );

        button.setText( "..." );

        button.released.connect( this, "chooseFile()" );
    }
}
