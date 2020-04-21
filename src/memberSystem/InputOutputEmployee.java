package memberSystem;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class InputOutputEmployee {

    static String path = "tmp/Employee.txt";
      
      public static void WriteEmployee(Employee em){
         try (FileOutputStream fos = new FileOutputStream("tmp/Employee"+".txt");
                BufferedOutputStream bos=new BufferedOutputStream(fos);
                DataOutputStream dos = new DataOutputStream(bos)) {
            String textfromfile = "";
            while (em.)

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
      }
//    public static void WriteEmployee(Employee em) {
//        try ( FileOutputStream fos = new FileOutputStream(path);  ObjectOutputStream oos = new ObjectOutputStream(fos)) {
//            oos.writeObject(em + "\n");
//            oos.close();
//            fos.close();
//        } catch (FileNotFoundException ex) {
//            ex.printStackTrace();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }

//    public static Employee ReadEmployee() {
//        Employee em = null;
//        try ( FileInputStream fis = new FileInputStream(path);  ObjectInputStream ois = new ObjectInputStream(fis)) {
//
//            while (fis.available() != 0) {
//                em = (Employee) ois.readObject();
//            }
//
//        } catch (FileNotFoundException ex) {
//            ex.printStackTrace();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace();
//        }
//        return em;
//    }
}
