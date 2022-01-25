package xyz.ham5teak.doublejump.listeners;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;
import xyz.ham5teak.doublejump.utils.Configuration;
import xyz.ham5teak.doublejump.DoubleJump;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class Jump implements Listener {

    public Jump() {
    }

    public Configuration config;

    private HashSet<Player> nodamage = new HashSet<>();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        if(DoubleJump.getInstance().DoubleJumpEnabled().equals(Boolean.TRUE)) {
            if(e.getPlayer().hasPermission("cmi.command.fly") &&
                    DoubleJump.getInstance().CMIFlightEnabled().equals(Boolean.TRUE)){
                return;
            }
            e.getPlayer().setAllowFlight(true);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerDoubleJump(PlayerToggleFlightEvent e){
        Player p = e.getPlayer();
        Block b = p.getWorld().getBlockAt(p.getLocation().subtract(0,2,0));
        if(p.getGameMode() != GameMode.CREATIVE &&
                DoubleJump.getInstance().DoubleJumpEnabled().equals(Boolean.TRUE)){
            if(e.getPlayer().hasPermission("cmi.command.fly") && DoubleJump.getInstance().CMIFlightEnabled().equals(Boolean.TRUE)){
                return;
            }
                HashMap<Player, Long> cooldown = new HashMap<>();
                cooldown.put(p, (System.currentTimeMillis() / 1000));
                if((cooldown.get(p) + 1) >= (System.currentTimeMillis() / 1000)){
                    if(b.getType().equals(Material.AIR)) {
                        e.setCancelled(true);
                        p.setAllowFlight(false);
                        Bukkit.getScheduler().scheduleSyncDelayedTask(DoubleJump.getInstance(),
                                () -> p.setAllowFlight(true), DoubleJump.getInstance().Cooldown());
                        return;
                    }
                    e.setCancelled(true);
                    p.setAllowFlight(false);
                    p.setVelocity(new Vector(p.getVelocity().getX() * DoubleJump.getInstance().XZInterval(),
                            DoubleJump.getInstance().YInterval(),
                            p.getVelocity().getZ() * DoubleJump.getInstance().XZInterval()
                    ));
                    nodamage.add(p);
                    Bukkit.getScheduler().scheduleSyncDelayedTask(DoubleJump.getInstance(),
                            () -> p.setAllowFlight(true), DoubleJump.getInstance().Cooldown());
                }
        }
    }

    @EventHandler
    public void onFall(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            if (e.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
                Player p = (Player) e.getEntity();
                if (nodamage.contains(p)) {
                    e.setCancelled(true);
                    nodamage.remove(p);
                }
            }
        }
    }

    public static Plugin getJump() {
        return Bukkit.getPluginManager().getPlugin("DoubleJump");
    }
}
