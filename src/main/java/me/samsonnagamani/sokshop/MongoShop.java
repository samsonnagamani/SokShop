package me.samsonnagamani.sokshop;

import com.mongodb.client.MongoCollection;
import me.samsonnagamani.gerconomy.GerConomy;
import me.samsonnagamani.sokshop.MessageManager;
import me.samsonnagamani.gerconomy.Player.PlayerManager;
import me.samsonnagamani.gerconomy.Team.TeamManager;
import me.samsonnagamani.sokshop.Shop.ShopManager;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.*;

public class MongoShop extends me.samsonnagamani.gerconomy.MongoConnect {
    private SokShop plugin = SokShop.getPlugin();
    private MongoCollection<Document> shopData;
    private Scoreboard board = plugin.getServer().getScoreboardManager().getMainScoreboard();

    @Override
    public void connect() {
        super.connect();
        setShopDataCollection(super.database.getCollection("shop"));

    }

    public void loadShopData() {
        List<Document> products = plugin.mongoShop.getShopDataCollection().find().into(new ArrayList<>());
        for (Document product : products) {
            String name = product.getString("name");
            double sellPrice = product.getDouble("sell_price");
            double buyPrice = product.getDouble("buy_price");

            plugin.shopManagerHashMap.put(name, new ShopManager(name, sellPrice, buyPrice));
        }

        MessageManager.consoleGood("Shop Info Loaded!");
    }


    @Override
    public void loadPlayerData(Player player) {
        // If player has joined server before load their data
        if (player.hasPlayedBefore()) {
            UUID uuid = player.getUniqueId();

            if (GerConomy.getPlugin().economyCore.hasAccount(uuid.toString())) {
                Document playerdata = (Document) plugin.mongoShop.getPlayerDataCollection().find(new Document("uuid", uuid.toString())).first();
                double balance = playerdata.getDouble("balance");

                plugin.playerManagerHashMap.put(uuid, new PlayerManager(uuid.toString(), balance));
                me.samsonnagamani.gerconomy.MessageManager.playerInfo(player, "Player Info Loaded!");
            }
        } else {
            addNewPlayer(player);
        }
    }

    @Override
    public void loadTeamData() {
        for (String teamname : GerConomy.getPlugin().teamManager.getTeamNames()) {
            Team team = board.getTeam(teamname);
            if (GerConomy.getPlugin().economyCore.hasTeamAccount(teamname)) {
                Document teamdata = (Document) plugin.mongoShop.getTeamDataCollection().find(new Document("name", teamname)).first();
                double balance = teamdata.getDouble("balance");

                // List of each player's uuid in a team
                List<String> teamPlayerUuids = new ArrayList<>();

                for (String playername : team.getEntries()) {
                    Player teamPlayer = plugin.getServer().getPlayer(playername);
                    teamPlayerUuids.add(teamPlayer.getUniqueId().toString());
//                    MessageManager.consoleInfo(teamPlayer.getUniqueId().toString());
                }

                plugin.teamManagerHashMap.put(teamname, new TeamManager(teamname, balance, teamPlayerUuids));

            } else {
                addNewTeam(team.getName());
            }
        }
        MessageManager.consoleGood("Team Info Loaded!");
    }

    public void setShopDataDocument(Object value, String identifier, String name) {
        Document document = new Document("name", name);
        if (shopData.find(document).first() == null) {
            shopData.insertOne(document);
        }
        Bson newValue = new Document(identifier, value);
        Bson updateOperation = new Document("$set", newValue);
        shopData.updateOne(document, updateOperation);
    }

    public Object getShopDataDocument(String identifier, String name) {
        Document document = new Document("name", name);
        if (shopData.find(document).first() != null) {
            Document found = shopData.find(document).first();
            return found.get(identifier);
        }

        MessageManager.consoleBad("Document is null for name " + name);
        return null;
    }

    public MongoCollection<Document> getShopDataCollection() {
        return shopData;
    }

    public void setShopDataCollection(MongoCollection<Document> shopData) {
        this.shopData = shopData;
    }
}
