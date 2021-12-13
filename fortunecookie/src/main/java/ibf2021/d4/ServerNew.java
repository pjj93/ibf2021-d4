package ibf2021.d4;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerNew {
    public static void main(String[] args) {
        int port = -1;
        String filename = "";
        //try with resources
        if (args.length == 2) {
            port = Integer.parseInt(args[0]);
            filename = args[1];
        } 

        ExecutorService threadpool = Executors.newFixedThreadPool(3);
        try (ServerSocket server = new ServerSocket(port);) {
            while (true) {
                Socket socket = server.accept();
                System.out.println("New client connected!");
                CookieClientHandler worker = new CookieClientHandler(socket, filename);
                threadpool.submit(worker);
            }
        } catch (IOException e) {
            System.err.println("Error. Check that you have specified a port number");
            e.printStackTrace();
        }

    }
}