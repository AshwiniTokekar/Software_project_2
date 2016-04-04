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
public class Airlock {
    
    int parkingspace=0;
    int in_airlock=0;
    
    int getparkingspaces()
    {
        return parkingspace;
    }
    int getnumberofvehiclesinairlock()
    {
        return in_airlock;
    }
    Boolean full()
    {
        return !(parkingspace<100);
    }
    
    Boolean entry(Vehicle v)
    {
        if(v.vehicle_type.equals("Normal")&&in_airlock<=3)
        {
            parkingspace+=in_airlock;
            v.set_airlock_id(this);
            System.out.println(v.get_vehicle_id());
            return true;
        }
        else if("SpaceOre".equals(v.vehicle_type)&&in_airlock==1)
        {
            parkingspace++;
            return true;
        }
        return false;
    }
    
    Boolean exit(Vehicle v)
    {
        if(v.vehicle_type.equals("Normal")&&in_airlock<=3)
        {
            parkingspace-=in_airlock;
            return true;
        }
        else if(v.vehicle_type.equals("SpaceOre")&&in_airlock==1)
        {
            parkingspace--;
            return true;
        }    
        return false;
    }
    Boolean opengate(int v_count,String type)
    {
        if(in_airlock+v_count<=3&&type.equals("Normal"))
        {
            in_airlock+=v_count;
            return true;            
        }
        else if(type.equals("SpaceOre")&&v_count+in_airlock==1)
        {
            in_airlock++;
            return true;
        }
       
        return false;
            
    }        
   void closegate()
   {
       in_airlock=0;
   }
}
