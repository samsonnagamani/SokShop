package me.samsonnagamani.sokshop.Shop;

import me.samsonnagamani.sokshop.SokShop;

public class ShopManager {
    private SokShop plugin = SokShop.getPlugin();
    private String name;
    private double sellPrice;
    private double buyPrice;

    public ShopManager() {}

    public ShopManager(String name, double sellPrice, double buyPrice) {
        this.name = name;
        this.sellPrice = sellPrice;
        this.buyPrice = buyPrice;

        setName(name);
        setSellPrice(sellPrice);
        setBuyPrice(buyPrice);
    }
    public String getName() {
        return (String) plugin.mongoShop.getShopDataDocument("name", name);
    }

    public void setName(String name) {
        this.name = name;
        plugin.mongoShop.setShopDataDocument(name, "name", this.name);
    }

    public double getSellPrice() {
        return (double) plugin.mongoShop.getShopDataDocument("sell_price", name);
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
        plugin.mongoShop.setShopDataDocument(sellPrice, "sell_price", name);
    }

    public double getBuyPrice() {
        return (double) plugin.mongoShop.getShopDataDocument("buy_price", name);
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
        plugin.mongoShop.setShopDataDocument(buyPrice, "buy_price", name);
    }
}
