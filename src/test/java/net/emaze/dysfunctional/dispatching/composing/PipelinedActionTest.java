package net.emaze.dysfunctional.dispatching.composing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class PipelinedActionTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullIterableYieldsException() {
        new PipelinedAction<String>(null);
    }

    @Test
    public void actionsAreCalledInOrder() {
        final List<String> bucket = new ArrayList<String>();
        final List<Consumer<String>> actions = Arrays.<Consumer<String>>asList(new BucketFillingAction<String>(bucket, "former"), new BucketFillingAction<String>(bucket, "latter"));
        final PipelinedAction<String> pipeline = new PipelinedAction<String>(actions);
        pipeline.accept("ignored_value");
        Assert.assertEquals(Arrays.asList("former", "latter"), bucket);
    }

    public static class BucketFillingAction<T> implements Consumer<T> {

        private final List<T> bucket;
        private final T elementToAdd;

        public BucketFillingAction(List<T> bucket, T elementToAdd) {
            this.bucket = bucket;
            this.elementToAdd = elementToAdd;
        }

        @Override
        public void accept(T element) {
            bucket.add(elementToAdd);
        }
    }
}
