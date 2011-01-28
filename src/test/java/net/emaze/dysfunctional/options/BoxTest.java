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
        final Box<Banana> bento = new Box<Banana>();
        bento.setContent(banana);
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

    @Test
    public void nonEmptyContainersWithDifferentContentAreNotEqual() {
        final Box<Banana> myBento = new Box<Banana>();
        final Box<Dick> yourBento = new Box<Dick>();
        yourBento.setContent(new Dick());
        myBento.setContent(new Banana());
        Assert.assertFalse(yourBento.equals(myBento));
    }

    @Test
    public void emptyContainersAreEqual() {
        Box<Banana> myBento = new Box<Banana>();
        Box<Dick> yourBento = new Box<Dick>();
        Assert.assertEquals(yourBento, myBento);
    }

    public static class Dick {
    }

    public static class Banana {
    }
}
