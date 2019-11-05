/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieProject;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
/*import movieProject.Models;*/
import movieProject./*Models.*/MovieModel;

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
    
    public LinkedList<MovieModel> Search(String mName, String genre) throws SQLException {
        LinkedList<MovieModel> moviesList = new LinkedList<MovieModel>(); 
        MovieModel model = new /*Models.*/MovieModel();
        PreparedStatement statement = null;
        
        String sql = "select movieName, description, genreName from Movies, Genre where movieName =%'"+ mName +"'% and genreName = %'"+ genre +"'% and Genre.genreId = Movies.movieId";
        
        try {
            statement = DbSpeak.getConnection().prepareStatement(sql);
            ResultSet results = statement.executeQuery();
            MovieModel movie;
            while(results.next()) {
                MovieModel.setMovie(results.getString("movieName"), results.getString("description"), results.getString("genreName"));
                moviesList.add(model);
                if(genre != null && !(genre.equals(results.getString("genreName")))) {
                    
                }
            }
            
        }catch(SQLException ex) {
            System.out.println("Server query failed");
        }
        return moviesList;
    }

    /**
     *
     * @param movieId
     * @param mName
     * @param genreId
     * @throws SQLException
     */
    public static void addMovie(String movieId, String mName,String description, String genreId) throws SQLException {
        PreparedStatement statement = null;
        String sql = "INSERT INTO Movies(movieId, movieName, description, genreId) VALUES ("+ movieId +", '"+ mName +"', '"+ description +"', "+ genreId +")";
        try {
            statement = DbSpeak.getConnection().prepareStatement(sql);
        } catch(SQLException ex) {
            System.out.println("movie add error");
        }
        
    }
    public static void addGenre(String genreId, String genreName) {
        PreparedStatement statement = null;
        String sql = "INSERT INTO Genre(genreId, genreName) VALUES ("+ genreId +", '"+ genreName +"')";
        try {
            statement = DbSpeak.getConnection().prepareStatement(sql);
        } catch(SQLException ex) {
            System.out.println("genre add error");
        }
    }
    public static boolean login(String username, String password) {
        boolean success = false;
        
        String sql = "SELECT * FROM User where username = ? and password = ?";
        try {
            PreparedStatement statement = DbSpeak.getConnection().prepareStatement(sql);
            ResultSet results = statement.executeQuery();
            
            while(results.next()) {
                if((username == null ? results.getString("username") == null : username.equals(results.getString("username"))) && (password == null ? results.getString("password") == null : password.equals(results.getString("password")))) {
                    success = true;
                }
            }
        } catch (SQLException ex) {
            System.out.println("login error");
        }
        
        return success;
    }
    public static void deleteMovie(String movieId) {
        PreparedStatement statement = null;
        
        String sql = "delete from Movies where movieId = "+ movieId;
        try {
            statement = DbSpeak.getConnection().prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("delete movie error");
        }
    }
}


