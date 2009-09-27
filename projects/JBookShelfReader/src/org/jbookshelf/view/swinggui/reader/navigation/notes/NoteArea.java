/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.navigation.notes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

import org.jbookshelf.model.db.Note;
import org.jdesktop.swingx.border.DropShadowBorder;

public class NoteArea
    extends JPanel
    implements
    TableCellRenderer
{
    private static final int MAX_LEN  = 200;

    private final JTextArea  textArea = new JTextArea( 3, 20 );
    private final JLabel     label    = new JLabel();
    private Note             note;

    public NoteArea()
    {
        super( new BorderLayout() );
        setBorder( new DropShadowBorder() );

        add( textArea, BorderLayout.CENTER );
        add( label, BorderLayout.NORTH );

        textArea.setEditable( false );
        textArea.setFont( textArea.getFont().deriveFont( Font.ITALIC, 10f ) );
        textArea.setBackground( Color.ORANGE );
        textArea.setLineWrap( true );
        textArea.setWrapStyleWord( true );
    }

    @Override
    public Component getTableCellRendererComponent(
        final JTable table,
        final Object value,
        final boolean isSelected,
        final boolean hasFocus,
        final int row,
        final int column )
    {
        this.note = (Note) value;

        final String content = note.getContent().trim();
        textArea.setText( content.length() > MAX_LEN
            ? content.substring( 0, MAX_LEN ) + "..." : content );

        final String title = note.getTitle().trim();
        label.setText( title.length() > MAX_LEN
            ? title.substring( 0, MAX_LEN ) + "..." : title );

        return this;
    }
}