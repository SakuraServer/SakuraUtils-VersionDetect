/**
 * SakuraUtils - Package: net.syamn.utils.craftbukkit.api
 * Created: 2012/12/23 20:03:35
 */
package net.syamn.utils.craftbukkit.api;

import org.bukkit.World;

/**
 * CraftBukkitAbstraction (CraftBukkitAbstraction.java)
 * @author syam(syamn)
 */
public interface CraftBukkitAbstraction {
    public void forceLightLevel(World world, int x, int y, int z, int level);
    public void recalculateLightLevel(World world, int x, int y, int z);
}
