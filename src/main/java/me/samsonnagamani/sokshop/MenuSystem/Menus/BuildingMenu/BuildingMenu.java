package me.samsonnagamani.sokshop.MenuSystem.Menus.BuildingMenu;

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

public class BuildingMenu extends Menu {
    private SokShop plugin = SokShop.getPlugin();
    private String transferType = playerMenuUtility.getTransferType();

    public BuildingMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "Building Blocks Menu";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {
        ItemStack clickedItem = new ItemStack(event.getCurrentItem().getType(), event.getCurrentItem().getAmount());
        Player player = (Player) event.getWhoClicked();

        double balance;
        double price = getProductPrice(clickedItem);

        // Player can only buy
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

    }

    @Override
    public void setMenuItems() {
        //
        // Planks and Logs
        //

        ItemStack oakLog = new ItemStack(Material.OAK_LOG, 64);
        ItemMeta oakLogMeta = oakLog.getItemMeta();
        oakLogMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(oakLog));
        oakLog.setItemMeta(oakLogMeta);

        inventory.setItem(0, oakLog);

        ItemStack darkOakLog = new ItemStack(Material.DARK_OAK_LOG, 64);
        ItemMeta darkOakLogMeta = darkOakLog.getItemMeta();
        darkOakLogMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(darkOakLog));
        darkOakLog.setItemMeta(darkOakLogMeta);

        inventory.setItem(1, darkOakLog);

        ItemStack spruceLog = new ItemStack(Material.SPRUCE_LOG, 64);
        ItemMeta spruceLogMeta = spruceLog.getItemMeta();
        spruceLogMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(spruceLog));
        spruceLog.setItemMeta(spruceLogMeta);

        inventory.setItem(2, spruceLog);

        ItemStack acaciaLog = new ItemStack(Material.ACACIA_LOG, 64);
        ItemMeta acaciaLogMeta = acaciaLog.getItemMeta();
        acaciaLogMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(acaciaLog));
        acaciaLog.setItemMeta(acaciaLogMeta);

        inventory.setItem(3, acaciaLog);

        ItemStack birchLog = new ItemStack(Material.BIRCH_LOG, 64);
        ItemMeta birchLogMeta = birchLog.getItemMeta();
        birchLogMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(birchLog));
        birchLog.setItemMeta(birchLogMeta);

        inventory.setItem(4, birchLog);

        ItemStack oakPlanks = new ItemStack(Material.OAK_PLANKS, 64);
        ItemMeta oakPlanksMeta = oakPlanks.getItemMeta();
        oakPlanksMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(oakPlanks));
        oakPlanks.setItemMeta(oakPlanksMeta);

        inventory.setItem(9, oakPlanks);

        ItemStack darkOakPlanks = new ItemStack(Material.DARK_OAK_PLANKS, 64);
        ItemMeta darkOakPlanksMeta = darkOakPlanks.getItemMeta();
        darkOakPlanksMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(darkOakPlanks));
        darkOakPlanks.setItemMeta(darkOakPlanksMeta);

        inventory.setItem(10, darkOakPlanks);

        ItemStack sprucePlanks = new ItemStack(Material.SPRUCE_PLANKS, 64);
        ItemMeta sprucePlanksMeta = sprucePlanks.getItemMeta();
        sprucePlanksMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(sprucePlanks));
        sprucePlanks.setItemMeta(sprucePlanksMeta);

        inventory.setItem(11, sprucePlanks);

        ItemStack acaciaPlanks = new ItemStack(Material.ACACIA_PLANKS, 64);
        ItemMeta acaciaPlanksMeta = acaciaPlanks.getItemMeta();
        acaciaPlanksMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(acaciaPlanks));
        acaciaPlanks.setItemMeta(acaciaPlanksMeta);

        inventory.setItem(12, acaciaPlanks);

        ItemStack birchPlanks = new ItemStack(Material.BIRCH_PLANKS, 64);
        ItemMeta birchPlanksMeta = birchPlanks.getItemMeta();
        birchPlanksMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(birchPlanks));
        birchPlanks.setItemMeta(birchPlanksMeta);

        inventory.setItem(13, birchPlanks);

        //
        // Terracotta
        //

        ItemStack terracotta = new ItemStack(Material.TERRACOTTA, 64);
        ItemMeta terracottaMeta = terracotta.getItemMeta();
        terracottaMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(terracotta));
        terracotta.setItemMeta(terracottaMeta);

        inventory.setItem(18, terracotta);

        ItemStack whiteTerracotta = new ItemStack(Material.WHITE_TERRACOTTA, 64);
        ItemMeta whiteTerracottaMeta = whiteTerracotta.getItemMeta();
        whiteTerracottaMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(whiteTerracotta));
        whiteTerracotta.setItemMeta(whiteTerracottaMeta);

        inventory.setItem(19, whiteTerracotta);

        ItemStack orangeTerracotta = new ItemStack(Material.ORANGE_TERRACOTTA, 64);
        ItemMeta orangeTerracottaMeta = orangeTerracotta.getItemMeta();
        orangeTerracottaMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(orangeTerracotta));
        orangeTerracotta.setItemMeta(orangeTerracottaMeta);

        inventory.setItem(20, orangeTerracotta);

        ItemStack magentaTerracotta = new ItemStack(Material.MAGENTA_TERRACOTTA, 64);
        ItemMeta magentaTerracottaMeta = magentaTerracotta.getItemMeta();
        magentaTerracottaMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(magentaTerracotta));
        magentaTerracotta.setItemMeta(magentaTerracottaMeta);

        inventory.setItem(21, magentaTerracotta);

        ItemStack lightBlueTerracotta = new ItemStack(Material.LIGHT_BLUE_TERRACOTTA, 64);
        ItemMeta lightBlueTerracottaMeta = lightBlueTerracotta.getItemMeta();
        lightBlueTerracottaMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(lightBlueTerracotta));
        lightBlueTerracotta.setItemMeta(lightBlueTerracottaMeta);

        inventory.setItem(22, lightBlueTerracotta);

        ItemStack yellowTerracotta = new ItemStack(Material.YELLOW_TERRACOTTA, 64);
        ItemMeta yellowTerracottaMeta = yellowTerracotta.getItemMeta();
        yellowTerracottaMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(yellowTerracotta));
        yellowTerracotta.setItemMeta(yellowTerracottaMeta);

        inventory.setItem(23, yellowTerracotta);

        ItemStack limeTerracotta = new ItemStack(Material.LIME_TERRACOTTA, 64);
        ItemMeta limeTerracottaMeta = limeTerracotta.getItemMeta();
        limeTerracottaMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(limeTerracotta));
        limeTerracotta.setItemMeta(limeTerracottaMeta);

        inventory.setItem(24, limeTerracotta);

        ItemStack pinkTerracotta = new ItemStack(Material.PINK_TERRACOTTA, 64);
        ItemMeta pinkTerracottaMeta = pinkTerracotta.getItemMeta();
        pinkTerracottaMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(pinkTerracotta));
        pinkTerracotta.setItemMeta(pinkTerracottaMeta);

        inventory.setItem(25, pinkTerracotta);

        ItemStack grayTerracotta = new ItemStack(Material.GRAY_TERRACOTTA, 64);
        ItemMeta grayTerracottaMeta = grayTerracotta.getItemMeta();
        grayTerracottaMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(grayTerracotta));
        grayTerracotta.setItemMeta(grayTerracottaMeta);

        inventory.setItem(26, grayTerracotta);

        ItemStack lightGrayTerracotta = new ItemStack(Material.LIGHT_GRAY_TERRACOTTA, 64);
        ItemMeta lightGrayTerracottaMeta = lightGrayTerracotta.getItemMeta();
        lightGrayTerracottaMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(lightGrayTerracotta));
        lightGrayTerracotta.setItemMeta(lightGrayTerracottaMeta);

        inventory.setItem(27, lightGrayTerracotta);

        ItemStack cyanTerracotta = new ItemStack(Material.CYAN_TERRACOTTA, 64);
        ItemMeta cyanTerracottaMeta = cyanTerracotta.getItemMeta();
        cyanTerracottaMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(cyanTerracotta));
        cyanTerracotta.setItemMeta(cyanTerracottaMeta);

        inventory.setItem(28, cyanTerracotta);

        ItemStack purpleTerracotta = new ItemStack(Material.PURPLE_TERRACOTTA, 64);
        ItemMeta purpleTerracottaMeta = purpleTerracotta.getItemMeta();
        purpleTerracottaMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(purpleTerracotta));
        purpleTerracotta.setItemMeta(purpleTerracottaMeta);

        inventory.setItem(29, purpleTerracotta);

        ItemStack blueTerracotta = new ItemStack(Material.BLUE_TERRACOTTA, 64);
        ItemMeta blueTerracottaMeta = blueTerracotta.getItemMeta();
        blueTerracottaMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(blueTerracotta));
        blueTerracotta.setItemMeta(blueTerracottaMeta);

        inventory.setItem(30, blueTerracotta);

        ItemStack brownTerracotta = new ItemStack(Material.BROWN_TERRACOTTA, 64);
        ItemMeta brownTerracottaMeta = brownTerracotta.getItemMeta();
        brownTerracottaMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(brownTerracotta));
        brownTerracotta.setItemMeta(brownTerracottaMeta);

        inventory.setItem(31, brownTerracotta);

        ItemStack greenTerracotta = new ItemStack(Material.GREEN_TERRACOTTA, 64);
        ItemMeta greenTerracottaMeta = greenTerracotta.getItemMeta();
        greenTerracottaMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(greenTerracotta));
        greenTerracotta.setItemMeta(greenTerracottaMeta);

        inventory.setItem(32, greenTerracotta);

        ItemStack redTerracotta = new ItemStack(Material.RED_TERRACOTTA, 64);
        ItemMeta redTerracottaMeta = redTerracotta.getItemMeta();
        redTerracottaMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(redTerracotta));
        redTerracotta.setItemMeta(redTerracottaMeta);

        inventory.setItem(33, redTerracotta);

        ItemStack blackTerracotta = new ItemStack(Material.BLACK_TERRACOTTA, 64);
        ItemMeta blackTerracottaMeta = blackTerracotta.getItemMeta();
        blackTerracottaMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(blackTerracotta));
        blackTerracotta.setItemMeta(blackTerracottaMeta);

        inventory.setItem(34, blackTerracotta);

        //
        // Concrete
        //

        ItemStack whiteConcrete = new ItemStack(Material.WHITE_CONCRETE, 64);
        ItemMeta whiteConcreteMeta = whiteConcrete.getItemMeta();
        whiteConcreteMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(whiteConcrete));
        whiteConcrete.setItemMeta(whiteConcreteMeta);

        inventory.setItem(36, whiteConcrete);

        ItemStack orangeConcrete = new ItemStack(Material.ORANGE_CONCRETE, 64);
        ItemMeta orangeConcreteMeta = orangeConcrete.getItemMeta();
        orangeConcreteMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(orangeConcrete));
        orangeConcrete.setItemMeta(orangeConcreteMeta);

        inventory.setItem(37, orangeConcrete);

        ItemStack magentaConcrete = new ItemStack(Material.MAGENTA_CONCRETE, 64);
        ItemMeta magentaConcreteMeta = magentaConcrete.getItemMeta();
        magentaConcreteMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(magentaConcrete));
        magentaConcrete.setItemMeta(magentaConcreteMeta);

        inventory.setItem(38, magentaConcrete);

        ItemStack lightBlueConcrete = new ItemStack(Material.LIGHT_BLUE_CONCRETE, 64);
        ItemMeta lightBlueConcreteMeta = lightBlueConcrete.getItemMeta();
        lightBlueConcreteMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(lightBlueConcrete));
        lightBlueConcrete.setItemMeta(lightBlueConcreteMeta);

        inventory.setItem(39, lightBlueConcrete);

        ItemStack yellowConcrete = new ItemStack(Material.YELLOW_CONCRETE, 64);
        ItemMeta yellowConcreteMeta = yellowConcrete.getItemMeta();
        yellowConcreteMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(yellowConcrete));
        yellowConcrete.setItemMeta(yellowConcreteMeta);

        inventory.setItem(40, yellowConcrete);

        ItemStack limeConcrete = new ItemStack(Material.LIME_CONCRETE, 64);
        ItemMeta limeConcreteMeta = limeConcrete.getItemMeta();
        limeConcreteMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(limeConcrete));
        limeConcrete.setItemMeta(limeConcreteMeta);

        inventory.setItem(41, limeConcrete);

        ItemStack pinkConcrete = new ItemStack(Material.PINK_CONCRETE, 64);
        ItemMeta pinkConcreteMeta = pinkConcrete.getItemMeta();
        pinkConcreteMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(pinkConcrete));
        pinkConcrete.setItemMeta(pinkConcreteMeta);

        inventory.setItem(42, pinkConcrete);

        ItemStack grayConcrete = new ItemStack(Material.GRAY_CONCRETE, 64);
        ItemMeta grayConcreteMeta = grayConcrete.getItemMeta();
        grayConcreteMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(grayConcrete));
        grayConcrete.setItemMeta(grayConcreteMeta);

        inventory.setItem(43, grayConcrete);

        ItemStack lightGrayConcrete = new ItemStack(Material.LIGHT_GRAY_CONCRETE, 64);
        ItemMeta lightGrayConcreteMeta = lightGrayConcrete.getItemMeta();
        lightGrayConcreteMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(lightGrayConcrete));
        lightGrayConcrete.setItemMeta(lightGrayConcreteMeta);

        inventory.setItem(45, lightGrayConcrete);

        ItemStack cyanConcrete = new ItemStack(Material.CYAN_CONCRETE, 64);
        ItemMeta cyanConcreteMeta = cyanConcrete.getItemMeta();
        cyanConcreteMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(cyanConcrete));
        cyanConcrete.setItemMeta(cyanConcreteMeta);

        inventory.setItem(46, cyanConcrete);

        ItemStack purpleConcrete = new ItemStack(Material.PURPLE_CONCRETE, 64);
        ItemMeta purpleConcreteMeta = purpleConcrete.getItemMeta();
        purpleConcreteMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(purpleConcrete));
        purpleConcrete.setItemMeta(purpleConcreteMeta);

        inventory.setItem(47, purpleConcrete);

        ItemStack blueConcrete = new ItemStack(Material.BLUE_CONCRETE, 64);
        ItemMeta blueConcreteMeta = blueConcrete.getItemMeta();
        blueConcreteMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(blueConcrete));
        blueConcrete.setItemMeta(blueConcreteMeta);

        inventory.setItem(48, blueConcrete);

        ItemStack brownConcrete = new ItemStack(Material.BROWN_CONCRETE, 64);
        ItemMeta brownConcreteMeta = brownConcrete.getItemMeta();
        brownConcreteMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(brownConcrete));
        brownConcrete.setItemMeta(brownConcreteMeta);

        inventory.setItem(49, brownConcrete);

        ItemStack greenConcrete = new ItemStack(Material.GREEN_CONCRETE, 64);
        ItemMeta greenConcreteMeta = greenConcrete.getItemMeta();
        greenConcreteMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(greenConcrete));
        greenConcrete.setItemMeta(greenConcreteMeta);

        inventory.setItem(50, greenConcrete);

        ItemStack redConcrete = new ItemStack(Material.RED_CONCRETE, 64);
        ItemMeta redConcreteMeta = redConcrete.getItemMeta();
        redConcreteMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(redConcrete));
        redConcrete.setItemMeta(redConcreteMeta);

        inventory.setItem(51, redConcrete);

        ItemStack blackConcrete = new ItemStack(Material.BLACK_CONCRETE, 64);
        ItemMeta blackConcreteMeta = blackConcrete.getItemMeta();
        blackConcreteMeta.setDisplayName(ChatColor.GREEN + "£" + getProductPrice(blackConcrete));
        blackConcrete.setItemMeta(blackConcreteMeta);

        inventory.setItem(52, blackConcrete);

        setFillerGlass();
    }

    public double getProductPrice(ItemStack itemStack) {

        String materialName = itemStack.getType().toString();
        double itemAmount = itemStack.getAmount();

        ShopManager shopManager;
        double price = 0;
            shopManager = plugin.shopManagerHashMap.get(materialName);
            price = itemAmount * shopManager.getBuyPrice();

        return price;
    }
}
