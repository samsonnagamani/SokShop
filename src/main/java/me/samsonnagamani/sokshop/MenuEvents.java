package me.samsonnagamani.sokshop;

import me.samsonnagamani.sokshop.MenuSystem.Menu;
import me.samsonnagamani.sokshop.MenuSystem.Menus.BuildingMenu.PickAccountBuildingMenu;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.InventoryHolder;

public class MenuEvents implements Listener {
    private SokShop plugin = SokShop.getPlugin();

    @EventHandler
    public void onMenuClick(InventoryClickEvent event){

        InventoryHolder holder = event.getInventory().getHolder();

        if (holder instanceof Menu) {
            // Prevents player from messing with the inventory
            event.setCancelled(true);

            // Deal with null exceptions
            if (event.getCurrentItem() == null) {
                return;
            }

            // Menu Class extend InventoryHolder
            Menu menu = (Menu) holder;

            // Call the handleMenu object to take the event and process it
            menu.handleMenu(event);
        }

    }

    @EventHandler
    public void onSignMenuClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        // Player has to be in world "world_sokshop" to access shops
        if (player.getWorld().getName().equalsIgnoreCase("world_sokshop")) {
            Block clickedBlock = event.getClickedBlock();
            BlockState state = clickedBlock != null ? clickedBlock.getState() : null;

            if (state == null) {
                return;
            }

            if (state instanceof Sign) {
                Sign sign = (Sign) state;
                String line0 = sign.getLine(0);
                String line2 = sign.getLine(2);
                String line3 = sign.getLine(3);

                // Initialise Sign
                // Line 3 is used as a password to create a shop sign
                if (line0.equalsIgnoreCase("[Building Blocks]") && line2.equalsIgnoreCase("Buy") && line3.equalsIgnoreCase("Init: " + "1234")) {
                    sign.setLine(0, ChatColor.LIGHT_PURPLE + "[Building Blocks]");
                    sign.setLine(2, ChatColor.GREEN + "BUY");
                    // Line 3 is set as empty to remove the password from sign
                    sign.setLine(3, "");

                    sign.update();
                }
                if (line0.equalsIgnoreCase(ChatColor.LIGHT_PURPLE + "[Building Blocks]") && line2.equalsIgnoreCase(ChatColor.GREEN + "Buy")) {
                    new PickAccountBuildingMenu(SokShop.getPlayerMenuUtility(player)).open();
                }
                return;
            }
        } else if (player.getWorld().getName().equalsIgnoreCase("world")) {
            MessageManager.playerInfo(player, "You need to be the SokShop world to use access shop");
        }
    }
}
