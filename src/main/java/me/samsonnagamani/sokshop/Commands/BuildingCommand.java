package me.samsonnagamani.sokshop.Commands;

import me.samsonnagamani.gerconomy.GerConomy;
import me.samsonnagamani.gerconomy.MessageManager;
import me.samsonnagamani.sokshop.MenuSystem.Menus.BankMenu.BankMenu;
import me.samsonnagamani.sokshop.MenuSystem.Menus.BuildingMenu.BuildingMenu;
import me.samsonnagamani.sokshop.MenuSystem.Menus.BuildingMenu.PickAccountBuildingMenu;
import me.samsonnagamani.sokshop.SokShop;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

public class BuildingCommand extends BukkitCommand {

    private GerConomy plugin = GerConomy.getPlugin();


    public BuildingCommand() {
        super("building");
        this.description = "Opens Bank GUI to Sell/Buy metals and diamonds";
        this.usageMessage = command();
        this.setPermission("sokshop.player.shop.building");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!player.hasPermission(this.getPermission())) {
                MessageManager.playerBad(player, "You don't have permission.");
                return true;
            }

            if (args.length != 0) {
                MessageManager.playerBad(player, command());
//                MessageManager.playerBad(player, command() + " <player>");
                return true;
            }

//            // Create Building menu then open it for the player
            new PickAccountBuildingMenu(SokShop.getPlayerMenuUtility(player)).open();
        }
        return true;
    }

    private String command(){
        return "/building";
    }
}
