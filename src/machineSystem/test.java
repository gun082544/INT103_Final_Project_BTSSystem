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
import memberSystem.InputOutputEmployee;
import paymentSystem.PaymentSystem;

public class test {
    private Employee employees[];
    private RabbitCard rabbitCards[];
    private int employeeCount;
    private int rabbitCardCount;
    public test() throws IOException {
        InputOutputEmployee e1 = new InputOutputEmployee();
        employees = e1.ReadEmployeeData();
        rabbitCards = new RabbitCard[10]; //Change to BD Later & set rabbitCardCount
    }
    
    public void addEmployee(String id,String pass,Person p1) throws IOException, ClassNotFoundException{
        if(this.isEmployeeFull() == true){
           Employee temp[] = employees;
           this.employees = new Employee[employeeCount+10];
            for (int i = 0; i < temp.length; i++) {
                employees[i] = temp[i];
            }
        }
        employees[employeeCount++] = new Employee(id,pass,p1);
        InputOutputEmployee e1 = new InputOutputEmployee();
        e1.EmployeeWriter(employees); 
        
    }
    
    public void addRabbitCard(){
        rabbitCards[0] = new RabbitCard(1001, new Person("Somchai", "Somtam",1102003160431L,894908646L),100, 100, CardStatus.ACTIVE, CardType.ADULT);
    }
    public boolean isEmployeeFull(){
        for (int i = 0; i < employees.length; i++) {
            if(employees[i] == null){
                return false;
            }
        }
        return true;
    }
      public boolean isRabbitCardFull(){
        for (int i = 0; i < rabbitCards.length; i++) {
            if(rabbitCards[i] == null){
                return false;
            }
        }
        return true;
    }
    public void RedeemPoint(String id,String pass,int id_card,int money){ 
        for (int i = 0; i < employees.length; i++) {
            if(id.equals(employees[i].getEmployee_id())&& pass.equals(employees[i].getPassword())){
                for (int j = 0; j < rabbitCards.length; j++) {
                    if(rabbitCards[i].getRbc_idCard() == id_card){
                        rabbitCards[i].setRbc_money(rabbitCards[i].getRbc_money()+money);
                        System.out.println("RabbitCard ID "+rabbitCards[i].getRbc_idCard()+" has redeem sucessful.");
                        //and Update money in DB
                    }
                }
                System.out.println("RabbitCard ID is Undefined");
                return;
            }
            else System.out.println("Incorrect ID or Password.");
            return;
        }
      }
      
     public String listAllRabbitCard(){
         return null;
     }
     
     public void Travel(int id_card,String atIn,String atOut) throws IOException{
         for (int i = 0; i < rabbitCards.length; i++) {
             if(rabbitCards[i] == null) return;
             if(rabbitCards[i].getRbc_idCard()==id_card){
                 if(rabbitCards[i].getRbc_cardStatus()== CardStatus.EXPIRED){
                     System.out.println("RabbitCard ID "+rabbitCards[i].getRbc_idCard()+" : Out of money");
                     return;
                 }
                 PaymentSystem payment = new PaymentSystem();
                 int totalPoint = rabbitCards[i].getRbc_money() - payment.getTotalPay(atIn, atOut);
                 rabbitCards[i].setRbc_money(totalPoint);
                 System.out.println("RabbitCard ID "+rabbitCards[i].getRbc_idCard()+" : Station "+atIn
                                    +" to Station "+atOut+" has successful");
                 System.out.println("Your current money : "+rabbitCards[i].getRbc_money());
                 //and Update money in DB
                 this.writeRabbitCardLog(rabbitCards[i], atIn, atOut);
                 if(rabbitCards[i].getRbc_money()<1){
                     rabbitCards[i].setRbc_cardStatus(CardStatus.EXPIRED);
                     System.out.println("RabbitCard ID "+rabbitCards[i].getRbc_idCard()+" : Now your card are expired."
                             + "Please redeem your rabbitcard !");
                 }
                 return;
             }
             System.out.println("RabbitCard ID is Undefined");
         }
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

    @Override
    public String toString() {
       StringBuilder r1 = new StringBuilder();
       r1.append("BTS Machine System :\n");
       r1.append("Employee list :\n");
        for (int i = 0; i < employees.length; i++) {
            if(employees[i]==null){continue;}
            r1.append(employees[i]+"\n");
        }
       r1.append("RabbitCard List :\n");
        for (int i = 0; i < rabbitCards.length; i++) {
            if(rabbitCards[i]==null)continue;
            r1.append(rabbitCards[i]+"\n");
        }
        return r1.toString();
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
    
    public static void main(String[] args) throws IOException {
        test v1 = new test();
        v1.addRabbitCard();
//        v1.Travel(1001, "Siam", "Nana");
//        v1.Travel(1001, "Siam", "Chit lom");
//        v1.Travel(1001, "Siam","Phra Khanong");
//        v1.Travel(1001, "Nana", "Siam");
        v1.Travel(1001, "Siam", "Nana");
        System.out.println(v1);
        
        
    }
}
