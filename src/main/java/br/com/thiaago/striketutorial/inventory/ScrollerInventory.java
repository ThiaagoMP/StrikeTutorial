package br.com.thiaago.striketutorial.inventory;

import br.com.thiaago.strikecore.api.itemBuilder.Item;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ScrollerInventory implements Listener {

    private static final String nextPageName = ChatColor.AQUA + "Próxima página";
    private static final String previousPageName = ChatColor.AQUA + "Página anterior";
    private static Map<UUID, ScrollerInventory> users = new ConcurrentHashMap<>();
    private ArrayList<Inventory> pages = new ArrayList<>();
    private UUID id;
    private int currPage = 0;

    public ScrollerInventory() {
    }

    public ScrollerInventory(List<ItemStack> items, String name, Player player) {
        id = UUID.randomUUID();
        Inventory page = getBlankPage(name);
        for (int i = 0; i < items.size(); i++) {
            if (page.firstEmpty() == 46) {
                pages.add(page);
                page = getBlankPage(name);
                page.addItem(items.get(i));
            } else {
                page.addItem(items.get(i));
            }
        }
        pages.add(page);
        player.openInventory(pages.get(currPage));
        users.put(player.getUniqueId(), this);
    }

    public static String getNextPageName() {
        return nextPageName;
    }

    public static String getPreviousPageName() {
        return previousPageName;
    }

    public static Map<UUID, ScrollerInventory> getUsers() {
        return users;
    }

    public static void setUsers(Map<UUID, ScrollerInventory> users) {
        ScrollerInventory.users = users;
    }

    private Inventory getBlankPage(String name) {
        Inventory page = Bukkit.createInventory(null, 54, name);

        ItemStack nextPage = new Item(Material.SKULL_ITEM).setDurabilitys((short) SkullType.PLAYER.ordinal()).setDisplayName(nextPageName).
                setCustomSkull2("f2f3a2dfce0c3dab7ee10db385e5229f1a39534a8ba2646178e37c4fa93b");
        ItemStack prevPage = new Item(Material.SKULL_ITEM).setDurabilitys((short) SkullType.PLAYER.ordinal()).setDisplayName(previousPageName).
                setCustomSkull2("bb0f6e8af46ac6faf88914191ab66f261d6726a7999c637cf2e4159fe1fc477");

        page.setItem(50, nextPage);
        page.setItem(48, prevPage);

        return page;
    }

    @EventHandler(ignoreCancelled = true)
    public void onClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player))
            return;
        if (event.getInventory().getTitle().equals("§aTutorial")) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            if (!ScrollerInventory.users.containsKey(player.getUniqueId()))
                return;
            ScrollerInventory inv = ScrollerInventory.users.get(player.getUniqueId());
            if (event.getCurrentItem() == null)
                return;
            if (event.getCurrentItem().getItemMeta() == null)
                return;
            if (event.getCurrentItem().getItemMeta().getDisplayName() == null)
                return;
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ScrollerInventory.nextPageName)) {
                event.setCancelled(true);
                if (inv.currPage >= inv.pages.size() - 1) {
                } else {
                    inv.currPage += 1;
                    player.openInventory(inv.pages.get(inv.currPage));
                }
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ScrollerInventory.previousPageName)) {
                event.setCancelled(true);
                if (inv.currPage > 0) {
                    inv.currPage -= 1;
                    player.openInventory(inv.pages.get(inv.currPage));
                }
            }
        }
    }

    public ArrayList<Inventory> getPages() {
        return pages;
    }

    public void setPages(ArrayList<Inventory> pages) {
        this.pages = pages;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }
}
