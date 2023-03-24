/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etu2001.framework;

/**
 *
 * @author ITU
 */
public class Mapping {
    private String classe;
    private String method;

    public String getClasse(){
        return classe;
    }

    public String getMethod(){
        return method;
    }

    public void setClasse(String classe){
        this.classe = classe;
    }

    public void setMethod(String method){
        this.method  = method;
    }
}
