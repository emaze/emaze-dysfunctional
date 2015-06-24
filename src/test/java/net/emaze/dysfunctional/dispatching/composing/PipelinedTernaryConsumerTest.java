package net.emaze.dysfunctional.dispatching.composing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.emaze.dysfunctional.dispatching.composing.PipelinedTernaryConsumerTest.BucketFillingTernaryAction;
import net.emaze.dysfunctional.dispatching.actions.TriConsumer;
import org.junit.Test;
import org.junit.Assert;

/**
 *
 * @author rferranti
 */
public class PipelinedTernaryConsumerTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullIterableYieldsException() {
        new PipelinedTernaryConsumer<String, String, String>(null);
    }

    @Test
    public void actionsAreCalledInOrder() {
        final List<String> bucket = new ArrayList<String>();
        final List<TriConsumer<String, String, String>> actions = Arrays.<TriConsumer<String, String, String>>asList(new BucketFillingTernaryAction<String>(bucket, "former"), new BucketFillingTernaryAction<String>(bucket, "latter"));
        final TriConsumer<String, String, String> pipeline = new PipelinedTernaryConsumer<String, String, String>(actions);
        pipeline.accept("ignored_value", "ignored_value", "ignored_value");
        Assert.assertEquals(Arrays.asList("former", "latter"), bucket);
    }

    public static class BucketFillingTernaryAction<T> implements TriConsumer<T, T, T> {

        private final List<T> bucket;
        private final T elementToAdd;

        public BucketFillingTernaryAction(List<T> bucket, T elementToAdd) {
            this.bucket = bucket;
            this.elementToAdd = elementToAdd;
        }

        @Override
        public void accept(T first, T second, T third) {
            bucket.add(elementToAdd);
        }
    }
}
