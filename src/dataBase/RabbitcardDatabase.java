package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import memberSystem.CardStatus;
import memberSystem.CardType;
import memberSystem.Person;

public class RabbitcardDatabase {

    public void insertDB(long rbc_idCard, Person rbc_person, int rbc_money,int rbc_point,CardStatus rbc_cardStatus,CardType rbc_cardType) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://103.86.49.133:3306/RabbitCard", "KodlnwSoftwarehouse", "Kodlnw1234");
                Statement stm = conn.createStatement();) {

           int row = stm.executeUpdate("INSERT INTO RABBITCARD VALUES(" + rbc_idCard + ","+ rbc_person + ","+rbc_money+","+rbc_point+","+rbc_cardStatus+"."+rbc_cardType+")");
            System.out.println(row);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
