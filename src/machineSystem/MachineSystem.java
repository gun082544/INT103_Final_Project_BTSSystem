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

public class MachineSystem {

    private Employee employees[];
    private RabbitCard rabbitCards[];
    private int employeeCount;
    private int rabbitCardCount;

    public MachineSystem() throws IOException {
        RabbitcardDatabase b1 = new RabbitcardDatabase();
        InputOutputEmployee e1 = new InputOutputEmployee();
        employees = e1.ReadEmployeeData();
        for (int i = 0; i < employees.length; i++) {
            employeeCount++;
        }
        //Write IO into array & set employeeCount  **COMPLETE**
        rabbitCards = b1.selectRabbitCard();
        for (int i = 0; i < rabbitCards.length; i++) {
            rabbitCardCount++;
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
        if (this.isEmployeeFull() == true) {
            Employee temp[] = employees;
            this.employees = new Employee[employeeCount + 10];
            for (int i = 0; i < temp.length; i++) {
                employees[i] = temp[i];
            }
            //Check Array that full ,If full then increase size **COMPLETE**
        }
        employees[employeeCount++] = new Employee(id, pass, p1);
        InputOutputEmployee e1 = new InputOutputEmployee();
        e1.EmployeeWriter(employees);
        //Add new employee into array and IO **COMPLETE**

    }

    public void addRabbitCard(String id, String pass, RabbitCard r1) {
        if (this.isRabbitCardFull() == true) {
            RabbitCard temp[] = rabbitCards;
            this.rabbitCards = new RabbitCard[rabbitCardCount + 10];
            for (int i = 0; i < temp.length; i++) {
                rabbitCards[i] = temp[i];
            }
            //Check Array that full ,If full then increase size **COMPLETE**
        }
        for (int i = 0; i < employees.length; i++) {
            if (id.equals(employees[i].getEmployee_id()) && pass.equals(employees[i].getPassword())) {
                this.rabbitCards[rabbitCardCount++] = r1;
                RabbitcardDatabase b1 = new RabbitcardDatabase();
                b1.insertDBPerson(r1.getFirstname(), r1.getLastName(), r1.getC_id(), r1.getPhone());
                b1.insertDBRabbitcard(r1.getRbc_idCard(), r1.getRbc_money(), r1.getRbc_point(),
                        r1.getRbc_cardStatus() == CardStatus.ACTIVE ? "ACTIVE" : "EXPIRED",
                        r1.getRbc_cardType() == CardType.STUDENT ? "STUDENT" : "ADULT");
                System.out.println("RabbitCard " + r1.getRbc_idCard() + " has been added sucessful.");
                return;
                //Add new rabbitcard into array and IO **COMPLETE**
                //Only Employee can use this method using id & password
            }
        }
        System.out.println("Incorrect ID or Password.");
    }

    public void RedeemPoint(String id, String pass, int id_card, int money) {
        for (int i = 0; i < employees.length; i++) {
            if (id.equals(employees[i].getEmployee_id()) && pass.equals(employees[i].getPassword())) {
                for (int j = 0; j < rabbitCards.length; j++) {
                    if (rabbitCards[i].getRbc_idCard() == id_card) {
                        rabbitCards[i].setRbc_money(rabbitCards[i].getRbc_money() + money);
                        System.out.println("RabbitCard ID " + rabbitCards[i].getRbc_idCard() + " has redeem sucessful.");
                        //Decrease money in rabbitcard **COMPLETE**
                        //Only Employee can use this method using id & password
                        //and Update money in DB **INPROCESS**
                    }
                }
                System.out.println("RabbitCard ID is Undefined");
                return;
            } else {
                System.out.println("Incorrect ID or Password.");
            }
            return;
        }
    }

    public String listAllRabbitCard() {
        //**INPROCESS**
        return null;
    }

    public void Travel(int id_card, String atIn, String atOut) throws IOException {
        for (int i = 0; i < rabbitCards.length; i++) {
            if (rabbitCards[i] == null) {
                return;
            }
            if (rabbitCards[i].getRbc_idCard() == id_card) {
                if (rabbitCards[i].getRbc_cardStatus() == CardStatus.EXPIRED) {
                    System.out.println("RabbitCard ID " + rabbitCards[i].getRbc_idCard() + " : Out of money");
                    return;
                }
                PaymentSystem payment = new PaymentSystem();
                int totalPoint = rabbitCards[i].getRbc_money() - payment.getTotalPay(atIn, atOut);
                rabbitCards[i].setRbc_money(totalPoint);
                System.out.println("RabbitCard ID " + rabbitCards[i].getRbc_idCard() + " : Station " + atIn
                        + " to Station " + atOut + " has successful");
                System.out.println("Your current money : " + rabbitCards[i].getRbc_money());
                //and Update money in DB
                this.writeRabbitCardLog(rabbitCards[i], atIn, atOut);
                if (rabbitCards[i].getRbc_money() < 1) {
                    rabbitCards[i].setRbc_cardStatus(CardStatus.EXPIRED);
                    System.out.println("RabbitCard ID " + rabbitCards[i].getRbc_idCard() + " : Now your card are expired."
                            + "Please redeem your rabbitcard !");
                //Caculate station & payment **COMPLETE**
                }
                return;
            }
            System.out.println("RabbitCard ID is Undefined");
        }
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
        r1.append("BTS Machine System :\n");
        r1.append("Employee list :\n");
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) {
                continue;
            }
            r1.append(employees[i] + "\n");
        }
        r1.append("RabbitCard List :\n");
        for (int i = 0; i < rabbitCards.length; i++) {
            if (rabbitCards[i] == null) {
                continue;
            }
            r1.append(rabbitCards[i] + "\n");
        }
        return r1.toString();
    }
}
