//Responsible by Thanapat Loharattanavisit 62130500034
package machineSystem;

import dataBase.StationDataBase;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import memberSystem.RabbitCard;
import paymentSystem.PaymentSystem;

public class MachineInterface{
    private MachineSystem machineSystem;
    public MachineInterface(MachineSystem m1) throws IOException {
        this.machineSystem = m1;
    }
    
    public void memberUseMachine(RabbitCard r1) throws IOException {
        StationDataBase s1 = new StationDataBase();
        String station[] = s1.SelectNameFromSTATION();
        int menu;
        do {
            System.out.println("Hi " + r1.getFirstname());
            System.out.println("BTS Skytrain Machine System:");
            System.out.println("1.Travel");
            System.out.println("2.Get current money");
            menu = inputNumberForCase(1, 2, 0);
            switch (menu) {
                case 1:
                    for (int i = 0; i < station.length; i++) {
                        System.out.println((i+1)+". Station "+station[i]);
                    }
                    System.out.print("Please input origin station ");
                    int origin = inputNumberForCase(1, station.length, 0);
                    if(origin == 0)break;
                    System.out.print("Please input destination station ");
                    int destination = inputNumberForCase(1, station.length, 0);
                    if(destination == 0)break;
                    System.out.println("Station "+station[origin-1]+" to Station "+station[destination-1]);
                    PaymentSystem payment = new PaymentSystem();
                    System.out.println("Price : "+ payment.getTotalPay(station[origin-1], station[destination-1]));
                    String yn;
                    do{
                        System.out.print("Do you want to proceed? [y/n] :");
                        Scanner Sc = new Scanner(System.in);
                        yn = Sc.nextLine();
                        if(yn.toLowerCase().equals("y")){
                            machineSystem.Travel(r1.getRbc_idCard(),station[origin-1] ,station[destination-1]);
                        }
                        else if(yn.toLowerCase().equals("n")){
                            break;
                        }
                        
                    }while(!yn.toLowerCase().equals("y"));
                    break;
                case 2:
                    System.out.println("Your current money : " + r1.getRbc_money());
                    System.out.println("----------------------------------------------------");
                    break;
            }
        System.out.println("----------------------------------------------------");
        } while (menu != 0);

    }

    private static int inputNumberForCase(int since, int until, int exit) {
        int i;
        do {
            System.out.print("Input number " + since + " - " + until + " (press " + exit + " for exit) :");
            try {
                Scanner Sc = new Scanner(System.in);
                i = Sc.nextInt();
            } catch (InputMismatchException ex) {
                i = -1;
            }
            if (i == exit) {
                return i;
            } else if (i < since || i > until) {
                System.out.println("Please try again.");
            }
        } while (i < since || i > until);
        return i;
    }
}
