/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.emaze.dysfunctional.strings;

import java.util.Iterator;
import junit.framework.Assert;
import net.emaze.dysfunctional.Iterations;
import org.junit.Test;

public class ConcatStringsTest {

    @Test(expected = IllegalArgumentException.class)
    public void concatNullIteratorYieldsException() {
        new ConcatStrings().perform(null);
    }

    @Test
    public void canConcatMultipleStrings() {
        final Iterator<String> iterator = Iterations.iterator("1", "2");
        final String got = new ConcatStrings().perform(iterator);
        Assert.assertEquals("12", got);
    }
}
