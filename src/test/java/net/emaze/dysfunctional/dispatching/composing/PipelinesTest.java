package net.emaze.dysfunctional.dispatching.composing;

import net.emaze.dysfunctional.Pipelines;
import java.util.Iterator;
import junit.framework.Assert;
import net.emaze.dysfunctional.dispatching.actions.Action;
import net.emaze.dysfunctional.dispatching.actions.BinaryAction;
import net.emaze.dysfunctional.dispatching.actions.BinaryNoop;
import net.emaze.dysfunctional.dispatching.actions.Noop;
import net.emaze.dysfunctional.dispatching.actions.TernaryAction;
import net.emaze.dysfunctional.dispatching.actions.TernaryNoop;
import net.emaze.dysfunctional.dispatching.composing.PipelinesTest.BinaryTest;
import net.emaze.dysfunctional.dispatching.composing.PipelinesTest.FacadeTest;
import net.emaze.dysfunctional.dispatching.composing.PipelinesTest.PipesTest;
import net.emaze.dysfunctional.dispatching.composing.PipelinesTest.TernaryTest;
import net.emaze.dysfunctional.dispatching.composing.PipelinesTest.UnaryTest;
import net.emaze.dysfunctional.Iterations;
import net.emaze.dysfunctional.testing.O;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author rferranti
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    UnaryTest.class,
    BinaryTest.class,
    TernaryTest.class,
    PipesTest.class,
    FacadeTest.class
})
public class PipelinesTest {

    public static class UnaryTest {

        @Test
        public void canCreatePipelineFromAnIterable() {
            final Iterable<Action<O>> iterable = Iterations.<Action<O>>iterable(new Noop<O>());
            final Action<O> pipeline = Pipelines.Unary.pipeline(iterable);
            Assert.assertNotNull(pipeline);
        }

        @Test
        public void canCreatePipelineFromAnIterator() {
            final Iterator<Action<O>> iterator = Iterations.<Action<O>>iterator(new Noop<O>());
            final Action<O> pipeline = Pipelines.Unary.pipeline(iterator);
            Assert.assertNotNull(pipeline);
        }
    }

    public static class BinaryTest {

        @Test
        public void canCreatePipelineFromAnIterable() {
            final Iterable<BinaryAction<O, O>> iterable = Iterations.<BinaryAction<O, O>>iterable(new BinaryNoop<O, O>());
            final BinaryAction<O, O> pipeline = Pipelines.Binary.pipeline(iterable);
            Assert.assertNotNull(pipeline);
        }

        @Test
        public void canCreatePipelineFromAnIterator() {
            final Iterator<BinaryAction<O, O>> iterator = Iterations.<BinaryAction<O, O>>iterator(new BinaryNoop<O, O>());
            final BinaryAction<O, O> pipeline = Pipelines.Binary.pipeline(iterator);
            Assert.assertNotNull(pipeline);
        }
    }

    public static class TernaryTest {

        @Test
        public void canCreatePipelineFromAnIterable() {
            final Iterable<TernaryAction<O, O, O>> iterable = Iterations.<TernaryAction<O, O, O>>iterable(new TernaryNoop<O, O, O>());
            final TernaryAction<O, O, O> pipeline = Pipelines.Ternary.pipeline(iterable);
            Assert.assertNotNull(pipeline);
        }

        @Test
        public void canCreatePipelineFromAnIterator() {
            final Iterator<TernaryAction<O, O, O>> iterator = Iterations.<TernaryAction<O, O, O>>iterator(new TernaryNoop<O, O, O>());
            final TernaryAction<O, O, O> pipeline = Pipelines.Ternary.pipeline(iterator);
            Assert.assertNotNull(pipeline);
        }
    }

    public static class PipesTest {

        @Test
        public void canCreatePipelineFromAnAction() {
            final Noop<O> noop = new Noop<O>();
            final Action<O> pipeline = Pipelines.pipeline(noop);
            Assert.assertNotNull(pipeline);
        }

        @Test
        public void canCreatePipelineFromTwoActions() {
            final Noop<O> noop = new Noop<O>();
            final Action<O> pipeline = Pipelines.pipeline(noop, noop);
            Assert.assertNotNull(pipeline);
        }

        @Test
        public void canCreatePipelineFromThreeActions() {
            final Noop<O> noop = new Noop<O>();
            final Action<O> pipeline = Pipelines.pipeline(noop, noop, noop);
            Assert.assertNotNull(pipeline);
        }

        @Test
        public void canCreatePipelineFromManyActions() {
            final Noop<O> noop = new Noop<O>();
            final Action<O> pipeline = Pipelines.pipeline(noop, noop, noop, noop);
            Assert.assertNotNull(pipeline);
        }

        @Test
        public void canCreatePipelineFromBinaryAction() {
            final BinaryNoop<O, O> noop = new BinaryNoop<O, O>();
            final BinaryAction<O, O> pipeline = Pipelines.pipeline(noop);
            Assert.assertNotNull(pipeline);
        }

        @Test
        public void canCreatePipelineFromTwoBinaryActions() {
            final BinaryNoop<O, O> noop = new BinaryNoop<O, O>();
            final BinaryAction<O, O> pipeline = Pipelines.pipeline(noop, noop);
            Assert.assertNotNull(pipeline);
        }

        @Test
        public void canCreatePipelineFromThreeBinaryActions() {
            final BinaryNoop<O, O> noop = new BinaryNoop<O, O>();
            final BinaryAction<O, O> pipeline = Pipelines.pipeline(noop, noop, noop);
            Assert.assertNotNull(pipeline);
        }

        @Test
        public void canCreatePipelineFromManyBinaryActions() {
            final BinaryNoop<O, O> noop = new BinaryNoop<O, O>();
            final BinaryAction<O, O> pipeline = Pipelines.pipeline(noop, noop, noop, noop);
            Assert.assertNotNull(pipeline);
        }

        @Test
        public void canCreatePipelineFromTernaryAction() {
            final TernaryNoop<O, O, O> noop = new TernaryNoop<O, O, O>();
            final TernaryAction<O, O, O> pipeline = Pipelines.pipeline(noop);
            Assert.assertNotNull(pipeline);
        }

        @Test
        public void canCreatePipelineFromTwoTernaryActions() {
            final TernaryNoop<O, O, O> noop = new TernaryNoop<O, O, O>();
            final TernaryAction<O, O, O> pipeline = Pipelines.pipeline(noop, noop);
            Assert.assertNotNull(pipeline);
        }

        @Test
        public void canCreatePipelineFromThreeTernaryActions() {
            final TernaryNoop<O, O, O> noop = new TernaryNoop<O, O, O>();
            final TernaryAction<O, O, O> pipeline = Pipelines.pipeline(noop, noop, noop);
            Assert.assertNotNull(pipeline);
        }

        @Test
        public void canCreatePipelineFromManyTernaryActions() {
            final TernaryNoop<O, O, O> noop = new TernaryNoop<O, O, O>();
            final TernaryAction<O, O, O> pipeline = Pipelines.pipeline(noop, noop, noop, noop);
            Assert.assertNotNull(pipeline);
        }
    }
    
    public static class FacadeTest{
        @Test
        public void pipelinesIsNotFinal(){
            new Pipelines() {};
        }
        @Test
        public void unaryIsNotFinal(){
            new Pipelines.Unary() {};
        }
        @Test
        public void binaryIsNotFinal(){
            new Pipelines.Binary() {};
        }
        @Test
        public void ternaryIsNotFinal(){
            new Pipelines.Ternary() {};
        }
    }
}
