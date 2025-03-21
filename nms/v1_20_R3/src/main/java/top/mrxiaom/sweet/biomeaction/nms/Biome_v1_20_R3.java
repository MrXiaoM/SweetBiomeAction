package top.mrxiaom.sweet.biomeaction.nms;

import net.minecraft.core.Holder;
import net.minecraft.core.IRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.MinecraftKey;
import net.minecraft.world.level.biome.BiomeBase;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_20_R3.CraftRegionAccessor;
import top.mrxiaom.sweet.biomeaction.utils.Key;

public class Biome_v1_20_R3 implements IBiome {
    @Override
    public Key getRealBiomeType(World world, int x, int y, int z) {
        IRegistry<BiomeBase> registry = ((CraftRegionAccessor) world).getHandle().I_().d(Registries.at);
        Holder<BiomeBase> biomeHolder = ((CraftRegionAccessor) world).getHandle().getNoiseBiome(x >> 2, y >> 2, z >> 2);
        BiomeBase biome = biomeHolder.a();
        if (biome == null) return null;
        MinecraftKey key = registry.b(biome);
        if (key == null) return null;
        return new Key(key.b(), key.a());
    }
}
