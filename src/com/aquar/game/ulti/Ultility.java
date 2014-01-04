package com.aquar.game.ulti;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ultility {
    
    public static String getDateStr(Date date) {
        String format = "yyyy/MM/dd";
        String ret = new SimpleDateFormat(format).format(date);
        return ret;
    }
}
