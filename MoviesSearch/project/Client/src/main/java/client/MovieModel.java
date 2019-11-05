/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author Student
 */
public class MovieModel {
     private static String movieName;
        private static int ID;
        private static String description;
        private static String genre;
        public String getMovie()
        {
            return this.movieName;
        }
        public static void setMovie(String name, String description, String genre)
        {
            MovieModel.movieName = name;  
            MovieModel.description = description; 
            MovieModel.ID = ID;
            MovieModel.genre = genre; 
        }
        public String getName() {
            return this.movieName;
        }
}
