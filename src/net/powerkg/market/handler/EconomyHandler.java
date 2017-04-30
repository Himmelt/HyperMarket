package net.powerkg.market.handler;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

/**
 * Hook Vault
 * �����п��ƽ��������������
 * **/
public class EconomyHandler
{
	private static Economy economy = null;

	public boolean setupEconomy()
	{
		try
		{
			if (economy == null)
			{
				RegisteredServiceProvider<Economy> economyProvider = Bukkit.getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
				if (economyProvider != null)
				{
					economy = economyProvider.getProvider();
				}
			}
		} catch (NoClassDefFoundError err)
		{
			return false;
		}
		return (economy != null);
	}

	public static Economy getEconomy()
	{
		return economy;
	}

	/**
	 * ����Ϊ Fast-easy method.
	 * 
	 * ����ֱ�ӵĵ���
	 * **/

	public static double getBalance(String name)
	{
		return economy.getBalance(name);
	}

	public static boolean hasMoney(String name, double amount)
	{
		return getBalance(name) >= amount;
	}

	public static EconomyResponse costMoney(String name, double amount)
	{
		return economy.withdrawPlayer(name, amount);
	}

	public static EconomyResponse giveMoney(String name, double amount)
	{
		return economy.depositPlayer(name, amount);
	}
}
