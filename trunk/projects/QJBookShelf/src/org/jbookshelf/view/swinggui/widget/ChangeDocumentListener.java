/**
 * 
 */
package org.jbookshelf.view.swinggui.widget;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

public abstract class ChangeDocumentListener
    implements
    DocumentListener
{
    private final JTextComponent textField;

    public ChangeDocumentListener(
        final JTextComponent textField )
    {
        this.textField = textField;
    }

    public void changedUpdate(
        final DocumentEvent e )
    {
        onChange( textField.getText() );
    }

    public void insertUpdate(
        final DocumentEvent e )
    {
        onChange( textField.getText() );
    }

    public abstract void onChange(
        String newText );

    public void removeUpdate(
        final DocumentEvent e )
    {
        onChange( textField.getText() );
    }
}