package ibf2021.d4;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        String[] tcpip = new String[2];
        if (args.length == 1) {
            tcpip = args[0].split(":");
        } 
        else {
            System.out.println("Please specify tcp/ip");
            System.exit(1);
        }

        String input = "";
        String cookietext = "";
        Socket socket = new Socket(tcpip[0], Integer.parseInt(tcpip[1]));

        InputStream is = socket.getInputStream();
        // DataInputStream dis = new DataInputStream(is);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        OutputStream os = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        try (DataInputStream dis = new DataInputStream(is)) {
            while (!input.equals("exit")) {
                input = br.readLine();
                dos.writeUTF(input);
                dos.flush();
                cookietext = dis.readUTF();
                System.out.println(cookietext.substring(12, cookietext.length())); //substring gets the string after "cookie-text "
            }
        } catch (EOFException e) {
            System.out.println("Closing connection....");
        }

        dos.close();
        socket.close();
    }
}
