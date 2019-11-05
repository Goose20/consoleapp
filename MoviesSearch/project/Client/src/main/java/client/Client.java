/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sjoosthuizen
 */
public class Client {
    private Client() {
        
    }
    
        
    public LinkedList<MovieModel> search(String mName, String genre) {
        LinkedList<MovieModel> moviesList = new LinkedList<MovieModel>(); 
        MovieModel model = new /*Models.*/MovieModel();
        try {
            Socket s = new Socket("127.0.0.1",7777);
            InputStreamReader in = new InputStreamReader(s.getInputStream());
            PrintStream out =new PrintStream(s.getOutputStream());
            while(true) {
                out.println("search");
                out.println(mName);
                out.println(genre);
            }
            
        } catch (IOException ex) {
            System.out.println("Search error");
        }
        
    }
    public boolean login(String username, String password) {
        boolean isConnected = false ; 
        try {
            Socket s = new Socket("127.0.0.1",7777);
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintStream ps =new PrintStream(s.getOutputStream());
            while(true) {
                ps.println("login");
                ps.println(username);
                ps.println(password);
                
            }
        } catch (IOException ex) {
            System.out.println("Search error");
        }
        return isConnected;
    }
    public void addMovie(String movieId, String mName, String description, String genreId) {
        try {
            Socket s = new Socket("127.0.0.1",7777);
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintStream ps =new PrintStream(s.getOutputStream());
            while(true) {
                ps.println("addMovie");
                ps.println(movieId);
                ps.println(mName);
                ps.println(description);
                ps.println(genreId);
            }
        } catch (IOException ex) {
            System.out.println("Search error");
        }
    }
    public void addGenre(String genreId, String genreName) {
        try {
            Socket s = new Socket("127.0.0.1",7777);
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintStream ps =new PrintStream(s.getOutputStream());
            while(true) {
                ps.println("addGenre");
                ps.println(genreId);
                ps.println(genreName);
            }
        } catch (IOException ex) {
            System.out.println("Search error");
        }
    }
    public void deleteMovie(String movieId) {
        try {
            Socket s = new Socket("127.0.0.1",7777);
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintStream ps =new PrintStream(s.getOutputStream());
            while(true) {
                ps.println("deleteMovie");
                ps.println(movieId);
            }
        } catch (IOException ex) {
            System.out.println("Search error");
        }
    }


}
