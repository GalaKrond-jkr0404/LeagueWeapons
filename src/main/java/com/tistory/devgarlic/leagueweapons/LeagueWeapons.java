package com.tistory.devgarlic.leagueweapons;
import com.tistory.devgarlic.leagueweapons.commands.WeaponGetCommand;
import com.tistory.devgarlic.leagueweapons.events.ClickEvents;
import com.tistory.devgarlic.leagueweapons.events.KillEvents;
import com.tistory.devgarlic.leagueweapons.items.ItemManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Item;
import org.bukkit.plugin.java.JavaPlugin;
public class LeagueWeapons extends JavaPlugin{
    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + " [LeagueWeapons] - Hello world! :D ");

        ItemManager.init();
        initCommands();
        initEvents();
    }

    private void initCommands(){
        getCommand("getnasusq").setExecutor(new WeaponGetCommand());
        getCommand("getjaxe").setExecutor(new WeaponGetCommand());
        getCommand("getcogmawr").setExecutor(new WeaponGetCommand());
        getCommand("getakalir").setExecutor(new WeaponGetCommand());
        getCommand("viewitemmeta").setExecutor(new WeaponGetCommand());
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + " [LeagueWeapons] - Command load completed ");
    }
    private void  initEvents(){
        getServer().getPluginManager().registerEvents(new KillEvents(), this);
        getServer().getPluginManager().registerEvents(new ClickEvents(), this);
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + " [LeagueWeapons] - Event load completed ");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + " [LeagueWeapons] - GoodBye Sengen! ");
    }
}
