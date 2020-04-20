package memberSystem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class InputOutputEmployee {

    static String path = "INT103_Final_Project_BTSSystem\\tmp";
    static String filename = "Employee.txt";
    static String absolutePath = path + filename;

    public static void WriteEmployee(Employee em) {
        try ( FileOutputStream fos = new FileOutputStream(absolutePath, false);  
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(em + "\n");
            oos.close();
            fos.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Employee ReadEmployee() {
        Employee em = null;
        try (FileInputStream fis = new FileInputStream(absolutePath);
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            
            while(fis.available()!=0){
               em = (Employee) ois.readObject();
            }
           
        }catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
           ex.printStackTrace();
        }
        return em;
    }
}

