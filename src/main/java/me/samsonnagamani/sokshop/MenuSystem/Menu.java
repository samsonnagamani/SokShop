package me.samsonnagamani.sokshop.MenuSystem;

import me.samsonnagamani.sokshop.MessageManager;
import me.samsonnagamani.sokshop.SokShop;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.UUID;


public abstract class Menu implements InventoryHolder {

    protected PlayerMenuUtility playerMenuUtility;
    protected Inventory inventory;
    protected ItemStack FILLER_GLASS = makeItem(Material.BLACK_STAINED_GLASS_PANE, " ");
    private SokShop plugin = SokShop.getPlugin();

    public Menu(PlayerMenuUtility playerMenuUtility) {
        this.playerMenuUtility = playerMenuUtility;
    }

    // Let each menu decide their name
    public abstract String getMenuName();

    // Let each menu decide their number of slots
    public abstract int getSlots();

    // Let each menu decide how the items in the menu will be handled when clicked
    public abstract void handleMenu(InventoryClickEvent event);

    // Let each menu decide what items in the menu are placed in the inventory menu
    public abstract void setMenuItems();

    public void open() {
        // Create new inventory
        // Owner of type InventoryHolder is the Menu class itself
        inventory = Bukkit.createInventory(this, getSlots(), getMenuName());

        // Grab all the items specified to be used for this menu and add them to inventory
        this.setMenuItems();

        // Open the inventory for the player
        plugin.getServer().getPlayer(UUID.fromString(playerMenuUtility.getPlayer().getUuid())).openInventory(inventory);
//        MessageManager.consoleInfo(playerMenuUtility.getPlayer().toString());
    }

    // Override method from the InventoryHolder interface
    @Override
    public Inventory getInventory() {
        return inventory;
    }

    // Utility method to fill remaining empty slots with "filler glass"
    public void setFillerGlass() {
        for (int i = 0; i < getSlots(); i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, FILLER_GLASS);
            }
        }
    }

    public ItemStack makeItem(Material material, String displayName, String... lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(displayName);

        itemMeta.setLore((Arrays.asList(lore)));
        item.setItemMeta(itemMeta);

        return item;
    }

}
