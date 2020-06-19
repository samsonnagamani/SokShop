# SokShop Plugin

### Building
Build the source code with Maven:

<code>$mvn install</code>

### Introduction | Usage
This plugin is a GUI shop plugin which **requires** Gerconomy \(another plugin\)

**MongoDB**  
Database Name: minecraft_server  
Collection: shop  

To add the item's sell price and buy price to the database, import the "SHOP_COLLECTION_INITIALISATION.json" file

To set up a shop, follow these steps:
1. Place sign
2. On the first line type "[Building Blocks]"
3. On the third line type "Buy"
4. On the fourth line type "Init: 1234"
5. Click "ok"
6. Finally, click the sign to initialise it

**Note**: to use the shop the player must be in a world called "world_sokshop, otherwise this will not work"

The player will also have access to the **/bank** command.
Which opens a GUI where they can Deposit or Withdraw from the bank.
The current currencies are:
- iron
- gold
- diamond
- and their block versions