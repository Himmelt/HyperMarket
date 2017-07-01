package net.powerkg.market.handler;

import net.powerkg.hyper.HyperHandler;
import net.powerkg.market.command.CommandHyperMarket;
import org.bukkit.plugin.Plugin;

public class CommandHandler implements HyperHandler {
    @Override
    public boolean load(Plugin plugin) {
        CommandHyperMarket.load();
        return true;
    }
}
