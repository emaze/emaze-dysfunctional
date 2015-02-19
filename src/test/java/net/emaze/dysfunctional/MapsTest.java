package net.emaze.dysfunctional;

import java.util.HashMap;
import java.util.Map;
import net.emaze.dysfunctional.Maps.Nested;
import net.emaze.dysfunctional.casts.Vary;
import net.emaze.dysfunctional.collections.HashMapFactory;
import net.emaze.dysfunctional.collections.builders.MapBuilder;
import net.emaze.dysfunctional.collections.builders.NestedMapBuilder;
import java.util.function.Supplier;
import net.emaze.dysfunctional.order.ComparableComparator;
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
    MapsTest.Builder.class,
    MapsTest.NestedBuilder.class,
    MapsTest.Facades.class
})
public class MapsTest {

    private static final Map<String, Object> A_MAP = new HashMap<String, Object>();
    private static final Supplier<Map<String, Object>> PROVIDER = Compositions.compose(new Vary<HashMap<String, Object>, Map<String, Object>>(), new HashMapFactory<String, Object>());

    public static class Builder {

        @Test
        public void canCreateBuilderFromMap() {
            final MapBuilder<String, Object> builder = Maps.from(A_MAP);
            Assert.assertNotNull(builder);
        }

        @Test
        public void canCreateBuilderFromProvider() {
            final MapBuilder<String, Object> builder = Maps.from(PROVIDER);
            Assert.assertNotNull(builder);
        }

        @Test
        public void canCreateEmptyUnsortedBuilder() {
            final MapBuilder<String, Object> builder = Maps.builder();
            Assert.assertNotNull(builder);
        }

        @Test
        public void canCreateEmptyLinkedBuilder() {
            final MapBuilder<String, Object> builder = Maps.linked();
            Assert.assertNotNull(builder);
        }

        @Test
        public void canCreateEmptyTreeBuilder() {
            final MapBuilder<String, Object> builder = Maps.tree();
            Assert.assertNotNull(builder);
        }

        @Test
        public void canCreateEmptyTreeWithComparatorBuilder() {
            final MapBuilder<String, Object> builder = Maps.tree(new ComparableComparator<String>());
            Assert.assertNotNull(builder);
        }
    }

    public static class NestedBuilder {

        @Test
        public void canCreateBuilderFromProvider() {
            final NestedMapBuilder<String> builder = Nested.from(PROVIDER);
            Assert.assertNotNull(builder);
        }

        @Test
        public void canCreateEmptyBuilder() {
            final NestedMapBuilder<String> builder = Nested.builder();
            Assert.assertNotNull(builder);
        }

        @Test
        public void canCreateEmptyLinkedBuilder() {
            final NestedMapBuilder<String> builder = Nested.linked();
            Assert.assertNotNull(builder);
        }

        @Test
        public void canCreateEmptyTreeBuilder() {
            final NestedMapBuilder<String> builder = Nested.tree();
            Assert.assertNotNull(builder);
        }

        @Test
        public void canCreateEmptyTreeWithComparatorBuilder() {
            final NestedMapBuilder<String> builder = Nested.tree(new ComparableComparator<String>());
            Assert.assertNotNull(builder);
        }
    }

    public static class Facades {

        @Test
        public void mapsIsNotFinal() {
            new Maps() {
            };
        }

        @Test
        public void nestedIsNotFinal() {
            new Nested() {
            };
        }
    }
}
