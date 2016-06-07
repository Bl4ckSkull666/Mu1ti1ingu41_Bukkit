/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bl4ckskull666.mu1ti1ingu41;

import de.bl4ckskull666.mu1ti1ingu41.commands.LanguageCommand;
import de.bl4ckskull666.mu1ti1ingu41.listener.PluginMessageReceiver;
import de.bl4ckskull666.mu1ti1ingu41.listener.PlayerJoin;
import java.util.UUID;
import org.bukkit.Bukkit;
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
        
        _isSpigot = Bukkit.getVersion().toLowerCase().contains("spigot");
        if(getConfig().getBoolean("save-user-language", true))
            UUIDLanguages.loadPlayerLanguages();

        UUIDLanguages._players.put(UUID.fromString("00000000-0000-0000-0000-000000000000"), getConfig().getString("default-language", "en"));
        Language.loadLanguage();
        
        if(getConfig().getBoolean("use-bungeecord", false) && _isSpigot) {
            getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
            getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new PluginMessageReceiver());
        }
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getCommand("language").setExecutor(new LanguageCommand());
        
        _firstPrefix = getConfig().getBoolean("prefix-for-time", false);
    }
    
    @Override
    public void onDisable() {
        if(getConfig().getBoolean("save-user-language", true))
            UUIDLanguages.savePlayerLanguages();
        
    }
    
    private static boolean _isSpigot = false;
    public static boolean isSpigot() {
        return _isSpigot;
    }
    
    private static boolean _firstPrefix = false;
    public static boolean getFirstPrefix() {
        return _firstPrefix;
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
     * @param pl = YourPlugin
     * @param folder = folder in your plugin , like languages in default
     */
    public static void loadExternalDefaultLanguage(Plugin pl, String folder) {
        Language.loadDefaultLanguage(pl, folder);
    }
}
