package net.emaze.dysfunctional.dispatching.actions;

import net.emaze.dysfunctional.dispatching.actions.TernaryNoop;
import net.emaze.dysfunctional.dispatching.actions.TernaryAction;
import net.emaze.dysfunctional.dispatching.actions.PipelinedTernaryAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.emaze.dysfunctional.dispatching.actions.PipelinedTernaryActionTest.BucketFillingTernaryAction;
import net.emaze.dysfunctional.testing.O;
import org.junit.Test;
import org.junit.Assert;

/**
 *
 * @author rferranti
 */
public class PipelinedTernaryActionTest {

    @Test(expected = IllegalArgumentException.class)
    public void addingNullFunctorToPipelineYieldsException() {
        PipelinedTernaryAction<O, O, O> pipeline = new PipelinedTernaryAction<O, O, O>();
        pipeline.add(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removingNullFunctorToPipelineYieldsException() {
        PipelinedTernaryAction<O, O, O> pipeline = new PipelinedTernaryAction<O, O, O>();
        pipeline.remove(null);
    }

    @Test
    public void removingNonPresentPipelineYieldsFalse() {
        PipelinedTernaryAction<O, O, O> pipeline = new PipelinedTernaryAction<O, O, O>();
        boolean got = pipeline.remove(new TernaryNoop<O, O, O>());
        Assert.assertFalse(got);
    }

    @Test
    public void removingPresentPipelineYieldsTrue() {
        PipelinedTernaryAction<O, O, O> pipeline = new PipelinedTernaryAction<O, O, O>();
        TernaryNoop<O, O, O> noop = new TernaryNoop<O, O, O>();
        pipeline.add(noop);
        boolean got = pipeline.remove(noop);
        Assert.assertTrue(got);
    }

    @Test(expected = IllegalArgumentException.class)
    public void settingNullFunctorsCollectionYieldsException() {
        PipelinedTernaryAction<O, O, O> pipeline = new PipelinedTernaryAction<O, O, O>();
        pipeline.setFunctors(null);
    }

    @Test
    public void canSetFunctors() {
        PipelinedTernaryAction<O, O, O> pipeline = new PipelinedTernaryAction<O, O, O>();
        pipeline.setFunctors(Arrays.<TernaryAction<O, O, O>>asList(new TernaryNoop<O, O, O>(), new TernaryNoop<O, O, O>()));
    }

    @Test
    public void actionsAreCalledInOrder() {
        final List<String> bucket = new ArrayList<String>();
        PipelinedTernaryAction<String, String, String> pipeline = new PipelinedTernaryAction<String, String, String>();
        pipeline.add(new BucketFillingTernaryAction<String>(bucket, "former"));
        pipeline.add(new BucketFillingTernaryAction<String>(bucket, "latter"));
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
