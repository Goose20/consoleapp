/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieProject;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import movieProject.DbSpeak;

/**
 *
 * @author sjoosthuizen
 */
public class Server {
    private ServerSocket serverSocket;
        private static ExecutorService pool = Executors.newFixedThreadPool(4);
        private boolean listening;
        
        public void StartServer(){
            try {
                this.serverSocket = new ServerSocket(7777);
                this.listening = true;
                System.out.println("StartServer()");
            }
            catch(IOException ex){
                System.out.println("Startup Faliar");
            }
            

            while(this.listening)
            {
                try
                {
                    Socket clientSocket = serverSocket.accept();
                    Diplomat thread = new Diplomat(clientSocket);
                    thread.run();
                    pool.execute(thread);
                    System.out.println("RunningThreads");
                }
                catch(IOException ex){
                    System.out.println("Loop Failiar!");
                }
                
            }
        }
}
