package me.samsonnagamani.sokshop.MenuSystem.Menus.BankMenu;

import me.samsonnagamani.sokshop.MenuSystem.Menu;
import me.samsonnagamani.sokshop.MenuSystem.Menus.BankMenu.BankTransferMenu;
import me.samsonnagamani.sokshop.MenuSystem.PlayerMenuUtility;
import me.samsonnagamani.sokshop.SokShop;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class PickAccountBankMenu extends Menu {
    private SokShop plugin = SokShop.getPlugin();

    public PickAccountBankMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "Choose which account to use: ";
    }

    @Override
    public int getSlots() {
        return 9;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Material itemClicked = event.getCurrentItem().getType();

        // Get PlayerMenuUtility
        PlayerMenuUtility playerMenuUtility = SokShop.getPlayerMenuUtility(player);

        switch (itemClicked) {
            case PLAYER_HEAD:
                playerMenuUtility.setAccountType("personal");

                // Send player to Choose which account they want to deposit to
                new BankTransferMenu(playerMenuUtility).open();
                break;
            case DRAGON_HEAD:
                playerMenuUtility.setAccountType("team");

                // Send player to Choose which account they want to withdraw from
                new BankTransferMenu(playerMenuUtility).open();
                break;
        }


    }

    @Override
    public void setMenuItems() {
        ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1);
        ItemMeta playerHeadMeta = playerHead.getItemMeta();
        playerHeadMeta.setDisplayName(ChatColor.GREEN + "Personal");
        ArrayList<String> playerHeadLore = new ArrayList<>();
        playerHeadLore.add(ChatColor.AQUA + "£" + playerMenuUtility.getPlayer().getBalance());
        playerHeadMeta.setLore(playerHeadLore);
        playerHead.setItemMeta(playerHeadMeta);


        inventory.setItem(3, playerHead);

        ItemStack dragonHead = new ItemStack(Material.DRAGON_HEAD, 1);
        ItemMeta dragonHeadMeta = dragonHead.getItemMeta();
        dragonHeadMeta.setDisplayName(ChatColor.GREEN + "Team");
        ArrayList<String> dragonHeadLore = new ArrayList<>();
        dragonHeadLore.add(ChatColor.AQUA + "£" + playerMenuUtility.getTeam().getBalance());
        dragonHeadMeta.setLore(dragonHeadLore);
        dragonHead.setItemMeta(dragonHeadMeta);

        inventory.setItem(5, dragonHead);

        setFillerGlass();
    }
}
