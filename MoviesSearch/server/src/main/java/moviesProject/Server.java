package moviesProject;

import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.net.*;
import moviesProject.Diplomat;
import java.io.IOException;
public class Server{

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
                System.out.println("FuckUp!");
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
                    System.out.println("Loop FuckUp!");
                }
                
            }
        }
        


}