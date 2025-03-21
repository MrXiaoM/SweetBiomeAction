package top.mrxiaom.sweet.biomeaction.func;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.mrxiaom.pluginbase.func.AutoRegister;
import top.mrxiaom.pluginbase.utils.Util;
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
            String str = params.substring(7);
            Location loc;
            if (str.length() == 7) {
                loc = player.getLocation();
            } else {
                String[] split = str.split("_");
                if (split.length != 3) {
                    return "";
                }
                Integer x = Util.parseInt(split[0]).orElse(null);
                Integer y = Util.parseInt(split[1]).orElse(null);
                Integer z = Util.parseInt(split[2]).orElse(null);
                if (x == null || y == null || z == null) {
                    return "";
                }
                loc = new Location(player.getWorld(), x, y, z);
            }
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
