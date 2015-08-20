/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bl4ckskull666.mu1ti1ingu41.commands;

import de.bl4ckskull666.mu1ti1ingu41.Language;
import de.bl4ckskull666.mu1ti1ingu41.Mu1ti1ingu41;
import de.bl4ckskull666.mu1ti1ingu41.UUIDLanguages;
import java.util.UUID;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author PapaHarni
 */
public class LanguageCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender s, Command c, String l, String[] a) {
        if(a.length > 0) {
            if(a[0].equalsIgnoreCase("reload")) {
                UUID uuid = s instanceof Player?((Player)s).getUniqueId():UUID.fromString("00000000-0000-0000-0000-000000000000");
                if(!s.hasPermission("mu1ti1ing41.admin")) {
                    s.sendMessage(Language.getMessage(Mu1ti1ingu41.getPlugin(), uuid, "command.reload.no-permission", "You dont have permission to use this command."));
                    return true;
                }
                
                Language.loadLanguage();
                s.sendMessage(Language.getMessage(Mu1ti1ingu41.getPlugin(), uuid, "command.reload.successful", "Language files has been reloaded."));
                return true;
            }
        }
        
        if(!(s instanceof Player)) {
            s.sendMessage("This command can only run by a player.");
            return true;
        }
        
        Player p = (Player)s;
        if(a.length < 1) {
            p.sendMessage(Language.getMessage(Mu1ti1ingu41.getPlugin(), p.getUniqueId(), "command.need-language", "Please select one of the following language :"));
            String ava = "";
            for(String str: Mu1ti1ingu41.getPlugin().getConfig().getConfigurationSection("short-language").getKeys(false))
                ava += ava.isEmpty()?"§e":"§9, §e" + str;
            p.sendMessage(ava);
            return true;
        }
        
        if(!Mu1ti1ingu41.getPlugin().getConfig().isString("short-language." + a[0].toLowerCase())) {
            p.sendMessage(Language.getMessage(Mu1ti1ingu41.getPlugin(), p.getUniqueId(), "command.unknown-language", "Can't find the wished language."));
            return true;
        }
        
        UUIDLanguages._players.put(p.getUniqueId(), Mu1ti1ingu41.getPlugin().getConfig().getString("short-language." + a[0].toLowerCase()));
        p.sendMessage(Language.getMessage(Mu1ti1ingu41.getPlugin(), p.getUniqueId(), "command.changed-language", "The Wished Language has been changed."));
        return true;
    }
}