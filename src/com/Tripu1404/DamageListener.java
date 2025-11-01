package com.example.instantdamagemodifier;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.event.Listener;
import cn.nukkit.event.HandlerList;

public class Main extends PluginBase {

    private static Main instance;
    private double damageMultiplier;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        reloadConfig();
        damageMultiplier = getConfig().getDouble("damage-multiplier", 2.0);

        getServer().getPluginManager().registerEvents(new DamageListener(this), this);
        getLogger().info("InstantDamageModifier habilitado con multiplicador: " + damageMultiplier);
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
        getLogger().info("InstantDamageModifier deshabilitado.");
    }

    public double getDamageMultiplier() {
        return damageMultiplier;
    }

    public static Main getInstance() {
        return instance;
    }
}
