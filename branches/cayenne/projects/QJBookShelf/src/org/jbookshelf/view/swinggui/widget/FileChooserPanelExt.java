package org.jbookshelf.view.swinggui.widget;

import icons.IMG;

import java.awt.Dimension;
import java.io.File;

import javax.swing.Action;
import javax.swing.JFileChooser;

import org.jbookshelf.controller.settings.Settings;
import org.jbookshelf.controller.singleton.Single;
import org.xnap.commons.gui.FileChooserPanel;

/**
 * a {@link FileChooserPanel} that stores in {@link Settings} the last opened file
 * 
 * @author eav 2009
 */
public class FileChooserPanelExt
    extends FileChooserPanel
{
    private String key;

    public FileChooserPanelExt(
        final File file,
        final int columns )
    {
        super( file, columns );
        getFileChooserAction().putValue( Action.SMALL_ICON, IMG.icon( IMG.DOCUMENT_OPEN_FOLDER_PNG ) );
    }

    public FileChooserPanelExt(
        final int columns,
        final String name )
    {
        this( null, columns );
        key = name;

        final String pathname = Single.instance( Settings.class ).get( getKey(), System.getProperty( "user.home" ) );
        final File file = new File( pathname );
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
        final JFileChooser fileChooser = getFileChooser();
        fileChooser.setPreferredSize( new Dimension( 800, 600 ) );
        fileChooser.setSelectedFile( getFile() );
        // todo make constants for different modes and choosers
        if ( fileChooser.showDialog( getParent(), fileChooser.getApproveButtonText() ) == JFileChooser.APPROVE_OPTION )
        {
            setFile( fileChooser.getSelectedFile() );
            return true;
        }
        return false;
    }
}
