package net.emaze.dysfunctional.strings;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class WordsToPascalCaseTest {
    
    @Test(expected = IllegalArgumentException.class)
    public void transformingNullArrayOfWordsYieldsException() {
        new WordsToPascalCase().perform(null);
    }

    @Test
    public void validArrayOfWordsYieldsPascalString() {
        final String got = new WordsToPascalCase().perform(new String[]{"SomE", "wOrd"});
        Assert.assertEquals("SomeWord", got);
    }

    @Test
    public void validArrayWithSingleWordsYieldsTitleCasedWord() {
        final String got = new WordsToPascalCase().perform(new String[]{"SomE"});
        Assert.assertEquals("Some", got);
    }
}
