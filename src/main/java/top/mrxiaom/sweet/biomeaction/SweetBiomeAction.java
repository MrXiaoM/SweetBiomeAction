package top.mrxiaom.sweet.biomeaction;

import org.bukkit.configuration.file.YamlConfiguration;
import top.mrxiaom.pluginbase.BukkitPlugin;
import top.mrxiaom.pluginbase.economy.EnumEconomy;
import top.mrxiaom.pluginbase.resolver.DefaultLibraryResolver;
import top.mrxiaom.pluginbase.utils.ClassLoaderWrapper;
import top.mrxiaom.pluginbase.utils.ConfigUtils;
import top.mrxiaom.pluginbase.utils.scheduler.FoliaLibScheduler;
import top.mrxiaom.sweet.biomeaction.nms.MCVersion;
import top.mrxiaom.sweet.biomeaction.nms.NMS;

import java.io.File;
import java.net.URL;
import java.util.List;

public class SweetBiomeAction extends BukkitPlugin {
    public static SweetBiomeAction getInstance() {
        return (SweetBiomeAction) BukkitPlugin.getInstance();
    }

    public SweetBiomeAction() throws Exception {
        super(options()
                .bungee(false)
                .adventure(true)
                .database(false)
                .reconnectDatabaseWhenReloadConfig(false)
                .economy(EnumEconomy.NONE)
                .scanIgnore("top.mrxiaom.sweet.biomeaction.libs")
        );
        scheduler = new FoliaLibScheduler(this);

        try {
            //noinspection ResultOfMethodCallIgnored
            getDescription().getLibraries();
        } catch (LinkageError ignored) {
            info("正在检查依赖库状态");
            File librariesDir = ClassLoaderWrapper.isSupportLibraryLoader
                    ? new File("libraries")
                    : new File(this.getDataFolder(), "libraries");
            DefaultLibraryResolver resolver = new DefaultLibraryResolver(getLogger(), librariesDir);

            YamlConfiguration overrideLibraries = ConfigUtils.load(resolve("./.override-libraries.yml"));
            for (String key : overrideLibraries.getKeys(false)) {
                resolver.getStartsReplacer().put(key, overrideLibraries.getString(key));
            }
            resolver.addResolvedLibrary(BuildConstants.RESOLVED_LIBRARIES);

            List<URL> libraries = resolver.doResolve();
            info("正在添加 " + libraries.size() + " 个依赖库到类加载器");
            for (URL library : libraries) {
                this.classLoader.addURL(library);
            }
        }
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
