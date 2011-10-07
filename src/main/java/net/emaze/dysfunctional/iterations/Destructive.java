package net.emaze.dysfunctional.iterations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 *                          "Now I am become Death, the destroyer of iterators."
 * Inverse King Midas behaviour. Anything the annotated class touches becomes shit.
 * Iterators, Collections, Streams, Sequences will get maimed without mercy by method
 * or class annotated by me.
 * 
 *                             ____
 *                     __,-~~/~    `---.
 *                   _/_,---(      ,    )
 *               __ /        <    /   )  \___
 *- ------===;;;'====------------------===;;;===----- -  -
 *                  \/  ~"~"~"~"~"~\~"~)~"/
 *                  (_ (   \  (     >    \)
 *                   \_( _ <         >_>'
 *                      ~ `-i' ::>|--"
 *                          I;|.|.|
 *                         <|i::|i|`.
 *                        (` ^'"`-' ")
 *------------------------------------------------------------------
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface Destructive {
}
