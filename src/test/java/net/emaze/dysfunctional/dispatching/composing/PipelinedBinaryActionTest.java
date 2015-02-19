package net.emaze.dysfunctional.dispatching.composing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class PipelinedBinaryActionTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullIterableYieldsException() {
        new PipelinedBinaryAction<String, String>(null);
    }

    @Test
    public void actionsAreCalledInOrder() {
        final List<String> bucket = new ArrayList<String>();
        final List<BiConsumer<String, String>> actions = Arrays.<BiConsumer<String, String>>asList(new BucketFillingBinaryAction<String>(bucket, "former"), new BucketFillingBinaryAction<String>(bucket, "latter"));
        final PipelinedBinaryAction<String, String> pipeline = new PipelinedBinaryAction<String, String>(actions);
        pipeline.accept("ignored_value", "ignored_value");
        Assert.assertEquals(Arrays.asList("former", "latter"), bucket);
    }

    public static class BucketFillingBinaryAction<T> implements BiConsumer<T, T> {

        private final List<T> bucket;
        private final T elementToAdd;

        public BucketFillingBinaryAction(List<T> bucket, T elementToAdd) {
            this.bucket = bucket;
            this.elementToAdd = elementToAdd;
        }

        @Override
        public void accept(T former, T latter) {
            bucket.add(elementToAdd);
        }
    }
}
