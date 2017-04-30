package net.powerkg.market.file;

import java.io.File;
import java.util.HashMap;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import net.powerkg.hyper.Market;

public class FileHandler
{
	private static HashMap<String, String> translaterMap = new HashMap<>();

	private static FileHandler instance = null;

	private static File lanuageFile = null;
	private static FileConfiguration choseLanuage = null;

	private static ConfigSetting setting = null;
	private static TaxSetting taxSetting = null;

	public boolean initConfig(Plugin plugin)
	{
		if (instance != null)
			return false;
		else
			instance = new FileHandler();

		plugin.saveDefaultConfig();
		saveDefLanuageFile(plugin);

		readConfig(plugin);
		return true;
	}

	/**
	 * ��ȡ����
	 * **/
	private static void readConfig(Plugin plugin)
	{
		FileConfiguration config = plugin.getConfig();

		//���������ı�
		lanuageFile = new File(plugin.getDataFolder() + "/languages", config.getString("language"));

		if (lanuageFile.exists())
		{
			choseLanuage = YamlConfiguration.loadConfiguration(lanuageFile);
			for (String s : choseLanuage.getConfigurationSection("").getKeys(false))
			{
				translaterMap.put(s, choseLanuage.getString(s));
			}
		}

		//��ȡSetting
		setting = new ConfigSetting(config);
		taxSetting = new TaxSetting(config);
	}

	/**
	 * ����ȫ�������ļ���������
	 * **/
	private static void saveDefLanuageFile(Plugin plugin)
	{
		File lanuageFile = new File(plugin.getDataFolder() + "/languages");
		if (!lanuageFile.exists())
		{
			lanuageFile.mkdir();

			plugin.saveResource(lanuageFile.getPath().replace("plugins\\" + Market.getInstance().getName() + "\\", "") + "/cn.yml", false);
		}
	}

	public static String translate(String en)
	{
		String afterHandle = en.replaceAll(" ", "");
		if (translaterMap.containsKey(afterHandle))
		{
			return choseLanuage.getString(afterHandle).replaceAll("&", "��");
		} else
			return en;
	}

	public static ConfigSetting getSetting()
	{
		return setting;
	}

	public static TaxSetting getTaxSetting()
	{
		return taxSetting;
	}

}
