package net.emaze.dysfunctional.delegates;

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
    public void addingNullFunctorToPipelineYieldsException() {
        PipelinedBinaryAction<String, String> pipeline = new PipelinedBinaryAction<String, String>();
        pipeline.add(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removingNullFunctorToPipelineYieldsException() {
        PipelinedBinaryAction<String, String> pipeline = new PipelinedBinaryAction<String, String>();
        pipeline.remove(null);
    }

    @Test
    public void removingNonPresentPipelineYieldsFalse() {
        PipelinedBinaryAction<String, String> pipeline = new PipelinedBinaryAction<String, String>();
        boolean got = pipeline.remove(new BinaryNoop<String, String>());
        Assert.assertFalse(got);
    }

    @Test
    public void removingPresentPipelineYieldsTrue() {
        PipelinedBinaryAction<String, String> pipeline = new PipelinedBinaryAction<String, String>();
        BinaryNoop<String, String> noop = new BinaryNoop<String, String>();
        pipeline.add(noop);
        boolean got = pipeline.remove(noop);
        Assert.assertTrue(got);
    }

    @Test(expected = IllegalArgumentException.class)
    public void settingNullFunctorsCollectionYieldsException() {
        PipelinedBinaryAction<String, String> pipeline = new PipelinedBinaryAction<String, String>();
        pipeline.setFunctors(null);
    }

    @Test
    public void canSetFunctors() {
        PipelinedBinaryAction<String, String> pipeline = new PipelinedBinaryAction<String, String>();
        pipeline.setFunctors(Arrays.<BinaryAction<String, String>>asList(new BinaryNoop<String, String>(), new BinaryNoop<String, String>()));
    }

    @Test
    public void actionsAreCalledInOrder() {
        final List<String> bucket = new ArrayList<String>();
        PipelinedBinaryAction<String, String> pipeline = new PipelinedBinaryAction<String, String>();
        pipeline.add(new BucketFillingBinaryAction<String>(bucket, "former"));
        pipeline.add(new BucketFillingBinaryAction<String>(bucket, "latter"));
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
