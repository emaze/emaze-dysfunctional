package net.emaze.dysfunctional.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class StringIteratorTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingStringIterFromNullStringYieldsException() {
        new StringIterator(null);
    }

    @Test
    public void emptyStringHasNoNext() {
        Assert.assertFalse(new StringIterator("").hasNext());
    }
    
    @Test
    public void nonEmptyStringHasNext() {
        Assert.assertTrue(new StringIterator("a").hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratingOverBoundYieldsException() {
        new StringIterator("").next();
    }
    
    @Test
    public void iteratingYieldsCharactersInOrder() {
        StringIterator iter = new StringIterator("12");
        List<Character> got = new ArrayList<Character>();
        got.add(iter.next());
        got.add(iter.next());
        Assert.assertEquals(Arrays.asList('1','2'), got);
    }
}
