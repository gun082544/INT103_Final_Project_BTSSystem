package memberSystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class InputOutputEmployee {

    static File file = new File("C:\\Users\\Gigabyte\\Documents\\GitHub\\INT103_Final_Project_BTSSystem\\tmp\\Employee.txt");

    public static void WriteEmployee(Employee e) throws IOException {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            Path file = Paths.get("C:\\Users\\Gigabyte\\Documents\\GitHub\\INT103_Final_Project_BTSSystem\\tmp\\Employee.txt");
            BufferedReader bf = Files.newBufferedReader(file, Charset.defaultCharset());
            String line = null ;
            StringBuilder tempt = new StringBuilder();
            while ((line = bf.readLine()) == null) {
                    tempt.append(line +"\n");
            }
            BufferedWriter writer = new BufferedWriter(fw);
            StringBuilder result = new StringBuilder();
            result.append(e.toString() + "\n");
            result.append("---------------------------------------------------------------\n");
            writer.write(tempt.toString());
            writer.write(result.toString());
            writer.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void ReadEmployee() throws IOException {
        try {
            Path file = Paths.get("C:\\Users\\Gigabyte\\Documents\\GitHub\\INT103_Final_Project_BTSSystem\\tmp\\Employee.txt");
            BufferedReader bf = Files.newBufferedReader(file, Charset.defaultCharset());
            String line = null;
            StringBuilder temp = new StringBuilder();
            while ((line = bf.readLine()) != null) {
                temp.append(line + "\n");
            }
            System.out.println(temp.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Person p1 = new Person("JJ", "Montika", "62130500024", "086849641");
        Person p2 = new Person("Leng", "Loha", "62130500056", "086148152");
        Person p3 = new Person("Gun", "Chalor", "62130500072", "092151852");

        Employee e1 = new Employee("A01", "btsemp", p1);
        Employee e2 = new Employee("A02", "btsemp", p2);
        Employee e3 = new Employee("A03", "btsemp", p3);
        
        WriteEmployee(e1);

    }
}
