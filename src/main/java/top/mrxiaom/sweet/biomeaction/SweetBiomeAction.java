package top.mrxiaom.sweet.biomeaction;
        
import org.jetbrains.annotations.NotNull;
import top.mrxiaom.pluginbase.BukkitPlugin;
import top.mrxiaom.pluginbase.EconomyHolder;
import top.mrxiaom.pluginbase.utils.PAPI;
import top.mrxiaom.sweet.biomeaction.nms.MCVersion;
import top.mrxiaom.sweet.biomeaction.nms.NMS;

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
    protected void beforeLoad() {
        if (!NMS.init(getLogger())) {
            getLogger().warning("插件不支持当前版本 " + MCVersion.getVersion().name() + "，将不会有自定义生物群系支持");
        }
    }

    @Override
    protected void afterEnable() {
        getLogger().info("SweetBiomeAction 加载完毕");
    }
}
