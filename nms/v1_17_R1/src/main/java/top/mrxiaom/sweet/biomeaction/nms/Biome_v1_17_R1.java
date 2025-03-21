package top.mrxiaom.sweet.biomeaction.nms;

import net.minecraft.core.IRegistry;
import net.minecraft.resources.MinecraftKey;
import net.minecraft.world.level.biome.BiomeBase;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_17_R1.CraftRegionAccessor;
import top.mrxiaom.sweet.biomeaction.utils.Key;

public class Biome_v1_17_R1 implements IBiome {
    @Override
    public Key getRealBiomeType(World world, int x, int y, int z) {
        IRegistry<BiomeBase> registry = ((CraftRegionAccessor) world).getHandle().t().b(IRegistry.aO);
        BiomeBase biome = ((CraftRegionAccessor) world).getHandle().getBiome(x >> 2, y >> 2, z >> 2);
        if (biome == null) return null;
        MinecraftKey key = registry.getKey(biome);
        if (key == null) return null;
        return new Key(key.getNamespace(), key.getKey());
    }
}
