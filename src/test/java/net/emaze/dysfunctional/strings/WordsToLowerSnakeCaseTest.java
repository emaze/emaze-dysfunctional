package net.emaze.dysfunctional.strings;

import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class WordsToLowerSnakeCaseTest {

    @Test(expected = IllegalArgumentException.class)
    public void transformingNullArrayOfWordsYieldsException() {
        new WordsToLowerSnakeCase("-").perform(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingDelegateWithNullGlyphYieldsException() {
        new WordsToLowerSnakeCase(null);
    }

    @Test
    public void validArrayOfWordsYieldsSnakedString() {
        final String got = new WordsToLowerSnakeCase("-").perform(new String[]{"SomE", "wOrd"});
        Assert.assertEquals("some-word", got);
    }

    @Test
    public void validArrayWithSingleWordsYieldsLowerCasedWord() {
        final String got = new WordsToLowerSnakeCase("-").perform(new String[]{"SomE"});
        Assert.assertEquals("some", got);
    }
}
