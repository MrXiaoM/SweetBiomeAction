package top.mrxiaom.sweet.biomeaction.nms;

import net.minecraft.server.v1_14_R1.BlockPosition;
import net.minecraft.server.v1_14_R1.IRegistry;
import net.minecraft.server.v1_14_R1.MinecraftKey;
import net.minecraft.server.v1_14_R1.BiomeBase;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_14_R1.CraftWorld;
import top.mrxiaom.sweet.biomeaction.utils.Key;

public class Biome_v1_14_R1 implements IBiome {
    @Override
    public Key getRealBiomeType(World world, int x, int y, int z) {
        BiomeBase biome = ((CraftWorld) world).getHandle().getBiome(new BlockPosition(x, y, z));
        if (biome == null) return null;
        MinecraftKey key = IRegistry.BIOME.getKey(biome);
        if (key == null) return null;
        return new Key(key.getNamespace(), key.getKey());
    }
}
