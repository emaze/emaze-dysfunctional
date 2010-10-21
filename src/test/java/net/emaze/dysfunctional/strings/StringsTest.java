package net.emaze.dysfunctional.strings;

import java.util.Arrays;
import java.util.Iterator;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class StringsTest {
    
    @Test
    public void testThatCanInterposeIntegers(){
        Iterator<Integer> values = Arrays.asList(1,2,3,4,5).iterator();
        String output = Strings.interpose(values, 0);
        Assert.assertEquals("102030405", output);
    }
}