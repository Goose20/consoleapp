/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieProject;

import java.sql.*;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sjoosthuizen
 */
public class DbSpeak {
    public DbSpeak(){}
    
    private static Connection connection;
    
    public static Connection getConnection() {
        if(connection == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/MoviesDB","root","admin123");
                
                
            } catch (SQLException | ClassNotFoundException e) {
                System.out.println(e);
            }
        }
        return connection;
    }
    
    public LinkedList<MovieModel> getMovie(String mName) throws SQLException {
        LinkedList<MovieModel> moviesList = new LinkedList<MovieModel>(); 
        MovieModel model = new MovieModel();
        PreparedStatement statement = null;
        
        String sql = "select * from Movies where movieName =?";
        
        statement = DbSpeak.getConnection().prepareStatement(sql);
        statement.setString(1, mName);
        ResultSet results = statement.executeQuery();
        try {
            while(results.next()) {
                model.setName(results.getString("movieName"));
                model.setGenre(results.getString("genreId"));
                moviesList.add(model);
            }
        }catch(SQLException ex) {
            System.out.println("Server query failed");
        }
        return moviesList;
    }
    public void addMovie(long movieId, String mName,Long genreId) throws SQLException {
        PreparedStatement statement = null;
        String sql = "INSERT INTO Movies(movieId, movieName, description, genreId) VALUES ("+ movieId +", '"+ mName +"', null, "+ genreId +")";
        try {
            statement = DbSpeak.getConnection().prepareStatement(sql);
        } catch(SQLException ex) {
            System.out.println("movie add error");
        }
        
    }
    public boolean login(String username, String password) {
        boolean sucess = false;
        
        String sql = "SELECT * FROM User where username = ? and password = ?";
        try {
            PreparedStatement statement = DbSpeak.getConnection().prepareStatement(sql);
            ResultSet results = statement.executeQuery();
            
            while(results.next()) {
                if(username == results.getString("username") && password == results.getString("password")) {
                    sucess = true;
                }
            }
        } catch (SQLException ex) {
            System.out.println("login error");
        }
        
        return sucess;
    }
    public void deketeMovie(long movieId) {
        PreparedStatement statement = null;
        
        String sql = "delete from Movies where movieId = "+ movieId;
        try {
            statement = DbSpeak.getConnection().prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("delete movie error");
        }
    }

    class MovieModel{
        private String Name;
        private String Genre;
        public String getName()
        {
            return this.Name;
        }
        public void setName(String name)
        {
            this.Name = name; 
        }

        private void setGenre(String genre) {
            this.Genre = genre;
        }
    }
}


