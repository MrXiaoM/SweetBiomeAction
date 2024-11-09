package top.mrxiaom.sweet.biomeaction.nms;

import net.minecraft.core.Holder;
import net.minecraft.core.IRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.MinecraftKey;
import net.minecraft.world.level.biome.BiomeBase;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_19_R3.CraftRegionAccessor;
import org.bukkit.craftbukkit.v1_19_R3.util.CraftNamespacedKey;

public class Biome_v1_19_R3 implements IBiome {
    @Override
    public NamespacedKey getRealBiomeType(World world, int x, int y, int z) {
        IRegistry<BiomeBase> registry = ((CraftRegionAccessor) world).getHandle().u_().d(Registries.an);
        Holder<BiomeBase> biomeHolder = ((CraftRegionAccessor) world).getHandle().getNoiseBiome(x >> 2, y >> 2, z >> 2);
        BiomeBase biome = biomeHolder.a();
        if (biome == null) return null;
        MinecraftKey key = registry.b(biome);
        if (key == null) return null;
        return CraftNamespacedKey.fromMinecraft(key);
    }
}
