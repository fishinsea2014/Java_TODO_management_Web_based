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

/**
 *
 * @author QSG
 */
@WebServlet(name = "test1", urlPatterns = {"/test1"})
public class test1 extends HttpServlet {
    
    private static List<Element> xmlList;
    private static Element root;
    private static String filename;
    private Document document;
    
    private void readXmlFile(){
        SAXBuilder builder = new SAXBuilder();
        System.out.print(filename);
        document = null;
        try {
            document = builder.build(filename);
        } catch (JDOMException ex) {
            Logger.getLogger(test1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(test1.class.getName()).log(Level.SEVERE, null, ex);
        }
        root = document.getRootElement();
        xmlList = root.getChildren("todo");
    }
    
    private void saveXml(){
       XMLOutputter XMLOut = new XMLOutputter();
        try { 
            XMLOut.output(document,new FileOutputStream(filename));
        } catch (Exception ex) {
            Logger.getLogger(test1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void addTodo(String id,String description){
        Element newElement=new Element("todo");
        newElement.addContent(new Element("id").setText(id));
        newElement.addContent(new Element("description").setText(description));
        xmlList.add(newElement);
    }
    
    private boolean deleteTodo(String id) {
        int index = -1;
        for (int i=0;i<xmlList.size();i++){
            String ids=xmlList.get(i).getChildText("id");
            System.out.println("id is:"+ids);
            if (ids.equals(id)) 
                index=i;
        }
        if(index!=-1) {
            xmlList.remove(index);
            return true;
        }else 
            return false;
    }
    
    private boolean updateTodo(String id,String updateString){
        for (Element e: xmlList){
            if(e.getChildText("id").equals(id)){
                e.getChild("description").setText(updateString);
                return true;
            }
        }
        return false;
    }


    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
        filename = this.getServletContext().getRealPath("/data.xml");
                readXmlFile();
//                addTodo("1001", "1001add todo test");
                 String rtrn=null;
                System.out.println(xmlList.size());
//                deleteTodo("1000");
                if (updateTodo("1001", "the todo updated")) {
                    rtrn="update success";
                }
                System.out.println(xmlList.size());
                saveXml();

        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet test1</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet test1 at " + rtrn + "</h1>");
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
    }
}
    

    
   

