/**
 * 
 */
package org.jbookshelf.view.swinggui.widget;

import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.ComboBoxModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.JTextComponent;

import org.xnap.commons.gui.settings.SettingComponentMediator;
import org.xnap.commons.settings.BooleanSetting;
import org.xnap.commons.settings.IntSetting;
import org.xnap.commons.settings.Setting;
import org.xnap.commons.settings.StringSetting;

/**
 * @author eav 2009
 */
public class ChangeDetectMediator
    extends SettingComponentMediator
{
    private final Map<Setting, Object> oldValues = new HashMap<Setting, Object>();

    @Override
    public void add(
        final BooleanSetting setting,
        final AbstractButton button )
    {
        oldValues.put( setting, setting.getValue() );
        super.add( setting, button );
    }

    @Override
    public void add(
        final IntSetting setting,
        final SpinnerNumberModel spinnerModel )
    {
        oldValues.put( setting, setting.getValue() );
        super.add( setting, spinnerModel );
    }

    @Override
    public <T> void add(
        final Setting<T> setting,
        final ComboBoxModel model )
    {
        oldValues.put( setting, setting.getValue() );
        super.add( setting, model );
    }

    @Override
    public void add(
        final StringSetting setting,
        final JTextComponent textComponent )
    {
        oldValues.put( setting, setting.getValue() );
        super.add( setting, textComponent );
    }

    @SuppressWarnings( "unchecked" )
    public <T> T getOldValue(
        final Setting<T> setting )
    {
        return (T) oldValues.get( setting );
    }

    public boolean isChanged(
        final Setting setting )
    {
        final Object oldValue = oldValues.get( setting );
        return !setting.getValue().equals( oldValue );
    }
}
