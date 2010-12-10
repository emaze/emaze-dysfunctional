package net.emaze.dysfunctional.strings;

import java.util.Arrays;
import java.util.Iterator;
import junit.framework.Assert;
import net.emaze.dysfunctional.iterations.ConstantIterator;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class StringsTest {

    @Test
    public void canInterposeIntegers() {
        Iterator<Integer> values = Arrays.asList(1, 2, 3, 4, 5).iterator();
        String output = Strings.interpose(values, 0);
        Assert.assertEquals("102030405", output);
    }
    @Test
    public void canInterposeWithMultipleSeparators() {
        Iterator<Integer> values = Arrays.asList(1, 2, 3, 4, 5).iterator();
        Iterator<String> separators = Arrays.asList("-", "+", "-", "+").iterator();
        String output = Strings.interpose(values, separators);
        Assert.assertEquals("1-2+3-4+5", output);
    }

    @Test
    public void canJoinIntegers() {
        Iterator<Integer> values = Arrays.asList(1, 2, 3, 4).iterator();
        String output = Strings.join(values);
        Assert.assertEquals("1234", output);
    }

    @Test
    public void canJoinEmptyIterator() {
        Iterator<Object> values = Arrays.asList().iterator();
        String output = Strings.join(values);
        Assert.assertEquals("", output);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void interposingNullValuesYieldsException() {
        Strings.interpose(null, 0);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void interposingNullValuesYieldsExceptionOverload() {
        Strings.interpose(null, new ConstantIterator<Integer>(0));
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void interposingNullSeparatorYieldsException() {
        Strings.interpose(Arrays.<String>asList().iterator(), null);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void interposingNullSeparatorsYieldsException() {
        Strings.interpose(Arrays.<String>asList().iterator(), (Iterator<String>)null);
    }
}
