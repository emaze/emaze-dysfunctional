package net.emaze.dysfunctional.interceptions;

import java.util.ArrayList;
import java.util.List;
import net.emaze.dysfunctional.delegates.BinaryDelegate;
import net.emaze.dysfunctional.tuples.Pair;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class BinaryInterceptorAdapterTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingAdapterWithNullAdapteeYieldsException() {
        new BinaryInterceptorAdapter<Object, Object, Object>(null, new FirstParam<Object, Object>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingAdapterWithNullInnerDelegateYieldsException() {
        new BinaryInterceptorAdapter<Object, Object, Object>(new BinaryNoopInterceptor<Object, Object>(), null);
    }

    @Test
    public void beforeAndAfterAreCalled() {
        final BucketFillingBinaryInterceptor<String,String> interceptor = new BucketFillingBinaryInterceptor<String, String>();
        final BinaryDelegate<String, String, String> nestedDelegate = new FirstParam<String, String>();
        final BinaryDelegate<String, String, String> delegate = new BinaryInterceptorAdapter<String, String, String>(interceptor, nestedDelegate);
        delegate.perform("useless_param", "useless_param");
        Assert.assertTrue(interceptor.getBeforeBucket().size() == 1 && interceptor.getAfterBucket().size() == 1);
    }

    @Test
    public void beforeAndAfterAreCalledInCaseOfException() {
        final BucketFillingBinaryInterceptor<String,String> interceptor = new BucketFillingBinaryInterceptor<String, String>();
        final BinaryDelegate<String, String, String> delegate = new BinaryInterceptorAdapter<String, String, String>(interceptor, new BinaryDelegate<String, String, String>() {

            @Override
            public String perform(String former, String latter) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        try {
            delegate.perform("useless_param", "useless_param");
            Assert.fail("delegate is supposed to throw UnsupportedOperationException");
        } catch (UnsupportedOperationException ex) {
            Assert.assertTrue(interceptor.getBeforeBucket().size() == 1 && interceptor.getAfterBucket().size() == 1);
        }
    }

    public static class FirstParam<T1, T2> implements BinaryDelegate<T1, T1, T2> {

        @Override
        public T1 perform(T1 former, T2 latter) {
            return former;
        }
    }

    public static class BinaryNoopInterceptor<T1, T2> implements BinaryInterceptor<T1, T2> {

        @Override
        public void before(T1 first, T2 second) {
        }

        @Override
        public void after(T1 first, T2 second) {
        }
    }

    public static class BucketFillingBinaryInterceptor<T1, T2> implements BinaryInterceptor<T1, T2> {

        private final List<Pair<T1, T2>> beforeBucket = new ArrayList<Pair<T1, T2>>();
        private final List<Pair<T1, T2>> afterBucket = new ArrayList<Pair<T1, T2>>();

        @Override
        public void before(T1 first, T2 second) {
            beforeBucket.add(new Pair(first, second));
        }

        @Override
        public void after(T1 first, T2 second) {
            afterBucket.add(new Pair(first, second));
        }

        public List<Pair<T1, T2>> getAfterBucket() {
            return afterBucket;
        }

        public List<Pair<T1, T2>> getBeforeBucket() {
            return beforeBucket;
        }
        
    }
}
