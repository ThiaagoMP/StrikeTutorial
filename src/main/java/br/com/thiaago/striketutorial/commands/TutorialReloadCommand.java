package br.com.thiaago.striketutorial.commands;

import br.com.thiaago.strikecore.StrikeCore;
import br.com.thiaago.striketutorial.controller.TutorialController;
import lombok.AllArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

@AllArgsConstructor
public class TutorialReloadCommand implements CommandExecutor {

    private TutorialController tutorialController;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("tutorial.reload")) {
            sender.sendMessage(StrikeCore.getInstance().getPREFIX() + "§cSem permissão!");
            return true;
        }
        tutorialController.loadPoints();
        sender.sendMessage(StrikeCore.getInstance().getPREFIX() + "§aTutorial recarregado!");
        return false;
    }
}
