/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieProject;

/**
 *
 * @author sjoosthuizen
 */
public final class App {
    private App() {
        
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        new loginForm().setVisible(true);
        DbSpeak.getConnection();
        Server server = new Server();
        server.StartServer();
    }
    
}
