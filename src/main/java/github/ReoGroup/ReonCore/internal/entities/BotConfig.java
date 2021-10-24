package github.ReoGroup.ReonCore.internal.entities;
import org.bukkit.ChatColor;
import org.yaml.snakeyaml.Yaml;
import github.ReoGroup.ReonCore.BungeeMain.Main;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;

public class BotConfig {
    public Map<String, Object> config;

    public void CreateConfig() throws IOException {
        try {
            this.config = new Yaml().load(
                    new InputStreamReader(new FileInputStream("./config.yml"))
            );
            Main.getInstance().getLogger().info(ChatColor.GREEN + "The configuration file is loaded.");
        } catch (FileNotFoundException error) {
            Files.copy(
                    Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("config.yml")),
                    Paths.get(new File("").getAbsolutePath() + "/config.yml")
            );
            Main.getInstance().getLogger().warning("The configuration file has been created. Enter valid data there and run me again.");
            System.exit(0);
        }
    }

    public Object getConfig(Object key) {
        return this.config.get(key);
    }

}
