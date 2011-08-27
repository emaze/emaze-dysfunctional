package net.emaze.dysfunctional.dispatching.actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        final List<BinaryAction<String, String>> actions = Arrays.<BinaryAction<String, String>>asList(new BucketFillingBinaryAction<String>(bucket, "former"), new BucketFillingBinaryAction<String>(bucket, "latter"));
        final PipelinedBinaryAction<String, String> pipeline = new PipelinedBinaryAction<String, String>(actions);
        pipeline.perform("ignored_value", "ignored_value");
        Assert.assertEquals(Arrays.asList("former", "latter"), bucket);
    }

    public static class BucketFillingBinaryAction<T> implements BinaryAction<T, T> {

        private final List<T> bucket;
        private final T elementToAdd;

        public BucketFillingBinaryAction(List<T> bucket, T elementToAdd) {
            this.bucket = bucket;
            this.elementToAdd = elementToAdd;
        }

        @Override
        public void perform(T former, T latter) {
            bucket.add(elementToAdd);
        }
    }
}
