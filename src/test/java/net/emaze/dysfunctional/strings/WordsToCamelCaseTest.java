package net.emaze.dysfunctional.strings;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class WordsToCamelCaseTest {
    
    private final WordsToCamelCase toCamel = new WordsToCamelCase();
    
    @Test
    public void camelForEmptyArrayIsEmptyString() {
        Assert.assertEquals("", toCamel.perform(new String[0]));
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void camelForNullArrayYieldsException() {
        toCamel.perform(null);
    }
    
    @Test
    public void canCamelCaseMultipleWords() {
        String[] source = {"Blah", "BlaH" };
        Assert.assertEquals("blahBlah", toCamel.perform(source));
    }
}
