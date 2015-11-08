package pt.ua.deti.fff.f_battery;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Test_Battery 
{
    public static void main( String[] args )
    {
        BatteryStatus data = new BatteryStatus();
        data.setLimit(30);
        
        Scanner in = new Scanner(System.in);
        int id, status;
        while (true)
        {
            System.out.print("\nIntroduza o id do bombeiro: " );
            id = in.nextInt();
            do
            {
                System.out.print("\nIntroduza o estado da bateria: " );
                status = in.nextInt();
            } while (id < 0);
            
            try {
                data.setStatus(id, status);
            } catch (LowBatteryWarningException ex) {
                Logger.getLogger(Test_Battery.class.getName()).log(Level.SEVERE, null, ex);
                
                System.out.println("A bateria do bombeiro " + ex.getFireman_id() + " está a " + ex.getStatus() + "%");
            }           
        }
    }
}
