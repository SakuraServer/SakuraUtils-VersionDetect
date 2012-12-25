/**
 * SakuraUtils - Package: net.syamn.utils
 * Created: 2012/12/26 4:46:46
 */
package net.syamn.utils;

import java.util.logging.Logger;

import org.bukkit.plugin.Plugin;

/**
 * LogUtil (LogUtil.java)
 * @author syam(syamn)
 */
public class LogUtil {
    private static Logger logger;
    
    public static void init(final Plugin plugin){
        logger = plugin.getLogger();
    }
}
