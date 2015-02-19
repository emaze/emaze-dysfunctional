package net.emaze.dysfunctional.interceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import net.emaze.dysfunctional.dispatching.delegates.Identity;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class InterceptorAdapterTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingAdapterWithNullAdapteeYieldsException() {
        new InterceptorAdapter<Object, Object>(null, new Identity<Object>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingAdapterWithNullInnerDelegateYieldsException() {
        new InterceptorAdapter<Object, Object>(new NoopInterceptor<Object>(), null);
    }

    @Test
    public void beforeAndAfterAreCalled() {
        List<Integer> bucket = new ArrayList<Integer>();
        Function<String, String> delegate = new InterceptorAdapter<String, String>(new BucketFillingInterceptor(bucket), new Identity<String>());
        delegate.apply("useless_param");
        Assert.assertEquals(2, bucket.size());

    }

    @Test
    public void beforeAndAfterAreCalledInCaseOfException() {
        List<Integer> bucket = new ArrayList<Integer>();
        Function<String, String> delegate = new InterceptorAdapter<String, String>(new BucketFillingInterceptor(bucket), new Function<String, String>() {

            @Override
            public String apply(String t) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        try {
            delegate.apply("useless_param");
            Assert.fail("delegate is supposed to throw UnsupportedOperationException");
        }catch(UnsupportedOperationException ex){
            Assert.assertEquals(2, bucket.size());
        }
    }

    public static class NoopInterceptor<T> implements Interceptor<T> {

        @Override
        public void before(T value) {
        }

        @Override
        public void after(T value) {
        }
    }

    public static class BucketFillingInterceptor<T> implements Interceptor<T> {

        private final List<T> bucket;

        public BucketFillingInterceptor(List<T> bucket) {
            this.bucket = bucket;
        }

        @Override
        public void before(T value) {
            bucket.add(value);
        }

        @Override
        public void after(T value) {
            bucket.add(value);
        }
    }
}
