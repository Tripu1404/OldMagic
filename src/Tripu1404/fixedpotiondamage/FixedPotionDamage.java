package Tripu1404.fixedpotiondamage;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.event.Listener;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.entity.projectile.EntityArrow;
import cn.nukkit.entity.Entity;
import cn.nukkit.Player;
import cn.nukkit.utils.Config;

import java.io.File;

public class FixedPotionDamage extends PluginBase implements Listener {

    private float customDamage = 2.0f;

    @Override
    public void onEnable() {
        // Cargar config.yml
        File configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            getDataFolder().mkdirs();
            Config config = new Config(configFile, Config.YAML);
            config.set("arrow_damage", 2.0);
            config.save();
        }

        Config config = new Config(configFile, Config.YAML);
        customDamage = (float) config.getDouble("arrow_damage", 2.0);

        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("FixedPotionDamage habilitado con daño de flechas: " + customDamage);
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        Entity target = event.getEntity();

        if (!(damager instanceof EntityArrow)) return;

        event.setCancelled();

        target.attack(new EntityDamageEvent(target, EntityDamageEvent.DamageCause.PROJECTILE, customDamage));
    }
}
