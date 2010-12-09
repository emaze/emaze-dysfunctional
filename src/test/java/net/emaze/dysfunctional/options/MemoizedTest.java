package net.emaze.dysfunctional.options;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class MemoizedTest {

    @Test
    public void invalidatingRemoveMemoizedValue(){
        Memoized<Integer> memo = new Memoized<Integer>();
        memo.memoize(1);
        memo.invalidate();
        Assert.assertFalse(memo.isMemoized());
    }
    
    @Test(expected=IllegalStateException.class)
    public void cannotFetchValueWhenNonMemoized(){
        new Memoized<Integer>().value();
    }

    @Test
    public void canFetchValueWhenMemoized(){
        Memoized<Integer> memo = new Memoized<Integer>();
        memo.memoize(1);
        Assert.assertEquals(1, (int)memo.value());
    }
}