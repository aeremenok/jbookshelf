package org.jbookshelf.view.swinggui.widgets;

import images.IMG;

import java.io.File;

import javax.swing.Action;
import javax.swing.JFileChooser;

import org.jbookshelf.controller.settings.Settings;
import org.jbookshelf.controller.singleton.Single;
import org.xnap.commons.gui.FileChooserPanel;

public class FileChooserPanelExt
    extends FileChooserPanel
{
    private String key;

    public FileChooserPanelExt(
        final File file,
        final int columns )
    {
        super( file, columns );
        getFileChooserAction().putValue( Action.SMALL_ICON, IMG.icon( IMG.DOCUMENT_OPEN_FOLDER_16_PNG ) );
    }

    public FileChooserPanelExt(
        final int columns,
        final String name )
    {
        this( null, columns );
        key = name;
        final Settings settings = Single.instance( Settings.class );
        final File file = new File( settings.get( getKey(), "" ) );
        if ( file.exists() )
        {
            setFile( file );
        }
    }

    private String getKey()
    {
        if ( key == null )
        {
            // fixme such a key can be non-unique
            return (getParent() != null
                ? getParent().getClass().getName() + "/" : "") + getClass().getName();
        }
        return key;
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
