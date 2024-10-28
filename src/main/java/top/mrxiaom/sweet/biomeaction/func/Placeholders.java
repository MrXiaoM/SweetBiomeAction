package top.mrxiaom.sweet.biomeaction.func;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.mrxiaom.pluginbase.func.AutoRegister;
import top.mrxiaom.sweet.biomeaction.SweetBiomeAction;
import top.mrxiaom.sweet.biomeaction.nms.NMS;

@AutoRegister(requirePlugins = "PlaceholderAPI")
public class Placeholders extends AbstractModule{
    public class Internal extends PlaceholderExpansion {
        @Override
        public boolean register() {
            super.unregister();
            return super.register();
        }

        @Override
        public @NotNull String getIdentifier() {
            return plugin.getDescription().getName().toLowerCase();
        }
        @Override
        public @NotNull String getAuthor() {
            return String.join(", ", plugin.getDescription().getAuthors());
        }

        @Override
        public @NotNull String getVersion() {
            return plugin.getDescription().getVersion();
        }

        @Override
        public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {
            return Placeholders.this.onPlaceholderRequest(player, params);
        }
    }
    final Internal placeholders;
    public Placeholders(SweetBiomeAction plugin) {
        super(plugin);
        placeholders = new Internal();
        placeholders.register();
    }

    private String onPlaceholderRequest(Player player, String params) {
        if (params.startsWith("current") && (params.length() == 7 || params.substring(7).startsWith("_"))) {
            Location loc = player.getLocation();
            NamespacedKey biomeType = NMS.getRealBiomeType(player.getWorld(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
            if (biomeType == null) {
                return params.length() >= 8 ? params.substring(8) : "";
            } else {
                return biomeType.toString();
            }
        }
        return "";
    }
}
