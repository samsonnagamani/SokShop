package me.samsonnagamani.sokshop;

import me.samsonnagamani.sokshop.Commands.Manager.CommandManager;
import me.samsonnagamani.gerconomy.Player.PlayerManager;
import me.samsonnagamani.gerconomy.Team.TeamManager;
import me.samsonnagamani.sokshop.MenuSystem.PlayerMenuUtility;
import me.samsonnagamani.sokshop.Shop.ShopManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class SokShop extends JavaPlugin {
    public HashMap<String, TeamManager> teamManagerHashMap = new HashMap<>();
    public HashMap<UUID, PlayerManager> playerManagerHashMap = new HashMap<>();
    public HashMap<String, ShopManager> shopManagerHashMap = new HashMap<>();
    public MongoShop mongoShop;
    private static final HashMap<Player, PlayerMenuUtility> playerMenuUtilityHashMap = new HashMap<>();
    private CommandManager commandManager;
    private static SokShop plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        instanceClasses();

        mongoShop.connect();
        commandManager.setup();

        getServer().getPluginManager().registerEvents(new PlayerEvents(), this);
        getServer().getPluginManager().registerEvents(new MenuEvents(), this);

        MessageManager.consoleGood("\n\n SokShop has been Enabled \n\n");
    }

    private void instanceClasses() {
        mongoShop = new MongoShop();
        commandManager = new CommandManager();
    }

    // Provide a player
    // Return a menu system for player
    // Create a menu system if they dont already have one
    public static PlayerMenuUtility getPlayerMenuUtility(Player player) {
        PlayerMenuUtility playerMenuUtility;
        // See if a PlayerMenuUtility already exists for player
        if (!(playerMenuUtilityHashMap.containsKey(player))) {
            playerMenuUtility = new PlayerMenuUtility(player);
            playerMenuUtilityHashMap.put(player, playerMenuUtility);

            return playerMenuUtility;
        } else {
            return playerMenuUtilityHashMap.get(player);
        }
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
        MessageManager.consoleBad("\n\n SokShop has been Disabled \n\n");
    }

    public static SokShop getPlugin() {
        return plugin;
    }
}
