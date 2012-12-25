/**
 * SakuraUtils - Package: net.syamn.utils.craftbukkit
 * Created: 2012/12/23 20:26:18
 */
package net.syamn.utils.craftbukkit;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.plugin.Plugin;

import net.syamn.utils.craftbukkit.api.CraftBukkitAbstraction;

/**
 * CraftBukkitAccessor (CraftBukkitAccessor.java)
 * @author syam(syamn)
 */
public class CraftBukkitAccessor {
    private static CraftBukkitAbstraction cb = null;
    
    public static CraftBukkitAbstraction init(final Plugin plugin) throws InstantiationException,
                    IllegalAccessException, IllegalArgumentException, InvocationTargetException,
                    NoSuchMethodException, SecurityException, ClassNotFoundException{
        final String serverPackage = plugin.getServer().getClass().getPackage().getName(); // get CraftServer package
        final String ver = serverPackage.substring(serverPackage.lastIndexOf(".") + 1); // get package version by CraftServer package name
        if (ver.equals("craftbukkit")){
            // no versioned package, throw error
            throw new IllegalStateException("This CraftBukkit is not versioned package.");//TODO don't throw error, use default package
        }
        
        //final Class<?> clazz = Class.forName("net.syamn.utils.craftbukkit." + ver + ".CraftBukkitHandler");
        final Class<?> clazz = Class.forName(plugin.getClass().getPackage().getName() + ".utils.craftbukkit." + ver + ".CraftBukkitHandler");
        
        // check clazz implementing CraftBukkitAbstraction
        if (CraftBukkitAbstraction.class.isAssignableFrom(clazz)){
            cb = (CraftBukkitAbstraction) clazz.getConstructor().newInstance();
        }else{
            throw new IllegalStateException("Class " + clazz.getName() + " does not implement CraftBukkitAbstraction");
        }
        
        return cb;
    }
    
    public static CraftBukkitAbstraction getCB(){
        return cb;
    }
}
