package top.mrxiaom.sweet.biomeaction.nms;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.biome.Biome;
import org.bukkit.World;
import org.bukkit.craftbukkit.CraftRegionAccessor;
import top.mrxiaom.sweet.biomeaction.utils.Key;

public class Biome_v26_1 implements IBiome {
    @Override
    public Key getRealBiomeType(World world, int x, int y, int z) {
        Registry<Biome> registry = ((CraftRegionAccessor) world).getHandle().registryAccess().lookupOrThrow(Registries.BIOME);
        Holder<Biome> biomeHolder = ((CraftRegionAccessor) world).getHandle().getNoiseBiome(x >> 2, y >> 2, z >> 2);
        Biome biome = biomeHolder.value();
        Identifier key = registry.getKey(biome);
        if (key == null) return null;
        return new Key(key.getNamespace(), key.getPath());
    }
}
