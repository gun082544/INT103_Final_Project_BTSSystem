package memberSystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class InputOutputEmployee {

    public static void EmployeeWriter(Employee[] e) throws IOException {
        try {
            FileWriter fw = new FileWriter("tmp\\Employee.txt");
            BufferedWriter bf = new BufferedWriter(fw);

            FileReader fr = new FileReader("tmp\\Employee.txt");
            BufferedReader br = new BufferedReader(fr);

            String s = null;
            StringBuilder temp = new StringBuilder();
            while ((s = br.readLine()) != null) {
                temp.append(s + "\n");
            }

            StringBuilder data = new StringBuilder();
            LocalDateTime logTime = LocalDateTime.now();
            data.append(logTime.toLocalTime());
            data.append(e);
            bf.write(temp.toString());
            bf.write(data.toString());
            bf.close();
            fw.close();


        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        } catch (IOException e2) {
            System.out.println("Error initializing stream");
        }
    }
  
    public static void ReadEmployeeData() throws IOException {
        try {  
                FileReader fr = new FileReader("tmp\\Employee.txt");
                BufferedReader br = new BufferedReader(fr);
                String s1 ;
                String s2 = new String();
                while ((s1 = br.readLine())!=null) {
                 s2 += s1 + "\n";
            }
                System.out.println(s2);
                
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        } catch (IOException e2) {
            System.out.println("Error initializing stream");
        }
    }

//    public static void ReadEmployeeData() throws IOException {
//        Employee[] eo = null;
//        try {
//            FileInputStream fi = new FileInputStream("tmp\\Employee.emp");
//            ObjectInputStream oi = new ObjectInputStream(fi);
//            while (fi.available() != 0) {
//                eo = (Employee[]) oi.readObject();
//            }
//            for (int i = 0; i < eo.length; i++) {
//                System.out.println(eo[i]);
//            }
//            oi.close();
//            fi.close();
//        } catch (FileNotFoundException ex) {
//            System.out.println("File not found");
//        } catch (IOException e2) {
//            System.out.println("Error initializing stream");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Person p1 = new Person("JJ", "Montika", 1, 2);
        Person p2 = new Person("Leng", "Loha", 1, 3);
        Person p3 = new Person("Gun", "Chalor", 4, 5);

        Employee e1 = new Employee("A01", "btsemp", p1);
        Employee e2 = new Employee("A02", "btsemp", p2);
        Employee e3 = new Employee("A03", "btsemp", p3);

//        Employee[] em = new Employee[3];
//        em[0] = e1;
//        em[1] = e2;
//        em[2] = e3;
//        EmployeeWriter(em);
           ReadEmployeeData();

    }
}
