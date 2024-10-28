package top.mrxiaom.sweet.biomeaction.func;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import top.mrxiaom.pluginbase.func.AutoRegister;
import top.mrxiaom.sweet.biomeaction.SweetBiomeAction;
import top.mrxiaom.sweet.biomeaction.nms.NMS;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

@AutoRegister
public class BiomeChecker extends AbstractModule {
    Map<String, Long> cooldown = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    Map<UUID, NamespacedKey> lastBiome = new HashMap<>();
    public BiomeChecker(SweetBiomeAction plugin) {
        super(plugin);
        Bukkit.getScheduler().runTaskTimer(plugin, this::everySecond, 20L, 20L);
    }

    private void everySecond() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            check(player);
        }
    }

    public void check(Player player) {
        Location loc = player.getLocation();
        UUID uuid = player.getUniqueId();
        NamespacedKey fromBiome = lastBiome.get(uuid);
        NamespacedKey toBiome = NMS.getRealBiomeType(player.getWorld(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
        if (toBiome != null && !toBiome.equals(fromBiome)) {
            lastBiome.put(uuid, toBiome);
            // TODO: 触发操作 以及冷却时间
        }
    }
}
