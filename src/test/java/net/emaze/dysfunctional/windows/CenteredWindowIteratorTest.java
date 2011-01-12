package net.emaze.dysfunctional.windows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import net.emaze.dysfunctional.consumers.Consumers;
import net.emaze.dysfunctional.options.Maybe;
import org.junit.Test;
import org.junit.Assert;

/**
 *
 * @author rferranti
 */
public class CenteredWindowIteratorTest {

    @Test
    public void windowsAreInOrder() {
        Iterator<Integer> iter = Arrays.<Integer>asList(1, 2, 3).iterator();
        CenteredWindowIterator<Integer> win = new CenteredWindowIterator<Integer>(iter, 3);
        List<Queue<Maybe<Integer>>> got = Consumers.all(win);
        List<List<Maybe<Integer>>> expected = new ArrayList<List<Maybe<Integer>>>();
        expected.add(Arrays.asList(Maybe.<Integer>nothing(), Maybe.just(1), Maybe.just(2)));
        expected.add(Arrays.asList(Maybe.just(1), Maybe.just(2), Maybe.just(3)));
        expected.add(Arrays.asList(Maybe.just(2), Maybe.just(3), Maybe.<Integer>nothing()));
        Assert.assertEquals(expected, got);
    }

    @Test
    public void singletonIteratorYieldOneElementInWindow(){
        Iterator<Integer> iter = Arrays.<Integer>asList(1).iterator();
        CenteredWindowIterator<Integer> win = new CenteredWindowIterator<Integer>(iter, 3);
        List<Queue<Maybe<Integer>>> got = Consumers.all(win);
        List<List<Maybe<Integer>>> expected = new ArrayList<List<Maybe<Integer>>>();
        expected.add(Arrays.asList(Maybe.<Integer>nothing(), Maybe.just(1), Maybe.<Integer>nothing()));
        Assert.assertEquals(expected, got);

    }
}
