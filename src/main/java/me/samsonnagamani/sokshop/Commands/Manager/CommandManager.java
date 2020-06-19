package me.samsonnagamani.sokshop.Commands.Manager;

import me.samsonnagamani.sokshop.Commands.*;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;

import java.lang.reflect.Field;

public class CommandManager {


    // Register commands without accessing plugin.yml
    public void setup() {
        try {
            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");

            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());


            commandMap.register("bank", new BankCommand());
            // Test commands for development
//            commandMap.register("building", new BuildingCommand());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
