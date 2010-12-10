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
public class PipelinedActionTest {

    @Test(expected = IllegalArgumentException.class)
    public void addingNullFunctorToPipelineYieldsException() {
        PipelinedAction<String> pipeline = new PipelinedAction<String>();
        pipeline.add(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removingNullFunctorToPipelineYieldsException() {
        PipelinedAction<String> pipeline = new PipelinedAction<String>();
        pipeline.remove(null);
    }

    @Test
    public void removingNonPresentPipelineYieldsFalse() {
        PipelinedAction<String> pipeline = new PipelinedAction<String>();
        boolean got = pipeline.remove(new Noop<String>());
        Assert.assertFalse(got);
    }

    @Test
    public void removingPresentPipelineYieldsTrue() {
        PipelinedAction<String> pipeline = new PipelinedAction<String>();
        Noop<String> noop = new Noop<String>();
        pipeline.add(noop);
        boolean got = pipeline.remove(noop);
        Assert.assertTrue(got);
    }

    @Test(expected = IllegalArgumentException.class)
    public void settingNullFunctorsCollectionYieldsException() {
        PipelinedAction<String> pipeline = new PipelinedAction<String>();
        pipeline.setFunctors(null);
    }
    
    @Test
    public void canSetFunctors() {
        PipelinedAction<String> pipeline = new PipelinedAction<String>();
        pipeline.setFunctors(Arrays.<Action<String>>asList(new Noop<String>() , new Noop<String>()));
        pipeline.perform("ignored_value");
    }

    @Test
    public void actionsAreCalledInOrder(){
        final List<String> bucket = new ArrayList<String>();
        PipelinedAction<String> pipeline = new PipelinedAction<String>();
        pipeline.add(new Action<String>(){

            @Override
            public void perform(String element) {
                bucket.add("former");
            }
        });
        pipeline.add(new Action<String>(){

            @Override
            public void perform(String element) {
                bucket.add("latter");
            }
        });
        pipeline.perform("ignored_value");
        Assert.assertEquals(Arrays.asList("former", "latter"), bucket);
    }
}
