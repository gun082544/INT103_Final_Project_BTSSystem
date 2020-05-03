package memberSystem;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class InputOutputEmployee {

    public static void EmployeeWriter(Employee e[]) throws IOException, ClassNotFoundException {

        try {
            FileOutputStream f = new FileOutputStream("tmp\\Employee.emp");
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(e);
            o.close();
            f.close();
            System.out.println("-----------Writing Data Complete-----------");
        } catch (FileNotFoundException ex) {
            System.out.println("-----------File not found-----------");
        } catch (IOException e2) {
            System.out.println(e2.getMessage());
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
            oi.close();
            fi.close();
        } catch (FileNotFoundException ex) {
            System.out.println("-----------File not found-----------");
        } catch (IOException e2) {
            System.out.println(e2.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return eo;
    }
}
