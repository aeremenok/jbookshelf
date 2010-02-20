/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.widget;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * a {@link CardLayout} that remembers the displayed {@link Component}'s name and stores a {@link Map} of all
 * {@link Component}s and their names
 * 
 * @author eav 2009
 */
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
        if ( !containsComponent( comp ) )
        {
            super.addLayoutComponent( name, comp );
            components.put( name, comp );
        }
    }

    public boolean containsComponent(
        final Component component )
    {
        return components.containsValue( component );
    }

    public boolean containsName(
        final String name )
    {
        return components.containsKey( name );
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