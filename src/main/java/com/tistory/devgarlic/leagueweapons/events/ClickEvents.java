package com.tistory.devgarlic.leagueweapons.events;

import com.tistory.devgarlic.leagueweapons.LeagueWeapons;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BoundingBox;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ClickEvents implements Listener {
    @EventHandler
    public void ClickEvents(PlayerInteractEvent event){

        if(event.hasItem() && event.getItem().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(LeagueWeapons.getPlugin(LeagueWeapons.class), "CWName"), PersistentDataType.STRING)){
            switch (event.getItem().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(LeagueWeapons.getPlugin(LeagueWeapons.class), "CWName"), PersistentDataType.STRING)){
                case "jax":
                    JaxE(event);
                    break;
                case "cogmaw":
                    CogmawR(event);
                    break;
                default:
                    break;
            }
        }
    }

    private boolean CooldownManager(Player player, Material material){
        if(player.getCooldown(material) > 0) {
            player.sendMessage("§3You can use this after " + ChatColor.AQUA + ((player.getCooldown(material) / 20) + 1) + " §3Seconds !"); return false; }
        else { return true; }
    }

    private ArrayList<Entity> BoundingBoxEntityCollector(Location loaction, double xoffset, double yoffset, double zoffset, Player player){
        BoundingBox box = new BoundingBox(
                loaction.getX()-xoffset, loaction.getY()-yoffset, loaction.getZ()-zoffset,
                loaction.getX()+xoffset,loaction.getY()+yoffset,loaction.getZ()+zoffset);
        Collection<Entity> wntity = player.getWorld().getNearbyEntities(box);
        ArrayList<Entity> bdEntity = new ArrayList<>(wntity);
        return bdEntity;
    }

    private void CogmawR(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if(CooldownManager(player, Material.ENDER_EYE)) {
            player.setCooldown(Material.ENDER_EYE, 60);
            Location targetBlock = player.getTargetBlock(null, 100).getLocation();

            Particle.DustTransition Warning = new Particle.DustTransition(Color.fromRGB(150, 0, 0), Color.fromRGB(205, 30, 30), 1.3F);
            Particle.DustTransition Landing = new Particle.DustTransition(Color.fromRGB(0, 100, 0), Color.fromRGB(0, 250, 0), 3F);
            player.getWorld().playSound(player.getLocation(), Sound.ITEM_TRIDENT_RIPTIDE_1, 20, 1);
            new BukkitRunnable() {
                private int dpc = 0;
                @Override
                public void run() {
                    if (dpc == 3) {
                        this.cancel(); // <--
                        return;
                    }
                    player.spawnParticle(Particle.DUST_COLOR_TRANSITION, targetBlock.getX(), targetBlock.getY(),targetBlock.getZ(), 180, 2, 1, 2,Warning);
                    dpc++;
                }
            }.runTaskTimer(LeagueWeapons.getPlugin(LeagueWeapons.class), 0L /*<-- the initial delay */, 6L /*<-- the interval */);

            new BukkitRunnable() {
                @Override
                public void run() { player.getWorld().playSound(targetBlock, Sound.ITEM_TRIDENT_RIPTIDE_1, 20, 1); }
            }.runTaskLater(LeagueWeapons.getPlugin(LeagueWeapons.class), 32L);


            new BukkitRunnable() {
                @Override
                public void run() {
                    //no damage
                    player.getWorld().strikeLightningEffect(targetBlock);
                    player.spawnParticle(Particle.FLASH, targetBlock.getX(), targetBlock.getY(), targetBlock.getZ(), 15, 2, 0, 2);
                    player.spawnParticle(Particle.LAVA, targetBlock.getX(), targetBlock.getY(), targetBlock.getZ(), 200, 3, 0,3);
                    player.spawnParticle(Particle.DUST_COLOR_TRANSITION, targetBlock.getX(), targetBlock.getY(), targetBlock.getZ(), 400, 3, 3,3,Landing);
                    player.spawnParticle(Particle.GLOW_SQUID_INK, targetBlock.getX(), targetBlock.getY(), targetBlock.getZ(), 300, 3, 0,3);
                    player.getWorld().playSound(targetBlock,Sound.ENTITY_GENERIC_EXPLODE, 10, 4);

                    ArrayList<Entity> TargetEntityList = BoundingBoxEntityCollector(targetBlock, 3,3,3, player);
                    for(Entity entity : TargetEntityList){
                        if(entity instanceof LivingEntity && entity != (Entity)player && !(entity.getName() == player.getName())) {
                            ((LivingEntity) entity).damage(8, player);}
                    }
                }
            }.runTaskLater(LeagueWeapons.getPlugin(LeagueWeapons.class), 35L);


        }
        event.setCancelled(true);
    }

    private void JaxE(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if (CooldownManager(player, Material.LANTERN)){
            player.setCooldown(Material.LANTERN,200);
            PotionEffect invincible = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 30, 3, false, false);
            PotionEffect disablejump = new PotionEffect(PotionEffectType.JUMP, 40, 249, false, false);
            PotionEffect disablemove = new PotionEffect(PotionEffectType.SLOW, 40, 7, false, false);
            PotionEffect weakness = new PotionEffect(PotionEffectType.WEAKNESS, 40, 100, false, false);
            ArrayList<PotionEffect> enemyeffect = new ArrayList<>(); enemyeffect.add(disablemove); enemyeffect.add(disablejump); enemyeffect.add(weakness);
            Particle.DustTransition dustTransitionL = new Particle.DustTransition(Color.fromRGB(201, 199, 252), Color.fromRGB(0, 0, 252), 1.2F);
            Particle.DustTransition dustTransitionH = new Particle.DustTransition(Color.fromRGB(0, 0, 246), Color.fromRGB(0, 0, 132), 2.5F);
            Particle.DustTransition dustTransitionG = new Particle.DustTransition(Color.fromRGB(176, 225, 246), Color.fromRGB(155, 191, 246), 0.8F);

            // initial Particle
            player.spawnParticle(Particle.DUST_COLOR_TRANSITION, player.getLocation().getX(), player.getLocation().getY() + 2, player.getLocation().getZ(), 180, 1.4, 0.5, 1.4,dustTransitionL);
            player.addPotionEffect(invincible);
            player.playSound(player, Sound.BLOCK_BASALT_BREAK, 1, 1);

            //delay particle
            new BukkitRunnable() {
                private int dpc = 0;
                @Override
                public void run() {
                    if (dpc == 3) {
                        this.cancel(); // <--
                        return;
                    }
                    player.spawnParticle(Particle.DUST_COLOR_TRANSITION, player.getLocation().getX(), player.getLocation().getY() + 2, player.getLocation().getZ(), 120, 1.4, 1, 1.4,dustTransitionG);
                    player.playSound(player, Sound.ENTITY_ZOMBIE_ATTACK_IRON_DOOR, 1, -5);
                    dpc++;
                }
            }.runTaskTimer(LeagueWeapons.getPlugin(LeagueWeapons.class), 6L /*<-- the initial delay */, 8L /*<-- the interval */);
            // final Particle
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.spawnParticle(Particle.DUST_COLOR_TRANSITION, player.getLocation().getX(), player.getLocation().getY() + 1, player.getLocation().getZ(), 240, 1.8, 0.5, 1.8,dustTransitionH);
                    player.playSound(player,Sound.ENTITY_IRON_GOLEM_DAMAGE, 3, 0);
                    ArrayList<Entity> bdEntity = BoundingBoxEntityCollector(player.getLocation(), 2.5, 2.5, 2.5, player);

                    for(Entity entity : bdEntity){
                        if(entity instanceof LivingEntity && entity != (Entity)player && !(entity.getName() == player.getName())) {
                            ((LivingEntity) entity).addPotionEffects(enemyeffect);
                            ((LivingEntity) entity).damage(15, player);}
                        player.playSound(player, Sound.ITEM_CROSSBOW_SHOOT, 2, 0);
                    }
                }
            }.runTaskLater(LeagueWeapons.getPlugin(LeagueWeapons.class), 30L /*<-- the delay */);
        }
        event.setCancelled(true);
    }
}
