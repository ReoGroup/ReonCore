package github.ReoGroup.ReonCore.internal.utils;

/*
 * @author d1m0s23
 * @created 2021 10 26
 */

import github.ReoGroup.ReonCore.BungeeMain.Main;
import net.md_5.bungee.api.ChatColor;

public class MessageFormatUtil {
//    Messages
    public static String info(String message) {
        return ChatColor.GRAY + Main.getInstance().getDescription().getName() + ChatColor.RESET + "=>" + ChatColor.GREEN + message;
    }

    public static String warn(String message) {
        return ChatColor.GRAY + Main.getInstance().getDescription().getName() + ChatColor.RESET + "=>" + ChatColor.YELLOW + message;
    }

    public static String error(String message) {
        return ChatColor.GRAY + Main.getInstance().getDescription().getName() + ChatColor.RESET + "=>" + ChatColor.RED + message;
    }

// translate colors with char &
    public static String translateColors(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
