package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import memberSystem.CardStatus;
import memberSystem.CardType;
import memberSystem.Person;
import memberSystem.RabbitCard;

public class RabbitcardDatabase {
    public void createTables(){
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://103.86.49.133:3306/RabbitCard", "KodlnwSoftwarehouse", "Kodlnw1234");
                Statement stm = conn.createStatement();) {
            try {stm.executeUpdate("DROP TABLE PERSON");} catch (SQLException ex) {}
            try {stm.executeUpdate("DROP TABLE RABBITCARD");} catch (SQLException ex) {}
            
            try {stm.executeUpdate("CREATE TABLE RABBITCARD (rbc_idCard BIGINT NOT NULL, rbc_money INT, rbc_point INT,rbc_cardStatus VARCHAR(10),rbc_cardType VARCHAR(10),PRIMARY KEY (rbc_idCard))");} catch (SQLException ex) {} 
            try {stm.executeUpdate("CREATE TABLE PERSON (firstname VARCHAR(200),lastname VARCHAR(200),c_id BIGINT NOT NULL,phone BIGINT,PRIMARY KEY (c_id))");} catch (SQLException ex) {}
            System.out.println("Create table has sucessful.");
            System.out.println("---------------------------------------------------------------------------");
    }   catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }    
    public void updateMoney(int money,long id){
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://103.86.49.133:3306/RabbitCard", "KodlnwSoftwarehouse", "Kodlnw1234");
                Statement stm = conn.createStatement();) {
            stm.executeUpdate("UPDATE RABBITCARD SET rbc_money = "+money+" WHERE rbc_idCard ="+id);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void insertDBRabbitcard(long rbc_idCard, int rbc_money, int rbc_point, String rbc_cardStatus, String rbc_cardType) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://103.86.49.133:3306/RabbitCard", "KodlnwSoftwarehouse", "Kodlnw1234");
                Statement stm = conn.createStatement();) {

            stm.executeUpdate("INSERT INTO RABBITCARD VALUES(" + rbc_idCard + "," + rbc_money + "," + rbc_point + ",'" + rbc_cardStatus + "','" + rbc_cardType + "')");
            System.out.println("Insert RABBITCARD has been sucessful.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void insertDBPerson(String firstname, String lastname, long c_id, long phone) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://103.86.49.133:3306/RabbitCard", "KodlnwSoftwarehouse", "Kodlnw1234");
                Statement stm = conn.createStatement();) {

            stm.executeUpdate("INSERT PERSON VALUES('" + firstname + "','" + lastname + "'," + c_id + "," + phone + ")");
            System.out.println("Insert person has been sucessful.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public RabbitCard[] selectRabbitCard() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://103.86.49.133:3306/RabbitCard", "KodlnwSoftwarehouse", "Kodlnw1234");
                Statement stm = conn.createStatement();) {
            int count = 0, i = 0, k = 0;
            ResultSet rabbitCardCount = stm.executeQuery("SELECT * FROM RABBITCARD");
            while (rabbitCardCount.next()) {
                count++;
            }
            RabbitCard RabbitCard_temp[] = new RabbitCard[count];
            Person person_temp[] = new Person[count];
            ResultSet person = stm.executeQuery("SELECT * FROM PERSON");
            while (person.next()) {
                person_temp[k++] = new Person(person.getString("firstname"), person.getString("lastname"), person.getLong("c_id"), person.getLong("phone"));
            }
            ResultSet Rabbit = stm.executeQuery("SELECT * FROM RABBITCARD");
            while (Rabbit.next()) {
                CardStatus cardStatusTemp = null;
                CardType cardTypeTemp = null;
                if(Rabbit.getString("rbc_cardStatus").toUpperCase().equals("ACTIVE")){
                    cardStatusTemp = CardStatus.ACTIVE;
                }
                else{
                    cardStatusTemp = CardStatus.EXPIRED;
                }
                if(Rabbit.getString("rbc_cardType").toUpperCase().equals("STUDENT")){
                    cardTypeTemp = CardType.STUDENT;
                }
                else{
                    cardTypeTemp = CardType.ADULT;
                }
                RabbitCard_temp[i] = new RabbitCard(Rabbit.getLong("rbc_idCard"),
                        person_temp[i],
                         Rabbit.getInt("rbc_money"), Rabbit.getInt("rbc_point"), cardStatusTemp, cardTypeTemp);
                i++;
            }
            return RabbitCard_temp;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
