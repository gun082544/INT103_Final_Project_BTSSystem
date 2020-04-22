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

public class test {
    public void writeRabbitCardLog(RabbitCard r1, String atIn, String atOut) throws IOException {
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
    public static void main(String[] args) throws IOException {
        RabbitCard r1 = new RabbitCard(1001, new Person("Somchai", "Somtam", "1102170018970", "0806849641"),100, 100, CardStatus.ACTIVE, CardType.ADULT);
        RabbitcardDatabase DBR1 = new RabbitcardDatabase();
        DBR1.insertDB(0, new Person("Somchai", "Somtam", "1102170018970", "0806849641"), 0, 0, CardStatus.ACTIVE, CardType.ADULT);
        test mac1 = new test();
        mac1.writeRabbitCardLog(r1, "Siam", "leng house");
    }
}
