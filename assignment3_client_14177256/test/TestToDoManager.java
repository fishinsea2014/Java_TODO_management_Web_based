/*
 * When test for second time, please override data.xml withe contents of data_org.xml,
 * for after test, data.xml has been changed.
 */

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nz.ac.massey.cs.webtech.ass3.s_14177256.client.ToDoManager;
import org.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
//import org.jdom2.Document;
//import org.jdom2.Element;
//import org.jdom2.JDOMException;
//import org.jdom2.input.SAXBuilder;
//import org.jdom2.output.XMLOutputter;
import org.json.JSONArray;
import org.json.JSONException;


/**
 *
 * @author QSG
 */
public class TestToDoManager{
    static String originalData;


    public TestToDoManager() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        originalData=ToDoManager.getAll();
        
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
    public void testGetAll() throws Exception {
//        String return="aa";
//        String return= ToDoManager.sendHttpGet("http://localhost:8084/assignment3_server_14177256/todos");
        String expected = "[{\"description\":\"get a class member variables\",\"id\":101065},{\"description\":\"set a class member variables\",\"id\":102654},{\"description\":\"construct a class\",\"id\":201798},{\"description\":\"surround with a try or catch block\",\"id\":3018761},{\"description\":\"throw an exception\",\"id\":401654}]";
        assertEquals(originalData,expected);
    }

    @Test
    public void testGetById() throws Exception {
        String r = ToDoManager.getById("101065");
        String expected = "{\"description\":\"get a class member variables\",\"id\":101065}";
        assertEquals(r, expected);
    }

    @Test
    public void testSearch() throws Exception {
        String rtn = ToDoManager.search("class");
        String expected = "[{\"description\":\"get a class member variables\",\"id\":101065},{\"description\":\"set a class member variables\",\"id\":102654},{\"description\":\"construct a class\",\"id\":201798}]";
        assertEquals(expected, rtn);
    }

    @Test
    public void testInsert() throws Exception {
        JSONObject paraJSONObject = new JSONObject();
        paraJSONObject.put("id", 20015465);
        paraJSONObject.put("description", "this is a test post todo");
        String url = "http://localhost:8084/assignment3_server_14177256/todos";
        ToDoManager.insert(paraJSONObject);
        String r = ToDoManager.getById("20015465");
        System.out.println("post return:"+r);
        String expected = "{\"description\":\"this is a test post todo\",\"id\":20015465}";
        assertEquals(r,expected);
    }
    @Test
    public void testDelete() throws Exception{
        JSONObject paraJSONObject = new JSONObject();
        paraJSONObject.put("id", 20015465);
        ToDoManager.delete(paraJSONObject);
        String expected = "{\"description\":\"this is a test post todo\",\"id\":20015465}";
        assertEquals(ToDoManager.getAll().indexOf(expected),-1);
    }
    
    @Test
    public void testUpdate() throws  Exception{
        JSONObject paraJSONObject = new JSONObject();
        paraJSONObject.put("id", 401654);
        paraJSONObject.put("description", "");
        String expected="{\"description\":\"throw an exception\", \"id\":401654}";
        assertTrue(expected!=ToDoManager.getById("401654"));
    }
}
