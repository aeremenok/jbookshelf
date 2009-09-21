/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.textpanel;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class CardLayoutExt
    extends CardLayout
{
    private String                       displayedComponentName = "";
    private final Map<String, Component> components             = new HashMap<String, Component>();

    @SuppressWarnings( "deprecation" )
    @Override
    public void addLayoutComponent(
        final String name,
        final Component comp )
    {
        if ( !contains( comp ) )
        {
            super.addLayoutComponent( name, comp );
            components.put( name, comp );
        }
    }

    public boolean contains(
        final Component component )
    {
        return components.containsValue( component );
    }

    public Component getDisplayedComponent()
    {
        return components.get( displayedComponentName );
    }

    @Override
    public void removeLayoutComponent(
        final Component comp )
    {
        super.removeLayoutComponent( comp );
        for ( final Entry<String, Component> entry : components.entrySet() )
        {
            if ( entry.getValue().equals( comp ) )
            {
                components.remove( entry.getKey() );
                break;
            }
        }
    }

    @Override
    public void show(
        final Container parent,
        final String name )
    {
        if ( !name.equals( displayedComponentName ) )
        {
            super.show( parent, name );
            displayedComponentName = name;
        }
    }
}