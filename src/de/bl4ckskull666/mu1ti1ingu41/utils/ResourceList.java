/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bl4ckskull666.mu1ti1ingu41.utils;

import de.bl4ckskull666.mu1ti1ingu41.Mu1ti1ingu41;
import java.io.IOException;
import java.net.URL;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public final class ResourceList {
    public static Collection<String> getResources(String folder, String ending) {
        CodeSource src = Mu1ti1ingu41.class.getProtectionDomain().getCodeSource();
        Collection<String> list = new ArrayList<>();
        if(src != null) {
            try {
                URL jar = src.getLocation();
                ZipInputStream zip = new ZipInputStream( jar.openStream());
                ZipEntry ze;
                
                while( ( ze = zip.getNextEntry() ) != null ) {
                    String entryName = ze.getName();
                    if(entryName.startsWith(folder) && (ending.isEmpty() || entryName.endsWith(ending)))
                        list.add(entryName);
                }
            } catch (IOException ex) {
                Mu1ti1ingu41.getPlugin().getLogger().log(Level.WARNING, "Error : ", ex);
            }
        }
        return list;
    }
    
    public static Collection<String> getResources(Class pl, String folder) {
        CodeSource src = pl.getProtectionDomain().getCodeSource();
        Collection<String> list = new ArrayList<>();
        if(src != null) {
            try {
                URL jar = src.getLocation();
                ZipInputStream zip = new ZipInputStream(jar.openStream());
                ZipEntry ze;
                
                while( ( ze = zip.getNextEntry() ) != null ) {
                    String entryName = ze.getName();
                    if(entryName.startsWith(folder) && entryName.endsWith(".yml"))
                        list.add(entryName);
                }
            } catch (IOException ex) {
                Mu1ti1ingu41.getPlugin().getLogger().log(Level.WARNING, "Error : ", ex);
            }
        }
        return list;
    }
}
