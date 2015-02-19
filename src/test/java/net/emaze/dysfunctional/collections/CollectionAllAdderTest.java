package net.emaze.dysfunctional.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class CollectionAllAdderTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingCollecitonAdderWithNullCollectionYieldsException() {
        new CollectionAllAdder<Collection<O>, O>(null);
    }

    @Test
    public void canAddToCollection() {
        final List<O> bucket = new ArrayList<O>();
        new CollectionAllAdder<List<O>, O>(bucket).apply(Arrays.asList(O.ONE));
        Assert.assertEquals(1, bucket.size());
    }

    @Test(expected = ClassCastException.class)
    public void passingWrongTypeToErasureYieldsException() {
        final List<O> bucket = new ArrayList<O>();
        Function d = new CollectionAllAdder<List<O>, O>(bucket);
        d.apply(new Object());
    }
}
