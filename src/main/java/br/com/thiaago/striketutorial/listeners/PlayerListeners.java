package br.com.thiaago.striketutorial.listeners;

import br.com.thiaago.strikecore.StrikeCore;
import br.com.thiaago.strikecore.api.itemBuilder.NBTEditor;
import br.com.thiaago.striketutorial.controller.TutorialController;
import lombok.AllArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

@AllArgsConstructor
public class PlayerListeners implements Listener {

    private TutorialController tutorialController;

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        tutorialController.getPlayers().remove(event.getPlayer());
    }

    @EventHandler
    public void onKick(PlayerKickEvent event) {
        tutorialController.getPlayers().remove(event.getPlayer());
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        if (tutorialController.getPlayers().containsKey(event.getPlayer()) &&
                !tutorialController.getCommandsAlloweds().contains(event.getMessage().split(" ")[0].replace("/", ""))) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(StrikeCore.getInstance().getPREFIX() + "§cVocê não pode executar esse comando estando no tutorial!");
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getInventory().getTitle().equals("§aTutorial")) {
            if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR ||
                    !NBTEditor.contains(event.getCurrentItem(), "order")) return;
            Player player = (Player) event.getWhoClicked();
            if (event.getSlot() == 49) {
                tutorialController.startTutorial(player, 1);
                return;
            }
            tutorialController.startTutorial(player, NBTEditor.getInt(event.getCurrentItem(), "order"));
        }
    }

}
