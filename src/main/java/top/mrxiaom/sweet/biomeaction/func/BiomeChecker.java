package top.mrxiaom.sweet.biomeaction.func;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemoryConfiguration;
import org.bukkit.entity.Player;
import top.mrxiaom.pluginbase.func.AutoRegister;
import top.mrxiaom.sweet.biomeaction.Actions;
import top.mrxiaom.sweet.biomeaction.SweetBiomeAction;
import top.mrxiaom.sweet.biomeaction.nms.NMS;
import top.mrxiaom.sweet.biomeaction.utils.Key;

import java.util.*;

@AutoRegister
public class BiomeChecker extends AbstractModule {
    public static class Checker {
        public final Key biome;
        public final long cooldownSecond;
        public final List<String> commands;

        Checker(Key biome, long cooldownSecond, List<String> commands) {
            this.biome = biome;
            this.cooldownSecond = cooldownSecond;
            this.commands = commands;
        }
    }
    Set<String> blacklistWorlds = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
    Map<String, Long> cooldown = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    Map<UUID, Key> lastBiome = new HashMap<>();
    Map<Key, Checker> checkerMap = new TreeMap<>();
    public BiomeChecker(SweetBiomeAction plugin) {
        super(plugin);
        Bukkit.getScheduler().runTaskTimer(plugin, this::everySecond, 20L, 20L);
    }

    private void everySecond() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            check(player);
        }
    }

    @Override
    public void reloadConfig(MemoryConfiguration config) {
        super.reloadConfig(config);
        Actions.titleFadeIn = config.getInt("title-time.fade-in", 10);
        Actions.titleStay = config.getInt("title-time.stay", 30);
        Actions.titleFadeOut = config.getInt("title-time.fade-out", 10);
        blacklistWorlds.clear();
        blacklistWorlds.addAll(config.getStringList("blacklist-worlds"));
        checkerMap.clear();
        ConfigurationSection section = config.getConfigurationSection("biomes");
        if (section != null) for (String namespace : section.getKeys(false)) {
            ConfigurationSection section1 = section.getConfigurationSection(namespace);
            if (section1 != null) for (String value : section1.getKeys(false)) {
                Key biome = new Key(namespace, value);
                ConfigurationSection section2 = section1.getConfigurationSection(value + ".requirements");
                if (section2 != null) for (String key : section2.getKeys(false)) {
                    // TODO: 有空再写，命令触发条件 requirements
                }
                long cooldownSecond = section1.getLong(value + ".cooldown");
                List<String> commands = section1.getStringList(value + ".commands");
                checkerMap.put(biome, new Checker(biome, cooldownSecond, commands));
            }
        }
    }

    public void check(Player player) {
        Location loc = player.getLocation();
        UUID uuid = player.getUniqueId();
        Key fromBiome = lastBiome.get(uuid);
        Key toBiome = NMS.getRealBiomeType(player.getWorld(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
        if (toBiome != null && !toBiome.equals(fromBiome)) {
            lastBiome.put(uuid, toBiome);
            Checker checker = checkerMap.get(toBiome);
            if (checker == null) return;
            long now = System.currentTimeMillis();
            String key = uuid + ";" + toBiome;
            if (now < cooldown.getOrDefault(key, now)) {
                // 冷却中
                return;
            }
            cooldown.put(key, now + checker.cooldownSecond * 1000L);
            Actions.execute(player, checker.commands);
        }
    }
}
