package net.emaze.dysfunctional.interceptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.dispatching.delegates.FirstParamOfThree;
import net.emaze.dysfunctional.dispatching.delegates.TernaryDelegate;
import net.emaze.dysfunctional.Iterations;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class TernaryInterceptorChainTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingChainWithNullInnermostDelegateYieldsException() {
        final Iterator<TernaryInterceptor<O, O, O>> chain = Iterations.iterator();
        new TernaryInterceptorChain<O, O, O, O>(null, chain);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingChainWithNullChaingYieldsException() {
        final TernaryDelegate<O, O, O, O> firstParam = new FirstParamOfThree<O, O, O>();
        new TernaryInterceptorChain<O, O, O, O>(firstParam, null);
    }

    @Test
    public void chainingIsDoneInCorrectOrder() {
        final List<Integer> bucket = new ArrayList<Integer>();
        final Iterator<TernaryInterceptor<O, O, O>> chain = Iterations.<TernaryInterceptor<O, O, O>>iterator(
                new BucketFillingInterceptor(3, bucket),
                new BucketFillingInterceptor(2, bucket),
                new BucketFillingInterceptor(1, bucket));
        final TernaryInterceptorChain<O, O, O, O> ic = new TernaryInterceptorChain<O, O, O, O>(new BucketFillingDelegate(4, bucket), chain);
        ic.perform(O.IGNORED, O.IGNORED, O.IGNORED);
        Assert.assertEquals(Arrays.asList(1, 2, 3, 4, 3, 2, 1), bucket);
    }

    @Test
    public void whenAnInterceptorThrowsInBeforeCorrectAfterAreCalled() {
        final List<Integer> bucket = new ArrayList<Integer>();
        final Iterator<TernaryInterceptor<O, O, O>> chain = Iterations.<TernaryInterceptor<O, O, O>>iterator(
                new BucketFillingInterceptor(3, bucket),
                new ThrowingInterceptor(),
                new BucketFillingInterceptor(1, bucket));
        final TernaryInterceptorChain<O, O, O, O> ic = new TernaryInterceptorChain<O, O, O, O>(new BucketFillingDelegate(4, bucket), chain);
        try {
            ic.perform(O.IGNORED, O.IGNORED, O.IGNORED);
        } catch (Exception ex) {
        }
        Assert.assertEquals(Arrays.asList(1, 1), bucket);
    }

    public static class BucketFillingDelegate implements TernaryDelegate<O, O, O, O> {

        private final int id;
        private final List<Integer> bucket;

        public BucketFillingDelegate(int id, List<Integer> bucket) {
            this.id = id;
            this.bucket = bucket;
        }

        @Override
        public O perform(O first, O second, O third) {
            bucket.add(id);
            return null;
        }
    }

    public static class BucketFillingInterceptor implements TernaryInterceptor<O, O, O> {

        private final int id;
        private final List<Integer> bucket;

        public BucketFillingInterceptor(int id, List<Integer> bucket) {
            this.id = id;
            this.bucket = bucket;
        }

        @Override
        public void before(O first, O second, O third) {
            bucket.add(id);
        }

        @Override
        public void after(O first, O second, O third) {
            bucket.add(id);
        }
    }

    public class ThrowingInterceptor implements TernaryInterceptor<O, O, O> {

        @Override
        public void before(O first, O second, O third) {
            throw new IllegalStateException();
        }

        @Override
        public void after(O first, O second, O third) {
            throw new IllegalStateException();
        }
    }
}
