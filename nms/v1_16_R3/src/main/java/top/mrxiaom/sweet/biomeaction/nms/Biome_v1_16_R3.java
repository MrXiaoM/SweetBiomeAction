package top.mrxiaom.sweet.biomeaction.nms;

import net.minecraft.server.v1_16_R3.IRegistry;
import net.minecraft.server.v1_16_R3.MinecraftKey;
import net.minecraft.server.v1_16_R3.BiomeBase;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import top.mrxiaom.sweet.biomeaction.utils.Key;

public class Biome_v1_16_R3 implements IBiome {
    @Override
    public Key getRealBiomeType(World world, int x, int y, int z) {
        IRegistry<BiomeBase> registry = ((CraftWorld) world).getHandle().r().b(IRegistry.ay);
        BiomeBase biome = ((CraftWorld) world).getHandle().getBiome(x >> 2, y >> 2, z >> 2);
        if (biome == null) return null;
        MinecraftKey key = registry.getKey(biome);
        if (key == null) return null;
        return new Key(key.getNamespace(), key.getKey());
    }
}
