package machineSystem;

import dataBase.RabbitcardDatabase;
import dataBase.StationDataBase;
import java.io.IOException;
import memberSystem.CardStatus;
import memberSystem.CardType;
import memberSystem.Person;
import memberSystem.RabbitCard;

public class TestMachineSystem {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //Set up database
        StationDataBase s1 = new StationDataBase();
        RabbitcardDatabase b1 = new RabbitcardDatabase();
        s1.createTables();
        s1.insertDB(1, "Siam", 1);
        s1.insertDB(2, "Chit Lom", 2);
        s1.insertDB(3, "Nana", 3);
        s1.insertDB(4, "Sukhumvit", 4);
        s1.insertDB(5, "Phrom Phong", 5);
        s1.insertDB(6, "Thong Lo", 6);
        s1.insertDB(7, "Phra Khanong", 7);
        b1.createTables();

        //Create Machine 
        MachineSystem v1 = new MachineSystem();
        MachineInterface m1 = new MachineInterface(v1);
        
        v1.deleteEmployee("lengleng0909", "0869788258");
        Person p1 = new Person("Tanapat", "Loharattanavisit", 1003, 66874453245L);
        v1.addEmployee("lengleng0909", "0869788258", p1);

        Person p2 = new Person("Nachanon", "Montikanon", 1001, 66874453245L);
        RabbitCard r1 = new RabbitCard(1001, p2, 100, 3, CardStatus.ACTIVE, CardType.STUDENT);
        v1.addRabbitCard("lengleng0909", "0869788258", r1);

        Person p3 = new Person("Punnapop", "Chalor", 1002, 66774562157L);
        RabbitCard r2 = new RabbitCard(1002, p3, 200, 5, CardStatus.ACTIVE, CardType.ADULT);
        v1.addRabbitCard("lengleng0909", "0869788258", r2);

        v1.RedeemPoint("lengleng0909", "0869788258", 1001, 100);
        
        //Invalid ID RabbitCard
        v1.RedeemPoint("lengleng0909", "0869788258", 1005, 100);
        //Wrong ID Employee
        v1.RedeemPoint("BaBaBa", "0869788258", 1003, 100);

        m1.memberUseMachine(r1);
        m1.memberUseMachine(r2);

        System.out.println(v1);

    }
}
