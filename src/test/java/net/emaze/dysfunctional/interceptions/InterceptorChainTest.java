package net.emaze.dysfunctional.interceptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import net.emaze.dysfunctional.Iterations;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class InterceptorChainTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingChainWithNullInnermostDelegateYieldsException() {
        final Iterator<Interceptor<O>> chain = Iterations.iterator();
        new InterceptorChain<O, O>(null, chain);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingChainWithNullChaingYieldsException() {
        final Function<O, O> firstParam = UnaryOperator.identity();
        new InterceptorChain<O, O>(firstParam, null);
    }

    @Test
    public void chainingIsDoneInCorrectOrder() {
        final List<Integer> bucket = new ArrayList<Integer>();
        final Iterator<Interceptor<O>> chain = Iterations.<Interceptor<O>>iterator(
                new BucketFillingInterceptor(3, bucket),
                new BucketFillingInterceptor(2, bucket),
                new BucketFillingInterceptor(1, bucket));
        final InterceptorChain<O, O> ic = new InterceptorChain<O, O>(new BucketFillingDelegate(4, bucket), chain);
        ic.apply(O.IGNORED);
        Assert.assertEquals(Arrays.asList(1, 2, 3, 4, 3, 2, 1), bucket);
    }

    @Test
    public void whenAnInterceptorThrowsInBeforeCorrectAfterAreCalled() {
        final List<Integer> bucket = new ArrayList<Integer>();
        final Iterator<Interceptor<O>> chain = Iterations.<Interceptor<O>>iterator(
                new BucketFillingInterceptor(3, bucket),
                new ThrowingInterceptor(),
                new BucketFillingInterceptor(1, bucket));
        final InterceptorChain<O, O> ic = new InterceptorChain<O, O>(new BucketFillingDelegate(4, bucket), chain);
        try {
            ic.apply(O.IGNORED);
        } catch (Exception ex) {
        }
        Assert.assertEquals(Arrays.asList(1, 1), bucket);
    }

    public static class BucketFillingDelegate implements Function<O, O> {

        private final int id;
        private final List<Integer> bucket;

        public BucketFillingDelegate(int id, List<Integer> bucket) {
            this.id = id;
            this.bucket = bucket;
        }

        @Override
        public O apply(O value) {
            bucket.add(id);
            return value;
        }
    }

    public static class BucketFillingInterceptor implements Interceptor<O> {

        private final int id;
        private final List<Integer> bucket;

        public BucketFillingInterceptor(int id, List<Integer> bucket) {
            this.id = id;
            this.bucket = bucket;
        }

        @Override
        public void before(O value) {
            bucket.add(id);
        }

        @Override
        public void after(O value) {
            bucket.add(id);
        }
    }

    public static class ThrowingInterceptor implements Interceptor<O> {

        @Override
        public void before(O value) {
            throw new IllegalStateException();
        }

        @Override
        public void after(O value) {
            throw new IllegalStateException();
        }
    }
}
