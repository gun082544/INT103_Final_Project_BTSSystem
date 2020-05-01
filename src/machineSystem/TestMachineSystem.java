package machineSystem;
import dataBase.RabbitcardDatabase;
import dataBase.StationDataBase;
import java.io.IOException;
import memberSystem.CardStatus;
import memberSystem.CardType;
import memberSystem.Person;
import memberSystem.RabbitCard;
public class TestMachineSystem{
    public static void main(String[] args) throws IOException, ClassNotFoundException {
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
        Person p1 = new Person("Punnapop","Chalor",1004,66774562157L);
        v1.addEmployee("gunzaza", "1234", p1);
        
        Person p2 = new Person("Tanapat","Loharattanavisit",1003,66874453245L);
//        v1.addEmployee("lengleng0909","0869788258", p2);
        
        
        Person p3 = new Person("Tanawat","Srijarattanon",1001,66874453245L);
        RabbitCard r1 = new RabbitCard(1001,p3,100,3,CardStatus.ACTIVE,CardType.STUDENT);
        
        Person p4 = new Person("Somchai","Somtam",1002,66894908646L);
        RabbitCard r2 = new RabbitCard(1002,p4,200,5,CardStatus.ACTIVE,CardType.ADULT);
        

        v1.addRabbitCard("lengleng0909","0869788258", r1);
        v1.addRabbitCard("lengleng0909","0869788258", r2);
        
//        v1.deleteEmployee("A01","btsemp");
//        v1.deleteEmployee("A03","btsemp");
        
        v1.Travel(1001, "Siam", "Nana");
        
        v1.RedeemPoint("lengleng0909","0869788258", 1001, 50);
        
        System.out.println(v1);
    }
}
