package br.com.thiaago.striketutorial.commands;

import br.com.thiaago.strikecore.StrikeCore;
import br.com.thiaago.striketutorial.controller.TutorialController;
import lombok.AllArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@AllArgsConstructor
public class TutorialExitCommand implements CommandExecutor {

    private TutorialController tutorialController;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cApenas players podem executar isso!");
            return true;
        }
        Player player = (Player) sender;
        if (!tutorialController.getPlayers().containsKey(player)) {
            player.sendMessage(StrikeCore.getInstance().getPREFIX() + "§cVocê não está no tutorial! Use /tutorial para entrar!");
            return true;
        }
        tutorialController.exitTutorial(player);
        player.sendMessage(StrikeCore.getInstance().getPREFIX() + "§aVocê saiu do tutorial!");
        return false;
    }
}
