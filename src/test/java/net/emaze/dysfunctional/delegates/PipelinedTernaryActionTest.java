package net.emaze.dysfunctional.delegates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.emaze.dysfunctional.delegates.PipelinedTernaryActionTest.BucketFillingTernaryAction;
import org.junit.Test;
import org.junit.Assert;

/**
 *
 * @author rferranti
 */
public class PipelinedTernaryActionTest {

    @Test(expected = IllegalArgumentException.class)
    public void addingNullFunctorToPipelineYieldsException() {
        PipelinedTernaryAction<String, String, String> pipeline = new PipelinedTernaryAction<String, String, String>();
        pipeline.add(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removingNullFunctorToPipelineYieldsException() {
        PipelinedTernaryAction<String, String, String> pipeline = new PipelinedTernaryAction<String, String, String>();
        pipeline.remove(null);
    }

    @Test
    public void removingNonPresentPipelineYieldsFalse() {
        PipelinedTernaryAction<String, String, String> pipeline = new PipelinedTernaryAction<String, String, String>();
        boolean got = pipeline.remove(new TernaryNoop<String, String, String>());
        Assert.assertFalse(got);
    }

    @Test
    public void removingPresentPipelineYieldsTrue() {
        PipelinedTernaryAction<String, String, String> pipeline = new PipelinedTernaryAction<String, String, String>();
        TernaryNoop<String, String, String> noop = new TernaryNoop<String, String, String>();
        pipeline.add(noop);
        boolean got = pipeline.remove(noop);
        Assert.assertTrue(got);
    }

    @Test(expected = IllegalArgumentException.class)
    public void settingNullFunctorsCollectionYieldsException() {
        PipelinedTernaryAction<String, String, String> pipeline = new PipelinedTernaryAction<String, String, String>();
        pipeline.setFunctors(null);
    }

    @Test
    public void canSetFunctors() {
        PipelinedTernaryAction<String, String, String> pipeline = new PipelinedTernaryAction<String, String, String>();
        pipeline.setFunctors(Arrays.<TernaryAction<String, String, String>>asList(new TernaryNoop<String, String, String>(), new TernaryNoop<String, String, String>()));
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