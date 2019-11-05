/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieProject;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import movieProject.DbSpeak.*;
/*import movieProject.Models;*/
import movieProject./*Models.*/MovieModel;


/**
 *
 * @author sjoosthuizen
 */
public class Diplomat implements Runnable{
    public Socket client;
    private BufferedReader in;
    private PrintWriter out;

    Diplomat(Socket clientSocket)throws IOException{
        this.client = clientSocket;
        this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        this.out = new PrintWriter(client.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            DbSpeak query = new DbSpeak();
            String s = null;
            String request = this.in.readLine();
            while(true) {
                if(request.equals("search")) {
                    LinkedList<MovieModel> results = query.Search(this.in.readLine(), this.in.readLine());
                    out.print(results);
                }
                else if(request.equals("login")) {
                    boolean logedin = query.login(this.in.readLine(), this.in.readLine());
                    out.println(logedin);
                }
                else if(request.equals("addMovie")) {
                    DbSpeak.addMovie(this.in.readLine(), this.in.readLine(), this.in.readLine(), this.in.readLine());
                }
                else if(request.equals("addGenre")) {
                    DbSpeak.addGenre(this.in.readLine(), this.in.readLine());
                }
                else if(request.equals("deleteMovie")) {
                    DbSpeak.deleteMovie(this.in.readLine());
                }
                
            }
        } catch(IOException e) {

        } catch (SQLException ex) {
            Logger.getLogger(Diplomat.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(Diplomat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
