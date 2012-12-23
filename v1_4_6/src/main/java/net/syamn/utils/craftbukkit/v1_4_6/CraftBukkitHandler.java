/**
 * SakuraUtils - Package: net.syamn.utils.craftbukkit.v1_4_6
 * Created: 2012/12/23 20:08:31
 */
package net.syamn.utils.craftbukkit.v1_4_6;

import org.bukkit.World;
import org.bukkit.craftbukkit.v1_4_6.CraftWorld;

import net.minecraft.server.v1_4_6.EnumSkyBlock;
import net.syamn.utils.craftbukkit.api.CraftBukkitAbstraction;

/**
 * CraftBukkitHandler (CraftBukkitHandler.java)
 * @author syam(syamn)
 */
public class CraftBukkitHandler implements CraftBukkitAbstraction{
    /* (非 Javadoc)
     * @see net.syamn.utils.craftbukkit.api.CraftBukkitAbstraction#forceLightLevel(org.bukkit.World, int, int, int, int)
     */
    @Override
    public void forceLightLevel(World world, int x, int y, int z, int level) {
        net.minecraft.server.v1_4_6.World w = ((CraftWorld) world).getHandle();
        w.b(EnumSkyBlock.BLOCK, x, y, z, level);
    }

    /* (非 Javadoc)
     * @see net.syamn.utils.craftbukkit.api.CraftBukkitAbstraction#recalculateLightLevel(org.bukkit.World, int, int, int)
     */
    @Override
    public void recalculateLightLevel(World world, int x, int y, int z) {
        net.minecraft.server.v1_4_6.World w = ((CraftWorld) world).getHandle();
        w.z(x, y, z);
    }
}
