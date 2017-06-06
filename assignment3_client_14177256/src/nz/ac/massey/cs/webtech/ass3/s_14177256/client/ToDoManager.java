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
import org.apache.http.util.EntityUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;


/**
 *
 * @author QSG
 */
public class ToDoManager {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Enter expression to evaluate, type exti to exit");
            System.out.print("> ");
            String line = br.readLine();
            if ("exit".equals(line)) {
                System.exit(0);
            }
            analyseGet(line);
        }
    }

    public static String analyseGet(String line) throws Exception {

        boolean USE_Proxy = false;
        String PROXY_HOST = "tur-cache1.massey.ac.nz";

        int PROXY_PORT = 8080;
        DefaultHttpClient httpClient = new DefaultHttpClient();
        if (USE_Proxy) {
            HttpHost proxy = new HttpHost(PROXY_HOST, PROXY_PORT);
            httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
        }

        URIBuilder builder = new URIBuilder();
        //http://localhost:8084/webtech-tutorial2-id14177256/Caculator?formula=1%2B2
//        builder.setScheme("http").setHost("localHost").setPort(8084).setPath("/assignment3_server_14177256/todos")
//                .setParameter("expression", line);

        HttpGet request = new HttpGet(line);
        HttpResponse response = httpClient.execute(request);

        String content = EntityUtils.toString(response.getEntity());
        JSONArray recArray = new JSONArray();
        recArray = (JSONArray) new JSONTokener(content.toString()).nextValue();
        System.out.println(" = " + recArray.toString());
        return recArray.toString();
    }

    public static JSONObject analysePost(String url, JSONObject jsonParam, boolean noNeedResponse) {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        JSONObject jsonResult = null;
        HttpPost method = new HttpPost();

        try {
            if (jsonParam != null) {
                StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                method.setEntity(entity);
            }
            HttpResponse result=httpClient.execute(method);
            url=URLDecoder.decode(url, "UTF-8");
            if(result.getStatusLine().getStatusCode()==200){
                String str="";
                try {
                    str=EntityUtils.toString(result.getEntity());
                    if(noNeedResponse){
                        return null;
                    }
                    jsonResult=new JSONObject(str);
                } catch (Exception ex) {
                    Logger.getLogger(ToDoManager.class.getName()).log(Level.SEVERE, null, ex);
                    
                } 
        } 
    }catch (Exception ex) {
            Logger.getLogger(ToDoManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jsonResult;
}
}
