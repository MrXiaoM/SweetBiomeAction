package top.mrxiaom.sweet.biomeaction.nms;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import top.mrxiaom.sweet.biomeaction.utils.Key;

import java.util.logging.Logger;

public class NMS {
    private static IBiome biome = null;

    public static boolean init(Logger logger) {
        MCVersion.replaceLogger(logger);
        MCVersion version = MCVersion.getVersion();

        switch (version) {
            case MC1_21_R3:
                biome = new Biome_v1_21_R3();
                return true;
            case MC1_21_R2:
                biome = new Biome_v1_21_R2();
                return true;
            case MC1_21_R1:
                biome = new Biome_v1_21_R1();
                return true;
            case MC1_20_R4:
                biome = new Biome_v1_20_R4();
                return true;
            case MC1_20_R3:
                biome = new Biome_v1_20_R3();
                return true;
            case MC1_20_R2:
                biome = new Biome_v1_20_R2();
                return true;
            case MC1_20_R1:
                biome = new Biome_v1_20_R1();
                return true;
            case MC1_19_R3:
                biome = new Biome_v1_19_R3();
                return true;
            case MC1_18_R2:
                biome = new Biome_v1_18_R2();
                return true;
            case MC1_17_R1:
                biome = new Biome_v1_17_R1();
                return true;
            case MC1_16_R3:
                biome = new Biome_v1_16_R3();
                return true;
            case MC1_15_R1:
                biome = new Biome_v1_15_R1();
                return true;
            case MC1_14_R1:
                biome = new Biome_v1_14_R1();
                return true;
            case MC1_13_R2:
                biome = new Biome_v1_13_R2();
                return true;
        }
        return false;
    }

    public static Key getRealBiomeType(World world, int x, int y, int z) {
        if (biome == null) {
            return new Key("minecraft", world.getBiome(x, y, z).name());
        }
        return biome.getRealBiomeType(world, x, y, z);
    }
}
