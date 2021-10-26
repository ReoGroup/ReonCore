package github.ReoGroup.ReonCore.listeners.minecraft;

/*
 * @author d1m0s23
 * @created 2021 10 26
 */

import github.ReoGroup.ReonCore.BungeeMain.Main;
import github.ReoGroup.ReonCore.internal.entities.Messages;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayerChatListener implements Listener {
    private static final Pattern URL_PATTERN = Pattern.compile("(?:(https?)://)?([-\\w_.]{2,}\\.[a-z]{2,4})(/\\S*)?");
    private static final Messages message = Main.getInstance().getMessages();

    @EventHandler
    public void onChat(ChatEvent event) {
        ProxiedPlayer player = (ProxiedPlayer) event.getSender();
        Matcher matcher = URL_PATTERN.matcher(event.getMessage());

        if (matcher.find()) {
            matcher.replaceAll(message.getString("messages.error.url_deleted"));
        }
    }
}
