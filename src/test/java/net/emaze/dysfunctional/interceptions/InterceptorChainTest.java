package net.emaze.dysfunctional.interceptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.emaze.dysfunctional.delegates.Delegate;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class InterceptorChainTest {

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


    public static class BucketFillingInterceptor implements Interceptor<String> {

        private final int id;
        private final List<Integer> bucket;

        public BucketFillingInterceptor(int id, List<Integer> bucket) {
            this.id = id;
            this.bucket = bucket;
        }

        @Override
        public void before(String value) {
            bucket.add(id);
        }

        @Override
        public void after(String value) {
            bucket.add(id);
        }
    }

    public static class ThrowingInterceptor implements Interceptor<String> {

        @Override
        public void before(String value) {
            throw new IllegalStateException();
        }

        @Override
        public void after(String value) {
            throw new IllegalStateException();
        }
    }

    public static class BucketFillingDelegate implements Delegate<String, String> {

        private final int id;
        private final List<Integer> bucket;

        public BucketFillingDelegate(int id, List<Integer> bucket) {
            this.id = id;
            this.bucket = bucket;
        }

        @Override
        public String perform(String value) {
            bucket.add(id);
            return value;
        }
    }
}
