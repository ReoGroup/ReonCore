package github.ReoGroup.ReonCore.internal.entities;
import org.bukkit.ChatColor;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.user.UserStatus;
import github.ReoGroup.ReonCore.BungeeMain.Main;

import java.io.*;
import java.util.*;

public class DiscordClient {
    public DiscordApi api;
    public BotConfig config = new BotConfig();

    public void Client() {
        Main.getInstance().getLogger().info(ChatColor.GREEN + "ReonCore welcomes you!");
        try {
            config.CreateConfig();
        } catch (IOException ignored) {
            Main.getInstance().getLogger().warning("owo");
        }
    }

    public void run() {
        this.Client();
        Main.getInstance().getLogger().info(ChatColor.GREEN + "Initialization of the client API...");
        this.api = new DiscordApiBuilder()
                .setToken((String) config.getConfig("token"))
                .login()
                .join();

        Main.getInstance().getLogger().info(ChatColor.GREEN + "Setting the status and activity...");

        String[] statuses = (String[]) config.getConfig("statusesBot");
        Thread statusDiscord = new Thread(() -> {
            Random r = new Random();
            while (true) {
                api.updateActivity((String) config.getConfig("statusesType"), statuses[r.nextInt(statuses.length)]);
                try {
                    Thread.sleep(40 * 1000);
                } catch (InterruptedException e) {
                    Main.getInstance().getLogger().warning("An error has been detected: " + e);
                }
            }
        });
        statusDiscord.start();
        api.updateStatus(UserStatus.IDLE);

        Main.getInstance().getLogger().info(ChatColor.GREEN + "Connecting to the WEBSOCKET...");
        Main.getInstance().getLogger().info(ChatColor.GREEN + "Done! I logged in under the account " + api.getUserById(api.getClientId()).join().getDiscriminatedName() + "!");

    }



}