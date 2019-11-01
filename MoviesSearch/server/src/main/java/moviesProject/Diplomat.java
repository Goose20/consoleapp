package moviesProject;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


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
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream());
    }

    @Override
    public void run() {
        try {
            while(true) {
                String request = in.readLine();
                if(!request.isBlank()){
                    String result = QueryDb(request);
                    out.println(result);
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
    public String QueryDb(String request){

        return request+"Slep";
    }

}
