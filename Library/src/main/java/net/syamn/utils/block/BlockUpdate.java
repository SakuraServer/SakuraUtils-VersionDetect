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
    private final List<ChangedBlock> changedBlocks = new ArrayList<ChangedBlock>();
    private final CraftBukkitAbstraction cb;
    
    // set max value to min variables
    private int minX = Integer.MAX_VALUE;
    private int minZ = Integer.MAX_VALUE;
    // set min value to max variables
    private int maxX = Integer.MIN_VALUE;
    private int maxZ = Integer.MIN_VALUE;
    
    private int changedCount = 0;
    
    public BlockUpdate(World world){
        this.world = world;
        this.cb = CraftBukkitAccessor.getCB();
        if (cb == null){
            throw new IllegalStateException("CraftBukkitAbstraction is not available");
        }
    }
    
    private List<ChunkCoords> calculateChunks(){
        List<ChunkCoords> ret = new ArrayList<ChunkCoords>();
        if (changedCount == 0) return ret;
        
        int x0 = minX >> 4;
        int x1 = maxX >> 4;
        int z0 = minZ >> 4;
        int z1 = maxZ >> 4;
        
        for (int x = x0; x <= x1; x++){
            for (int z = z0; z <= z1; z++){
                ret.add(new ChunkCoords(x, z));
            }
        }
        return ret;
    }
    
    private void sendChanges(List<ChunkCoords> affected){
        int limit = Bukkit.getServer().getViewDistance() << 4;
        int x0 = minX - limit;
        int x1 = maxX + limit;
        int z0 = minZ - limit;
        int z1 = maxZ + limit;
        
        for (Player player : world.getPlayers()){
            Location loc = player.getLocation();
            int x = loc.getBlockX();
            int z = loc.getBlockZ();
            if (x >= x0 && x <= x1 && z >= z0 && z <= z1){
                for (ChunkCoords pair : affected){
                    cb.addUpdateChunkQueue(player, pair.x, pair.z);
                }
            }
        }
    }
    
    public void notifyClients(){
        List<ChunkCoords> affected = calculateChunks();
        if (!affected.isEmpty()){
            sendChanges(affected);
            changedCount = 0;
            minX = minZ = Integer.MAX_VALUE;
            maxX = maxZ = Integer.MIN_VALUE;
        }
    }
    
    public void addChangedBlock(Block block){
        int x = block.getX();
        int z = block.getZ();
        
        minX = Math.min(minX, x);
        maxX = Math.max(maxX, x);
        minZ = Math.min(minZ, z);
        maxZ = Math.max(maxZ, z);
        
        changedCount++;
        changedBlocks.add(new ChangedBlock(x, block.getY(), z, block.getTypeId()));
    }
    
    private class ChunkCoords{
        public final int x, z;
        public ChunkCoords(int x, int z){
            this.x = x;
            this.z = z;
        }
    }
    private class ChangedBlock{
        public final int x, y, z;
        public final int id;
        
        public ChangedBlock(int x, int y, int z, int id){
            this.x = x;
            this.y = y;
            this.z = z;
            this.id = id;
        }
    }
}
