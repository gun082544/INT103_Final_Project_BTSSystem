
package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import memberSystem.Person;


public class PersonDatabase {
        public void insertDB(String firstname, String lastname,long c_id, long phone) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://103.86.49.133:3306/Person", "KodlnwSoftwarehouse", "Kodlnw1234");
                Statement stm = conn.createStatement();) {

           int row = stm.executeUpdate("INSERT INTO STATION VALUES(" +firstname + ","+  lastname + ","+c_id+","+phone+")");
            System.out.println(row);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
