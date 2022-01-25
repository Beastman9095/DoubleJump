package xyz.ham5teak.doublejump;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginDescriptionFile;
import xyz.ham5teak.doublejump.commands.CommandManager;
import xyz.ham5teak.doublejump.commands.TabCompletion;
import xyz.ham5teak.doublejump.listeners.Jump;
import xyz.ham5teak.doublejump.utils.Configuration;

public final class DoubleJump extends JavaPlugin {

    public static DoubleJump instance = null;

    public static DoubleJump getInstance() { return instance; }

    private PluginDescriptionFile pluginInfo = getDescription();

    public static Configuration config;

    public static void ReloadConfig() {
        config.reloadConfig();
    }

    public static void SaveConfig(){
        config.saveConfig();
    }

    public static void ToggleJump(Boolean bool){
        config.getConfig().set("DoubleJump", bool);
    }

    public static void SetX(Float i){
        config.getConfig().set("XZinterval", i);
    }

    public static void SetY(Float i){
        config.getConfig().set("Yinterval", i);
    }

    public static void SetCooldown(Integer i){
        config.getConfig().set("Cooldown", i);
    }

    public static void ToggleFlight(Boolean bool){
        config.getConfig().set("CMIFlight", bool);
    }

    public Boolean CMIFlightEnabled(){
        return config.getConfig().getBoolean("CMIFlight");
    }

    public Boolean DoubleJumpEnabled(){
        return config.getConfig().getBoolean("DoubleJump");
    }

    public Double XZInterval(){
        return config.getConfig().getDouble("XZinterval");
    }

    public Double YInterval(){
        return config.getConfig().getDouble("Yinterval");
    }

    public long Cooldown(){
        return config.getConfig().getLong("Cooldown");
    }


    @Override
    public void onEnable() {

        // Plugin startup logic
        getServer().getConsoleSender().sendMessage(String.format("[%s] %s v%s\n" +
                        "  _____                 _      _             _                          \n" +
                        " |  __ \\               | |    | |           | |                         \n" +
                        " | |  | |  ___   _   _ | |__  | |  ___      | | _   _  _ __ ___   _ __  \n" +
                        " | |  | | / _ \\ | | | || '_ \\ | | / _ \\ _   | || | | || '_ ` _ \\ | '_ \\ \n" +
                        " | |__| || (_) || |_| || |_) || ||  __/| |__| || |_| || | | | | || |_) |\n" +
                        " |_____/  \\___/  \\__,_||_.__/ |_| \\___| \\____/  \\__,_||_| |_| |_|| .__/ \n" +
                        "                                                                 | |    \n" +
                        "                                                                 |_|    " +
                        "\n" +
                        "Author: %s",
                pluginInfo.getName(), pluginInfo.getName(), pluginInfo.getVersion(), pluginInfo.getAuthors().toString()));

        config = new Configuration();

        if(!config.getConfig().contains("DoubleJump")) {
            config.getConfig().set("DoubleJump", Boolean.TRUE);
        }
        if(!config.getConfig().contains("XZinterval")) {
            config.getConfig().set("XZinterval", 3.0);
        }
        if(!config.getConfig().contains("Yinterval")) {
            config.getConfig().set("Yinterval", 0.5);
        }
        if(!config.getConfig().contains("CMIFlight")) {
            config.getConfig().set("CMIFlight", Boolean.TRUE);
        }
        if(!config.getConfig().contains("Cooldown")) {
            config.getConfig().set("Cooldown", 50);
        }
        config.saveConfig();

        instance = this;

        getServer().getPluginManager().registerEvents(new Jump(), this);

        // commands
        getCommand("doublejump").setExecutor(new CommandManager());
        getCommand("doublejump").setTabCompleter(new TabCompletion());
    }

    @Override
    public void onDisable() {

        getServer().getConsoleSender().sendMessage(String.format("[%s] %s v%s Shutting Down\n" +
                    "Author: %s",
            pluginInfo.getName(), pluginInfo.getName(), pluginInfo.getVersion(), pluginInfo.getAuthors().toString()));

    }
}
