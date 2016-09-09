/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bl4ckskull666.mu1ti1ingu41.utils;

import de.bl4ckskull666.mu1ti1ingu41.Language;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.entity.Player;

/**
 *
 * @author PapaHarni
 */
public final class Utils {
    public static boolean isHoverAction(String str) {
        try {
            HoverEvent.Action a = HoverEvent.Action.valueOf(str.toUpperCase());
            return (a != null);
        } catch(Exception ex) {
            return false;
        }
    }
    
    public static boolean isClickAction(String str) {
        try {
            ClickEvent.Action a = ClickEvent.Action.valueOf(str.toUpperCase());
            return (a != null);
        } catch(Exception ex) {
            return false;
        }
    }
    
    public static String getDateTime(UUID uuid) {
        String format = Language.getMu1ti1ingu41Text(uuid, "format.string", "%H:%i:%s");
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        String tmp = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
        format = format.replace("%d", tmp);
        format = format.replace("%D", (tmp.length() == 1?"0":"") + tmp);
        
        tmp = String.valueOf(cal.get(Calendar.MONTH)+1);
        format = format.replace("%m", tmp);
        format = format.replace("%M", (tmp.length() == 1?"0":"") + tmp);
        
        tmp = String.valueOf(cal.get(Calendar.YEAR));
        format = format.replace("%y", tmp);
        format = format.replace("%Y", tmp.substring(2));
        
        format = format.replace("%w", String.valueOf(cal.get(Calendar.DAY_OF_WEEK)));
        format = format.replace("%z", String.valueOf(cal.get(Calendar.DAY_OF_YEAR)));
        format = format.replace("%W", String.valueOf(cal.get(Calendar.WEEK_OF_YEAR)));
        
        String[] ld = {"","Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
        String[] sd = {"","Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
        tmp = String.valueOf(cal.get(Calendar.DAY_OF_WEEK));
        format = format.replace("%l", Language.getMu1ti1ingu41Text(uuid, "format.days.short." + tmp, sd[Integer.parseInt(tmp)]));
        format = format.replace("%L", Language.getMu1ti1ingu41Text(uuid, "format.days.long." + tmp, ld[Integer.parseInt(tmp)]));
        
        String[] lm = {"January","February","March","April","May","June","Jule","August","September","October","November","December"};
        String[] sm = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
        tmp = String.valueOf(cal.get(Calendar.MONTH));
        format = format.replace("%f", Language.getMu1ti1ingu41Text(uuid, "format.months.short." + tmp, sm[Integer.parseInt(tmp)]));
        format = format.replace("%F", Language.getMu1ti1ingu41Text(uuid, "format.months.long." + tmp, lm[Integer.parseInt(tmp)]));
        
        String am_pm = "am";
        if(cal.get(Calendar.HOUR_OF_DAY) > 12)
            am_pm = "pm";
        
        format = format.replace("%a", am_pm); //am/pm
        format = format.replace("%A", am_pm.toUpperCase()); //AM/PM
        
        String hour = String.valueOf(cal.get(Calendar.HOUR)+1);
        format = format.replace("%g", hour); //Hour 1-12
        format = format.replace("%G", (hour.length() == 1?"0":"") + hour); //Hour 01-12
        hour = String.valueOf(cal.get(Calendar.HOUR_OF_DAY));
        format = format.replace("%h", hour); //Hour 0-23
        format = format.replace("%H", (hour.length() == 1?"0":"") + hour); //Hour 00-23
        
        format = format.replace("%i", (cal.get(Calendar.MINUTE) < 10?"0":"") + String.valueOf(cal.get(Calendar.MINUTE))); //Minutes 00-59
        format = format.replace("%s", (cal.get(Calendar.SECOND) < 10?"0":"") + String.valueOf(cal.get(Calendar.SECOND))); //Seconds 00-59
        format = format.replace("%S", String.valueOf(cal.get(Calendar.MILLISECOND))); //Millesconds 0-999
        
        return ChatColor.translateAlternateColorCodes('&', format);
    }
    
    public static String getLastColor(String str) {
        if(str.length() < 2)
            return "";
        
        List<String> cc = new ArrayList<>();
        cc.add("0");
        cc.add("1");
        cc.add("2");
        cc.add("3");
        cc.add("4");
        cc.add("5");
        cc.add("6");
        cc.add("7");
        cc.add("8");
        cc.add("9");
        cc.add("a");
        cc.add("b");
        cc.add("c");
        cc.add("d");
        cc.add("e");
        cc.add("f");

        String code = "";
        for(int i = 0; i < (str.length()-1); i++) {
            char c1 = str.charAt(i);
            char c2 = str.charAt(i+1);
            
            if(c1 != '&' && c1 != '§')
                continue;
            
            if(cc.contains(String.valueOf(c2)))
                code = String.valueOf(c1) + String.valueOf(c2);
        }
        return code;
    }
    
    public static String getLastFormat(String str) {
        if(str.length() < 2)
            return "";
        
        List<String> fc = new ArrayList<>();
        fc.add("k");
        fc.add("l");
        fc.add("m");
        fc.add("n");
        fc.add("o");
        fc.add("r");
        
        String code = "";
        for(int i = 0; i < (str.length()-1); i++) {
            char c1 = str.charAt(i);
            char c2 = str.charAt(i+1);
            
            if(c1 != '&' && c1 != '§')
                continue;
            
            if(fc.contains(String.valueOf(c2)))
                code = String.valueOf(c1) + String.valueOf(c2);
        }
        return code;
    }
}
