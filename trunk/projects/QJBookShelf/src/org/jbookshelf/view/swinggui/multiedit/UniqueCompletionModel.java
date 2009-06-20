/**
 * 
 */
package org.jbookshelf.view.swinggui.multiedit;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.annotation.Nonnull;
import javax.swing.DefaultComboBoxModel;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.log4j.Logger;
import org.jbookshelf.model.db.LogRunner;
import org.jbookshelf.model.db.Unique;
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
    private static final Logger                    log = Logger.getLogger( UniqueCompletionModel.class );

    private final Class<? extends Unique>          clazz;

    private final ListTableModel<? extends Unique> model;

    public UniqueCompletionModel(
        @Nonnull final Class<? extends Unique> clazz,
        final ListTableModel<? extends Unique> model )
    {
        super();
        this.clazz = clazz;
        this.model = model;
    }

    @SuppressWarnings( "unchecked" )
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

        final Collection<? extends Unique> uniques = model.getValues();
        if ( uniques.size() > 0 )
        {
            q.append( " and upper(name) not in (" );
            for ( final Unique unique : uniques )
            {
                q.append( "'" ).append( unique.getName().toUpperCase() ).append( "'," );
            }
            q.deleteCharAt( q.length() - 1 );
            q.append( ")" );
        }

        // run it
        final LogRunner runner = new LogRunner();
        try
        {
            final ArrayListHandler handler = new ArrayListHandler();
            final List<Object[]> items = (List<Object[]>) runner.query( q.toString(), handler );

            // fill the completion popup 
            removeAllElements();
            for ( final Object[] object : items )
            {
                addElement( object[0] );
            }
            return items.size() > 0;
        }
        catch ( final SQLException e )
        {
            log.error( e, e );
            return false;
        }
    }

    @Override
    public String completeUniquePrefix(
        final String prefix )
    {
        // todo find largest common prefix
        return prefix;
    }
}
