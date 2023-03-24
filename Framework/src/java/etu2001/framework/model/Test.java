/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etu2001.framework.model;

import etu2001.framework.annotation.Annotation;
/**
 *
 * @author ITU
 */
public class Test {
     private String adresse;
    

    @Annotation(url = "/adresse")
    public String getAdresse(){
        return "haha";
    }

    @Annotation(url = "/test")
    public String getTest1(){
        return "huhu";
    } 

}
