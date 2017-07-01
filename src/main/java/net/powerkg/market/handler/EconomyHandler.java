package net.powerkg.market.handler;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.powerkg.hyper.HyperHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;

/**
 * Hook Vault
 * 该类中控制金融相关所有数据
 **/
public class EconomyHandler implements HyperHandler {
    private static Economy economy = null;

    @Override
    public boolean load(Plugin plugin) {
        return setupEconomy();
    }

    public boolean setupEconomy() {
        try {
            if (economy == null) {
                System.out.println("economy null");
                System.out.println(net.milkbowl.vault.economy.Economy.class.getName());
                RegisteredServiceProvider<Economy> economyProvider = Bukkit.getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
                if (economyProvider != null) {
                    System.out.println("economy not null");
                    economy = economyProvider.getProvider();
                }
            }
        } catch (NoClassDefFoundError e) {
            e.printStackTrace();
            return false;
        }
        return (economy != null);
    }

    public static Economy getEconomy() {
        return economy;
    }

    /**
     * 以下为 Fast-easy method.
     * <p>
     * 方便直接的调用
     **/

    public static double getBalance(String name) {
        return economy.getBalance(name);
    }

    public static boolean hasMoney(String name, double amount) {
        return getBalance(name) >= amount;
    }

    public static EconomyResponse costMoney(String name, double amount) {
        return economy.withdrawPlayer(name, amount);
    }

    public static EconomyResponse giveMoney(String name, double amount) {
        return economy.depositPlayer(name, amount);
    }
}
