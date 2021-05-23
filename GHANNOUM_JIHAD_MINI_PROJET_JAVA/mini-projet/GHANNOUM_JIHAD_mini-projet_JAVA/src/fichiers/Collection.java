package fichiers;
import java.io.File;
import java.io.IOException;


public class Collection {
    public static void main(String[] args) {
        try {
            File file = new File("files/collection.txt");
            file.createNewFile();
            System.out.println("Empty File Created:- " + file.length());
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
}