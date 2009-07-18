/**
 * 
 */
package org.jbookshelf.controller.featuring;

import java.util.List;

/**
 * @author eav 2009
 */
public abstract class FeatureProcessor
{
    public static FeatureManager process(
        final Object featureContainer,
        final List<String> featureNames )
    {
        return new FeatureManager( featureContainer, featureNames );
    }

    public static FeatureManager process(
        final Object featureContainer,
        final String... featureNames )
    {
        return new FeatureManager( featureContainer, featureNames );
    }
}
