/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.massey.cs.webtech.ass3.s_14177256.client;

/**
 *
 * @author QSG
 */
public class NewClass {
    public static void main(String[] args){
        String s=ToDoManager.getInstance().sendHttpGet("localhost:8084/assignment3_server_14177256/todos");
        
        System.out.print(s);
    }
    
}
