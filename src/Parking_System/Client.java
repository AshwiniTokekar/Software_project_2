package Parking_System;

import javax.swing.JOptionPane;

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
    static Vehicle V[] = new Vehicle[300];
    static int v_in=0;
    static void init()
    {
        for(int i=0; i<A.length;i++)
        {
            A[i]=new Airlock();
        }
    }
    Airlock[] getairlocks()
    {
        return A;
    }
   public static void main(String args[])
   {
        init();
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
                v.set_airlock_id(i);
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
                V[v_in]=new Vehicle(v);
              System.out.println(V[v_in]);
                v_in++;
                break;
            }
            }
        }
               
    }
    
   static Boolean handle_exit(int vid) {
        // TODO code application logic here
        Vehicle temp = null;
        int aid = 0;
        if(v_in == 0)
        {
          System.out.println("IN if");
         return false;   
        }
       try{
            for(int i=0;i<v_in;i++)
        {
            if(V[i].vehicle_id==vid)
            {
                temp=V[i];
               for(int j=i;j<v_in;j++)
               {
                   V[i]=V[i+1];
               }
               break;
            }
        }
        
        aid = temp.get_airlock_id();
       }catch(NullPointerException e)
       {
         JOptionPane.showMessageDialog(null,"Exit not possible");  
       }
       
        return A[aid].exit(temp);
        
        
    }
}
