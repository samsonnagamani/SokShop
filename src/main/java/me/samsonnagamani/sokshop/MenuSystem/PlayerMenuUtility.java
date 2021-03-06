package me.samsonnagamani.sokshop.MenuSystem;

import me.samsonnagamani.gerconomy.Player.PlayerManager;
import me.samsonnagamani.gerconomy.Team.TeamManager;
import me.samsonnagamani.sokshop.MessageManager;
import me.samsonnagamani.sokshop.SokShop;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class PlayerMenuUtility {
    private SokShop plugin = SokShop.getPlugin();

    private Player player;
    private Team team;
    private String accountType;
    private boolean isWithdraw;
    private boolean isDeposit;

    public PlayerMenuUtility(Player player) {
        this.player = player;
    }


    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public PlayerManager getPlayer() {
        return plugin.playerManagerHashMap.get(player.getUniqueId());
    }

    public TeamManager getTeam() {
        PlayerManager playerManager = plugin.playerManagerHashMap.get(player.getUniqueId());

        Set<Team> teams = Bukkit.getScoreboardManager().getMainScoreboard().getTeams();
        String teamName = "";

        for (Team team : teams) {
            String name = team.getName();
            if (playerManager.getTeam().equalsIgnoreCase(name)) {
                teamName = name;
                MessageManager.consoleInfo(teamName);
            }
        }

        return plugin.teamManagerHashMap.get(teamName);
    }

    public void setWithdraw(boolean withdraw) {
        isWithdraw = withdraw;
    }

    public void setDeposit(boolean deposit) {
        isDeposit = deposit;
    }

    public String getTransferType() {
        if (isWithdraw) {
            return "Withdraw";
        } else if (isDeposit){
            return "Deposit";
        }

        return null;
    }
}
