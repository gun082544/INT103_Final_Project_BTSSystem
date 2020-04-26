package memberSystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class InputOutputEmployee {

    static File file = new File("C:\\Users\\Gigabyte\\Documents\\GitHub\\INT103_Final_Project_BTSSystem\\tmp\\Employee.emp");

    public static void EmployeeWriter(Employee e) throws IOException, ClassNotFoundException {
        try {
            FileOutputStream f = new FileOutputStream(file.getAbsoluteFile());
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(e);
            o.close();
            f.close(); 
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        } catch (IOException e2) {
            System.out.println("Error initializing stream");
        }
    }
  
    public static void ReadEmployeeData() throws IOException {
        Employee eo = null;
        try {
            FileInputStream fi = new FileInputStream(file.getAbsoluteFile());
            ObjectInputStream oi = new ObjectInputStream(fi);
           while(fi.available()!=0){
               eo = (Employee) oi.readObject();
            }
            System.out.println(eo.toString());
            oi.close();
            fi.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        } catch (IOException e2) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Person p1 = new Person("JJ", "Montika", "62130500024", "086849641");
        Person p2 = new Person("Leng", "Loha", "62130500056", "086148152");
        Person p3 = new Person("Gun", "Chalor", "62130500072", "092151852");

        Employee e1 = new Employee("A01", "btsemp", p1);
        Employee e2 = new Employee("A02", "btsemp", p2);
        Employee e3 = new Employee("A03", "btsemp", p3);

           EmployeeWriter(e1);
          ReadEmployeeData();
    }
}
