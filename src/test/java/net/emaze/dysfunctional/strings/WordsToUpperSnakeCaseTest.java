package net.emaze.dysfunctional.strings;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class WordsToUpperSnakeCaseTest {
    
    @Test(expected = IllegalArgumentException.class)
    public void transformingNullArrayOfWordsYieldsException() {
        new WordsToUpperSnakeCase("-").perform(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingDelegateWithNullGlyphYieldsException() {
        new WordsToUpperSnakeCase(null);
    }

    @Test
    public void validArrayOfWordsYieldsSnakedString() {
        final String got = new WordsToUpperSnakeCase("-").perform(new String[]{"SomE", "wOrd"});
        Assert.assertEquals("SOME-WORD", got);
    }

    @Test
    public void validArrayWithSingleWordsYieldsLowerCasedWord() {
        final String got = new WordsToUpperSnakeCase("-").perform(new String[]{"SomE"});
        Assert.assertEquals("SOME", got);
    }
}
