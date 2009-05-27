package org.jbookshelf.view.swinggui.widgets;

import java.util.Collection;

import javax.swing.JTextField;
import javax.swing.text.Document;

public class MultipleField
    // todo extend JPnael with JTextFields
    extends JTextField
{

    public MultipleField()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    public MultipleField(
        final Document doc,
        final String text,
        final int columns )
    {
        super( doc, text, columns );
        // TODO Auto-generated constructor stub
    }

    public MultipleField(
        final int columns )
    {
        super( columns );
        // TODO Auto-generated constructor stub
    }

    public MultipleField(
        final String text )
    {
        super( text );
        // TODO Auto-generated constructor stub
    }

    public MultipleField(
        final String text,
        final int columns )
    {
        super( text, columns );
        // TODO Auto-generated constructor stub
    }

    public void clear()
    {
        setText( "" );
    }

    public Collection getValue()
    {
        // todo
        return null;
    }

    public void setValue(
        final Collection objects )
    {
        // todo
        setText( objects.size() + "" );
    }

}
