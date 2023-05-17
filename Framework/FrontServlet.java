/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etu2001.framework.servlet;
import model.*;
import etu2001.framework.Annotation.*;
import etu2001.framework.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.*;
import java.lang.reflect.*;
import java.rmi.ServerException;

/**
 *
 * @author itu
 */
public class FrontServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    HashMap<String,Mapping> MappingUrls = new HashMap<>();
    public static List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                String className = packageName + "." + file.getName().substring(0, file.getName().length() - 6);
                Class<?> clazz = Class.forName(className);
                classes.add(clazz);
            }
        }
        return classes;
    }
    
    @Override
    public void init() throws ServletException{
        File f = null;
        try{
            f = new File("C:\\Program Files\\Apache Software Foundation\\Tomcat 10.0\\webapps\\framework\\WEB-INF\\classes\\etu2001\\framework");
            List<Class<?>> classes = FrontServlet.findClasses(f,"etu2001.framework");
            for(int i = 0;i<classes.size();i++){
                Class<?> clazz = classes.get(i);
                Method[] methods = clazz.getDeclaredMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(etu2001.framework.Annotation.Annotation.class)) {
                        Annotation annotation = method.getAnnotation(etu2001.framework.Annotation.Annotation.class);
                        String url = annotation.url();
                        Mapping newmap = new Mapping();
                        newmap.setclassName(clazz.getName());
                        newmap.setMethod(method.getName());
                        MappingUrls.put(url,newmap);
                    }
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<h3>Servlet UrlController at " + request.getServletPath() + "</h3>");
        out.println("<p>" + request.getContextPath() + "</p>");
        out.println(MappingUrls.size());
        for(Map.Entry<String, Mapping> entry : MappingUrls.entrySet()){
            String url = entry.getKey();
             out.println("<p>" + url + "</p>");
            Mapping map = entry.getValue();
             if(url.equals(request.getServletPath())){ 
                 out.println("<p>URL : " + url + "</p>");
                out.println("<p>" + map.getMethod() + "</p>");
                try{
                    Class<?> cla = Class.forName(map.getclassName());
                     Method m = cla.getMethod(map.getMethod());
                    ModelView model = (ModelView)(m.invoke(cla.newInstance()));
                     RequestDispatcher dispatch = request.getRequestDispatcher(model.getView());
                     dispatch.forward(request,response);
                 }
                 catch(Exception e){
                    out.println("<p>" + e + "</p>");
                 }
             }
         }
           
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
