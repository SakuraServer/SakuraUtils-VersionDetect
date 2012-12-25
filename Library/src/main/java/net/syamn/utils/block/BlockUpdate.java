/**
 * SakuraUtils - Package: net.syamn.utils.block
 * Created: 2012/12/23 21:59:56
 */
package net.syamn.utils.block;

import java.util.ArrayList;
import java.util.List;

import net.syamn.utils.craftbukkit.CraftBukkitAccessor;
import net.syamn.utils.craftbukkit.api.CraftBukkitAbstraction;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

/**
 * BlockUpdate (BlockUpdate.java)
 * @author syam(syamn)
 */
public class BlockUpdate {
    private final World world;
    private final List<SimpleCoords> changedBlocks = new ArrayList<SimpleCoords>();
    private final CraftBukkitAbstraction cb;
    
    // set max value to min variables
    private int minX = Integer.MAX_VALUE;
    private int minZ = Integer.MAX_VALUE;
    // set min value to max variables
    private int maxX = Integer.MIN_VALUE;
    private int maxZ = Integer.MIN_VALUE;
    
    public BlockUpdate(World world){
        this.world = world;
        this.cb = CraftBukkitAccessor.getCB();
        if (cb == null){
            throw new IllegalStateException("CraftBukkitAbstraction is not available");
        }
    }
    
    private List<SimpleCoords> calculateChunks(){
        List<SimpleCoords> ret = new ArrayList<SimpleCoords>();
        if (changedBlocks.size() == 0) return ret;
        
        int x0 = minX >> 4;
        int x1 = maxX >> 4;
        int z0 = minZ >> 4;
        int z1 = maxZ >> 4;
        
        for (int x = x0; x <= x1; x++){
            for (int z = z0; z <= z1; z++){
                ret.add(new SimpleCoords(x, z));
            }
        }
        return ret;
    }
    
    private void sendChanges(List<SimpleCoords> affected){
        int limit = Bukkit.getServer().getViewDistance() << 4;
        
        int x0 = minX - limit; int z0 = minZ - limit;
        int x1 = maxX + limit; int z1 = maxZ + limit;
        
        for (Player player : world.getPlayers()){
            Location loc = player.getLocation();
            int x = loc.getBlockX();
            int z = loc.getBlockZ();
            if (x >= x0 && x <= x1 && z >= z0 && z <= z1){
                for (SimpleCoords pair : affected){
                    cb.addUpdateChunkQueue(player, pair.x, pair.z);
                }
            }
        }
    }
    
    public void notifyClients(){
        List<SimpleCoords> affected = calculateChunks();
        if (!affected.isEmpty()){
            sendChanges(affected);
        }
        
        changedBlocks.clear();
        minX = minZ = Integer.MAX_VALUE;
        maxX = maxZ = Integer.MIN_VALUE;
    }
    
    public void addChangedBlock(Block block){
        int x = block.getX();
        int z = block.getZ();
        
        minX = Math.min(minX, x);
        maxX = Math.max(maxX, x);
        minZ = Math.min(minZ, z);
        maxZ = Math.max(maxZ, z);
        
        changedBlocks.add(new SimpleCoords(x, z));
    }
    
    private class SimpleCoords{
        final int x, z;
        public SimpleCoords(int x, int z){
            this.x = x;
            this.z = z;
        }
    }
}
