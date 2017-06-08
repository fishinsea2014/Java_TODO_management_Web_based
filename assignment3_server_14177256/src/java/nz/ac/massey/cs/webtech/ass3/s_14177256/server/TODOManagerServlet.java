/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.massey.cs.webtech.ass3.s_14177256.server;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.json.*;

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

    private static List<Element> xmlList;
    private  static Element root;
//    private static  String filename;
    private Document document;
//    private static JSONArray todos;
//    private static JSONObject todo = new JSONObject();
//    private static SAXBuilder builder;

    private JSONArray getAll() throws Exception {
        JSONArray returArray = getElements("all");
        return returArray;
    }

    private boolean insert(JSONObject todo) {
        try {
            String id = todo.getString("id").toString();
            String description = todo.getString("description");
            insertTodo(id, description);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean update(JSONObject todo) throws Exception {
        readXmlFile();
        try {
            String id = todo.getString("id").toString();
            String description = todo.getString("description");
            updateTodo(id, description);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean delete(JSONObject todo) throws Exception {
        String id = todo.getString("id");
        if (deleteTodo(id)) {
            return true;
        }
        return false;
    }

    private JSONObject getById(String id) throws Exception {
        JSONArray rtrn = getElements(id);
        JSONObject r = null;
        try {
            return rtrn.getJSONObject(0);
        } catch (Exception e) {
            return null;
        }
    }

    private JSONArray search(String keyword) throws Exception {
        String filename = this.getServletContext().getRealPath("/data.xml");

        readXmlFile();
        JSONArray rtrn = new JSONArray();
        for (int i = 0; i < xmlList.size(); i++) {
            Element t = (Element) xmlList.get(i);
            if (t.getChildText("description").indexOf(keyword) > 0) {
                JSONObject nt = new JSONObject();
                nt.put("id", Integer.parseInt(t.getChildText("id")));
                nt.put("description", t.getChildText("description"));
                rtrn.put(nt);
            }
        }
        return rtrn;
    }

    //Basic functions to operate the xml data.
    private void readXmlFile() throws Exception {
        SAXBuilder builder = new SAXBuilder();
//        xmlList=new ArrayList<Element>();
        String filename = this.getServletContext().getRealPath("/data.xml");
        System.out.print(filename);
        document = null;
        try {
            document = builder.build(filename);
        } catch (JDOMException ex) {
            Logger.getLogger(TODOManagerServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TODOManagerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        root = document.getRootElement();
        xmlList = root.getChildren("todo");
        
    }

    private void saveXml() {
        String filename = this.getServletContext().getRealPath("/data.xml");
        XMLOutputter XMLOut = new XMLOutputter();
        try {
            XMLOut.output(document, new FileOutputStream(filename));
        } catch (Exception ex) {
            Logger.getLogger(TODOManagerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void insertTodo(String id, String description) {
        String filename = this.getServletContext().getRealPath("/data.xml");
        filename = this.getServletContext().getRealPath("/data.xml");
        readXmlFile();
//        System.out.println("insertodo---id is:" + id + "desc is" + description);
        Element newElement = new Element("todo");
        newElement.addContent(new Element("id").setText(id));
        newElement.addContent(new Element("description").setText(description));
        xmlList.add(newElement);
        saveXml();
    }

    private boolean deleteTodo(String id) {
        int index = -1;
        for (int i = 0; i < xmlList.size(); i++) {
            String ids = xmlList.get(i).getChildText("id");
            System.out.println("id is:" + ids);
            if (ids.equals(id)) {
                index = i;
            }
        }
        if (index != -1) {
            xmlList.remove(index);
            return true;
        } else {
            return false;
        }
    }

    private boolean updateTodo(String id, String updateString) {
        for (Element e : xmlList) {
            if (e.getChildText("id").equals(id)) {
                e.getChild("description").setText(updateString);
                return true;
            }
        }
        return false;
    }

    //Useless method
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
//        String sPath = request.getServletPath();
        PrintWriter out = response.getWriter();

        String srch = request.getParameter("search");
        response.setContentType("application/json");
//        out.print(request);
        if (srch != null) {
            try {
                out.println(search(srch));
            } catch (Exception ex) {
                Logger.getLogger(TODOManagerServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            String[] ps = requestUri.split("/");
            for (String i : ps) {
                System.out.println(i + ",");
            }
            if (ps.length == 3) {
                try {
//                    out.println("do getElements() "+sPath);
                    out.println(getAll().toString());
                } catch (Exception ex) {
                    Logger.getLogger(TODOManagerServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (ps.length == 4) {

                try {
                    //                out.print("do fetch todo with id:" + ps[ps.length - 1]);
//                    JSONArray rsltArray = getElements(ps[3]);
                    JSONObject rslt = getById(ps[3]);

                    if (rslt != null) {
                        out.print(rslt.toString());
                    } else {
                        out.print("Nothing found");
                    }
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
        PrintWriter out = response.getWriter();
        try {
            String id = request.getParameter("id");
            String description = request.getParameter("description");
            int idN = Integer.parseInt(id);

            JSONObject todo=new JSONObject();
            todo.put("id", id);
            todo.put("description", description);
            System.out.println("post params: id: " + id + ", desc" + description);

            boolean success = insert(todo);
            if (success) {
                out.print("post successfully.");
            } else {
                out.print("post failed.");
            }

        } catch (Exception ex) {
            Logger.getLogger(TODOManagerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

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
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
        String filename = this.getServletContext().getRealPath("/data.xml");
        try {
            readXmlFile();
        } catch (Exception ex) {
            Logger.getLogger(TODOManagerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        String requestUri = req.getRequestURI();
        String sPath = req.getServletPath();
        PrintWriter out = resp.getWriter();

        resp.setContentType("text/html;charset=UTF-8");
//      
        String[] ps = requestUri.split("/");
        for (String i : ps) {
            System.out.println(i + ",");
        }
        if (ps.length == 4) {
            try {
                String delId = ps[3];
                JSONObject t = new JSONObject();
                t.put("id", delId);
                readXmlFile();
                boolean success = delete(t);
                if (success) {
                    out.print("ID:" + delId + "is deleted succesfuly");
                    saveXml();
                } else {
                    out.print("Item not found or wrong id");
                }
            } catch (Exception ex) {
                Logger.getLogger(TODOManagerServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp); //To change body of generated methods, choose Tools | Templates.
        String filename = this.getServletContext().getRealPath("/data.xml");
        String requestUri = req.getRequestURI();
        System.out.println(requestUri);
        String sPath = req.getServletPath();
        PrintWriter out = resp.getWriter();

        resp.setContentType("text/html;charset=UTF-8");
//      
        String[] ps = requestUri.split("/");
        for (String i : ps) {
            System.out.println(i + ",");
        }
        if (ps.length == 4) {
            try {
                String delId = ps[3];
                String description = req.getParameter("description");
                JSONObject j = new JSONObject();
                j.put("id", delId);
                j.put("description", description);
                boolean success = update(j);

                System.out.println("put params: id: " + delId + ", desc" + description);
                if (success) {
                    out.print("ID:" + delId + "is updated succesfuly");
                    saveXml();
                } else {
                    out.print("Item not found or wrong id");
                }
            } catch (Exception ex) {
                Logger.getLogger(TODOManagerServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private JSONArray getElements(String ids) throws Exception {
        JSONArray todos = new JSONArray();
//        SAXBuilder builder = new SAXBuilder();
        String filename = this.getServletContext().getRealPath("/data.xml");
//        System.out.print(filename);
//        Document document = builder.build(filename);
        root = document.getRootElement();
        
        readXmlFile();
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
        } else {
            int id = Integer.parseInt(ids);
            for (int i = 0; i < ts.size(); i++) {
                Element t = (Element) ts.get(i);
                if (id == Integer.parseInt(t.getChildText("id"))) {
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
