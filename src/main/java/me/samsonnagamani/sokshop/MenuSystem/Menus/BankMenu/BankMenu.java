package me.samsonnagamani.sokshop.MenuSystem.Menus.BankMenu;

import me.samsonnagamani.sokshop.MenuSystem.Menu;
import me.samsonnagamani.sokshop.MenuSystem.PlayerMenuUtility;
import me.samsonnagamani.sokshop.SokShop;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BankMenu extends Menu {
    private SokShop plugin = SokShop.getPlugin();

    public BankMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "Bank";
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

        // IMPORTANT
        // itemClicked.toString() is equal to plugin.shopManagerHashMap.get(itemClicked.toString()).getName()
        switch (itemClicked) {
            case GREEN_CONCRETE:
                playerMenuUtility.setWithdraw(false);
                playerMenuUtility.setDeposit(true);

                // Send player to Choose which account they want to deposit to
                new PickAccountBankMenu(playerMenuUtility).open();

                break;
            case RED_CONCRETE:
                playerMenuUtility.setDeposit(false);
                playerMenuUtility.setWithdraw(true);

                // Send player to Choose which account they want to withdraw from
                new PickAccountBankMenu(playerMenuUtility).open();
                break;
        }
    }

    @Override
    public void setMenuItems() {
        ItemStack greenConcrete = new ItemStack(Material.GREEN_CONCRETE, 1);
        ItemMeta greenConcreteMeta = greenConcrete.getItemMeta();
        greenConcreteMeta.setDisplayName(ChatColor.GREEN + "Deposit");
        greenConcrete.setItemMeta(greenConcreteMeta);

        inventory.setItem(3, greenConcrete);

        ItemStack redConcrete = new ItemStack(Material.RED_CONCRETE, 1);
        ItemMeta redConcreteMeta = redConcrete.getItemMeta();
        redConcreteMeta.setDisplayName(ChatColor.GREEN + "Withdraw");
        redConcrete.setItemMeta(redConcreteMeta);

        inventory.setItem(5, redConcrete);

        setFillerGlass();
    }
}
