package client;
import java.util.Scanner;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.BufferedReader;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }
    

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket("127.0.0.1", 7777);
            String query = "Start";
            Scanner input = new Scanner(System.in);
            while(query.toLowerCase() != "exit")
            {
            System.out.println("Enter Movie Name");
            query = input.next();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out.println(query);
            String result = in.readLine();
            System.out.println("The server replied:"+result);
            
            in.close();
            out.close();
            }
            input.close();
            clientSocket.close();
        }
        catch(Exception e)
        {
            System.out.println("Client failed");
        }
        

    }
}
