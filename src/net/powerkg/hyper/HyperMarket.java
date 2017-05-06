package net.powerkg.hyper;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import me.kg.easygui.GuiHandler;
import me.kg.fastuse.FastUse;
import me.kg.filedata.DataHandler;
import net.powerkg.mailbox.MailBoxHandler;
import net.powerkg.market.MarketCargoSaver;
import net.powerkg.market.MarketHandler;
import net.powerkg.market.file.MarketConfig;
import net.powerkg.market.handler.CommandHandler;
import net.powerkg.market.handler.EconomyHandler;

public class HyperMarket extends JavaPlugin
{
	private static HyperMarket instance;

	private static ConsoleCommandSender console;

	/**
	 * �������������
	 * 
	 * ������Щϵͳ��ȫ����Ϊǿ��֢����׷����չ��
	 * ����ȫ�����úܼ򵥵Ĳ�����棡
	 * **/

	//����ϵͳ
	public static final EconomyHandler economyHandler = new EconomyHandler();
	//���ô���ϵͳ
	public static final MarketConfig configHandler = new MarketConfig();
	//�������
	public static final CommandHandler commandHandler = new CommandHandler();
	//�������(�洢����)
	public static final MailBoxHandler mailboxHandler = new MailBoxHandler();

	@Override
	public void onEnable()
	{
		HyperMarket.instance = this;
		console = getServer().getConsoleSender();

		
		//��ʼ��Easy-Use
		GuiHandler.init(this);
		FastUse.init(this);
		DataHandler.init(this);

		//��ʼ������ϵͳ

		if (!configHandler.load(this))
		{
			tellConsole("��c�����ı�����ʧ��.");
		}

		if (!economyHandler.load(this))
		{
			tellConsole("��c���ؽ���ʧ��,���ֹͣ����,��ȷ���Ƿ�װVault���.");
			return;
		}

		mailboxHandler.load(this);

		commandHandler.load(this);

		MarketHandler.init();
		
		MarketCargoSaver.readCargos();
	}

	public static HyperMarket getInstance()
	{
		return instance;
	}

	public static void tellConsole(String msg)
	{
		console.sendMessage("��c[HyperMarket] ��f" + msg);
	}
}
