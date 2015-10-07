package de.bl4ckskull666.mu1ti1ingu41;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PapaHarni
 */
public class example extends JavaPlugin implements CommandExecutor {
    private static example _plugin;
    
    @Override
    public void onEnable() {
        _plugin = this;
        getCommand("example").setExecutor(this);
        Mu1ti1ingu41.loadExternalDefaultLanguage(this, "languages");
    }
    
    @Override
    public boolean onCommand(CommandSender s, Command c, String l, String[] a) {
        UUID uuid = UUID.fromString("00000000-0000-0000-0000-000000000000");
        if(s instanceof Player)
            uuid = ((Player)s).getUniqueId();
        
        if(a.length > 0) {
            if(a[0].equalsIgnoreCase("me") && s instanceof Player) {
                Player p = (Player)s;
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(p.getFirstPlayed());
                s.sendMessage(
                        Language.getMessage(
                                example.getPlugin(), 
                                uuid, 
                                "path-in-yaml-language-file", 
                                "I'm %name%, my IP is %ip%. I'm joined first time here at %firstjoin%. My Location is %world% X:%x% Y:%y% Z:%z%",
                                new String[] {
                                    "%name%",
                                    "%ip%",
                                    "%firstjoin%",
                                    "%world%",
                                    "%x%",
                                    "%y%",
                                    "%z%"
                                },
                                new String[] {
                                    p.getName(), 
                                    p.getAddress().getAddress().getHostAddress(), 
                                    getDateTime(cal), 
                                    p.getWorld().getName(), 
                                    String.valueOf(p.getLocation().getBlockX()), 
                                    String.valueOf(p.getLocation().getBlockY()), 
                                    String.valueOf(p.getLocation().getBlockZ())
                                }
                        )
                );
                return true;
            }
        }
        s.sendMessage(Language.getMessage(example.getPlugin(), uuid, "path-in-yaml-language-file" , "This command is only for insider. And you seem to be an outsider. Good Bye ;-)"));
        return true;
    }
    
    public static example getPlugin() {
        return _plugin;
    }
    
    private String getDateTime(Calendar cal) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:ii:ss");
        dateFormat.setTimeZone(cal.getTimeZone());
        return dateFormat.format(cal.getTime());
    }
}
