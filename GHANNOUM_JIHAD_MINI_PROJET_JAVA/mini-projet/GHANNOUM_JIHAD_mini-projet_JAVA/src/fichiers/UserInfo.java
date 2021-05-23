package fichiers;
import java.io.FileWriter;
import java.io.IOException;


public class UserInfo {
    public static void main(String[] args) {
        try {
            FileWriter file = new FileWriter("files/users_details.txt");
            file.write("Dupont,1234,1\n");
            file.write("Martin,1234,0\n");
            file.write("John,1234,1\n");
            file.write("Bill,1234,0\n");
            file.write("Eric,1234,0\n");
            System.out.println("File Created");
            file.close();
            
        } catch (IOException e) {
            System.out.println("An error occurred.");
        } 
    }
    
}
