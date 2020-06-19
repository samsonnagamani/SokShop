package me.samsonnagamani.sokshop.Commands;

import me.samsonnagamani.gerconomy.GerConomy;
import me.samsonnagamani.gerconomy.MessageManager;
import me.samsonnagamani.sokshop.MenuSystem.Menus.BankMenu.BankMenu;
import me.samsonnagamani.sokshop.SokShop;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

public class BankCommand extends BukkitCommand {
    private GerConomy plugin = GerConomy.getPlugin();


    public BankCommand() {
        super("bank");
        this.description = "Opens Bank GUI to Sell/Buy metals and diamonds";
        this.usageMessage = command();
        this.setPermission("sokshop.player.bank");
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

            // Create Bank menu then open it for the player
            new BankMenu(SokShop.getPlayerMenuUtility(player)).open();


        }
        return true;
    }

    private String command(){
        return "/bank";
    }
}
