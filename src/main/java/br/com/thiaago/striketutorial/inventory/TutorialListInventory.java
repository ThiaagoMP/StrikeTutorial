package br.com.thiaago.striketutorial.inventory;

import br.com.thiaago.strikecore.api.itemBuilder.Item;
import br.com.thiaago.strikecore.api.itemBuilder.NBTEditor;
import br.com.thiaago.striketutorial.controller.TutorialController;
import lombok.AllArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class TutorialListInventory implements Listener {

    private TutorialController tutorialController;

    public void open(Player player) {
        List<ItemStack> items = new ArrayList<>();
        tutorialController.getPoints().forEach(point -> items.add(NBTEditor.set(new Item(Material.BOOK).
                setDisplayName(point.getDisplayName().replace("&", "§")), point.getOrder(), "order")));
        new ScrollerInventory(items, "§aTutorial", player);
    }

}
