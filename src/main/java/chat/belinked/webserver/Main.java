package chat.belinked.webserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        try {
            ServerSocket serverConnect = new ServerSocket(Webserver.PORT);
            System.out.println("\n --- JAVA WEBSERVER BY JOHANNES BECKER --- \n");
            System.out.println("Server started.\nListening for connections on port : " + Webserver.PORT + " ...\n");

            // we listen until user halts server execution
            while (true) {
                Webserver myServer = new Webserver(serverConnect.accept());

                if (Webserver.verbose) {
                    System.out.println("Connection opened. (" + new Date() + ")");
                }

                // create dedicated thread to manage the client connection
                Thread thread = new Thread(myServer);
                thread.start();
            }

        } catch (IOException e) {
            System.err.println("Server Connection error : " + e.getMessage());
        }
    }
}
