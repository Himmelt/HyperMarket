package net.powerkg.market;

import net.powerkg.market.file.MarketConfig;
import net.powerkg.market.gui.GuiCommonBuy;
import net.powerkg.market.gui.GuiCommonPublishment;
import net.powerkg.utils.Tools;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommonPublish extends IPublishType implements IPublish {
    public static final CommonPublish instance = new CommonPublish();

    private static final ItemStack def = new ItemStack(Material.SIGN);

    static {
        Tools.setItemStack(def, "§c" + MarketConfig.translate("infoCommonPublish"), null);
    }

    public CommonPublish() {
        publish = this;
    }

    @Override
    public void tryToPublish(Player user) {
        GuiCommonPublishment.open(user);
    }

    @Override
    public ItemStack getDisplay() {
        return def;
    }

    @Override
    public void whenCargoClick(ICargo cargo, Player user, boolean isRightClick) {
        if (!isRightClick) {
            new GuiCommonBuy(cargo, user).show();
        }
    }

    @Override
    public String getMark() {
        return CommonPublish.class.getSimpleName();
    }
}
