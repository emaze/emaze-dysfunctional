package net.emaze.dysfunctional.interceptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import net.emaze.dysfunctional.delegates.TernaryDelegate;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class TernaryInterceptorChainTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingChainWithNullInnermostDelegateYieldsException() {
        new TernaryInterceptorChain<Object, Object, Object, Object>(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addingNullFunctorToChainYieldsException() {
        BucketFillingDelegate delegate = new BucketFillingDelegate(1, new ArrayList<Integer>());
        TernaryInterceptorChain<String, String, String, String> chain = new TernaryInterceptorChain<String, String, String, String>(delegate);
        chain.add(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removingNullFunctorToChainYieldsException() {
        BucketFillingDelegate delegate = new BucketFillingDelegate(1, new ArrayList<Integer>());
        TernaryInterceptorChain<String, String, String, String> chain = new TernaryInterceptorChain<String, String, String, String>(delegate);
        chain.remove(null);
    }

    @Test
    public void removingNonPresentInterceptorYieldsFalse() {
        final List<Integer> bucket = new ArrayList<Integer>();
        BucketFillingDelegate delegate = new BucketFillingDelegate(1, bucket);
        TernaryInterceptorChain<String, String, String, String> chain = new TernaryInterceptorChain<String, String, String, String>(delegate);
        boolean got = chain.remove(new BucketFillingInterceptor(2, bucket));
        Assert.assertFalse(got);
    }

    @Test
    public void removingPresentInterceptorYieldsTrue() {
        final List<Integer> bucket = new ArrayList<Integer>();
        BucketFillingDelegate delegate = new BucketFillingDelegate(1, bucket);
        TernaryInterceptorChain<String, String, String, String> chain = new TernaryInterceptorChain<String, String, String, String>(delegate);
        TernaryInterceptor<String, String, String> interceptor = new BucketFillingInterceptor(2, bucket);
        chain.add(interceptor);
        boolean got = chain.remove(interceptor);
        Assert.assertTrue(got);
    }

    @Test(expected = IllegalArgumentException.class)
    public void settingNullFunctorsCollectionYieldsException() {
        final List<Integer> bucket = new ArrayList<Integer>();
        BucketFillingDelegate delegate = new BucketFillingDelegate(1, bucket);
        TernaryInterceptorChain<String, String, String, String> chain = new TernaryInterceptorChain<String, String, String, String>(delegate);
        chain.setFunctors(null);
    }

    @Test
    public void settingFunctorsRemovesOldFunctors() {
        final List<Integer> bucket = new ArrayList<Integer>();
        BucketFillingDelegate delegate = new BucketFillingDelegate(1, bucket);
        TernaryInterceptorChain<String, String, String, String> chain = new TernaryInterceptorChain<String, String, String, String>(delegate);
        TernaryInterceptor<String, String, String> interceptor = new BucketFillingInterceptor(2, bucket);
        chain.add(interceptor);
        chain.setFunctors(Collections.<TernaryInterceptor<String, String, String>>emptyList());
        chain.perform("ignored", "ignored", "ignored");
        Assert.assertEquals(1, bucket.size());
    }

    @Test
    public void chainingIsDoneInCorrectOrder() {
        final List<Integer> bucket = new ArrayList<Integer>();
        final TernaryInterceptorChain<String, String, String, String> chain = new TernaryInterceptorChain<String, String, String, String>(new BucketFillingDelegate(4, bucket));
        chain.add(new BucketFillingInterceptor(1, bucket));
        chain.add(new BucketFillingInterceptor(2, bucket));
        chain.add(new BucketFillingInterceptor(3, bucket));
        chain.perform("ignored", "ignored", "ignored");
        Assert.assertEquals(Arrays.asList(1, 2, 3, 4, 3, 2, 1), bucket);
    }

    @Test
    public void whenAnInterceptorThrowsInBeforeCorrectAfterAreCalled() {
        final List<Integer> bucket = new ArrayList<Integer>();
        final TernaryInterceptorChain<String, String, String, String> chain = new TernaryInterceptorChain<String, String, String, String>(new BucketFillingDelegate(4, bucket));
        chain.add(new BucketFillingInterceptor(1, bucket));
        chain.add(new ThrowingInterceptor());
        chain.add(new BucketFillingInterceptor(3, bucket));
        try {
            chain.perform("ignored", "ignored", "ignored");
        } catch (Exception ex) {
        }
        Assert.assertEquals(Arrays.asList(1, 1), bucket);
    }

    public static class BucketFillingDelegate implements TernaryDelegate<String, String, String, String> {

        private final int id;
        private final List<Integer> bucket;

        public BucketFillingDelegate(int id, List<Integer> bucket) {
            this.id = id;
            this.bucket = bucket;
        }

        @Override
        public String perform(String first, String second, String third) {
            bucket.add(id);
            return null;
        }
    }

    public static class BucketFillingInterceptor implements TernaryInterceptor<String, String, String> {

        private final int id;
        private final List<Integer> bucket;

        public BucketFillingInterceptor(int id, List<Integer> bucket) {
            this.id = id;
            this.bucket = bucket;
        }

        @Override
        public void before(String first, String second, String third) {
            bucket.add(id);
        }

        @Override
        public void after(String first, String second, String third) {
            bucket.add(id);
        }
    }

    public class ThrowingInterceptor implements TernaryInterceptor<String, String, String> {

        @Override
        public void before(String first, String second, String third) {
            throw new IllegalStateException();
        }

        @Override
        public void after(String first, String second, String third) {
            throw new IllegalStateException();
        }
    }
}
