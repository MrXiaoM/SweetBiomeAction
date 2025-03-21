package top.mrxiaom.sweet.biomeaction.nms;

import org.bukkit.World;
import org.jetbrains.annotations.Nullable;
import top.mrxiaom.sweet.biomeaction.utils.Key;

public interface IBiome {
    @Nullable
    Key getRealBiomeType(World world, int x, int y, int z);
}
