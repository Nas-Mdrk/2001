/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etu2001.framework.annotation;
import java.lang.annotation.*;
import java.lang.reflect.*;
@Retention (RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Annotation{
    String url();
}

