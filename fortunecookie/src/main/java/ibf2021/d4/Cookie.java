package ibf2021.d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Cookie {
    private List<String> cookies = new ArrayList<String>();

    public String getRandomCookie() {
        String cookie = "";

        if (cookies.size() != 0) {
            int index = (int) (Math.random() * cookies.size());
            cookie = cookies.get(index);
        }
        else
            cookie = "No cookies";

        return cookie;
    }

    public int getSize() {
        return cookies.size();
    }

    // loads the text file containing a list of cookies to the list
    public void load(String filename) throws IOException {
        //clears the list of cookies currently stored
        cookies.clear();
        InputStream is = getClass().getResourceAsStream(filename);
        InputStreamReader isr = new InputStreamReader(is);
        // Path p = Paths.get("").toAbsolutePath();
        // Path path = Paths.get(p + "/" + filename);
        // File f = path.toFile();

        // FileReader fr = new FileReader(f);
        BufferedReader reader = new BufferedReader(isr);
        String cookie = "";

        while ((cookie = reader.readLine()) != null) {
            cookies.add(cookie);
        }

        reader.close();
    }

    public void list() {
        for (int i = 0 ; i < this.getSize(); i++) {
            System.out.println(this.cookies.get(i));
        }
    }
}
