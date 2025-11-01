package tripu1404.instantdamagemodifier;

import cn.nukkit.effect.Effect;
import cn.nukkit.entity.Entity;
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
        // Solo nos interesa daño mágico
        if(event.getCause() != DamageCause.MAGIC) return;

        Entity entity = event.getEntity();

        // Revisamos si la entidad tiene el efecto Instant Damage (ID 7)
        boolean hasInstantDamage = entity.getEffects().stream()
                                        .anyMatch(e -> e.getId() == Effect.INSTANT_DAMAGE.getId());

        if(hasInstantDamage) {
            // Calculamos el daño multiplicado
            float newDamage = (float)(event.getFinalDamage() * plugin.getDamageMultiplier());
            event.setDamage(newDamage);
        }
    }
}
