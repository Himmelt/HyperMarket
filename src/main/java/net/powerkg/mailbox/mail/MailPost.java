package net.powerkg.mailbox.mail;

import net.powerkg.market.file.MarketConfig;
import net.powerkg.utils.Tools;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Date;

public class MailPost extends IMail {
    private static final ItemStack letter = new ItemStack(Material.BOOK_AND_QUILL);

    private String Topic, Content, Sender;

    private ItemStack ownLetter = null;

    public MailPost(Date theData, String topic, String Content, String sender) {
        this.date = theData;
        this.Topic = topic;
        this.Content = Content;
        this.Sender = sender;
    }

    public MailPost() {
    }

    private ItemStack loadDisplay() {
        if (ownLetter == null) {
            ownLetter = letter.clone();
            Tools.setItemStack(ownLetter, "§f" + MarketConfig.translate("Topic") + ": " + Topic, null);
            Tools.addItemStackLore(ownLetter, "§f〢§l" + MarketConfig.translate("Sender") + ": " + Sender, "", " §f§l| §f§o" + Content);

            stickInfo(ownLetter);
            Tools.addItemStackLore(ownLetter, "§e§o(" + MarketConfig.translate("infoRightClickToConfirmDelete") + ")");
        }
        return ownLetter;
    }

    @Override
    public void write(String path, FileConfiguration file) {
        file.set(path + ".Date", date.getTime());
        file.set(path + ".Sender", Sender);
        file.set(path + ".Topic", Topic);
        file.set(path + ".Content", Content);
    }

    @Override
    public void read(String path, FileConfiguration file) {
        date = new Date(file.getLong(path + ".Date"));
        Sender = file.getString(path + ".Sender");
        Topic = file.getString(path + ".Topic");
        Content = file.getString(path + ".Content");
    }

    @Override
    public String getMark() {
        return this.getClass().getSimpleName();
    }

    @Override
    public ItemStack getDisplay() {
        return loadDisplay();
    }

    @Override
    public boolean click(Player player, boolean isRightClick) {
        if (isRightClick) {
            inBox.deleteMail(this);
        }
        return false;
    }

}
