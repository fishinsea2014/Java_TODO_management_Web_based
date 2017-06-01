/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.massey.cs.webtech.ass3.s_14177256.server;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.jdom2.xpath.XPathBuilder;

import java.util.*;

/**
 *
 * @author QSG
 */
public class test {
    private static List<Element> l= new ArrayList<Element>();
    
    public static void main(String[] args) throws Exception{
        System.out.print("hello");
        SAXBuilder builder= new SAXBuilder();
        Document document = builder.build(".\\web\\data.xml");
        Element root=document.getRootElement();
        List todos=root.getChildren("todo");
        for (int i=0;i<todos.size();i++){
            Element todo=(Element) todos.get(i);
            l.add(todo);
//            Todo t=(Todo)todo;
            System.out.println(todo.getName()+" : "+todo.getChildText("id")+todo.getChildText("description"));
        }
        System.out.println(l.get(0).getValue());
    }
}
