package ibf2021.d4;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    
    public static void main(String[] args) throws IOException {
        int port = 0;
        String filename = null;
        if (args.length == 2) {
            port = Integer.parseInt(args[0]);
            filename = args[1];
        } else {
            System.out.println("Server port and filename not intialized");
            System.exit(1);
        }
        
        Cookie fc = new Cookie();
        fc.load(filename);

        ServerSocket server = new ServerSocket(port);
        System.out.println("Listening to port 12345....");


        Socket socket = server.accept();
        System.out.println("Client connected!");
        OutputStream os = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        InputStream is = socket.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        DataInputStream dis = new DataInputStream(bis);

        String command = "";
        // try (DataInputStream dis = new DataInputStream(bis)) {
            while (!command.equals("exit")) {
                command = dis.readUTF();
                if (command.equals("get-cookie")) {
                    String cookietxt = "cookie-text " + fc.getRandomCookie();
                    dos.writeUTF(cookietxt);
                    dos.flush();
                }
            }

        System.out.println("Closing connection....");

        server.close();
    }
}