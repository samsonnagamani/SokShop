package me.samsonnagamani.sokshop;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public final class PlayerEvents implements Listener {
    private SokShop plugin = SokShop.getPlugin();
    private MongoShop mongoShop = new MongoShop();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        mongoShop.loadTeamData();
        mongoShop.loadPlayerData(player);
        mongoShop.loadShopData();
    }

    @EventHandler
    public void onSleep(PlayerBedEnterEvent event) {
        Player player = event.getPlayer();
        mongoShop.loadTeamData();
        mongoShop.loadPlayerData(player);
        mongoShop.loadShopData();
    }


}
