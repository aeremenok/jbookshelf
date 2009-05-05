package images;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class IMG
{
    public static final String CONFIGURE_PNG            = "configure.png";
    public static final String DOCUMENT_REVERT_PNG      = "document-revert.png";
    public static final String LIST_REMOVE_PNG          = "list-remove.png";
    public static final String DOCUMENT_OPEN_FOLDER_PNG = "document-open-folder.png";
    public static final String DOCUMENT_PREVIEW_PNG     = "document-preview.png";
    public static final String LIST_ADD_PNG             = "list-add.png";
    public static final String HELP_ABOUT_PNG           = "help-about.png";
    public static final String DOCUMENT_SAVE_PNG        = "document-save.png";
    public static final String DOCUMENT_IMPORT_PNG      = "document-import.png";
    public static final String GOOGLE_PNG               = "google.png";
    public static final String DOCUMENT_PROPERTIES_PNG  = "document-properties.png";

    public static Icon icon(
        final String fileName )
    {
        return new ImageIcon( img( fileName ) );
    }

    public static Image img(
        final String fileName )
    {
        final URL url = IMG.class.getResource( fileName );
        return Toolkit.getDefaultToolkit().getImage( url );
    }
}