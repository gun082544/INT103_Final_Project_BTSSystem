package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import memberSystem.CardStatus;
import memberSystem.CardType;

public class RabbitcardDatabase {

    public void insertDBRabbitcard(long rbc_idCard, int rbc_money,int rbc_point,CardStatus rbc_cardStatus,CardType rbc_cardType) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://103.86.49.133:3306/RabbitCard", "KodlnwSoftwarehouse", "Kodlnw1234");
                Statement stm = conn.createStatement();) {

           int row = stm.executeUpdate("INSERT INTO RABBITCARD VALUES(" + rbc_idCard + "+ ,"+rbc_money+","+rbc_point+","+rbc_cardStatus+"."+rbc_cardType+")");
            System.out.println(row);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void insertDBPerson(String firstname, String lastname,long c_id, long phone) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://103.86.49.133:3306/Rabbicard", "KodlnwSoftwarehouse", "Kodlnw1234");
                Statement stm = conn.createStatement();) {

           int row = stm.executeUpdate("INSERT PERSON VALUES(" +firstname + ","+  lastname + ","+c_id+","+phone+")");
            System.out.println(row);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
