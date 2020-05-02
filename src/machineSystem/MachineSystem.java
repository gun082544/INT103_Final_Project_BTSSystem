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
import java.util.logging.Level;
import java.util.logging.Logger;
import memberSystem.Employee;
import memberSystem.InputOutputEmployee;
import paymentSystem.PaymentSystem;

public class MachineSystem implements EmployeeService,RabbitcardService {

    private Employee employees[];
    private RabbitCard rabbitCards[];
    private int employeeCount;
    private int rabbitCardCount;

    public MachineSystem() throws IOException {
        RabbitcardDatabase b1 = new RabbitcardDatabase();
        InputOutputEmployee e1 = new InputOutputEmployee();
        employees = e1.ReadEmployeeData();
        for (int i = 0; i < employees.length; i++) {
            if(employees[i] != null){
                employeeCount++;
            }
        }
        //Write IO into array & set employeeCount  **COMPLETE**
        rabbitCards = b1.selectRabbitCard();
        for (int i = 0; i < rabbitCards.length; i++) {
            if(rabbitCards[i] != null){
                rabbitCardCount++;
            }
        }
        //Write DataBase into array & set rabbitCardCount **COMPLETE**
    }

    public boolean isEmployeeFull() {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) {
                return false;
            }
        }
        return true;
        //Check Array that full **COMPLETE**
    }

    public boolean isRabbitCardFull() {
        for (int i = 0; i < rabbitCards.length; i++) {
            if (rabbitCards[i] == null) {
                return false;
            }
        }
        return true;
        //Check Array that full **COMPLETE**
    }

    public void addEmployee(String id, String pass, Person p1) throws IOException, ClassNotFoundException {
        for (int i = 0; i < employees.length; i++) {
            if(employees[i]!=null &&employees[i].getEmployee_id().equals(id)){
                System.out.println("This ID already exists,Please try with another name ID.");
                System.out.println("----------------------------------------------------");
                return;
                //In case check in array,then id has already exists and return **COMPLETE**
            }
        }
        if (this.isEmployeeFull() == true) {
            Employee temp[] = employees;
            this.employees = new Employee[employeeCount + 1];
            for (int i = 0; i < temp.length; i++) {
                employees[i] = temp[i];
            }
            //Check Array that full ,If full then increase size **COMPLETE**
        }
        employees[employeeCount++] = new Employee(id, pass, p1);
        InputOutputEmployee e1 = new InputOutputEmployee();
        e1.EmployeeWriter(employees);
        System.out.println("Employee Id : "+id+" has been added sucessful.");
        System.out.println("----------------------------------------------------");
        //Add new employee into array and IO **COMPLETE**
    }
    
    public void deleteEmployee(String id, String pass) throws IOException, ClassNotFoundException{
        for (int i = 0; i < employees.length; i++) {
            if(employees[i] != null &&id.equals(employees[i].getEmployee_id())&&pass.equals(employees[i].getPassword())){
                employees[i] = null;
                employeeCount--;
            int k=0;
            Employee temp[] = new Employee[employees.length-1];
            for(int j = 0; j < employees.length; j++){
                if(employees[j]!=null){
                    temp[k++]=employees[j];
                }
            }
            employees = temp;
            InputOutputEmployee e1 = new InputOutputEmployee();
            e1.EmployeeWriter(employees);
            System.out.println("Delete "+id+" has been sucessful.");
            System.out.println("----------------------------------------------------");
            return;
            }
        }
        System.out.println("Delete employee failed, Incorrect ID or Password.");
        System.out.println("----------------------------------------------------");
    }
    
    @Override
    public void addRabbitCard(String id, String pass, RabbitCard r1) {
        if (this.isRabbitCardFull() == true) {
            RabbitCard temp[] = rabbitCards;
            this.rabbitCards = new RabbitCard[rabbitCardCount + 1];
            for (int i = 0; i < temp.length; i++) {
                rabbitCards[i] = temp[i];
            }
            //Check Array that full ,If full then increase size **COMPLETE**
        }
        for (int i = 0; i < employees.length; i++) {
            //Only Employee can use this method using id & password **COMPLETE**
            if (employees[i] !=null &&id.equals(employees[i].getEmployee_id()) && pass.equals(employees[i].getPassword())) {
                this.rabbitCards[rabbitCardCount++] = r1;
                RabbitcardDatabase b1 = new RabbitcardDatabase();
                b1.insertDBPerson(r1.getFirstname(), r1.getLastName(), r1.getC_id(), r1.getPhone());
                b1.insertDBRabbitcard(r1.getRbc_idCard(), r1.getRbc_money(), r1.getRbc_point(),
                        r1.getRbc_cardStatus() == CardStatus.ACTIVE ? "ACTIVE" : "EXPIRED",
                        r1.getRbc_cardType() == CardType.STUDENT ? "STUDENT" : "ADULT");
                System.out.println("RabbitCard " + r1.getRbc_idCard() + " has been added sucessful.");
                System.out.println("----------------------------------------------------");
                return;
                //Add new rabbitcard into array and Database **COMPLETE**
            }
        }
        System.out.println("Employee incorrect ID or Password.");
        System.out.println("----------------------------------------------------");
        //In case not found employee and return **COMPLETE**
    }
    
    @Override
    public void RedeemPoint(String id, String pass, long id_card, int money) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] !=null && id.equals(employees[i].getEmployee_id()) && pass.equals(employees[i].getPassword())) {
                //Only Employee can use this method using id & password **COMPLETE**
                for (int j = 0; j < rabbitCards.length; j++) {
                    //Search rabbitcard id in array **COMPLETE**
                    if (rabbitCards[j] != null && rabbitCards[j].getRbc_idCard() == id_card) {
                        rabbitCards[j].setRbc_money(rabbitCards[j].getRbc_money() + money);
                        System.out.println("RabbitCard ID " + rabbitCards[j].getRbc_idCard() + " has redeem "+money+" bath sucessful.");
                        System.out.println("Your current money : " + rabbitCards[j].getRbc_money());
                        System.out.println("----------------------------------------------------");
                        //Increase money in rabbitcard **COMPLETE**
                        RabbitcardDatabase b1 = new RabbitcardDatabase();
                        b1.updateMoney(rabbitCards[j].getRbc_money(), rabbitCards[j].getRbc_idCard());
                        //and Update money in DB **COMPLETE**
                        return;
                    }
                }
                System.out.println("RabbitCard ID : "+id_card+" Error Something please contact employee");
                System.out.println("----------------------------------------------------");
                return;
                //In case not found employee and return **COMPLETE**
            }
        }
        System.out.println("Employee incorrect ID or Password.");
        System.out.println("----------------------------------------------------");
        //In case password or id doesn't match in array **COMPLETE**
    }

    @Override
    public void Travel(int id_card, String atIn, String atOut) {
        for (int i = 0; i < rabbitCards.length; i++) {
            if (rabbitCards[i]!= null && rabbitCards[i].getRbc_idCard() == id_card) {
                if (rabbitCards[i].getRbc_cardStatus() == CardStatus.EXPIRED) {
                    System.out.println("RabbitCard ID " + rabbitCards[i].getRbc_idCard() + " : Out of money");
                    System.out.println("----------------------------------------------------");
                    return;
                }
                PaymentSystem payment = new PaymentSystem();
                int totalPoint = rabbitCards[i].getRbc_money() - payment.getTotalPay(atIn, atOut);
                System.out.println("RabbitCard ID " + rabbitCards[i].getRbc_idCard() + " : Station " + atIn
                        + " to Station " + atOut + " has successful");
                System.out.println("Your money : "+rabbitCards[i].getRbc_money());
                System.out.println("price : "+payment.getTotalPay(atIn, atOut));
                
                rabbitCards[i].setRbc_money(totalPoint);
                System.out.println("Your current money : " + rabbitCards[i].getRbc_money());
                System.out.println("----------------------------------------------------");
                
                RabbitcardDatabase b1 = new RabbitcardDatabase();
                b1.updateMoney(rabbitCards[i].getRbc_money(), rabbitCards[i].getRbc_idCard());
                try {
                    //and Update money in DB **COMPLETE**
                    this.writeRabbitCardLog(rabbitCards[i], atIn, atOut);
                } catch (IOException ex) {
                    Logger.getLogger(MachineSystem.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (rabbitCards[i].getRbc_money() < 1) {
                    rabbitCards[i].setRbc_cardStatus(CardStatus.EXPIRED);
                    System.out.println("RabbitCard ID " + rabbitCards[i].getRbc_idCard() + " : Now your card are expired."
                            + "Please redeem your rabbitcard !");
                    System.out.println("Your current money : " + rabbitCards[i].getRbc_money());
                    System.out.println("----------------------------------------------------");
                //Caculate station & payment **COMPLETE**
                return;
                }
                return;
            }
        }
        System.out.println("RabbitCard ID : "+id_card+" Error Something please contact employee");
        System.out.println("----------------------------------------------------");
    }

    private void writeRabbitCardLog(RabbitCard r1, String atIn, String atOut) throws IOException {
        try {
            FileInputStream fis = new FileInputStream("tmp/" + r1.getRbc_idCard() + "_Station_Log.txt");
        } catch (FileNotFoundException ex) {
            FileWriter S1 = new FileWriter("tmp/" + r1.getRbc_idCard() + "_Station_Log.txt");
        }
        //catch for FileNotFound and creat new txt file **COMPLETE**
        Path file = Paths.get("tmp/" + r1.getRbc_idCard() + "_Station_Log.txt");
        
        BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8);
        String line = null;
        StringBuilder temp = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            temp.append(line + "\n");
        }
        // backup file to temp **COMPLETE**

        BufferedWriter writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8);
        StringBuilder result = new StringBuilder();
        LocalDateTime logTime = LocalDateTime.now();
        result.append("Date : " + logTime.toLocalDate() + " Time : " + logTime.getHour() + ":" + logTime.getMinute() + "\n");
        result.append("Station " + atIn + " To " + atOut + "\n");
        result.append("------------------------------\n");
        writer.write(temp.toString());
        writer.write(result.toString());
        writer.close();
        // Write Temp & result in file **COMPLETE**

    }

    @Override
    public String toString() {
        StringBuilder r1 = new StringBuilder();
        r1.append("BTS Machine System ||\n");
        r1.append("Employee list ||\n");
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) {
                continue;
            }
            r1.append(employees[i] + "\n");
        }
        r1.append("RabbitCard List ||\n");
        for (int i = 0; i < rabbitCards.length; i++) {
            if (rabbitCards[i] == null) {
                continue;
            }
            r1.append(rabbitCards[i] + "\n");
        }
        return r1.toString();
    }
}