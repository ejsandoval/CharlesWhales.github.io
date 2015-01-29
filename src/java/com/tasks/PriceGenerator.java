package com.tasks;

import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class PriceGenerator implements Runnable {
    
    @Override
    public void run() {
    
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        //System.out.println("Hello! Current time is: " + dateFormat.format(date));
    }
    
}
