package client;
import java.util.logging.Level;
import java.util.logging.Logger;
import client.Client;

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
        new MainForm().setVisible(true);

    }
}
