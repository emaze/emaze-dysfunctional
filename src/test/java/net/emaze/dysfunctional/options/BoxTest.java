package net.emaze.dysfunctional.options;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class BoxTest {

    @Test
    public void accessorUnwrapsFromTheBox() {
        final Banana banana = new Banana();
        final Box<Banana> bento = Box.of(banana);
        Assert.assertEquals(banana, bento.getContent());
    }

    @Test
    public void emptyContainerIsNotEqualToNonBoxObjects() {
        final Box<Banana> bento = new Box<Banana>();
        Assert.assertFalse(bento.equals(null));
    }

    @Test
    public void canGetStringReprOfAnEmptyBox() {
        Assert.assertNotNull(new Box<Banana>().toString());
    }

    /**
     * you are what you eat.
     */
    @Test
    public void nonEmptyContainersWithDifferentContentAreNotEqual() {
        final Box<Banana> myBento = Box.of(new Banana());
        final Box<Dick> yourBento = Box.of(new Dick());
        Assert.assertFalse(yourBento.equals(myBento));
    }

    @Test
    public void emptyContainersAreEqual() {
        Box<Banana> myBento = new Box<Banana>();
        Box<Dick> yourBento = new Box<Dick>();
        Assert.assertEquals(yourBento, myBento);
    }

    @Test
    public void boxesWithEqualContentHaveSameHashcode(){
        Box<Banana> former = new Box<Banana>();
        Box<Banana> latter = new Box<Banana>();
        Assert.assertEquals(former.hashCode(), latter.hashCode());
    }

    public static class Dick {
    }

    public static class Banana {
    }
}
