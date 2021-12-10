package ibf2021.d4;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ServerTest {
    
    @Test
    public static void main(String[] args) {
        int port = 0;
        String filename = null;
        if (args.length == 2) {
            port = Integer.parseInt(args[0]);
            filename = args[1];
        }
        assertTrue(port == 12345);
        assertTrue(filename.equals("cookie-file.txt"));      
    }
}
