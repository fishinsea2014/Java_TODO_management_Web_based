/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.massey.cs.webtech.ass3.s_14177256.server;

import java.io.FileOutputStream;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author QSG
 */
public class NewClass {


 
          public static void main(String args[]) throws Exception {
        Element root,person,name,age;           
        root=new Element("personinfo");
        person=new Element("person");
        name =new Element("name");
        age=new Element("age");                 
        age.setText("20");
        name.setText("name");
        person.addContent(age);
        person.addContent(name);
        root.addContent(person);
        Document doc=new Document(root);//最终都是要得到一个Document...
        XMLOutputter XMLOut = new XMLOutputter();
                                                                 
        XMLOut.output(doc,new FileOutputStream("personinfo1.xml"));
}
}
