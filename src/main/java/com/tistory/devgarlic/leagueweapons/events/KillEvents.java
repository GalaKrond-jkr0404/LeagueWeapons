package com.tistory.devgarlic.leagueweapons.events;

import com.tistory.devgarlic.leagueweapons.LeagueWeapons;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

public class KillEvents implements Listener{

    @EventHandler
    public static void KillEvents(EntityDamageByEntityEvent event){
        try {


            if(!(event.getEntity() instanceof LivingEntity)){return;}
            LivingEntity victim = (LivingEntity) event.getEntity();
            LeagueWeapons leagueWeapons = LeagueWeapons.getPlugin(LeagueWeapons.class);

            if(!(event.getDamage() >= victim.getHealth() && event.getDamager() instanceof Player)){return;}

            Player player = (Player) event.getDamager();

            if(!(player.getInventory().getItemInMainHand() instanceof ItemStack)){return;}

            ItemStack CustomWeapon = player.getInventory().getItemInMainHand();
            ItemMeta CWMeta = CustomWeapon.getItemMeta();
            String CWname = CWMeta.getPersistentDataContainer().get(new NamespacedKey(leagueWeapons, "CWName"), PersistentDataType.STRING);

            if (CWname.equals("nasus")) { // will convert case?

                NamespacedKey key_NASUS = new NamespacedKey(leagueWeapons, "Q-Stacks");
                CWMeta.getPersistentDataContainer().set(key_NASUS, PersistentDataType.INTEGER, CWMeta.getPersistentDataContainer().get(key_NASUS, PersistentDataType.INTEGER) + 1);
                int stack = CWMeta.getPersistentDataContainer().get(key_NASUS, PersistentDataType.INTEGER);
                CWMeta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
                CWMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "AttackDamage", stack / 5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
                CWMeta.setDisplayName("§6Siphoning Strike " + "< " + stack + " >");
                player.getInventory().getItemInMainHand().setItemMeta(CWMeta);
                player.playSound(player, Sound.ENTITY_PHANTOM_SWOOP, 1, 0);
                player.sendMessage(ChatColor.GREEN + "The Power is getting stronger...");

            }
            else {}
        }
        catch(Exception e) {
            if(!(e == new NullPointerException())) {
                e.printStackTrace();
            }

        }
    }
}


