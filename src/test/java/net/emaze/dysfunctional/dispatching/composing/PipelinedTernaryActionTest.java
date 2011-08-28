package net.emaze.dysfunctional.dispatching.composing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.emaze.dysfunctional.dispatching.composing.PipelinedTernaryActionTest.BucketFillingTernaryAction;
import net.emaze.dysfunctional.dispatching.actions.TernaryAction;
import org.junit.Test;
import org.junit.Assert;

/**
 *
 * @author rferranti
 */
public class PipelinedTernaryActionTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullIterableYieldsException() {
        new PipelinedTernaryAction<String, String, String>(null);
    }

    @Test
    public void actionsAreCalledInOrder() {
        final List<String> bucket = new ArrayList<String>();
        final List<TernaryAction<String, String, String>> actions = Arrays.<TernaryAction<String, String, String>>asList(new BucketFillingTernaryAction<String>(bucket, "former"), new BucketFillingTernaryAction<String>(bucket, "latter"));
        final TernaryAction<String, String, String> pipeline = new PipelinedTernaryAction<String, String, String>(actions);
        pipeline.perform("ignored_value", "ignored_value", "ignored_value");
        Assert.assertEquals(Arrays.asList("former", "latter"), bucket);
    }

    public static class BucketFillingTernaryAction<T> implements TernaryAction<T, T, T> {

        private final List<T> bucket;
        private final T elementToAdd;

        public BucketFillingTernaryAction(List<T> bucket, T elementToAdd) {
            this.bucket = bucket;
            this.elementToAdd = elementToAdd;
        }

        @Override
        public void perform(T first, T second, T third) {
            bucket.add(elementToAdd);
        }
    }
}
