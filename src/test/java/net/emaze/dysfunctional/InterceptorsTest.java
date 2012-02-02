package net.emaze.dysfunctional;

import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.delegates.FirstParam;
import net.emaze.dysfunctional.dispatching.delegates.FirstParamOfThree;
import net.emaze.dysfunctional.dispatching.delegates.Identity;
import net.emaze.dysfunctional.dispatching.delegates.TernaryDelegate;
import net.emaze.dysfunctional.interceptions.BinaryInterceptor;
import net.emaze.dysfunctional.interceptions.Interceptor;
import net.emaze.dysfunctional.interceptions.TernaryInterceptor;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author rferranti
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    InterceptorsTest.Unary.class,
    InterceptorsTest.Binary.class,
    InterceptorsTest.Ternary.class
})
public class InterceptorsTest {

    public static class Unary {

        NullInterceptor<O> INTERCEPTOR = new NullInterceptor<O>();

        @Test
        public void canInterceptOne() {
            final Delegate<O, O> intercepted = Interceptors.intercept(new Identity<O>(), INTERCEPTOR);
            Assert.assertNotNull(intercepted);
        }

        @Test
        public void canInterceptTwo() {
            Delegate<O, O> intercepted = Interceptors.intercept(new Identity<O>(), INTERCEPTOR, INTERCEPTOR);
            Assert.assertNotNull(intercepted);
        }

        @Test
        public void canInterceptThree() {
            Delegate<O, O> intercepted = Interceptors.intercept(new Identity<O>(), INTERCEPTOR, INTERCEPTOR, INTERCEPTOR);
            Assert.assertNotNull(intercepted);
        }

        @Test
        public void canInterceptIterator() {
            Delegate<O, O> intercepted = Interceptors.intercept(new Identity<O>(), Iterations.iterator(INTERCEPTOR));
            Assert.assertNotNull(intercepted);
        }

        @Test
        public void canInterceptIterable() {
            Delegate<O, O> intercepted = Interceptors.intercept(new Identity<O>(), Iterations.iterable(INTERCEPTOR));
            Assert.assertNotNull(intercepted);
        }

        @Test(expected = IllegalArgumentException.class)
        public void interceptingNullIterableYieldsException() {
            final Iterable<NullInterceptor<O>> iterable = null;
            Interceptors.intercept(new Identity<O>(), iterable);
        }
    }

    public static class Binary {

        BinaryNullInterceptor<O, O> INTERCEPTOR = new BinaryNullInterceptor<O, O>();

        @Test
        public void canInterceptOne() {
            final BinaryDelegate<O, O, O> intercepted = Interceptors.intercept(new FirstParam<O, O>(), INTERCEPTOR);
            Assert.assertNotNull(intercepted);
        }

        @Test
        public void canInterceptTwo() {
            final BinaryDelegate<O, O, O> intercepted = Interceptors.intercept(new FirstParam<O, O>(), INTERCEPTOR, INTERCEPTOR);
            Assert.assertNotNull(intercepted);
        }

        @Test
        public void canInterceptThree() {
            final BinaryDelegate<O, O, O> intercepted = Interceptors.intercept(new FirstParam<O, O>(), INTERCEPTOR, INTERCEPTOR, INTERCEPTOR);
            Assert.assertNotNull(intercepted);
        }

        @Test
        public void canInterceptIterator() {
            final BinaryDelegate<O, O, O> intercepted = Interceptors.intercept(new FirstParam<O, O>(), Iterations.iterator(INTERCEPTOR));
            Assert.assertNotNull(intercepted);
        }

        @Test
        public void canInterceptIterable() {
            final BinaryDelegate<O, O, O> intercepted = Interceptors.intercept(new FirstParam<O, O>(), Iterations.iterable(INTERCEPTOR));
            Assert.assertNotNull(intercepted);
        }

        @Test(expected = IllegalArgumentException.class)
        public void interceptingNullIterableYieldsException() {
            final Iterable<BinaryInterceptor<O, O>> iterable = null;
            Interceptors.intercept(new FirstParam<O, O>(), iterable);
        }
    }

    public static class Ternary {

        TernaryNullInterceptor<O, O, O> INTERCEPTOR = new TernaryNullInterceptor<O, O, O>();

        @Test
        public void canInterceptOne() {
            final TernaryDelegate<O, O, O, O> intercepted = Interceptors.intercept(new FirstParamOfThree<O, O, O>(), INTERCEPTOR);
            Assert.assertNotNull(intercepted);
        }

        @Test
        public void canInterceptTwo() {
            final TernaryDelegate<O, O, O, O> intercepted = Interceptors.intercept(new FirstParamOfThree<O, O, O>(), INTERCEPTOR, INTERCEPTOR);
            Assert.assertNotNull(intercepted);
        }

        @Test
        public void canInterceptThree() {
            final TernaryDelegate<O, O, O, O> intercepted = Interceptors.intercept(new FirstParamOfThree<O, O, O>(), INTERCEPTOR, INTERCEPTOR, INTERCEPTOR);
            Assert.assertNotNull(intercepted);
        }

        @Test
        public void canInterceptIterator() {
            final TernaryDelegate<O, O, O, O> intercepted = Interceptors.intercept(new FirstParamOfThree<O, O, O>(), Iterations.iterator(INTERCEPTOR));
            Assert.assertNotNull(intercepted);
        }

        @Test
        public void canInterceptIterable() {
            final TernaryDelegate<O, O, O, O> intercepted = Interceptors.intercept(new FirstParamOfThree<O, O, O>(), Iterations.iterable(INTERCEPTOR));
            Assert.assertNotNull(intercepted);
        }

        @Test(expected = IllegalArgumentException.class)
        public void interceptingNullIterableYieldsException() {
            final Iterable<TernaryInterceptor<O, O, O>> iterable = null;
            Interceptors.intercept(new FirstParamOfThree<O, O, O>(), iterable);
        }
    }

    public static class NullInterceptor<T> implements Interceptor<T> {

        @Override
        public void before(T value) {
        }

        @Override
        public void after(T value) {
        }
    }

    public static class BinaryNullInterceptor<T1, T2> implements BinaryInterceptor<T1, T2> {

        @Override
        public void before(T1 first, T2 second) {
        }

        @Override
        public void after(T1 first, T2 second) {
        }
    }

    public static class TernaryNullInterceptor<T1, T2, T3> implements TernaryInterceptor<T1, T2, T3> {

        @Override
        public void before(T1 first, T2 second, T3 third) {
        }

        @Override
        public void after(T1 first, T2 second, T3 third) {
        }
    }
}