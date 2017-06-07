/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.massey.cs.webtech.ass3.s_14177256.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import static org.apache.http.HttpVersion.HTTP;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 *
 * @author QSG
 */
public class ToDoManager {

    private static final String url = "http://localhost:8084/assignment3_server_14177256/todos/";
    private static boolean USE_Proxy = false;
    private static String PROXY_HOST = "tur-cache1.massey.ac.nz";
    private static int PROXY_PORT = 8080;
    private static DefaultHttpClient httpClient;
    private static HttpHost proxy;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Enter expression to evaluate, type exti to exit");
            System.out.print("> ");
            String line = br.readLine();
            if ("exit".equals(line)) {
                System.exit(0);
            }
//            if ("post".equals(line)) {
//            postTodo(url, "2002", "post a todu test");
//            }
//            System.out.println(deleteTodo(url));
            System.out.println(updateTodo(url, "modified by Put"));
//            getTodo(line);
//            getAll();
//            getById("6001");
//            search("test");
            JSONObject ins = new JSONObject();
            ins.put("id", "9001");
//            ins.put("description", "build construction");
//              insert(ins);
//            update(ins);
delete(ins);
        }
    }

    public static void initTodoManager() {
        httpClient = new DefaultHttpClient();
        if (USE_Proxy) {
            proxy = new HttpHost(PROXY_HOST, PROXY_PORT);
            httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
        }

    }

    public static String getAll() throws Exception {
        initTodoManager();
        //http://localhost:8084/webtech-tutorial2-id14177256/Caculator?formula=1%2B2
//        builder.setScheme("http").setHost("localHost").setPort(8084).setPath("/assignment3_server_14177256/todos")
//                .setParameter("expression", line);
        HttpGet request = new HttpGet(url);
        HttpResponse response = httpClient.execute(request);
        String content = EntityUtils.toString(response.getEntity());
        JSONArray recArray =(JSONArray) new JSONTokener(content.toString()).nextValue();
        System.out.println(" = " + recArray.toString());
        return recArray.toString();
    }

    public static boolean insert(JSONObject todo) throws Exception {
        initTodoManager();
        if (postTodo(todo.getString("id"), todo.getString("description"))) {
            return true;
        }
        return false;
    }

    public static boolean update(JSONObject todo) throws Exception {
        initTodoManager();
        if (updateTodo(todo.getString("id"), todo.getString("description")) == "no response") {
            return false;
        } else {
            return true;
        }
    }
    
    public static boolean delete(JSONObject todo) {
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpDelete httpDelete = new HttpDelete(url+todo.getString("id"));
            HttpResponse result = httpClient.execute(httpDelete);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(ToDoManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static String getById(String id) throws Exception {
        initTodoManager();
        //http://localhost:8084/webtech-tutorial2-id14177256/Caculator?formula=1%2B2
//            URIBuilder builder=new URIBuilder();
//        builder.setScheme("http").setHost("localHost").setPort(8084).setPath("/assignment3_server_14177256/todos");
//                .setParameter("expression", line);
        HttpGet request = new HttpGet(url + id);

        HttpResponse response = httpClient.execute(request);
        String content = EntityUtils.toString(response.getEntity());
        System.out.println(content);
//        JSONArray recArray = new JSONArray();
        JSONObject reJSONObject = new JSONObject(content.toString());
        return reJSONObject.toString();
    }

    public static String search(String keyword) throws Exception {
        initTodoManager();
        URIBuilder builder = new URIBuilder();
        builder.setScheme("http").setHost("localHost").setPort(8084).setPath("/assignment3_server_14177256/todos").setParameter("search", keyword);
        URI uri = builder.build();
        System.out.print(uri);
        HttpGet request = new HttpGet(uri);
        HttpResponse response = httpClient.execute(request);
        String content = EntityUtils.toString(response.getEntity());
        System.out.println(content);
        JSONArray recArray = new JSONArray();
        recArray = (JSONArray) new JSONTokener(content.toString()).nextValue();
        return recArray.toString();
    }

    public static boolean postTodo(String id, String description) {

        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("id", id));
        params.add(new BasicNameValuePair("description", description));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
            HttpResponse result = httpClient.execute(httpPost);
            if (result.getStatusLine().getStatusCode() == 200) {
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(ToDoManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }



    public static String updateTodo(String url, String description) {
        String putUrl = url;
        System.out.print(putUrl);
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPut httpPut = new HttpPut(putUrl);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("description", description));

        try {
            httpPut.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
            HttpResponse result = httpClient.execute(httpPut);
            System.out.println(result.toString());
//            if (result.getStatusLine().getStatusCode() == 200) {
            return result.toString();
//            }

        } catch (Exception ex) {
            Logger.getLogger(ToDoManager.class.getName()).log(Level.SEVERE, null, ex);
            return "no response";
        }

    }
}
