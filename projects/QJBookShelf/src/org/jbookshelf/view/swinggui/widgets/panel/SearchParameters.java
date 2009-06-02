/**
 * 
 */
package org.jbookshelf.view.swinggui.widgets.panel;

/**
 * @author eav
 */
public class SearchParameters
{
    private final Boolean isRead;
    private final boolean searchContent;
    private final String  text;

    /**
     * @param isRead
     * @param searchContent
     * @param text
     */
    public SearchParameters(
        final Boolean isRead,
        final boolean searchContent,
        final String text )
    {
        super();
        this.isRead = isRead;
        this.searchContent = searchContent;
        this.text = text;
    }

    /**
     * @return the isRead
     */
    public Boolean isRead()
    {
        return this.isRead;
    }

    /**
     * @return the text
     */
    public String getText()
    {
        return this.text;
    }

    /**
     * @return the searchContent
     */
    public boolean isSearchContent()
    {
        return this.searchContent;
    }
}
