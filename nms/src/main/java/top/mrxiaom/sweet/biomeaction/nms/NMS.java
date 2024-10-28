package top.mrxiaom.sweet.biomeaction.nms;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.World;

import java.util.logging.Logger;

public class NMS {
    private static IBiome biome;

    public static boolean init(Logger logger) {
        MCVersion.replaceLogger(logger);
        MCVersion version = MCVersion.getVersion();

        switch (version) {
            case MC1_20_R3:
                biome = new Biome_v1_20_R3();
                return true;
        }
        logger.warning("插件不支持当前版本 " + version.name());
        return false;
    }

    public static NamespacedKey getRealBiomeType(World world, int x, int y, int z) {
        return biome.getRealBiomeType(world, x, y, z);
    }
}
