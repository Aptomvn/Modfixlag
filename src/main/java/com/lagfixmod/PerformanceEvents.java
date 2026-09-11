package com.lagfixmod;

import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public class PerformanceEvents {

    @SubscribeEvent
    public void onEntityJoin(EntityJoinLevelEvent event) {
        if (!(event.getLevel() instanceof Level level) || level.isClientSide()) {
            return;
        }

        if (Config.ENABLE_XP_CLUMPING.get() && event.getEntity() instanceof ExperienceOrb orb) {
            double radius = Config.XP_CLUMP_RADIUS.get();
            AABB box = orb.getBoundingBox().inflate(radius);
            List<ExperienceOrb> nearby = level.getEntitiesOfClass(
                    ExperienceOrb.class, box, e -> e != orb && e.isAlive());

            if (!nearby.isEmpty()) {
                nearby.get(0).value += orb.value;
                event.setCanceled(true);
                return;
            }
        }

        if (Config.ENABLE_MOB_CAP.get()
                && event.getEntity() instanceof Mob mob
                && !(event.getEntity() instanceof Player)) {

            double radius = Config.MOB_CAP_RADIUS.get();
            AABB box = mob.getBoundingBox().inflate(radius);
            long nearbyCount = level.getEntitiesOfClass(Mob.class, box, LivingEntity::isAlive).size();

            if (nearbyCount > Config.MAX_MOBS_NEARBY.get()) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public void onLivingTick(LivingEvent.LivingTickEvent event) {
        if (!Config.ENABLE_MOB_AI_THROTTLE.get()) {
            return;
        }

        LivingEntity entity = event.getEntity();
        if (entity.level().isClientSide()) {
            return;
        }
        if (!(entity instanceof Mob mob) || entity instanceof Player) {
            return;
        }

        double throttleDist = Config.MOB_THROTTLE_DISTANCE.get();
        Player nearest = mob.level().getNearestPlayer(mob, throttleDist * 4);

        boolean isFar = nearest == null || nearest.distanceToSqr(mob) > throttleDist * throttleDist;
        if (!isFar) {
            return;
        }

        int interval = Config.MOB_THROTTLE_INTERVAL.get();
        long time = mob.level().getGameTime() + mob.getId();
        if (time % interval != 0) {
            event.setCanceled(true);
        }
    }
}
