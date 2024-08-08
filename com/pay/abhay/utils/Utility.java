package com.pay.abhay.utils;

import java.sql.Timestamp;
import java.util.Random;


public class Utility {

    /*
     * Generate Account number
     */
        public static String generateAcNum(){
        //UUID randomUUID = UUID.randomUUID();
        Random rnd = new Random();
        int part1 = rnd.nextInt(654321);
        int part2 = rnd.nextInt(99999);

        return String.valueOf(part1 + "" + part2);
    }

    /*
     * Get Timestamp 
     */
    public static String getTimestamp(){
        //dont use security class timestamp use only sql class timestamp
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return String.valueOf(timestamp);
    }

}
