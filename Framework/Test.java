/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etu2001.framework;
import model.*;
import etu2001.framework.Annotation.*;
/**
 *
 * @author itu
 */
public class Test {
    String nom;
    public void setNom(String n){
        this.nom=n;
    }
    @Annotation(url="/adresse")
    public String getNom(){
        return " Adrienna";
    }
	@Annotation(url = "/adresse-maison")
    public ModelView getAdresse(){
        ModelView ok = new ModelView();
		String m = "Milay";
		String b = "Bonjour";
		ok.addData(m,b);
        ok.setView("maison.jsp");
        return ok;
    }

    @Annotation(url = "/test1")
    public ModelView getTest1(){
        ModelView ok = new ModelView();
        ok.setView("maison.jsp");
        return ok;
    } 

    @Annotation(url = "/test2")
    public ModelView getTest2(){
        ModelView ok = new ModelView();
        ok.setView("maison.jsp");
        return ok;
    }
    
    
}
