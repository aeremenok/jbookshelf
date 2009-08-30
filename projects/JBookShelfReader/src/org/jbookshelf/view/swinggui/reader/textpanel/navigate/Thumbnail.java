/**
 * 
 */
package org.jbookshelf.view.swinggui.reader.textpanel.navigate;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public abstract class Thumbnail
    extends JPanel
{
    public Thumbnail()
    {
        super( new BorderLayout() );
    }

    public abstract void setPageNumber(
        int pageNumber );
}