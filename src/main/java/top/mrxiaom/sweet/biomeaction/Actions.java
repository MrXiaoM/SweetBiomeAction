package top.mrxiaom.sweet.biomeaction;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import top.mrxiaom.pluginbase.utils.AdventureUtil;
import top.mrxiaom.pluginbase.utils.ColorHelper;
import top.mrxiaom.pluginbase.utils.PAPI;
import top.mrxiaom.pluginbase.utils.Util;

import java.util.List;

public class Actions {
    public static int titleFadeIn = 10;
    public static int titleStay = 30;
    public static int titleFadeOut = 10;
    public static void execute(Player player, List<String> actions) {
        execute(player, PAPI.setPlaceholders(player, actions), 0);
    }
    private static void execute(Player player, List<String> actions, int startIndex) {
        for (int i = startIndex; i < actions.size(); i++) {
            String action = actions.get(i);
            if (action.startsWith("[player]")) {
                Bukkit.dispatchCommand(player, ColorHelper.parseColor(action.substring(8).trim()));
                continue;
            }
            if (action.startsWith("[console]")) {
                Bukkit.dispatchCommand(player, ColorHelper.parseColor(action.substring(9).trim()));
                continue;
            }
            if (action.startsWith("[message]")) {
                AdventureUtil.sendMessage(player, action.substring(9));
                continue;
            }
            if (action.startsWith("[actionbar]")) {
                AdventureUtil.sendActionBar(player, action.substring(11));
                continue;
            }
            if (action.startsWith("[title]")) {
                String[] split = action.substring(7).split("\\<subtitle\\>", 2);
                if (split.length != 2) continue;
                AdventureUtil.sendTitle(player, split[0].trim(), split[1].trim(), titleFadeIn, titleStay, titleFadeOut);
                continue;
            }
            if (action.startsWith("[delay]")) {
                Long delay = Util.parseLong(action.substring(7)).orElse(null);
                if (delay != null) {
                    int index = i + 1;
                    SweetBiomeAction plugin = SweetBiomeAction.getInstance();
                    Bukkit.getScheduler().runTaskLater(plugin, () -> execute(player, actions, index), delay);
                    break;
                }
            }
        }
    }
}
