package ibf2021.d4;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class CookieClientHandler implements Runnable {
    private final Socket socket;
    private String filename;

    public CookieClientHandler (Socket socket, String filename) { 
        this.socket = socket;
        this.filename = filename;
    }

    @Override
    public void run () {
        String command = "";
        String msg = "";

        try (InputStream is = socket.getInputStream())  {
            BufferedInputStream bis = new BufferedInputStream(is);
            DataInputStream dis = new DataInputStream(bis);
            OutputStream os = socket.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(os);
            DataOutputStream dos = new DataOutputStream(bos);
            while (!command.equals("exit")) {
                command = dis.readUTF();
                if (command.equals("get-cookie")) {
                    Cookie fc = new Cookie();
                    fc.load(filename);
                    String cookie = fc.getRandomCookie();
                    msg = "cookie-text " + cookie;
                }
                dos.writeUTF(msg);
                dos.flush();
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
