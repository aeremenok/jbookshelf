/**
 * 
 */
package org.jbookshelf.view.swinggui.reader;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public abstract class ChangeDocumentListener
    implements
    DocumentListener
{
    private final JTextField textField;

    public ChangeDocumentListener(
        final JTextField textField )
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