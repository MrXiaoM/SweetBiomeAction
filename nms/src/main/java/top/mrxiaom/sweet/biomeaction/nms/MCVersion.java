package top.mrxiaom.sweet.biomeaction.nms;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * from item-nbt-api
 */
public enum MCVersion {
    UNKNOWN(Integer.MAX_VALUE),
    MC1_7_R4(174),
    MC1_8_R3(183),
    MC1_9_R1(191),
    MC1_9_R2(192),
    MC1_10_R1(1101),
    MC1_11_R1(1111),
    MC1_12_R1(1121),
    MC1_13_R1(1131),
    MC1_13_R2(1132),
    MC1_14_R1(1141),
    MC1_15_R1(1151),
    MC1_16_R1(1161),
    MC1_16_R2(1162),
    MC1_16_R3(1163),
    MC1_17_R1(1171),
    MC1_18_R1(1181, true),
    MC1_18_R2(1182, true),
    MC1_19_R1(1191, true),
    MC1_19_R2(1192, true),
    MC1_19_R3(1193, true),
    MC1_20_R1(1201, true),
    MC1_20_R2(1202, true),
    MC1_20_R3(1203, true),
    MC1_20_R4(1204, true),
    MC1_21_R1(1211, true);

    private static MCVersion version;
    private static Logger logger = Logger.getLogger("NBTAPI");
    private final int versionId;
    private final boolean mojangMapping;
    private static final Map<String, MCVersion> VERSION_TO_REVISION = new HashMap<String, MCVersion>() {
        {
            this.put("1.20", MCVersion.MC1_20_R1);
            this.put("1.20.1", MCVersion.MC1_20_R1);
            this.put("1.20.2", MCVersion.MC1_20_R2);
            this.put("1.20.3", MCVersion.MC1_20_R3);
            this.put("1.20.4", MCVersion.MC1_20_R3);
            this.put("1.20.5", MCVersion.MC1_20_R4);
            this.put("1.20.6", MCVersion.MC1_20_R4);
            this.put("1.21", MCVersion.MC1_21_R1);
            this.put("1.21.1", MCVersion.MC1_21_R1);
        }
    };

    private MCVersion(int versionId) {
        this(versionId, false);
    }

    private MCVersion(int versionId, boolean mojangMapping) {
        this.versionId = versionId;
        this.mojangMapping = mojangMapping;
    }

    public int getVersionId() {
        return this.versionId;
    }

    public boolean isMojangMapping() {
        return this.mojangMapping;
    }

    public String getPackageName() {
        if (this == UNKNOWN) {
            try {
                return Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
            } catch (Exception var2) {
            }
        }

        return this.name().replace("MC", "v");
    }

    public static boolean isAtLeastVersion(MCVersion version) {
        return getVersion().getVersionId() >= version.getVersionId();
    }

    public static boolean isNewerThan(MCVersion version) {
        return getVersion().getVersionId() > version.getVersionId();
    }

    public static MCVersion getVersion() {
        if (version != null) {
            return version;
        } else {
            try {
                String ver = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
                logger.info("发现 Minecraft 版本: " + ver + "! 尝试寻找 NMS 支持");
                version = valueOf(ver.replace("v", "MC"));
            } catch (Exception var1) {
                logger.info("发现 Minecraft 版本: " + Bukkit.getServer().getBukkitVersion().split("-")[0] + "! 尝试寻找 NMS 支持");
                version = (MCVersion)VERSION_TO_REVISION.getOrDefault(Bukkit.getServer().getBukkitVersion().split("-")[0], UNKNOWN);
            }
            return version;
        }
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void replaceLogger(Logger logger) {
        if (logger == null) {
            throw new NullPointerException("Logger can not be null!");
        } else {
            MCVersion.logger = logger;
        }
    }
}
