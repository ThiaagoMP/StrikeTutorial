package br.com.thiaago.striketutorial.config;

import br.com.thiaago.striketutorial.StrikeTutorial;
import lombok.Data;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

@Data
public class PointConfig {

    private FileConfiguration tutorialConfig;
    private File tutorialFile;

    public void setupConfig() {
        if (!StrikeTutorial.getInstance().getDataFolder().exists()) {
            StrikeTutorial.getInstance().getDataFolder().mkdir();
        }
        tutorialFile = new File(StrikeTutorial.getInstance().getDataFolder(), "tutorial.yml");
        String[] keys = {"x", "y", "z", "yaw", "pitch"};
        if (!tutorialFile.exists()) {
            try {
                tutorialFile.createNewFile();
                tutorialConfig = YamlConfiguration.loadConfiguration(tutorialFile);
                tutorialConfig.set("commandsAlloweds", Arrays.asList("tutorialexit"));
                ConfigurationSection section = tutorialConfig.createSection("locationExit");
                section.set("world", "spawn");
                for (String key : keys) {
                    section.set(key, 0);
                }
                tutorialConfig.createSection("points");
                save();
            } catch (IOException e) {
                Bukkit.getConsoleSender().sendMessage("§2[StrikeTutorial] §cNão foi possível criar/carregar o tutorial.yml");
            }
        }
        tutorialConfig = YamlConfiguration.loadConfiguration(tutorialFile);
    }

    public Location getLocationExit() {
        ConfigurationSection section = tutorialConfig.getConfigurationSection("locationExit");
        return new Location(Bukkit.getWorld(section.getString("world")), section.getDouble("x"), section.getDouble("y"), section.getDouble("z"),
                (float) section.getDouble("yaw"), (float) section.getDouble("pitch"));
    }

    public Location getLocation(String path) {
        ConfigurationSection section = tutorialConfig.getConfigurationSection("points").getConfigurationSection(path);
        return new Location(Bukkit.getWorld(section.getString("world")), section.getInt("x"), section.getInt("y"),
                section.getInt("z"), (float) section.getDouble("yaw"), (float) section.getDouble("pitch"));
    }

    @SneakyThrows
    public void save() {
        tutorialConfig.save(tutorialFile);
    }


}
