/**
 * SakuraUtils - Package: net.syamn.utils.craftbukkit.v1_4_6
 * Created: 2012/12/23 20:08:31
 */
package net.syamn.utils.craftbukkit.v1_4_6;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_4_6.CraftWorld;
import org.bukkit.craftbukkit.v1_4_6.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_4_6.ChunkCoordIntPair;
import net.minecraft.server.v1_4_6.EnumSkyBlock;
import net.minecraft.server.v1_4_6.Packet;
import net.syamn.utils.craftbukkit.api.CraftBukkitAbstraction;

/**
 * CraftBukkitHandler (CraftBukkitHandler.java)
 * @author syam(syamn)
 */
public class CraftBukkitHandler implements CraftBukkitAbstraction{
    @Override
    public void forceLightLevel(World world, int x, int y, int z, int level) {
        net.minecraft.server.v1_4_6.World w = ((CraftWorld) world).getHandle();
        w.b(EnumSkyBlock.BLOCK, x, y, z, level);
    }

    @Override
    public void recalculateLightLevel(World world, int x, int y, int z) {
        net.minecraft.server.v1_4_6.World w = ((CraftWorld) world).getHandle();
        w.z(x, y, z);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void addUpdateChunkQueue(Player player, int x, int z) {
        ((CraftPlayer) player).getHandle().chunkCoordIntPairQueue.add(new ChunkCoordIntPair(x, z));
    }
    
    @Override
    public void sendPacket(Player player, Object packet){
        if (!(packet instanceof Packet)){
            throw new IllegalArgumentException("Could not cast to Packet!");
        }
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket((Packet) packet);
    }
    
    @Override
    public void sendPacketToOnline(Object... packets){
        for (final Player player : Bukkit.getOnlinePlayers()){
            if (player == null || !player.isOnline()){
                continue;
            }
            for (Object packet : packets){
                sendPacket(player, packet);
            }
        }
    }
}
