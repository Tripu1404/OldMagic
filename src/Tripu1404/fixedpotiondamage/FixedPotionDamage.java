package Tripu1404.fixedpotiondamage;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.event.entity.EntityDamageEvent.DamageCause;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.entity.Entity;

public class FixedPotionDamage extends PluginBase implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("FixedPotionDamage ha sido habilitado");
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();

        if (!(entity instanceof Player)) return;

        DamageCause cause = event.getCause();
        if (cause == DamageCause.MAGIC) {
            event.setCancelled(); // Cancelamos el daño original
            ((Player) entity).attack(new EntityDamageEvent(entity, DamageCause.CUSTOM, 12.0f)); // Daño: 8 + 4
        }
    }
}
