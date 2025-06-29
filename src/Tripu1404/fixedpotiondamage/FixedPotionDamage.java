package Tripu1404.fixedpotiondamage;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.event.entity.EntityDamageEvent.DamageCause;
import cn.nukkit.plugin.PluginBase;

public class FixedPotionDamage extends PluginBase implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("FixedPotionDamage habilitado 🎯");
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        if (!(entity instanceof Player)) return;

        if (event.getCause() == DamageCause.MAGIC) {
            float originalDamage = event.getFinalDamage();
            float finalDamage;

            if (originalDamage >= 6.0f) {
                // Posible poción de daño instantáneo
                finalDamage = 12.0f;
            } else if (originalDamage <= 1.0f) {
                // Posible veneno (daño bajo y repetitivo)
                finalDamage = originalDamage + 2.0f;
            } else {
                // Posible wither o magia general
                finalDamage = originalDamage * 2.0f;
            }

            event.setCancelled(); // Cancelamos el daño normal
            ((Player) entity).attack(new EntityDamageEvent(entity, DamageCause.CUSTOM, finalDamage)); // Daño personalizado
        }
    }
}
