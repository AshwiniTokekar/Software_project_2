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

import java.util.Date;
public class Payment {
    
    long getpaymentamount(Vehicle v)
    {
        v.setexittime(new Date());
        long time;
        time = v.get_exit_time()-v.get_entry_time();
        time/=60*60*1000;
        if(time<6)
            return time/1000;
        else if(time<=24)
            return (long) (1.5*(time-6/1000)+6*60);
        
        return -1;
    }
    
}
