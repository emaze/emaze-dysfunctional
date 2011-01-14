package net.emaze.dysfunctional;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import net.emaze.dysfunctional.delegates.Delegate;
import net.emaze.dysfunctional.delegates.Predicate;
import net.emaze.dysfunctional.iterations.Iterations;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class DysTest {

    @Test
    public void everyLibraryFacadeIsForwardedInDys() {
        final Set<String> declaredFacadesNames = new HashSet<String>();
        for (Class<?> klass : Dys.class.getDeclaredClasses()) {
            declaredFacadesNames.add(klass.getSimpleName());
        }
        final Set<String> expectedFacades = enumerateFacades();
        expectedFacades.remove("dbc");
        expectedFacades.remove("Dys");
        Assert.assertEquals(expectedFacades, declaredFacadesNames);
    }

    private Set<String> enumerateFacades() {
        final Iterator<String> classNames = Iterations.transform(new JavaFilesIn("src/main/java").enumerate(), new Delegate<String, String>() {

            @Override
            public String perform(String file) {
                return file.substring(0, file.length() - 5).replace('/', '.');
            }
        });
        final List<String> result = new ArrayList<String>();
        for (String className : Iterations.oneTime(classNames)) {
            try {
                final Class<?> k = Class.forName(className);
                if (Modifier.isAbstract(k.getModifiers()) && !k.isInterface() && Iterations.every(k.getDeclaredMethods(), new MethodIsStatic())) {
                    result.add(k.getSimpleName());
                }
            } catch (Throwable t) {
                System.err.println("error:" + t);
            }
        }
        return new HashSet<String>(result);
    }

    public static class JavaFilesIn {

        private String relativeRoot;
        private Deque<File> toBeDone = new LinkedList<File>();

        public JavaFilesIn(String relativeRoot) {
            this.toBeDone.push(new File(relativeRoot));
            this.relativeRoot = relativeRoot;
        }

        public List<String> enumerate() {
            List<String> output = new ArrayList<String>();
            while (!toBeDone.isEmpty()) {
                for (File file : toBeDone.pop().listFiles()) {
                    if (file.isDirectory()) {
                        toBeDone.push(file);
                    }
                    if (file.isFile() && file.getPath().endsWith(".java")) {
                        output.add(file.getPath().substring(relativeRoot.length() + 1));
                    }
                }
            }
            return output;
        }
    }

    public static class MethodIsStatic implements Predicate<Method> {

        @Override
        public boolean test(Method m) {
            return Modifier.isStatic(m.getModifiers());
        }
    }
}
