package fileOutputStream;

import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        
        try {
            FileOutputStream fOutputStream = new FileOutputStream("./src/fileOutputStream/test.txt");
            int a = 65;
            // Writes the unicode representation of variable "a" to the file
            fOutputStream.write(a);
            fOutputStream.close();
            System.out.println("Success...");
        } catch (IOException e) {
            System.err.println("Error Writing File");
        }
    }
}
