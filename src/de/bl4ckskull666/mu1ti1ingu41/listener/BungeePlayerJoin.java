/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bl4ckskull666.mu1ti1ingu41.listener;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import de.bl4ckskull666.mu1ti1ingu41.Mu1ti1ingu41;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 *
 * @author PapaHarni
 */
public class BungeePlayerJoin implements Listener {
    
    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerJoin(PlayerJoinEvent e) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("request");
        out.writeUTF(e.getPlayer().getUniqueId().toString());
        e.getPlayer().sendPluginMessage(Mu1ti1ingu41.getPlugin(), "Mu1ti1ingu41", out.toByteArray());
    }
}
