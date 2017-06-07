/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass3.test;

import java.io.FileOutputStream;
import java.io.FileWriter;
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
public class test {
    private static List<Element> l= new ArrayList<Element>();
    
    public static void main(String[] args) throws Exception{
        Element root,person,name,age;           
        root=new Element("personinfo");
        person=new Element("person");
        name =new Element("name");
        age=new Element("age");                 
        age.setText("120");
        name.setText("name");
        person.addContent(age);
        person.addContent(name);
        root.addContent(person);
        Document doc=new Document(root);
        XMLOutputter XMLOut = new XMLOutputter();
                                                                 
        XMLOut.output(doc,new FileOutputStream("personinfo1.xml"));
    }
   
}
