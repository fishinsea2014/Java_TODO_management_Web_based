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
import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.servlet.annotation.WebServlet;

/**
 *
 * @author QSG
 */
// @WebServlet(name = "TODOManagerServlet", urlPatterns = {"/todos", "/todos/*"})
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
//        processRequest(request, response);
        String requestUri = request.getRequestURI();
        String sPath = request.getServletPath();
        PrintWriter out = response.getWriter();

        String search = request.getParameter("search");
        response.setContentType("application/json");
//        out.print(request);
        if (search != null) {
            out.println("do search");
            out.println("===============================");
        } else {
            String[] ps = requestUri.split("/");
            for (String i : ps) {
                System.out.println(i + ",");
            }
            if (ps.length == 3) {
                try {
//                    out.println("do getElements() "+sPath);
                    out.println(getElements("all").toString());
                } catch (Exception ex) {
                    Logger.getLogger(TODOManagerServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (ps.length == 4) {
                
                try {
                    //                out.print("do fetch todo with id:" + ps[ps.length - 1]);
                    JSONArray rsltArray=getElements(ps[3]);
                    if (rsltArray!=null)  out.print(rsltArray.toString());
                    else out.print("Nothing found");
                } catch (Exception ex) {
                    Logger.getLogger(TODOManagerServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                out.print("Wrong request.");
            }
        }
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
        JSONArray todoJSONArray = null;
        try {
            //        processRequest(request, response);
            todoJSONArray=getElements("all");
        } catch (Exception ex) {
            Logger.getLogger(TODOManagerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSONObject newItem=new JSONObject();
        int id=Integer.parseInt(request.getParameter("id"));
        String description=request.getParameter("description");
        try {
            newItem.put("id", id);
            newItem.put("description", description);
        } catch (JSONException ex) {
            Logger.getLogger(TODOManagerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        todoJSONArray.put(newItem);
        PrintWriter out = response.getWriter();
        out.print(todoJSONArray.toString());
        
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

    private JSONArray getElements(String ids) throws Exception {
        JSONArray todos = new JSONArray();
        SAXBuilder builder = new SAXBuilder();
        String filename = this.getServletContext().getRealPath("/data.xml");
        System.out.print(filename);
        Document document = builder.build(filename);
        Element root = document.getRootElement();
        List ts = root.getChildren("todo");
        if (ids == "all") {
            for (int i = 0; i < ts.size(); i++) {
                Element t = (Element) ts.get(i);
                JSONObject nt = new JSONObject();
                nt.put("id", Integer.parseInt(t.getChildText("id")));
                nt.put("description", t.getChildText("description"));
                todos.put(nt);
//            Todo t=(Todo)todo;
                System.out.println(t.getName() + " : " + t.getChildText("id") + t.getChildText("description"));
            }
        }else{
                int id=Integer.parseInt(ids);
                for (int i = 0; i < ts.size(); i++) {
                    Element t=(Element)ts.get(i);
                    if (id==Integer.parseInt(t.getChildText("id"))){
                        JSONObject nt = new JSONObject();
                        nt.put("id", Integer.parseInt(t.getChildText("id")));
                        nt.put("description", t.getChildText("description"));
                        todos.put(nt);
                    }
             }
        }
//        System.out.println(l.get(0).getValue());
        return todos;
    }
    
    
}
