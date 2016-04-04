/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parking_System;

/**
 *
 * @author ashwini
 */
public class Console {
    
    int[] get_empty_spaces(Airlock A[])
    {
        int[] emptyspaces=new int[A.length];
        for(int i=0;i<A.length;i++)
        {
            emptyspaces[i]=100-A[i].getparkingspaces();
        }
        return emptyspaces;
    }
    
    String get_services()
    {
        String title="Parking Time\tService\tCost\n";
        String h_1="1 hour\tCar Wash\t10\n";
        String h_2="2 hour\tCar Wash+Wheel Alignment\t10\n";
        String h_3="3 hour\tQuater Service\t10\n";
        String h_4="4 hour\tHalf Service\t10\n";
        String h_5="5 hour\tFull Service\t10\n";
        String h_6="6 hour\tFull Service+Engine Check\t10\n";
        String full=title+h_1+h_2+h_3+h_4+h_5+h_6;
                
        return full;
    }
    
    Boolean[] getairlockstatus(Airlock A[])
    {
        Boolean[] full = new Boolean[A.length];
        for(int i=0;i<A.length;i++)
        {
            full[i] = A[i].getnumberofvehiclesinairlock() >= 3;
        }
        return full;
    }
    
}
