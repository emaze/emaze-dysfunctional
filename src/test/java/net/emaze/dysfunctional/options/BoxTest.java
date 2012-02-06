package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.dispatching.delegates.ConstantDelegate;
import net.emaze.dysfunctional.dispatching.delegates.Identity;
import net.emaze.dysfunctional.testing.O;
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
    public void boxesWithEqualContentHaveSameHashcode() {
        Box<Banana> former = new Box<Banana>();
        Box<Banana> latter = new Box<Banana>();
        Assert.assertEquals(former.hashCode(), latter.hashCode());
    }

    @Test
    public void boxWithNoValueIsEmpty() {
        Box<O> box = Box.empty();
        Assert.assertTrue(box.isEmpty());
    }

    @Test
    public void emptyBoxHasNoContent() {
        Box<O> box = Box.empty();
        Assert.assertFalse(box.hasContent());
    }

    @Test
    public void boxWithValueIsNotEmpty() {
        Box<O> box = Box.of(O.ONE);
        Assert.assertFalse(box.isEmpty());
    }

    @Test
    public void loadedBoxHasContent() {
        Box<O> box = Box.of(O.ONE);
        Assert.assertTrue(box.hasContent());
    }

    @Test
    public void unloadingBoxLeadsToEmpty() {
        Box<O> box = Box.of(O.ONE);
        box.unload();
        Assert.assertTrue(box.isEmpty());
    }

    @Test
    public void fmappingAnEmptyBoxYieldsEmptyBox() {
        final Box<O> box = Box.empty();
        final Box<Integer> mapped = box.fmap(new ConstantDelegate<Integer, O>(1));
        Assert.assertEquals(mapped, Box.<Integer>empty());
    }

    @Test
    public void fmappingLoadedBoxYieldsEmptyBox() {
        final Box<Integer> box = Box.of(1);
        final Box<Integer> mapped = box.fmap(new Identity<Integer>());
        Assert.assertEquals(mapped, box);
    }

    public static class Dick {
    }

    public static class Banana {
    }
}
