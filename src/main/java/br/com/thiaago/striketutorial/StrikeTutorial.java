package br.com.thiaago.striketutorial;

import br.com.thiaago.striketutorial.commands.TutorialCommand;
import br.com.thiaago.striketutorial.commands.TutorialExitCommand;
import br.com.thiaago.striketutorial.commands.TutorialReloadCommand;
import br.com.thiaago.striketutorial.config.PointConfig;
import br.com.thiaago.striketutorial.controller.TutorialController;
import br.com.thiaago.striketutorial.inventory.ScrollerInventory;
import br.com.thiaago.striketutorial.listeners.PlayerListeners;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public final class StrikeTutorial extends JavaPlugin {

    private TutorialController tutorialController;
    private PointConfig pointConfig;

    public static StrikeTutorial getInstance() {
        return getPlugin(StrikeTutorial.class);
    }

    @Override
    public void onEnable() {
        pointConfig = new PointConfig();
        pointConfig.setupConfig();
        tutorialController = new TutorialController(new LinkedList<>(), new ConcurrentHashMap<>(),
                pointConfig, pointConfig.getLocationExit(), pointConfig.getTutorialConfig().getStringList("commandsAlloweds"));
        tutorialController.loadPoints();
        Bukkit.getPluginManager().registerEvents(new PlayerListeners(tutorialController), this);
        Bukkit.getPluginManager().registerEvents(new ScrollerInventory(), this);
        getCommand("tutorial").setExecutor(new TutorialCommand(tutorialController));
        getCommand("tutorialexit").setExecutor(new TutorialExitCommand(tutorialController));
        getCommand("tutorialreload").setExecutor(new TutorialReloadCommand(tutorialController));
    }

}
