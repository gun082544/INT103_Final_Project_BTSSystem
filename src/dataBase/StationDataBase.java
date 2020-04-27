package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StationDataBase {

    public void insertDB(int sta_id, String Sta_name, int sta_distance) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://103.86.49.133:3306/BTSMachineSystem", "KodlnwSoftwarehouse", "Kodlnw1234");
                Statement stm = conn.createStatement();) {

            stm.executeUpdate("INSERT INTO STATION VALUES(" + sta_id + ",'" + Sta_name + "'," + sta_distance + ")");
            System.out.println("Insert Station "+Sta_name+" has been sucessful.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void createTables(){
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://103.86.49.133:3306/BTSMachineSystem", "KodlnwSoftwarehouse", "Kodlnw1234");
                Statement stm = conn.createStatement();) {
            try {stm.executeUpdate("DROP TABLE STATION");} catch (SQLException ex) {}
 
            try {stm.executeUpdate("CREATE TABLE STATION (sta_id INT NOT NULL,sta_name VARCHAR(200),sta_distance INT,PRIMARY KEY (sta_id))");} catch (SQLException ex) {}
            System.out.println("Create table has sucessful.");
            System.out.println("---------------------------------------------------------------------------");
    }   catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    public String[] SelectNameFromSTATION() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://103.86.49.133:3306/BTSMachineSystem", "KodlnwSoftwarehouse", "Kodlnw1234");
                Statement stm = conn.createStatement();) {
            int count = 0, i = 0;
            ////////////////////////
            ResultSet stationNameCount = stm.executeQuery("SELECT * FROM STATION");
            while (stationNameCount.next()) {
                count++;
            }
            String Station_name[] = new String[count];
            ////////////////////////
            ResultSet stationName = stm.executeQuery("SELECT * FROM STATION");
            while (stationName.next()) {
                Station_name[i++] = stationName.getString("sta_name");
            }
            return Station_name;
            ////////////////////////
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
        public int[] SelectDistanceFromSTATION() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://103.86.49.133:3306/BTSMachineSystem", "KodlnwSoftwarehouse", "Kodlnw1234");
                Statement stm = conn.createStatement();) {
            int count = 0, i = 0;
            ////////////////////////
            ResultSet stationNameCount = stm.executeQuery("SELECT * FROM STATION");
            while (stationNameCount.next()) {
                count++;
            }
            int Station_distance[] = new int[count];
            ////////////////////////
            ResultSet stationName = stm.executeQuery("SELECT * FROM STATION");
            while (stationName.next()) {
                Station_distance[i++] = stationName.getInt("sta_distance");
            }
            return Station_distance;
            ////////////////////////
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
