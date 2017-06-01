/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.massey.cs.webtech.ass3.s_14177256.client;
import org.junit.Test;

/**
 *
 * @author QSG
 */
public class TestToDoManager_bak {
    
     
        
        
    
    @Test
    public void testSendHttpGet(){
//        String return="aa";
//        String return= ToDoManager.sendHttpGet("http://localhost:8084/assignment3_server_14177256/todos");
        String r=ToDoManager.getInstance().sendHttpGet("http://localhost:8084/assignment3_server_14177256/todos");
        System.out.print(r);
    }
}
