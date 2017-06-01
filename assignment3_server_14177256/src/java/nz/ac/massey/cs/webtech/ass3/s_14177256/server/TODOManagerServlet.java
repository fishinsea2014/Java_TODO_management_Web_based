/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.massey.cs.webtech.ass3.s_14177256.server;

import java.io.IOException;
import java.io.PrintWriter;

//import javax.xml.parsers.*;

//import org.apache.log4j.Logger;

import org.json.*;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.jdom2.xpath.XPathBuilder;

//import org.w3c.dom.Document;
//import org.w3c.dom.Element;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;

/**
 *
 * @author QSG
 */
public class TODOManagerServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    private static List<Todo> todos=new ArrayList<Todo>();
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TODOManagerServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TODOManagerServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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

     
    
    private List<JSONObject> getAll() throws Exception{
        ArrayList<JSONObject> todos=new ArrayList<JSONObject>();
        SAXBuilder builder= new SAXBuilder();
        Document document = builder.build(".\\web\\data.xml");
        Element root=document.getRootElement();
        List ts=root.getChildren("todo");
        for (int i=0;i<todos.size();i++){
            Element t=(Element) ts.get(i);
            JSONObject nt=new JSONObject();
            nt.put("id",Integer.parseInt(t.getChildText("id")));
            nt.put("description",t.getChildText("description"));
            todos.add(nt);
//            Todo t=(Todo)todo;
            System.out.println(t.getName()+" : "+t.getChildText("id")+t.getChildText("description"));
        }
//        System.out.println(l.get(0).getValue());
        return todos;
    
    }
}
