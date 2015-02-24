package net.emaze.dysfunctional.interceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class InterceptorAdapterTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingAdapterWithNullAdapteeYieldsException() {
        new InterceptorAdapter<Object, Object>(null, Function.identity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingAdapterWithNullInnerDelegateYieldsException() {
        new InterceptorAdapter<Object, Object>(new NoopInterceptor<Object>(), null);
    }

    @Test
    public void beforeAndAfterAreCalled() {
        List<Integer> bucket = new ArrayList<Integer>();
        final Function<String, String> function = new InterceptorAdapter<String, String>(new BucketFillingInterceptor(bucket), Function.identity());
        function.apply("useless_param");
        Assert.assertEquals(2, bucket.size());

    }

    @Test
    public void beforeAndAfterAreCalledInCaseOfException() {
        List<Integer> bucket = new ArrayList<Integer>();
        Function<String, String> function = new InterceptorAdapter<String, String>(new BucketFillingInterceptor(bucket), new Function<String, String>() {

            @Override
            public String apply(String t) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        try {
            function.apply("useless_param");
            Assert.fail("function is supposed to throw UnsupportedOperationException");
        } catch (UnsupportedOperationException ex) {
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
