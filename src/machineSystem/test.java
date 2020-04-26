package machineSystem;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import memberSystem.CardStatus;
import memberSystem.CardType;
import memberSystem.Person;
import memberSystem.RabbitCard;
import dataBase.RabbitcardDatabase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Collections;
import memberSystem.Employee;

public class test {
    private Employee employees[];
    private RabbitCard rabbitCards[];
    private int employeeCount;
    private int rabbitCardCount;
    public test() {
        employees = new Employee[10]; //Change to IO Later & set employeeCount
        rabbitCards = new RabbitCard[10]; //Change to BD Later & set rabbitCardCount
    }
    
    public void addEmployee(String id,String pass,Person p1){
        employees[employeeCount++] = new Employee(id,pass,p1);
    }
    
    public void addRabbitCard(){
        
    }
    public boolean isEmployeeFull(){
        for (int i = 0; i < employees.length; i++) {
            if(employees[i] == null){
                return true;
            }
        }
        return false;
    }
      public boolean isRabbitCardFull(){
        for (int i = 0; i < rabbitCards.length; i++) {
            if(rabbitCards[i] == null){
                return true;
            }
        }
        return false;
    }
    public void RedeemPoint(){ 
      }
      
     public String listAllRabbitCard(){
         return null;
     }
     
     public void Travel(){
         
     }
      
    
    private void writeRabbitCardLog(RabbitCard r1, String atIn, String atOut) throws IOException {
        /////// catch for FileNotFound and creat new txt file ///////
        try{
            FileInputStream fis = new FileInputStream("tmp/" + r1.getRbc_idCard() + "_Station_Log.txt");
        }catch(FileNotFoundException ex){
            FileWriter S1 = new FileWriter("tmp/" + r1.getRbc_idCard() + "_Station_Log.txt");
        }
        
        Path file = Paths.get("tmp/" + r1.getRbc_idCard() + "_Station_Log.txt");
        /////// backup file to temp ///////
        BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8);
        String line = null;
        StringBuilder temp = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            temp.append(line + "\n");
        }
        
        /////// Write Temp & result in file ///////
        BufferedWriter writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8);
        StringBuilder result = new StringBuilder();
        LocalDateTime logTime = LocalDateTime.now();
        result.append("Date : " + logTime.toLocalDate()+" Time : "+logTime.getHour()+":"+logTime.getMinute()+"\n");
        result.append("Station " + atIn + " To " + atOut + "\n");
        result.append("------------------------------\n");
        writer.write(temp.toString());
        writer.write(result.toString());
        writer.close();

    }
        public static void createCustomerTable() {

         try (Connection conn = DriverManager.getConnection("jdbc:mysql://103.86.49.133:3306/RabbitCard", "KodlnwSoftwarehouse", "Kodlnw1234");
                Statement stm = conn.createStatement()) {
            try {
                stm.executeUpdate("DROP TABLE rabbitcard");
            } catch (SQLException ex) {
            }
            try {
                stm.executeUpdate("CREATE TABLE rabbitcard (rbc_idCard INT NOT NULL,rbc_person VARCHAR(100),rbc_money INT,rbc_point INT,rbc_cardStatus VARCHAR(8),rbc_cardType VARCHAR(8)PRIMARY KEY(rbc_idCard))");
            } catch (SQLException ex) {
            }
            
        } catch (SQLException ex) {

        }
    }
//    public static void main(String[] args) throws IOException {
//        createCustomerTable();
//        RabbitCard r1 = new RabbitCard(1001, new Person("Somchai", "Somtam", "1102170018970", "0806849641"),100, 100, CardStatus.ACTIVE, CardType.ADULT);
//        RabbitcardDatabase DBR1 = new RabbitcardDatabase();
//        DBR1.insertDB(0, new Person("Somchai", "Somtam", "1102170018970", "0806849641"), 0, 0, CardStatus.ACTIVE, CardType.ADULT);
//        test mac1 = new test();
//        mac1.writeRabbitCardLog(r1, "Siam", "leng house");
//        
//
//         }
    
   
}
