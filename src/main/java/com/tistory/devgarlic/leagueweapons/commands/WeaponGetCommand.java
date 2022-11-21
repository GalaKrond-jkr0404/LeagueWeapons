package com.tistory.devgarlic.leagueweapons.commands;

import com.tistory.devgarlic.leagueweapons.LeagueWeapons;
import com.tistory.devgarlic.leagueweapons.items.ItemManager;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

public class WeaponGetCommand implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd,String label, String[] strings){


        if(!(sender instanceof Player)){
            sender.sendMessage("Seems you are not player, Only Player can use this command!");
        }

        //이거 switch로 안되나
        Player player = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("getnasusq")){
            player.getInventory().addItem(ItemManager.NASUS_Q);
        }

        else if(cmd.getName().equalsIgnoreCase("getjaxe")){
            player.getInventory().addItem(ItemManager.JAX_E);
        }

        else if(cmd.getName().equalsIgnoreCase("getcogmawr")){
            player.getInventory().addItem(ItemManager.COG_R);
        }

        else if(cmd.getName().equalsIgnoreCase("viewitemmeta")){
            player.sendMessage(player.getInventory().getItemInMainHand().getItemMeta().toString());
        }


        return true;
    }
}
