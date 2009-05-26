package org.jbookshelf.swinggui.widgets;

import images.IMG;

import java.io.File;

import javax.swing.Action;
import javax.swing.JFileChooser;

import org.jbookshelf.controller.singleton.Single;
import org.jbookshelf.settings.Settings;
import org.xnap.commons.gui.FileChooserPanel;

public class FileChooserPanelExt
    extends FileChooserPanel
{
    public FileChooserPanelExt(
        final File file,
        final int columns )
    {
        super( file, columns );
        getFileChooserAction().putValue( Action.SMALL_ICON, IMG.icon( IMG.DOCUMENT_OPEN_FOLDER_16_PNG ) );
    }

    public FileChooserPanelExt(
        final int columns )
    {
        this( null, columns );
    }

    private String getKey()
    {
        // fixme such a key can be not unique
        return getParent().getClass().getName() + "/" + getClass().getName();
    }

    @Override
    protected void fileSelected(
        final File file )
    {
        super.fileSelected( file );
        Single.instance( Settings.class ).put( getKey(), getTextField().getText() );
    }

    @Override
    protected boolean showChooser()
    {
        getFileChooser().setSelectedFile( getFile() );
        // todo make constants for different modes and choosers
        if ( getFileChooser().showDialog( getParent(), getFileChooser().getApproveButtonText() ) == JFileChooser.APPROVE_OPTION )
        {
            setFile( getFileChooser().getSelectedFile() );
            return true;
        }
        return false;
    }
}
