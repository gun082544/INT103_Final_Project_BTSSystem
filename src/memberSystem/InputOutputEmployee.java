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
import java.util.ArrayList;

public class InputOutputEmployee {

    public static void EmployeeWriter(Employee e[]) throws IOException, ClassNotFoundException {

        try {
            FileOutputStream f = new FileOutputStream("tmp\\Employee.emp");
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

    public static Employee[] ReadEmployeeData() throws IOException {
        Employee[] eo = null;
        try {
            FileInputStream fi = new FileInputStream("tmp\\Employee.emp");
            ObjectInputStream oi = new ObjectInputStream(fi);
            while (fi.available() != 0) {
                eo = (Employee[]) oi.readObject();
            }
            for (int i = 0; i < eo.length; i++) {
                System.out.println(eo[i]);
            }
            oi.close();
            fi.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        } catch (IOException e2) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return eo;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Person p1 = new Person("JJ", "Montika", 1, 2);
        Person p2 = new Person("Leng", "Loha", 1, 3);
        Person p3 = new Person("Gun", "Chalor", 4, 5);

        Employee e1 = new Employee("A01", "btsemp", p1);
        Employee e2 = new Employee("A02", "btsemp", p2);
        Employee e3 = new Employee("A03", "btsemp", p3);
        
//        Employee[] em = new Employee[3];
//        em[0] = e2;
//        em[1] = e1;
//        em[2] = e3;
//        EmployeeWriter(em);
        ReadEmployeeData();
    }
}
