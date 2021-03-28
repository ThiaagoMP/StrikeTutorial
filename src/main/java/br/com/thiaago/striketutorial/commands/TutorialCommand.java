package br.com.thiaago.striketutorial.commands;

import br.com.thiaago.strikecore.StrikeCore;
import br.com.thiaago.striketutorial.controller.TutorialController;
import br.com.thiaago.striketutorial.inventory.TutorialListInventory;
import lombok.AllArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@AllArgsConstructor
public class TutorialCommand implements CommandExecutor {

    private TutorialController tutorialController;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cApenas players podem executar isso!");
            return true;
        }
        Player player = (Player) sender;
        if (args.length == 0) {
            new TutorialListInventory(tutorialController).open(player);
        } else {
            if (!tutorialController.getPlayers().containsKey(player)) {
                player.sendMessage(StrikeCore.getInstance().getPREFIX() + "§cVocê não está no tutorial! Use /tutorial para entrar!");
                return true;
            }
            int stage = 0;
            try {
                stage = Integer.valueOf(args[0]);
            } catch (NumberFormatException error) {
                player.sendMessage(StrikeCore.getInstance().getPREFIX() + "§cUse /tutorial (estágio)!");
                return true;
            }
            if (stage <= tutorialController.getPoints().size())
                tutorialController.startTutorial(player, stage);
            else {
                tutorialController.exitTutorial(player);
                player.sendMessage(StrikeCore.getInstance().getPREFIX() + "§cAcabou os tutoriais! Você pode revisar usando /tutorial");
            }
        }
        return false;
    }
}
