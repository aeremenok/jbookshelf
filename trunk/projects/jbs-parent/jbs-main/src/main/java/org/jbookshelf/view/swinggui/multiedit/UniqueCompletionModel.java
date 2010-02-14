/**
 * 
 */
package org.jbookshelf.view.swinggui.multiedit;

import java.util.Collection;
import java.util.List;

import javax.annotation.Nonnull;
import javax.swing.DefaultComboBoxModel;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.jbookshelf.model.db.Named;
import org.jbookshelf.model.db.util.LogRunner;
import org.xnap.commons.gui.completion.CompletionModel;

/**
 * builds completions from the names of unique extracted from its table
 * 
 * @author eav 2009
 */
public class UniqueCompletionModel
    extends DefaultComboBoxModel
    implements
    CompletionModel
{
    private final Class<? extends Named>          clazz;

    private final ListTableModel<? extends Named> model;

    public UniqueCompletionModel(
        @Nonnull final Class<? extends Named> clazz,
        final ListTableModel<? extends Named> model )
    {
        super();
        this.clazz = clazz;
        this.model = model;
    }

    @Override
    public boolean complete(
        final String prefix )
    {
        if ( prefix.length() < 1 )
        {
            return false;
        }

        // build query 
        final StringBuilder q = new StringBuilder( "select name " );
        q.append( " from " ).append( clazz.getSimpleName() );
        q.append( " where upper(name) like '" ).append( prefix.toUpperCase() ).append( "%'" );

        final Collection<? extends Named> uniques = model.getValues();
        if ( uniques.size() > 0 )
        {
            q.append( " and upper(name) not in (" );
            for ( final Named unique : uniques )
            {
                q.append( "'" ).append( unique.getName().toUpperCase() ).append( "'," );
            }
            q.deleteCharAt( q.length() - 1 );
            q.append( ")" );
        }

        // run it
        final LogRunner runner = new LogRunner();
        final List<Object[]> items = runner.query( q.toString(), new ArrayListHandler() );

        // fill the completion popup 
        removeAllElements();
        for ( final Object[] object : items )
        {
            addElement( object[0] );
        }
        return getSize() > 0;
    }

    @Override
    public String completeUniquePrefix(
        final String prefix )
    {
        // todo find largest common prefix
        return prefix;
    }
}
