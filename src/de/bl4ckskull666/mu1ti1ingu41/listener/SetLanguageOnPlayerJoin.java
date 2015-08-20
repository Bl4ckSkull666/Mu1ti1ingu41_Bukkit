/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bl4ckskull666.mu1ti1ingu41.listener;

import de.bl4ckskull666.mu1ti1ingu41.Language;
import de.bl4ckskull666.mu1ti1ingu41.Mu1ti1ingu41;
import de.bl4ckskull666.mu1ti1ingu41.UUIDLanguages;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 *
 * @author PapaHarni
 */
public class SetLanguageOnPlayerJoin implements Listener {
    
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoin(PlayerJoinEvent e) {
        if(!UUIDLanguages._players.containsKey(e.getPlayer().getUniqueId())) {
            Language.setPlayerLanguage(e.getPlayer().getUniqueId());
            e.getPlayer().sendMessage(Language.getMessage(Mu1ti1ingu41.getPlugin(),
                            e.getPlayer().getUniqueId(),
                            "auto-language",
                            "Set your language to %language%",
                            new String[] {"%language%"},
                            new String[] {getLanguageName(UUIDLanguages.getPlayerLanguage(e.getPlayer().getUniqueId()))}
                    )
            );
        }
    }
    
    private String getLanguageName(String shortLang) {
        for(String key: Mu1ti1ingu41.getPlugin().getConfig().getConfigurationSection("short-language").getKeys(false)) {
            if(Mu1ti1ingu41.getPlugin().getConfig().getString("short-language." + key).equalsIgnoreCase(shortLang))
                return key;
        }
        return shortLang;
    }
}
