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
import java.util.Date;
public class Vehicle implements Runnable {
    
    int vehicle_id;
    String vehicle_type;
    Date entry_time;
    Date exit_time;
    String permit_type;
    String Payment_type;
    int Payment;
    Airlock airlock_id;
    private Thread t;
    
    Vehicle( int vehicle_id,String vehicle_type,String permit_type,String Payment_type)
    {
        this.vehicle_id=vehicle_id;
        this.vehicle_type=vehicle_type;
        this.entry_time=new Date();
        this.exit_time=null;
        this.permit_type=permit_type;
        this.Payment_type=Payment_type;
        this.Payment=0;
    }
    
    int get_vehicle_id()
    {
        return this.vehicle_id;
    }
    
    String get_vehicle_type()
    {
        return this.vehicle_type;
    }
    
    String get_permit_type()
    {
        return this.permit_type;
    }
    
    long get_entry_time()
    {
        return this.entry_time.getTime();
    }
    
    long get_exit_time()
    {
        return this.exit_time.getTime();
    }
    
    void set_airlock_id(Airlock num)
    {
        airlock_id=num;
    }
    
    Airlock get_airlock_id()
    {
        return airlock_id;
    }
    void setexittime(Date date) {
        this.exit_time=date;
    }

    @Override
    public void run() {
        Client.handle_entry(this, 1);
    }
}
