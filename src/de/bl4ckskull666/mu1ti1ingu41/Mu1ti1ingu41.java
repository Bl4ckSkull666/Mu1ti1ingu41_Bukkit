/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bl4ckskull666.mu1ti1ingu41;

import de.bl4ckskull666.mu1ti1ingu41.commands.LanguageCommand;
import de.bl4ckskull666.mu1ti1ingu41.listener.SetLanguageOnPlayerJoin;
import java.util.UUID;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author PapaHarni
 */
public class Mu1ti1ingu41 extends JavaPlugin {
    @Override
    public void onEnable() {
        _plugin = this;
        if(!getDataFolder().exists()) {
            getDataFolder().mkdir();
            getConfig().options().copyDefaults(true);
        }
        saveConfig();
        
        if(getConfig().getBoolean("save-user-language", true))
            UUIDLanguages.loadPlayerLanguages();

        UUIDLanguages._players.put(UUID.fromString("00000000-0000-0000-0000-000000000000"), getConfig().getString("default-language", "en"));
        Language.loadLanguage();
        getCommand("language").setExecutor(new LanguageCommand());
        getServer().getPluginManager().registerEvents(new SetLanguageOnPlayerJoin(), this);
    }
    
    @Override
    public void onDisable() {
        if(getConfig().getBoolean("save-user-language", true))
            UUIDLanguages.savePlayerLanguages();
        
    }
    
    //Statics
    private static Mu1ti1ingu41 _plugin;
    public static Mu1ti1ingu41 getPlugin() {
        return _plugin;
    }
    
    public static String name() {
        return _plugin.getDescription().getName();
    }
    
    /**
     * 
     * @param pl = YourPlugin.class
     * @param folder = folder in your plugin , like language in default
     */
    public static void loadExternalDefaultLanguage(Plugin pl, String folder) {
        Language.loadDefaultLanguage(pl, folder);
    }
}
