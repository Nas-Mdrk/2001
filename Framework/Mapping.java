/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etu2001.framework;

/**
 *
 * @author itu
 */
public class Mapping {
    String className;
    String Method;
    
    public void setclassName(String h){
        this.className=h;
    }
    
    public void setMethod(String a){
        this.Method=a;
    }
    
    public String getclassName(){
        return this.className;
    }
    
    public String getMethod(){
        return this.Method;
    }
    
}
