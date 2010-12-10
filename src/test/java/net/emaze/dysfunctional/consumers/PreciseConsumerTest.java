package net.emaze.dysfunctional.consumers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import net.emaze.dysfunctional.delegates.Always;
import net.emaze.dysfunctional.delegates.Never;
import net.emaze.dysfunctional.delegates.Predicate;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class PreciseConsumerTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullPredicateYieldsException() {
        new PreciseConsumer<Object>(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void consumingNullIteratorYieldsException() {
        new PreciseConsumer<Object>(new Always<Object>()).consume(null);
    }

    @Test(expected = IllegalStateException.class)
    public void predicateNeverMatchingYieldsException() {
        new PreciseConsumer<Object>(new Never<Object>()).consume(Collections.emptyList().iterator());
    }

    @Test
    public void matchingPredicateYieldsFirstMatchingElement() {
        List<Integer> consumable = Arrays.asList(1, 2, 3);
        PreciseConsumer<Integer> consumer = new PreciseConsumer<Integer>(new Always<Integer>());
        Integer got = consumer.consume(consumable.iterator());
        Assert.assertEquals(consumable.get(0), got);
    }

    @Test
    public void nonMatchingElementsAreSkipped() {
        List<Integer> consumable = Arrays.asList(1, 2, 3);
        PreciseConsumer<Integer> consumer = new PreciseConsumer<Integer>(new Predicate<Integer>() {

            @Override
            public boolean test(Integer element) {
                return element == 2;
            }
        });
        Integer got = consumer.consume(consumable.iterator());
        Assert.assertEquals(Integer.valueOf(2), got);
    }
}
