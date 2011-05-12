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
        new ToTitleCase().perform("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void titleCasingNullWordYieldsException() {
        new ToTitleCase().perform(null);
    }

    @Test
    public void canTitleCaseWordWithOneLetter() {
        Assert.assertEquals("A", new ToTitleCase().perform("a"));
    }

    @Test
    public void nonInitialCharactersAreLowerCase() {
        Assert.assertEquals("Ab", new ToTitleCase().perform("AB"));
    }
}
