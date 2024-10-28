package top.mrxiaom.sweet.biomeaction.func;
        
import top.mrxiaom.sweet.biomeaction.SweetBiomeAction;

@SuppressWarnings({"unused"})
public abstract class AbstractPluginHolder extends top.mrxiaom.pluginbase.func.AbstractPluginHolder<SweetBiomeAction> {
    public AbstractPluginHolder(SweetBiomeAction plugin) {
        super(plugin);
    }

    public AbstractPluginHolder(SweetBiomeAction plugin, boolean register) {
        super(plugin, register);
    }
}
