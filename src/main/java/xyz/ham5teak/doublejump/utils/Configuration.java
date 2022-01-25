package xyz.ham5teak.doublejump.utils;

import org.bukkit.configuration.file.FileConfiguration;


import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import xyz.ham5teak.doublejump.listeners.Jump;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class Configuration {

    private FileConfiguration dataConfig = null;
    private File configFile = null;
    private Plugin plugin = Jump.getJump();

    public void reloadConfig(){
        if(this.configFile == null){
            this.configFile = new File(Jump.getJump().getDataFolder(), "config.yml");

            this.dataConfig = YamlConfiguration.loadConfiguration(this.configFile);

            InputStream defaultStream = Jump.getJump().getResource("config.yml");
            if(defaultStream != null){
                YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
                this.dataConfig.setDefaults(defaultConfig);
            }
        }
    }

    public FileConfiguration getConfig() {
        if (this.dataConfig == null) {
            reloadConfig();
        }
        return this.dataConfig;
    }

    public void saveConfig(){
        if (this.dataConfig == null || this.configFile == null){
            return;
        }

        try {
            this.getConfig().save(this.configFile);
        } catch (IOException e) {
            Jump.getJump().getLogger().log(Level.SEVERE, "Could not save configuration file.");
        }

    }

    public void saveDefaultConfig() {
        if (this.configFile == null) {
            this.configFile = new File(Jump.getJump().getDataFolder(), "config.yml");
        }
        if (!this.configFile.exists()) {
            Jump.getJump().saveResource("config.yml", false);
        }
    }

}
