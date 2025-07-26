@Override
public void onEnable() {
    getServer().getPluginManager().registerEvents(this, this);
    getLogger().info("FixedPotionDamage habilitado 🎯");
}

@EventHandler
public void onEntityDamage(EntityDamageEvent event) {
    Entity entity = event.getEntity();
    if (!(entity instanceof Player)) return;
    Player player = (Player) entity;

    if (event.getCause() == DamageCause.MAGIC) {
        float originalDamage = event.getFinalDamage();
        float finalDamage;

        if (originalDamage >= 12.0f) {
            // Posible daño instantáneo → aplicar fijo
            finalDamage = 12.0f;
        } else if (originalDamage <= 1.0f) {
            // Posible veneno → calcular cuánto se puede aplicar sin matar
            float health = player.getHealth();
            finalDamage = Math.min(originalDamage + 2.0f, health - 1.0f);
            if (finalDamage <= 0.0f) return; // Ya está en medio corazón o menos
        } else {
            // Posible wither → duplicar daño
            finalDamage = originalDamage * 2.0f;
        }

        event.setCancelled();
        player.attack(new EntityDamageEvent(player, DamageCause.CUSTOM, finalDamage));
    }
}
