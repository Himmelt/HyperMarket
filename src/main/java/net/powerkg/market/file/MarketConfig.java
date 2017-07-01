package net.powerkg.market.file;

import net.powerkg.hyper.HyperHandler;
import net.powerkg.hyper.HyperMarket;
import net.powerkg.market.MarketCargoSaver;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class MarketConfig implements HyperHandler {
    private static DateFormat dateFormat;

    private static HashMap<String, String> translaterMap = new HashMap<>();

    private static MarketConfig instance = null;

    private static File lanuageFile = null;
    private static FileConfiguration choseLanuage = null;

    private static File marketFile = null;

    private static ConfigSetting setting = null;
    private static TaxSetting taxSetting = null;

    public boolean load(Plugin plugin) {
        if (instance != null)
            return false;
        else
            instance = new MarketConfig();

        plugin.saveDefaultConfig();
        saveDefLanuageFile(plugin);

        readConfig(plugin);
        return true;
    }

    /**
     * 读取配置
     **/
    private static void readConfig(Plugin plugin) {
        FileConfiguration config = plugin.getConfig();

        //加载语言文本
        lanuageFile = new File(plugin.getDataFolder() + "/lang", config.getString("language"));

        if (lanuageFile.exists()) {
            choseLanuage = YamlConfiguration.loadConfiguration(lanuageFile);
            for (String key : choseLanuage.getConfigurationSection("").getKeys(false)) {
                String utf8 = choseLanuage.getString(key);
                //String local = new String(utf8.getBytes(Charset.defaultCharset()), "UTF8");
                //System.out.println(utf8 + " --> " + local);
                translaterMap.put(key, utf8);
            }
        }

        //加载市场
        marketFile = new File(plugin.getDataFolder() + "/market");
        MarketCargoSaver.load(marketFile);
        if (!marketFile.exists()) {
            marketFile.mkdirs();
        }

        //读取Setting
        setting = new ConfigSetting(config);
        taxSetting = new TaxSetting(config);

        dateFormat = new SimpleDateFormat(setting.DateFormat);
    }

    /**
     * 保存全部语言文件到本地中
     **/
    private static void saveDefLanuageFile(Plugin plugin) {
        File langFile = new File(plugin.getDataFolder() + "/lang");
        if (!langFile.exists()) {
            langFile.mkdir();
            plugin.saveResource(langFile.getPath().replace("plugins\\" + HyperMarket.getInstance().getName() + "\\", "") + "/zh_CN.yml", false);
        }
    }

    public static String translate(String english) {
        String key = english.replaceAll(" ", "");
        if (translaterMap.containsKey(key)) {
            return choseLanuage.getString(key).replaceAll("&", "§");
            //return choseLanuage.getString(afterHandle).replaceAll("&", "§");
        } else {
            return english;
        }
    }

    public static DateFormat getDateFormat() {
        return dateFormat;
    }

    public static ConfigSetting getSetting() {
        return setting;
    }

    public static TaxSetting getTaxSetting() {
        return taxSetting;
    }

}
