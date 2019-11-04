/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author sjoosthuizen
 */
public class Client {
    private Client() {
        
    }
    public void start() throws Exception {
        try {
            Socket clientSocket = new Socket("127.0.0.1", 7777);
            String query = "Start";
            while(query.toLowerCase() != "exit")
            {
            System.out.println("Enter Movie Name");
            query = input.next();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out.println(query);
            String result = in.readLine();
            System.out.println("The server replied:"+result);
            
            in.close();
            out.close();
            }
            input.close();
            clientSocket.close();
        }
        catch(Exception e)
        {
            System.out.println("Client failed");
        }
    }
}
