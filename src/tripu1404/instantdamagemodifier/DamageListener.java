package tripu1404.instantdamagemodifier;

import cn.nukkit.event.Listener;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.event.entity.EntityDamageEvent.DamageCause;

public class DamageListener implements Listener {

    private final Main plugin;

    public DamageListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        // Solo afecta daño mágico (efecto Daño Instantáneo)
        if (event.getCause() == DamageCause.MAGIC) {
            double multiplier = plugin.getDamageMultiplier();
            double newDamage = event.getFinalDamage() * multiplier;
            event.setDamage(newDamage);
        }
    }
}
