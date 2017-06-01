/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.massey.cs.webtech.ass3.s_14177256.client;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.json.*;


/**
 *
 * @author QSG
 */
public class ToDoManager {
    private RequestConfig requestConfig=RequestConfig.custom()
            .setSocketTimeout(15000)
            .setConnectTimeout(15000)
            .setConnectionRequestTimeout(15000)
            .build();
    
    private static ToDoManager instance=null;
    private ToDoManager(){};
    public static ToDoManager getInstance(){
        if(instance==null){
            instance=new ToDoManager();
        }
        return instance;
    }
    
      
     public String sendHttpGet(String httpUrl) {  
        HttpGet httpGet = new HttpGet(httpUrl);//  
        return sendHttpGet(httpGet); 
        
    }
    
}
