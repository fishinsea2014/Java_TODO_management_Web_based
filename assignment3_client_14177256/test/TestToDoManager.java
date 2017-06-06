/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import nz.ac.massey.cs.webtech.ass3.s_14177256.client.ToDoManager;
import org.json.JSONObject;
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
    public void testSendHttpGetAll() throws Exception{
//        String return="aa";
//        String return= ToDoManager.sendHttpGet("http://localhost:8084/assignment3_server_14177256/todos");
        String r= ToDoManager.analyseGet("http://localhost:8084/assignment3_server_14177256/todos");
        String expected="[{\"description\":\"get a class member variables\",\"id\":101},{\"description\":\"set a class member variables\",\"id\":102},{\"description\":\"construct a class\",\"id\":201},{\"description\":\"surround with a try or catch block\",\"id\":301},{\"description\":\"throw an exception\",\"id\":401}]";
        assertEquals(r, expected);
    }
    
    @Test
    public void testSendHttpGetTodoById() throws Exception{
//        String return="aa";
//        String return= ToDoManager.sendHttpGet("http://localhost:8084/assignment3_server_14177256/todos/101");
        String r= ToDoManager.analyseGet("http://localhost:8084/assignment3_server_14177256/todos/101");
        String expected="[{\"description\":\"get a class member variables\",\"id\":101}]";
        assertEquals(r, expected);
    }
@Test
    public void testPutATodo() throws Exception{
        JSONObject paraJSONObject=new JSONObject();
        paraJSONObject.put("id", 201);
        paraJSONObject.put("description", "this is a test todo");
        String url="http://localhost:8084/assignment3_server_14177256/todos";
        ToDoManager.analysePost(url, paraJSONObject, true);
        String r= ToDoManager.analyseGet("http://localhost:8084/assignment3_server_14177256/todos/201");
        String expected="[{\"description\":\"this is a test todo\",\"id\":201}]";
        assertEquals(r, expected);
    }
}
