package top.mrxiaom.sweet.biomeaction.nms;

import net.minecraft.server.v1_13_R2.IRegistry;
import net.minecraft.server.v1_13_R2.MinecraftKey;
import net.minecraft.server.v1_13_R2.BlockPosition;
import net.minecraft.server.v1_13_R2.BiomeBase;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_13_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_13_R2.util.CraftNamespacedKey;

public class Biome_v1_13_R2 implements IBiome {
    @Override
    public NamespacedKey getRealBiomeType(World world, int x, int y, int z) {
        BiomeBase biome = ((CraftWorld) world).getHandle().getBiome(new BlockPosition(x, y, z));
        if (biome == null) return null;
        MinecraftKey key = IRegistry.BIOME.getKey(biome);
        if (key == null) return null;
        return CraftNamespacedKey.fromMinecraft(key);
    }
}
