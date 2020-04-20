package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StationDataBase {

    public void insertDB(int sta_id, String Sta_name, int sta_distance) {
        try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/INT103_Project", "lengleng0909", "0869788258");
                Statement stm = conn.createStatement();) {

            int row = stm.executeUpdate("INSERT INTO STATION VALUES(" + sta_id + ",'" + Sta_name + "'," + sta_distance + ")");
            System.out.println(row);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String[] SelectNameFromSTATION() {
        try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/INT103_Project", "lengleng0909", "0869788258");
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
        try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/INT103_Project", "lengleng0909", "0869788258");
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
