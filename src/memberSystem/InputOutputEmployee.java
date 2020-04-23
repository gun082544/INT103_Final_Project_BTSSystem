package memberSystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class InputOutputEmployee {

    static String path = "tmp/Employee.txt";

    public static void WriteEmployee(Employee em) throws IOException {
        try {
            FileInputStream fis = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            FileWriter fw = new FileWriter(path);
        }
        Path file = Paths.get(path);
        BufferedReader bf = Files.newBufferedReader(file, StandardCharsets.UTF_8);
        String line = null;
        StringBuilder temp = new StringBuilder();
        while ((line = bf.readLine()) != null) {
            temp.append(line + "\n");
        }
        BufferedWriter writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8);
        StringBuilder result = new StringBuilder();
        result.append("Employees's Data\n");
        result.append(em.toString() + "\n");
        result.append("------------------------------\n");
        writer.write(temp.toString());
        writer.write(result.toString());
        writer.close();
    }

    public static void ReadEmployee() throws IOException {
        try {
            FileInputStream fis = new FileInputStream(path);
            Path file = Paths.get(path);
            BufferedReader bf = Files.newBufferedReader(file, StandardCharsets.UTF_8);
            String line = null;
            StringBuilder temp = new StringBuilder();
            while ((line = bf.readLine()) != null) {
                temp.append(line + "\n");
            }
            System.out.println(temp.toString());
        } catch (FileNotFoundException e) {
            System.out.println("-----------No Employee Data-----------");
        }
    }
    public static void main(String[] args) throws IOException{
       Person p1 = new Person("JJ","Montika","62130500024","086849641");
       Person p2 = new Person("Leng","Loha","62130500056","086148152");
       Person p3 = new Person("Gun","Chalor","62130500072","092151852");
       
       Employee e1 = new Employee("A01","btsemp",p1);
       Employee e2 = new Employee("A02","btsemp",p2);
       Employee e3 = new Employee("A03","btsemp",p3);
       
       
       ReadEmployee();
    }
}
