/**
 * SakuraUtils - Package: net.syamn.utils.craftbukkit.api
 * Created: 2012/12/23 20:03:35
 */
package net.syamn.utils.craftbukkit.api;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

/**
 * CraftBukkitAbstraction (CraftBukkitAbstraction.java)
 * @author syam(syamn)
 */
public interface CraftBukkitAbstraction {
    public void forceLightLevel(World world, int x, int y, int z, int level);
    
    public void recalculateLightLevel(World world, int x, int y, int z);

    public void addUpdateChunkQueue(Player player, int x, int z);
    
    public void sendPacket(Player player, Object packet);

    public void sendPacketToOnline(Object... packets);
    
    //public void sendPacketNearby(Location location, Object packet, double radius);
}
