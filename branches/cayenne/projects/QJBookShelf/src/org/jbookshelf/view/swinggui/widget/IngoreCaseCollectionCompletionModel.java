/**
 * 
 */
package org.jbookshelf.view.swinggui.widget;

import java.util.Collection;

import javax.swing.DefaultComboBoxModel;

import org.apache.log4j.Logger;
import org.xnap.commons.gui.completion.CompletionModel;

/**
 * builds completions from the {@link Collection} of any objects by ignorecase comparing {@link Object#toString()}
 * 
 * @author eav 2009
 */
public class IngoreCaseCollectionCompletionModel
    extends DefaultComboBoxModel
    implements
    CompletionModel
{
    @SuppressWarnings( "unused" )
    private static final Logger log = Logger.getLogger( IngoreCaseCollectionCompletionModel.class );
    private final Collection    collection;

    public IngoreCaseCollectionCompletionModel(
        final Collection collection )
    {
        super();
        this.collection = collection;
    }

    @Override
    public boolean complete(
        final String prefix )
    {
        removeAllElements();
        for ( final Object object : collection )
        {
            if ( object.toString().toLowerCase().contains( prefix.toLowerCase() ) )
            {
                addElement( object );
            }
        }
        return getSize() > 0;
    }

    @Override
    public String completeUniquePrefix(
        final String prefix )
    {
        return prefix;
    }
}
