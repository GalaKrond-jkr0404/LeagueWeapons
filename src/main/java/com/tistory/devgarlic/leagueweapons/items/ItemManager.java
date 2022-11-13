package com.tistory.devgarlic.leagueweapons.items;

import com.tistory.devgarlic.leagueweapons.LeagueWeapons;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.bukkit.Bukkit.getServer;

public class ItemManager {
    public static ItemStack NASUS_Q;
    public static ItemStack JAX_E;
    public static ItemStack COG_R;

    public static void init(){
        create_Nasus_Q();
        create_Jax_E();
        create_Cogmaw_R();
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + " [LeagueWeapons] - ItemManager load completed ");
    }

    private static void create_Nasus_Q(){
        ItemStack nasusQ = new ItemStack(Material.WOODEN_HOE, 1);
        ItemMeta nasusQMeta = nasusQ.getItemMeta();

        nasusQMeta.getPersistentDataContainer().set(new NamespacedKey(LeagueWeapons.getPlugin(LeagueWeapons.class), "CWName"), PersistentDataType.STRING, "nasus");
        nasusQMeta.getPersistentDataContainer().set(new NamespacedKey(LeagueWeapons.getPlugin(LeagueWeapons.class), "Q-Stacks"), PersistentDataType.INTEGER, 0);

        nasusQMeta.setDisplayName("§6Siphoning Strike");
        List<String> nasusQLore = new ArrayList<>();
        nasusQLore.add("§9Gain 1 Stack for killing mobs. ");
        nasusQLore.add("§9each 5 stack gains additional 1 damage.");
        nasusQLore.add("The cycle of life and death continues.  ");
        nasusQLore.add("We will live, they will die.");
        nasusQLore.add("§o - Nasus, The Curator of the Sands");

        nasusQMeta.setUnbreakable(true);
        nasusQMeta.addEnchant(Enchantment.KNOCKBACK, 5, true);
        nasusQMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED,new AttributeModifier(UUID.randomUUID(), "AttackSpeed", -0.5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        nasusQMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED,new AttributeModifier(UUID.randomUUID(), "MovementSpeed", -0.08, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        nasusQMeta.setLore(nasusQLore);
        nasusQ.setItemMeta(nasusQMeta);
        NASUS_Q = nasusQ;
    }

    private static void create_Jax_E(){
        ItemStack jaxE = new ItemStack(Material.LANTERN, 1);
        ItemMeta jaxEMeta = jaxE.getItemMeta();

        jaxEMeta.getPersistentDataContainer().set(new NamespacedKey(LeagueWeapons.getPlugin(LeagueWeapons.class), "CWName"), PersistentDataType.STRING, "jax");

        jaxEMeta.setDisplayName("§9 Counter Strike");
        List<String> jaxELore = new ArrayList<>();
        jaxELore.add("§6 Immune to all damage for a short time.");
        jaxELore.add("§6 After that. deal damage to nearby Enemys and §7root §6them. ");
        jaxELore.add("§o§7 - cooldown : 10s");
        jaxELore.add("Let's do this!");
        jaxELore.add("§o- Jax, Grandmaster at Arms");
        jaxEMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED,new AttributeModifier(UUID.randomUUID(), "MovementSpeed", 0.005, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));

        jaxEMeta.setLore(jaxELore);
        jaxE.setItemMeta(jaxEMeta);
        JAX_E = jaxE;

    }
    
    private static void create_Cogmaw_R(){
        ItemStack cogR = new ItemStack(Material.ENDER_EYE, 1);
        ItemMeta cogRmeta = cogR.getItemMeta();

        cogRmeta.getPersistentDataContainer().set(new NamespacedKey(LeagueWeapons.getPlugin(LeagueWeapons.class), "CWName"), PersistentDataType.STRING, "cogmaw");

        cogRmeta.setDisplayName("§2 Living Artillery");
        List<String> cogRLore = new ArrayList<>();
        cogRLore.add("§6 Attacks from sky and Strikes At Facing Position");
        cogRLore.add("§6 Also have delay, warning Effects.");
        cogRLore.add("§6 Lightning is just Effect.");
        cogRLore.add("§o§7 - cooldown : 3s");
        cogRLore.add("Oblivion come.");
        cogRLore.add("§o- Cog'Maw, The Mouth of The Abyss");

        cogRmeta.setLore(cogRLore);
        cogR.setItemMeta(cogRmeta);
        COG_R = cogR;

    }
}
