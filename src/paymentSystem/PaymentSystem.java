package paymentSystem;
public class PaymentSystem extends StationCalculateSystem{
 private final int STATION_RATE = 15;

 public int getTotalPay(String checkIn,String checkOut){
    int result = this.calculateDistance(checkIn, checkOut);
    if(result>7){
        result = result + (STATION_RATE *4);
        return result;
    }
    else if(result>5){
         result = result + (STATION_RATE *3);
         return result;
    }
    else if(result>3){
        result = result + (STATION_RATE *2);
        return result;
    }
    else result += STATION_RATE;
    return result;
}
}
