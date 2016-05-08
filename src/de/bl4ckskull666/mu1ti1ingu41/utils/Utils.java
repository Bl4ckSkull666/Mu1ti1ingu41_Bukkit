/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bl4ckskull666.mu1ti1ingu41.utils;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;

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
}
