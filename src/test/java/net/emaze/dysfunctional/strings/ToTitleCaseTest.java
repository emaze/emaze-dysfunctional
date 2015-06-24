package net.emaze.dysfunctional.strings;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ToTitleCaseTest {

    @Test(expected = IllegalArgumentException.class)
    public void titleCasingEmptyWordYieldsException() {
        new ToTitleCase().apply("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void titleCasingNullWordYieldsException() {
        new ToTitleCase().apply(null);
    }

    @Test
    public void canTitleCaseWordWithOneLetter() {
        Assert.assertEquals("A", new ToTitleCase().apply("a"));
    }

    @Test
    public void nonInitialCharactersAreLowerCase() {
        Assert.assertEquals("Ab", new ToTitleCase().apply("AB"));
    }
}
