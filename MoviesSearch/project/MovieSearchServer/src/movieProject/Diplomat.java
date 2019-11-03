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
import java.util.LinkedList;
import movieProject.DbSpeak.MovieModel;


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
            while(true) {
                String request = this.in.readLine();
                if(!request.isBlank()){
                    LinkedList<MovieModel> result = QueryDb(request);
                    this.out.println(result);
                }
            }
        } catch(IOException e) {

        } finally {
            out.close();
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(Diplomat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public LinkedList<MovieModel> QueryDb(String request){
        try {
            DbSpeak query = new DbSpeak();
            LinkedList<DbSpeak.MovieModel> results = query.getMovie(request);
            
            return results;
        } catch (SQLException ex) {
            System.out.println("getmovie call");
        }
        return null;
    }

}
