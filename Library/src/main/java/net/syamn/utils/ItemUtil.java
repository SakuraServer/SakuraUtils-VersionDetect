/**
 * SakuraUtils - Package: net.syamn.utils
 * Created: 2012/12/29 6:55:47
 */
package net.syamn.utils;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.Material;

/**
 * ItemUtil (ItemUtil.java)
 * @author syam(syamn)
 */
public class ItemUtil {
    private final static List<Integer> possibleRepairs;
    static{
        possibleRepairs = new LinkedList<Integer>();
        for (int i = 256; i <= 259; i++){
            possibleRepairs.add(i);
        }
        for (int i = 267; i <= 279; i++){
            possibleRepairs.add(i);
        }
        for (int i = 283; i <= 286; i++){
            possibleRepairs.add(i);
        }
        for (int i = 290; i <= 294; i++){
            possibleRepairs.add(i);
        }
        for (int i = 298; i <= 317; i++){
            possibleRepairs.add(i);
        }
        possibleRepairs.add(Material.SHEARS.getId());
        possibleRepairs.add(Material.BOW.getId());
        possibleRepairs.add(Material.FISHING_ROD.getId());
        possibleRepairs.add(Material.CARROT_STICK.getId());
    }
    
    public boolean repairable(final int id){
        return possibleRepairs.contains(id);
    }
}
