/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bl4ckskull666.mu1ti1ingu41;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 *
 * @author PapaHarni
 */
public final class UUIDLanguages {
    public static HashMap<UUID, String> _players = new HashMap<>();
    
    public static String getPlayerLanguage(UUID uuid) {
        if(_players.containsKey(uuid))
            return _players.get(uuid);
        return Mu1ti1ingu41.getPlugin().getConfig().getString("default-language", "en");
    }
    
    public static void loadPlayerLanguages() {
        if(!Mu1ti1ingu41.getPlugin().getDataFolder().exists())
            Mu1ti1ingu41.getPlugin().getDataFolder().mkdir();
        
        File file = new File(Mu1ti1ingu41.getPlugin().getDataFolder(), "users.yml");
        FileConfiguration f = YamlConfiguration.loadConfiguration(file);
        
        for(String key: f.getKeys(false))
            _players.put(UUID.fromString(key), f.getString(key));
    }
    
    public static void savePlayerLanguages() {
        if(!Mu1ti1ingu41.getPlugin().getDataFolder().exists())
            Mu1ti1ingu41.getPlugin().getDataFolder().mkdir();
        
        File file = new File(Mu1ti1ingu41.getPlugin().getDataFolder(), "users.yml");
        FileConfiguration f = YamlConfiguration.loadConfiguration(file);
        
        for(Map.Entry<UUID, String> me: _players.entrySet())
            f.set(me.getKey().toString(), me.getValue());
        
        try {
            f.save(file);
        } catch (IOException ex) {
            Mu1ti1ingu41.getPlugin().getLogger().log(Level.WARNING, "Error on save Players language.");
        }
    }
}
