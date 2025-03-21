package top.mrxiaom.sweet.biomeaction;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.mrxiaom.sweet.biomeaction.nms.NMS;

public class Placeholders extends PlaceholderExpansion {
    public final SweetBiomeAction plugin;

    public Placeholders(SweetBiomeAction plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean register() {
        try {
            unregister();
        } catch (Throwable ignored) {}
        return super.register();
    }

    @Override
    public @NotNull String getIdentifier() {
        return plugin.getDescription().getName();
    }

    @Override
    public @NotNull String getAuthor() {
        return String.join("\n", plugin.getDescription().getAuthors());
    }

    @Override
    public @NotNull String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {
        if (params.equalsIgnoreCase("biome")) {
            World world = player.getWorld();
            Location loc = player.getLocation();
            NamespacedKey biome = NMS.getRealBiomeType(world, loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
            return biome.toString();
        }
        return super.onPlaceholderRequest(player, params);
    }
}
