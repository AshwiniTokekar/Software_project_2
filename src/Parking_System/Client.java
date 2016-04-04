package Parking_System;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ashwini
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    static Airlock A[ ] = new Airlock[3];
  
   public static void main(String args[])
   {
        int v_n=1; //Number of vehicles entering at time
        int v_id=1;
        String pa_t="Credit";
        String per_t = "Hologram";
        String v_t = "Normal";
        Vehicle v = new Vehicle(v_id,v_t,per_t,pa_t);
        handle_entry(v,v_n);
        MainScreen.Userscreen();
   }
   static void handle_entry(Vehicle v,int v_n) {
        // TODO code application logic here
        
        
        Survilence sur=new Survilence();        
        if (sur.detectvehicle(v))
        {
            for (int i=0;i<A.length;i++)
            {
                if(!A[i].full())
            {
                System.out.println("Entry by Airlock "+i);
                if(A[i].opengate(v_n, v.vehicle_type));
                {
                    for(int j=0;j<v_n;j++)
                    {
                         A[i].entry(v);
                         System.out.println(v.get_airlock_id());
                    }
                }
                A[i].closegate();
            }
            }
        }
               
    }
    
   static void handle_exit(Vehicle v,int v_n) {
        // TODO code application logic here
        
        
        
        
    }
}
