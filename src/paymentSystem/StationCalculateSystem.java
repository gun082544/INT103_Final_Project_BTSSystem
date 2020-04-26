package paymentSystem;
import dataBase.StationDataBase;
public abstract class StationCalculateSystem extends StationDataBase{
    public String nameStation[];
    public int distance[];
    public StationCalculateSystem(){
        nameStation = SelectNameFromSTATION();
        distance = SelectDistanceFromSTATION();
    }
    private int stationCheckAt (String StationCheck){
        distance = SelectDistanceFromSTATION();
        nameStation = SelectNameFromSTATION();
        for (int i = 0; i < nameStation.length; i++) {
            if(StationCheck.equals(nameStation[i])){
                return distance[i];
            }
            
        }
        return -1;
    }
    public int calculateDistance(String checkIn,String checkOut){
        int atIn = stationCheckAt(checkIn);
        int atOut = stationCheckAt(checkOut);
        int result = 0;
      if(atOut >= atIn){
       result = Math.abs(atOut) - Math.abs(atIn);
      }
      if(atOut < atIn){
          result = Math.abs(atIn) - Math.abs(atOut);
      }
        return result;
    }
}
