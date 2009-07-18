/**
 * 
 */
package org.jbookshelf.controller.featuring;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author eav 2009
 */
@Documented
@Retention( RetentionPolicy.RUNTIME )
@Target(
{ ElementType.FIELD, ElementType.TYPE } )
public @interface Feature
{
    String[] value();
}
