/**
 * SakuraUtils - Package: net.syamn.utils.block
 * Created: 2012/12/23 20:48:44
 */
package net.syamn.utils.block;

import net.syamn.utils.craftbukkit.CraftBukkitAccessor;
import net.syamn.utils.craftbukkit.api.CraftBukkitAbstraction;

import org.bukkit.block.Block;

/**
 * BlockUtil (BlockUtil.java)
 * @author syam(syamn)
 */
public class BlockUtil {
    /**
     * ブロックの光度を変更する
     * @param block
     * @param level
     */
    public static void forceLightLevel(Block block, int level){
        CraftBukkitAbstraction cb = CraftBukkitAccessor.getCB();
        if (cb == null){
            return;
        }
        
        cb.forceLightLevel(block.getWorld(), block.getX(), block.getY(), block.getZ(), level);
    }
    
    public static void recalculateLightLevel(Block block){
        CraftBukkitAbstraction cb = CraftBukkitAccessor.getCB();
        if (cb == null){
            return;
        }
        
        cb.recalculateLightLevel(block.getWorld(), block.getX(), block.getY(), block.getZ());
        System.out.println("force calcled!");
    }
}
