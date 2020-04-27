package machineSystem;
import dataBase.RabbitcardDatabase;
import dataBase.StationDataBase;
import java.io.IOException;
import memberSystem.CardStatus;
import memberSystem.CardType;
import memberSystem.Person;
import memberSystem.RabbitCard;
public class TestMachineSystem{
    public static void main(String[] args) throws IOException {
        //Set up database
        StationDataBase s1 = new StationDataBase();
        s1.createTables();
        s1.insertDB(1, "Siam", 1);
        s1.insertDB(2, "Chit Lom", 2);
        s1.insertDB(3, "Nana", 3);
        s1.insertDB(4, "Sukhumvit", 4);
        s1.insertDB(5, "Phrom Phong", 5);
        s1.insertDB(6, "Thong Lo", 6);
        s1.insertDB(7, "Phra Khanong", 7);
        RabbitcardDatabase b1 = new RabbitcardDatabase();
        b1.createTables();
        

        MachineSystem v1 = new MachineSystem();
        
        Person p1 = new Person("Tanawat","Srijarattanon",1001,66874453245L);
        Person p2 = new Person("Somchai","Somtam",1002,66894908646L);
        
        RabbitCard r1 = new RabbitCard(1001,p1,100,3,CardStatus.ACTIVE,CardType.STUDENT);
        RabbitCard r2 = new RabbitCard(1002,p2,200,5,CardStatus.ACTIVE,CardType.ADULT);
        
        v1.addRabbitCard("A03","btsemp", r1);
        v1.addRabbitCard("A03","btsemp", r2);
        
        v1.Travel(1001, "Siam", "Nana");
        
        v1.RedeemPoint("A03","btsemp", 1001, 50);
        
        System.out.println(v1);
    }
}
