package top.mrxiaom.sweet.biomeaction;
        
import org.jetbrains.annotations.NotNull;
import top.mrxiaom.pluginbase.BukkitPlugin;
import top.mrxiaom.pluginbase.EconomyHolder;

public class SweetBiomeAction extends BukkitPlugin {
    public static SweetBiomeAction getInstance() {
        return (SweetBiomeAction) BukkitPlugin.getInstance();
    }

    public SweetBiomeAction() {
        super(options()
                .bungee(false)
                .adventure(true)
                .database(false)
                .reconnectDatabaseWhenReloadConfig(false)
                .vaultEconomy(false)
                .scanIgnore("top.mrxiaom.sweet.biomeaction.libs")
        );
    }


    @Override
    protected void afterEnable() {
        getLogger().info("SweetBiomeAction 加载完毕");
    }
}
