package top.mrxiaom.sweet.biomeaction.nms;

import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.jetbrains.annotations.Nullable;

public interface IBiome {
    @Nullable
    NamespacedKey getRealBiomeType(World world, int x, int y, int z);
}
