package net.emaze.dysfunctional.interceptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.dispatching.multicasting.Multicasting;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class BinaryInterceptorChainTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingChainWithNullInnermostDelegateYieldsException() {
        new BinaryInterceptorChain<Object, Object, Object>(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addingNullFunctorToChainYieldsException() {
        BucketFillingDelegate delegate = new BucketFillingDelegate(1, new ArrayList<Integer>());
        BinaryInterceptorChain<String, String, String> chain = new BinaryInterceptorChain<String, String, String>(delegate);
        chain.add(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removingNullFunctorToChainYieldsException() {
        BucketFillingDelegate delegate = new BucketFillingDelegate(1, new ArrayList<Integer>());
        BinaryInterceptorChain<String, String, String> chain = new BinaryInterceptorChain<String, String, String>(delegate);
        chain.remove(null);
    }

    @Test(expected = ClassCastException.class)
    public void passingWrongTypeToRemoveInErasureYieldsException() {
        BucketFillingDelegate delegate = new BucketFillingDelegate(1, new ArrayList<Integer>());
        Multicasting multi = new BinaryInterceptorChain<String, String, String>(delegate);
        multi.remove(new Object());
    }

    @Test(expected = ClassCastException.class)
    public void passingWrongTypeToAddInErasureYieldsException() {
        BucketFillingDelegate delegate = new BucketFillingDelegate(1, new ArrayList<Integer>());
        Multicasting multi = new BinaryInterceptorChain<String, String, String>(delegate);
        multi.add(new Object());
    }

    @Test(expected = ClassCastException.class)
    public void passingWrongTypeToFirstArgumentOfPerformInErasureYieldsException() {
        BucketFillingDelegate delegate = new BucketFillingDelegate(1, new ArrayList<Integer>());
        BinaryDelegate d = new BinaryInterceptorChain<String, String, String>(delegate);
        d.perform(new Object(), "a string");
    }

    @Test(expected = ClassCastException.class)
    public void passingWrongTypeToSecondArgumentOfPerformInErasureYieldsException() {
        BucketFillingDelegate delegate = new BucketFillingDelegate(1, new ArrayList<Integer>());
        BinaryDelegate d = new BinaryInterceptorChain<String, String, String>(delegate);
        d.perform("a string", new Object());
    }

    @Test
    public void removingNonPresentInterceptorYieldsFalse() {
        final List<Integer> bucket = new ArrayList<Integer>();
        BucketFillingDelegate delegate = new BucketFillingDelegate(1, bucket);
        BinaryInterceptorChain<String, String, String> chain = new BinaryInterceptorChain<String, String, String>(delegate);
        boolean got = chain.remove(new BucketFillingInterceptor(2, bucket));
        Assert.assertFalse(got);
    }

    @Test
    public void removingPresentInterceptorYieldsTrue() {
        final List<Integer> bucket = new ArrayList<Integer>();
        BucketFillingDelegate delegate = new BucketFillingDelegate(1, bucket);
        BinaryInterceptorChain<String, String, String> chain = new BinaryInterceptorChain<String, String, String>(delegate);
        BinaryInterceptor<String, String> interceptor = new BucketFillingInterceptor(2, bucket);
        chain.add(interceptor);
        boolean got = chain.remove(interceptor);
        Assert.assertTrue(got);
    }

    @Test(expected = IllegalArgumentException.class)
    public void settingNullFunctorsCollectionYieldsException() {
        final List<Integer> bucket = new ArrayList<Integer>();
        BucketFillingDelegate delegate = new BucketFillingDelegate(1, bucket);
        BinaryInterceptorChain<String, String, String> chain = new BinaryInterceptorChain<String, String, String>(delegate);
        chain.setFunctors(null);
    }

    @Test
    public void settingFunctorsRemovesOldFunctors() {
        final List<Integer> bucket = new ArrayList<Integer>();
        BucketFillingDelegate delegate = new BucketFillingDelegate(1, bucket);
        BinaryInterceptorChain<String, String, String> chain = new BinaryInterceptorChain<String, String, String>(delegate);
        BinaryInterceptor<String, String> interceptor = new BucketFillingInterceptor(2, bucket);
        chain.add(interceptor);
        chain.setFunctors(Collections.<BinaryInterceptor<String, String>>emptyList());
        chain.perform("ignored", "ignored");
        Assert.assertEquals(1, bucket.size());
    }

    @Test
    public void chainingIsDoneInCorrectOrder() {
        final List<Integer> bucket = new ArrayList<Integer>();
        final BinaryInterceptorChain<String, String, String> chain = new BinaryInterceptorChain<String, String, String>(new BucketFillingDelegate(4, bucket));
        chain.add(new BucketFillingInterceptor(1, bucket));
        chain.add(new BucketFillingInterceptor(2, bucket));
        chain.add(new BucketFillingInterceptor(3, bucket));
        chain.perform("useless_value", "useless_value");
        Assert.assertEquals(Arrays.asList(1, 2, 3, 4, 3, 2, 1), bucket);
    }

    @Test
    public void whenAnInterceptorThrowsInBeforeCorrectAfterAreCalled() {
        final List<Integer> bucket = new ArrayList<Integer>();
        final BinaryInterceptorChain<String, String, String> chain = new BinaryInterceptorChain<String, String, String>(new BucketFillingDelegate(4, bucket));
        chain.add(new BucketFillingInterceptor(1, bucket));
        chain.add(new ThrowingInterceptor());
        chain.add(new BucketFillingInterceptor(3, bucket));
        try {
            chain.perform("useless_value", "useless_value");
        } catch (Exception ex) {
        }
        Assert.assertEquals(Arrays.asList(1, 1), bucket);
    }

    public static class BucketFillingDelegate implements BinaryDelegate<String, String, String> {

        private final int id;
        private final List<Integer> bucket;

        public BucketFillingDelegate(int id, List<Integer> bucket) {
            this.id = id;
            this.bucket = bucket;
        }

        @Override
        public String perform(String first, String second) {
            bucket.add(id);
            return null;
        }
    }

    public static class BucketFillingInterceptor implements BinaryInterceptor<String, String> {

        private final int id;
        private final List<Integer> bucket;

        public BucketFillingInterceptor(int id, List<Integer> bucket) {
            this.id = id;
            this.bucket = bucket;
        }

        @Override
        public void before(String first, String second) {
            bucket.add(id);
        }

        @Override
        public void after(String first, String second) {
            bucket.add(id);
        }
    }

    public class ThrowingInterceptor implements BinaryInterceptor<String, String> {

        @Override
        public void before(String first, String second) {
            throw new IllegalStateException();
        }

        @Override
        public void after(String first, String second) {
            throw new IllegalStateException();
        }
    }
}
