## Build and deploy

```
mvn clean package org.apache.maven.plugins:maven-deploy-plugin:2.7:deploy -DaltDeploymentRepository=mvn-intranet::default::http://<NEXUS-SERVER>/nexus/content/repositories/releases
```

## Changes

### 5.5

Added the following methods to `Iterations` façade:

* `Iterator<T> iterator(Provider<Maybe<T>> generator)` to generate an `Iterator` from a `Provider` of optional values:
* `Iterator<T> iterator(T seed, Delegate<Maybe<T>, T> generator)` to generate an `Iterator` by iterative application of a generating function:

```java
Iterations.iterator(0, new Delegate<Maybe<Integer>, Integer>() {
    public Maybe<Integer> perform(Integer value) {
        return value < 5
            ? Maybe.nothing()
            : Maybe.just(value + 1);
    }
}); // yields [0, 1, 2, 3, 4]
```
