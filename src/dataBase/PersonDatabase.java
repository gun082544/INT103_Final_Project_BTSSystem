
package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import memberSystem.Person;
import memberSystem.RabbitCard;


public class PersonDatabase {
        public void insertDB(int id_key,RabbitCard r1,Person s1) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://103.86.49.133:3306/RabbitCard", "KodlnwSoftwarehouse", "Kodlnw1234");
                Statement stm1 = conn.createStatement();) {

            stm1.executeUpdate("INSERT INTO PERSON VALUES("+id_key+ ",'" +s1.getFirstname() 
                   + "','"+  s1.getLastname() + "',"+s1.getC_id()+","+s1.getPhone()+")");
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
