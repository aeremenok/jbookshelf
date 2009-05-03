package images;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class IMG
{
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
