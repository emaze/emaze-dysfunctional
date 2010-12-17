package net.emaze.dysfunctional.interceptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class InterceptorChainTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingChainWithNullInnermostDelegateYieldsException() {
        new InterceptorChain<Object, Object>(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addingNullFunctorToChainYieldsException() {
        BucketFillingDelegate delegate = new BucketFillingDelegate(1, new ArrayList<Integer>());
        InterceptorChain<String, String> chain = new InterceptorChain<String, String>(delegate);
        chain.add(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removingNullFunctorToChainYieldsException() {
        BucketFillingDelegate delegate = new BucketFillingDelegate(1, new ArrayList<Integer>());
        InterceptorChain<String, String> chain = new InterceptorChain<String, String>(delegate);
        chain.remove(null);
    }
    
    @Test
    public void removingNonPresentInterceptorYieldsFalse() {
        final List<Integer> bucket = new ArrayList<Integer>();
        BucketFillingDelegate delegate = new BucketFillingDelegate(1, bucket);
        InterceptorChain<String, String> chain = new InterceptorChain<String, String>(delegate);
        boolean got = chain.remove(new BucketFillingInterceptor(2, bucket));
        Assert.assertFalse(got);
    }

    @Test
    public void removingPresentInterceptorYieldsTrue() {
        final List<Integer> bucket = new ArrayList<Integer>();
        BucketFillingDelegate delegate = new BucketFillingDelegate(1, bucket);
        InterceptorChain<String, String> chain = new InterceptorChain<String, String>(delegate);
        Interceptor<String> interceptor = new BucketFillingInterceptor(2, bucket);
        chain.add(interceptor);
        boolean got = chain.remove(interceptor);
        Assert.assertTrue(got);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void settingNullFunctorsCollectionYieldsException() {
        final List<Integer> bucket = new ArrayList<Integer>();
        BucketFillingDelegate delegate = new BucketFillingDelegate(1, bucket);
        InterceptorChain<String, String> chain = new InterceptorChain<String, String>(delegate);
        chain.setFunctors(null);
    }
    
    @Test
    public void settingFunctorsRemovesOldFunctors() {
        final List<Integer> bucket = new ArrayList<Integer>();
        BucketFillingDelegate delegate = new BucketFillingDelegate(1, bucket);
        InterceptorChain<String, String> chain = new InterceptorChain<String, String>(delegate);
        Interceptor<String> interceptor = new BucketFillingInterceptor(2, bucket);
        chain.add(interceptor);
        chain.setFunctors(Collections.<Interceptor<String>>emptyList());
        chain.perform("test");
        Assert.assertEquals(1, bucket.size());
    }

    @Test
    public void chainingIsDoneInCorrectOrder() {
        final List<Integer> bucket = new ArrayList<Integer>();
        final InterceptorChain<String, String> chain = new InterceptorChain<String, String>(new BucketFillingDelegate(4, bucket));
        chain.add(new BucketFillingInterceptor(1, bucket));
        chain.add(new BucketFillingInterceptor(2, bucket));
        chain.add(new BucketFillingInterceptor(3, bucket));
        chain.perform("useless_value");
        Assert.assertEquals(Arrays.asList(1, 2, 3, 4, 3, 2, 1), bucket);
    }

    @Test
    public void whenAnInterceptorThrowsInBeforeCorrectAfterAreCalled() {
        final List<Integer> bucket = new ArrayList<Integer>();
        final InterceptorChain<String, String> chain = new InterceptorChain<String, String>(new BucketFillingDelegate(4, bucket));
        chain.add(new BucketFillingInterceptor(1, bucket));
        chain.add(new ThrowingInterceptor());
        chain.add(new BucketFillingInterceptor(3, bucket));
        try {
            chain.perform("useless_value");
        } catch (Exception ex) {
        }
        Assert.assertEquals(Arrays.asList(1, 1), bucket);
    }
}
