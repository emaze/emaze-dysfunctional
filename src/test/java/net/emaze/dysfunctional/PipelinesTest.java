package net.emaze.dysfunctional;

import java.util.Iterator;
import junit.framework.Assert;
import java.util.function.Consumer;
import java.util.function.BiConsumer;
import net.emaze.dysfunctional.dispatching.actions.BinaryNoop;
import net.emaze.dysfunctional.dispatching.actions.Noop;
import net.emaze.dysfunctional.dispatching.actions.TriConsumer;
import net.emaze.dysfunctional.dispatching.actions.TernaryNoop;
import net.emaze.dysfunctional.PipelinesTest.BinaryTest;
import net.emaze.dysfunctional.PipelinesTest.FacadeTest;
import net.emaze.dysfunctional.PipelinesTest.PipesTest;
import net.emaze.dysfunctional.PipelinesTest.TernaryTest;
import net.emaze.dysfunctional.PipelinesTest.UnaryTest;
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
            final Iterable<Consumer<O>> iterable = Iterations.<Consumer<O>>iterable(new Noop<O>());
            final Consumer<O> pipeline = Pipelines.Unary.pipeline(iterable);
            Assert.assertNotNull(pipeline);
        }

        @Test
        public void canCreatePipelineFromAnIterator() {
            final Iterator<Consumer<O>> iterator = Iterations.<Consumer<O>>iterator(new Noop<O>());
            final Consumer<O> pipeline = Pipelines.Unary.pipeline(iterator);
            Assert.assertNotNull(pipeline);
        }
    }

    public static class BinaryTest {

        @Test
        public void canCreatePipelineFromAnIterable() {
            final Iterable<BiConsumer<O, O>> iterable = Iterations.<BiConsumer<O, O>>iterable(new BinaryNoop<O, O>());
            final BiConsumer<O, O> pipeline = Pipelines.Binary.pipeline(iterable);
            Assert.assertNotNull(pipeline);
        }

        @Test
        public void canCreatePipelineFromAnIterator() {
            final Iterator<BiConsumer<O, O>> iterator = Iterations.<BiConsumer<O, O>>iterator(new BinaryNoop<O, O>());
            final BiConsumer<O, O> pipeline = Pipelines.Binary.pipeline(iterator);
            Assert.assertNotNull(pipeline);
        }
    }

    public static class TernaryTest {

        @Test
        public void canCreatePipelineFromAnIterable() {
            final Iterable<TriConsumer<O, O, O>> iterable = Iterations.<TriConsumer<O, O, O>>iterable(new TernaryNoop<O, O, O>());
            final TriConsumer<O, O, O> pipeline = Pipelines.Ternary.pipeline(iterable);
            Assert.assertNotNull(pipeline);
        }

        @Test
        public void canCreatePipelineFromAnIterator() {
            final Iterator<TriConsumer<O, O, O>> iterator = Iterations.<TriConsumer<O, O, O>>iterator(new TernaryNoop<O, O, O>());
            final TriConsumer<O, O, O> pipeline = Pipelines.Ternary.pipeline(iterator);
            Assert.assertNotNull(pipeline);
        }
    }

    public static class PipesTest {

        @Test
        public void canCreatePipelineFromAnAction() {
            final Noop<O> noop = new Noop<O>();
            final Consumer<O> pipeline = Pipelines.pipeline(noop);
            Assert.assertNotNull(pipeline);
        }

        @Test
        public void canCreatePipelineFromTwoActions() {
            final Noop<O> noop = new Noop<O>();
            final Consumer<O> pipeline = Pipelines.pipeline(noop, noop);
            Assert.assertNotNull(pipeline);
        }

        @Test
        public void canCreatePipelineFromThreeActions() {
            final Noop<O> noop = new Noop<O>();
            final Consumer<O> pipeline = Pipelines.pipeline(noop, noop, noop);
            Assert.assertNotNull(pipeline);
        }

        @Test
        public void canCreatePipelineFromManyActions() {
            final Noop<O> noop = new Noop<O>();
            final Consumer<O> pipeline = Pipelines.pipeline(noop, noop, noop, noop);
            Assert.assertNotNull(pipeline);
        }

        @Test
        public void canCreatePipelineFromBinaryAction() {
            final BinaryNoop<O, O> noop = new BinaryNoop<O, O>();
            final BiConsumer<O, O> pipeline = Pipelines.pipeline(noop);
            Assert.assertNotNull(pipeline);
        }

        @Test
        public void canCreatePipelineFromTwoBinaryActions() {
            final BinaryNoop<O, O> noop = new BinaryNoop<O, O>();
            final BiConsumer<O, O> pipeline = Pipelines.pipeline(noop, noop);
            Assert.assertNotNull(pipeline);
        }

        @Test
        public void canCreatePipelineFromThreeBinaryActions() {
            final BinaryNoop<O, O> noop = new BinaryNoop<O, O>();
            final BiConsumer<O, O> pipeline = Pipelines.pipeline(noop, noop, noop);
            Assert.assertNotNull(pipeline);
        }

        @Test
        public void canCreatePipelineFromManyBinaryActions() {
            final BinaryNoop<O, O> noop = new BinaryNoop<O, O>();
            final BiConsumer<O, O> pipeline = Pipelines.pipeline(noop, noop, noop, noop);
            Assert.assertNotNull(pipeline);
        }

        @Test
        public void canCreatePipelineFromTernaryAction() {
            final TernaryNoop<O, O, O> noop = new TernaryNoop<O, O, O>();
            final TriConsumer<O, O, O> pipeline = Pipelines.pipeline(noop);
            Assert.assertNotNull(pipeline);
        }

        @Test
        public void canCreatePipelineFromTwoTernaryActions() {
            final TernaryNoop<O, O, O> noop = new TernaryNoop<O, O, O>();
            final TriConsumer<O, O, O> pipeline = Pipelines.pipeline(noop, noop);
            Assert.assertNotNull(pipeline);
        }

        @Test
        public void canCreatePipelineFromThreeTernaryActions() {
            final TernaryNoop<O, O, O> noop = new TernaryNoop<O, O, O>();
            final TriConsumer<O, O, O> pipeline = Pipelines.pipeline(noop, noop, noop);
            Assert.assertNotNull(pipeline);
        }

        @Test
        public void canCreatePipelineFromManyTernaryActions() {
            final TernaryNoop<O, O, O> noop = new TernaryNoop<O, O, O>();
            final TriConsumer<O, O, O> pipeline = Pipelines.pipeline(noop, noop, noop, noop);
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
