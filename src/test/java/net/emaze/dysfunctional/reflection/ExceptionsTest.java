package net.emaze.dysfunctional.reflection;

import java.lang.reflect.InvocationTargetException;
import java.util.NoSuchElementException;
import org.junit.Test;
import org.junit.Assert;

/**
 *
 * @author rferranti
 */
public class ExceptionsTest {

    @Test
    public void canUnwrapException() {
        Exception cause = new RuntimeException();
        Exception ite = new InvocationTargetException(cause);
        Throwable got = Exceptions.unwrap(ite, InvocationTargetException.class);
        Assert.assertTrue(got instanceof RuntimeException);
    }

    @Test
    public void unwrapReturnsSourceIfNotWrapped() {
        Exception ite = new RuntimeException();
        Throwable got = Exceptions.unwrap(ite, InvocationTargetException.class);
        Assert.assertTrue(got instanceof RuntimeException);
    }

    @Test
    public void unwrapReturnsNullIfNoCauseInAnUnwrappable() {
        Exception ite = new RuntimeException();
        Throwable got = Exceptions.unwrap(ite, RuntimeException.class);
        Assert.assertNull(got);
    }

    @Test
    public void canFindInThrowable() {
        NoSuchElementException inner = new NoSuchElementException("the interesting exception");
        RuntimeException re = new RuntimeException(inner);
        IllegalStateException ise = new IllegalStateException(re);
        Assert.assertEquals(inner, Exceptions.findInThrowable(ise, NoSuchElementException.class));
    }

    @Test
    public void canFindInItself() {
        NoSuchElementException inner = new NoSuchElementException("the interesting exception");
        Assert.assertEquals(inner, Exceptions.findInThrowable(inner, NoSuchElementException.class));
    }

    @Test
    public void returnsNullIfNotFound() {
        RuntimeException re = new RuntimeException("an exception with no cause");
        Assert.assertNull(Exceptions.findInThrowable(re, NoSuchElementException.class));
    }

    @Test
    public void facadeIsNotFinal() {
        new Exceptions() {
        };
    }
}
