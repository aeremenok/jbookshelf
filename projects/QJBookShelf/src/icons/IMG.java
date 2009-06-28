package icons;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class IMG
{
    public static final String CONFIGURE_PNG                  = "configure.png";
    public static final String DOCUMENT_REVERT_PNG            = "document-revert.png";
    public static final String LIST_REMOVE_PNG                = "list-remove.png";
    public static final String DOCUMENT_OPEN_FOLDER_PNG       = "document-open-folder.png";
    public static final String DOCUMENT_PREVIEW_PNG           = "document-preview.png";
    public static final String LIST_ADD_PNG                   = "list-add.png";
    public static final String HELP_ABOUT_PNG                 = "help-about.png";
    public static final String DOCUMENT_SAVE_PNG              = "document-save.png";
    public static final String DOCUMENT_IMPORT_PNG            = "document-import.png";
    public static final String GOOGLE_PNG                     = "google.png";
    public static final String DOCUMENT_PROPERTIES_PNG        = "document-properties.png";
    public static final String EDIT_CLEAR_LOCATIONBAR_RTL_PNG = "locationbar-erase.png";
    public static final String EDIT_FIND_PNG                  = "edit-find.png";
    public static final String FEED_SUBSCRIBE_PNG             = "feed-subscribe.png";
    public static final String USER_IDENITY_PNG               = "user-identity.png";
    public static final String BOOK_PNG                       = "x-office-address-book.png";
    public static final String SPLASH_PNG                     = "splash.png";
    public static final String LOGO_PNG                       = "logo.png";

    public static final String PREVIOUS_PNG                   = "go-previous-view.png";
    public static final String NEXT_PNG                       = "go-next-view.png";
    public static final String LAST_PNG                       = "go-last-view.png";
    public static final String FIRST_PNG                      = "go-first-view.png";

    public static Icon icon(
        final String fileName )
    {
        return icon( fileName, 16 );
    }

    public static ImageIcon icon(
        final String fileName,
        final int size )
    {
        return new ImageIcon( img( fileName, size ) );
    }

    public static Image img(
        final String fileName,
        final int size )
    {
        final URL url = IMG.class.getResource( size + "/" + fileName );
        return Toolkit.getDefaultToolkit().getImage( url );
    }
}
