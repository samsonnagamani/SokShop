package me.samsonnagamani.sokshop.MenuSystem.Menus.BankMenu;

import me.samsonnagamani.sokshop.MenuSystem.Menu;
import me.samsonnagamani.sokshop.MenuSystem.PlayerMenuUtility;
import me.samsonnagamani.sokshop.MessageManager;
import me.samsonnagamani.sokshop.Shop.ShopManager;
import me.samsonnagamani.sokshop.SokShop;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class BankTransferMenu extends Menu {
    private SokShop plugin = SokShop.getPlugin();
    private String transferType = playerMenuUtility.getTransferType();

    public BankTransferMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return transferType + " Menu";
    }

    @Override
    public int getSlots() {
        return 45;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {
        // Create list of materials that can be sold/bought in this menu
        List<Material> validMaterials = new ArrayList();
        validMaterials.add(Material.IRON_INGOT);
        validMaterials.add(Material.IRON_BLOCK);
        validMaterials.add(Material.GOLD_INGOT);
        validMaterials.add(Material.GOLD_BLOCK);
        validMaterials.add(Material.DIAMOND);
        validMaterials.add(Material.DIAMOND_BLOCK);

        // Create a new ItemStack with type and amount | No meta data
        ItemStack clickedItem = new ItemStack(event.getCurrentItem().getType(), event.getCurrentItem().getAmount());
        Player player = (Player) event.getWhoClicked();

        // Check if material is a valid item
        if (validMaterials.contains(clickedItem.getType())) {
            double balance;
            double price = getProductPrice(clickedItem);

            // Player chose to deposit
            if (transferType.equalsIgnoreCase("deposit")) {

                if (player.getInventory().contains(clickedItem.getType())) {
                    player.getInventory().removeItem(clickedItem);

                    // Checks which account player wants to use
                    if (playerMenuUtility.getAccountType().equalsIgnoreCase("personal")) {
                        balance = playerMenuUtility.getPlayer().getBalance();
                        playerMenuUtility.getPlayer().setBalance(balance + price);
                        MessageManager.playerGood(player, "Deposited £" + price);
                    } else if (playerMenuUtility.getAccountType().equalsIgnoreCase("team")) {
                        balance = playerMenuUtility.getTeam().getBalance();
                        playerMenuUtility.getTeam().setBalance(balance + price);
                        MessageManager.playerGood(player, "Deposited £" + price);
                    }
                } else {
                    MessageManager.playerBad(player, "You do not have clicked item in inventory");
                }
            }

            // Player chose to withdraw
            if (transferType.equalsIgnoreCase("withdraw")) {

                if (playerMenuUtility.getAccountType().equalsIgnoreCase("personal") && player.getInventory().firstEmpty() != -1) {
                    balance = playerMenuUtility.getPlayer().getBalance();
                    if (balance >= price) {
                        playerMenuUtility.getPlayer().setBalance(balance - price);
                        player.getInventory().addItem(clickedItem);
                        MessageManager.playerGood(player, "Bought item for £" + price);
                    } else {
                        MessageManager.playerBad(player, "You do not have enough money");
                    }

                } else if (playerMenuUtility.getAccountType().equalsIgnoreCase("team") && player.getInventory().firstEmpty() != -1) {
                    balance = playerMenuUtility.getTeam().getBalance();
                    if (balance >= price) {
                        playerMenuUtility.getTeam().setBalance(balance - price);
                        player.getInventory().addItem(clickedItem);
                        MessageManager.playerGood(player, "Bought item for " + price);
                    } else {
                        MessageManager.playerBad(player, "You do not have enough money");
                    }
                } else {
                    MessageManager.playerBad(player, "You do not have any empty slots in inventory");
                }
            }
        } else {
            MessageManager.playerBad(player, "You can not sell/buy this item in this menu");
        }
    }

    @Override
    public void setMenuItems() {
        // Column iron

        ItemStack ironIngot = new ItemStack(Material.IRON_INGOT, 1);
        ItemMeta ironIngotMeta = ironIngot.getItemMeta();
        ironIngotMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(ironIngot));
        ironIngot.setItemMeta(ironIngotMeta);

        inventory.setItem(1, ironIngot);

        ItemStack ironIngotStack = new ItemStack(Material.IRON_INGOT, 64);
        ItemMeta ironIngotStackMeta = ironIngotStack.getItemMeta();
        ironIngotStackMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(ironIngotStack));
        ironIngotStack.setItemMeta(ironIngotStackMeta);

        inventory.setItem(10, ironIngotStack);

        ItemStack ironBlock = new ItemStack(Material.IRON_BLOCK, 1);
        ItemMeta ironBlockMeta = ironBlock.getItemMeta();
        ironBlockMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(ironBlock));
        ironBlock.setItemMeta(ironBlockMeta);

        inventory.setItem(28, ironBlock);

        ItemStack ironBlockStack = new ItemStack(Material.IRON_BLOCK, 64);
        ItemMeta ironBlockStackMeta = ironBlockStack.getItemMeta();
        ironBlockStackMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(ironBlockStack));
        ironBlockStack.setItemMeta(ironBlockStackMeta);

        inventory.setItem(37, ironBlockStack);

        // Column gold

        ItemStack goldIngot = new ItemStack(Material.GOLD_INGOT, 1);
        ItemMeta goldIngotMeta = goldIngot.getItemMeta();
        goldIngotMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(goldIngot));
        goldIngot.setItemMeta(goldIngotMeta);

        inventory.setItem(4, goldIngot);

        ItemStack goldIngotStack = new ItemStack(Material.GOLD_INGOT, 64);
        ItemMeta goldIngotStackMeta = goldIngotStack.getItemMeta();
        goldIngotStackMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(goldIngotStack));
        goldIngotStack.setItemMeta(goldIngotStackMeta);

        inventory.setItem(13, goldIngotStack);

        ItemStack goldBlock = new ItemStack(Material.GOLD_BLOCK, 1);
        ItemMeta goldBlockMeta = goldBlock.getItemMeta();
        goldBlockMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(goldBlock));
        goldBlock.setItemMeta(goldBlockMeta);

        inventory.setItem(31, goldBlock);

        ItemStack goldBlockStack = new ItemStack(Material.GOLD_BLOCK, 64);
        ItemMeta goldBlockStackMeta = goldBlockStack.getItemMeta();
        goldBlockStackMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(goldBlockStack));
        goldBlockStack.setItemMeta(goldBlockStackMeta);

        inventory.setItem(40, goldBlockStack);

        // Column diamond

        ItemStack diamondIngot = new ItemStack(Material.DIAMOND, 1);
        ItemMeta diamondIngotMeta = diamondIngot.getItemMeta();
        diamondIngotMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(diamondIngot));
        diamondIngot.setItemMeta(diamondIngotMeta);

        inventory.setItem(7, diamondIngot);

        ItemStack diamondIngotStack = new ItemStack(Material.DIAMOND, 64);
        ItemMeta diamondIngotStackMeta = diamondIngotStack.getItemMeta();
        diamondIngotStackMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(diamondIngotStack));
        diamondIngotStack.setItemMeta(diamondIngotStackMeta);

        inventory.setItem(16, diamondIngotStack);

        ItemStack diamondBlock = new ItemStack(Material.DIAMOND_BLOCK, 1);
        ItemMeta diamondBlockMeta = diamondBlock.getItemMeta();
        diamondBlockMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(diamondBlock));
        diamondBlock.setItemMeta(diamondBlockMeta);

        inventory.setItem(34, diamondBlock);

        ItemStack diamondBlockStack = new ItemStack(Material.DIAMOND_BLOCK, 64);
        ItemMeta diamondBlockStackMeta = diamondBlockStack.getItemMeta();
        diamondBlockStackMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(diamondBlockStack));
        diamondBlockStack.setItemMeta(diamondBlockStackMeta);

        inventory.setItem(43, diamondBlockStack);



        setFillerGlass();
    }

    public double getProductPrice(ItemStack itemStack) {

        String materialName = itemStack.getType().toString();
        double itemAmount = itemStack.getAmount();

        ShopManager shopManager;
        double price = 0;
        if (transferType.equalsIgnoreCase("withdraw")) {
            shopManager = plugin.shopManagerHashMap.get(materialName);
            price = itemAmount * shopManager.getBuyPrice();
        } else if (transferType.equalsIgnoreCase("deposit")){
            shopManager = plugin.shopManagerHashMap.get(materialName);
            price = itemAmount * shopManager.getSellPrice();
        }

        return price;
    }
}
