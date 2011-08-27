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
public class PipelinedActionTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullIterableYieldsException() {
        new PipelinedAction<String>(null);
    }

    @Test
    public void actionsAreCalledInOrder() {
        final List<String> bucket = new ArrayList<String>();
        final List<Action<String>> actions = Arrays.<Action<String>>asList(new BucketFillingAction<String>(bucket, "former"), new BucketFillingAction<String>(bucket, "latter"));
        final PipelinedAction<String> pipeline = new PipelinedAction<String>(actions);
        pipeline.perform("ignored_value");
        Assert.assertEquals(Arrays.asList("former", "latter"), bucket);
    }

    public static class BucketFillingAction<T> implements Action<T> {

        private final List<T> bucket;
        private final T elementToAdd;

        public BucketFillingAction(List<T> bucket, T elementToAdd) {
            this.bucket = bucket;
            this.elementToAdd = elementToAdd;
        }

        @Override
        public void perform(T element) {
            bucket.add(elementToAdd);
        }
    }
}
