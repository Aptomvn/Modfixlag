package com.lagfixmod;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {

    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.BooleanValue ENABLE_XP_CLUMPING;
    public static final ForgeConfigSpec.DoubleValue XP_CLUMP_RADIUS;

    public static final ForgeConfigSpec.BooleanValue ENABLE_MOB_AI_THROTTLE;
    public static final ForgeConfigSpec.DoubleValue MOB_THROTTLE_DISTANCE;
    public static final ForgeConfigSpec.IntValue MOB_THROTTLE_INTERVAL;

    public static final ForgeConfigSpec.BooleanValue ENABLE_MOB_CAP;
    public static final ForgeConfigSpec.IntValue MAX_MOBS_NEARBY;
    public static final ForgeConfigSpec.DoubleValue MOB_CAP_RADIUS;

    static {
        BUILDER.push("xpOrbClumping");
        ENABLE_XP_CLUMPING = BUILDER
                .comment("Gom cac vien kinh nghiem (XP orb) dung gan nhau thanh 1 entity de giam so luong entity can tinh toan.")
                .define("enableXpOrbClumping", true);
        XP_CLUMP_RADIUS = BUILDER
                .comment("Ban kinh (block) de gom cac XP orb lai voi nhau.")
                .defineInRange("xpClumpRadius", 2.0, 0.5, 8.0);
        BUILDER.pop();

        BUILDER.push("mobAiThrottle");
        ENABLE_MOB_AI_THROTTLE = BUILDER
                .comment("Giam tan suat cap nhat AI/pathfinding cho quai vat o xa nguoi choi de tiet kiem CPU.")
                .define("enableMobAiThrottle", true);
        MOB_THROTTLE_DISTANCE = BUILDER
                .comment("Khoang cach (block) tinh tu nguoi choi gan nhat. Qua khoang nay quai se bi giam tan suat AI.")
                .defineInRange("mobThrottleDistance", 24.0, 8.0, 128.0);
        MOB_THROTTLE_INTERVAL = BUILDER
                .comment("Quai o xa chi duoc cap nhat AI moi N tick (2 = bo qua 1 nua so tick, cang cao cang tiet kiem nhung quai se 'giat' hon).")
                .defineInRange("mobThrottleInterval", 2, 2, 10);
        BUILDER.pop();

        BUILDER.push("mobCap");
        ENABLE_MOB_CAP = BUILDER
                .comment("Ngan khong cho quai vat spawn tu nhien them neu da qua dong trong 1 khu vuc nho.",
                          "Mac dinh TAT vi co the anh huong toi cac mo hinh mob farm / grinder.")
                .define("enableMobCap", false);
        MAX_MOBS_NEARBY = BUILDER
                .comment("So luong quai vat toi da cho phep trong ban kinh mobCapRadius truoc khi chan spawn moi.")
                .defineInRange("maxMobsNearby", 20, 5, 100);
        MOB_CAP_RADIUS = BUILDER
                .comment("Ban kinh (block) de dem so luong quai vat gan nhau.")
                .defineInRange("mobCapRadius", 16.0, 4.0, 64.0);
        BUILDER.pop();

        SPEC = BUILDER.build();
    }
}
