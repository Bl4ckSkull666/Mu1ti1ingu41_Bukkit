/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bl4ckskull666.mu1ti1ingu41.listener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import de.bl4ckskull666.mu1ti1ingu41.UUIDLanguages;
import java.util.UUID;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

/**
 *
 * @author PapaHarni
 */
public class PluginMessageReceiver implements PluginMessageListener {
    @Override
    public void onPluginMessageReceived(String c, Player p, byte[] m) {
        if (!c.equalsIgnoreCase("Mu1ti1ingu41"))
            return;

        ByteArrayDataInput in = ByteStreams.newDataInput(m);
        String sub = in.readUTF();
        if(sub.equalsIgnoreCase("player")) {
            UUID uuid = UUID.fromString(in.readUTF());
            String lang = in.readUTF();
            UUIDLanguages._players.put(uuid, lang);
        }
    }
}
