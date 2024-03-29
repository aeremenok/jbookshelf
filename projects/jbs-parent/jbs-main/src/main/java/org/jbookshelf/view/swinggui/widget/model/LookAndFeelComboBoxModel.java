package org.jbookshelf.view.swinggui.widget.model;

import javax.swing.DefaultComboBoxModel;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 * contains the installed {@link LookAndFeel}s
 * 
 * @author eav 2009
 */
public class LookAndFeelComboBoxModel
    extends DefaultComboBoxModel
// todo make immutable
{
    private static final LookAndFeelInfo[] lafs = UIManager.getInstalledLookAndFeels();

    /**
     * creates {@link LookAndFeel} from name
     * 
     * @param name {@link LookAndFeel} name
     * @return the lookandfeel
     */
    public static LookAndFeel fromName(
        final String name )
    {
        try
        {
            final String className = lafs[indexOf( name )].getClassName();
            final Class<?> clazz = Class.forName( className );
            return (LookAndFeel) clazz.newInstance();
        }
        catch ( final Exception e )
        {
            throw new Error( e );
        }
    }

    public static String pickFirstLAFName()
    {
        return lafs[0].getName();
    }

    private static int indexOf(
        final Object anItem )
    {
        for ( int i = 0; i < lafs.length; i++ )
        {
            if ( lafs[i].getName().equals( anItem ) )
            {
                return i;
            }
        }
        return 0;
    }

    @Override
    public Object getElementAt(
        final int index )
    {
        return lafs[index].getName();
    }

    @Override
    public int getSize()
    {
        return lafs.length;
    }
}
