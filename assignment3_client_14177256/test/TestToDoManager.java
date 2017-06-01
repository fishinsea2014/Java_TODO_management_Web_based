/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import nz.ac.massey.cs.webtech.ass3.s_14177256.client.ToDoManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author QSG
 */
public class TestToDoManager {
    
    public TestToDoManager() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testSendHttpGet(){
//        String return="aa";
//        String return= ToDoManager.sendHttpGet("http://localhost:8084/assignment3_server_14177256/todos");
        String r= ToDoManager.getInstance().sendHttpGet("localhost:8084/assignment3_server_14177256/todos");
        System.out.print(r);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
