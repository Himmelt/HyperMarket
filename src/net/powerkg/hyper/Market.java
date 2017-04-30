package net.powerkg.hyper;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import me.kg.easygui.GuiHandler;
import me.kg.fastuse.FastUse;
import net.powerkg.market.MarketHandler;
import net.powerkg.market.file.FileHandler;
import net.powerkg.market.handler.CommandHandler;
import net.powerkg.market.handler.EconomyHandler;

public class Market extends JavaPlugin
{
	private static Market instance;

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
	public static final FileHandler configHandler = new FileHandler();
	//�������
	public static final CommandHandler commandHandler = new CommandHandler();

	@Override
	public void onEnable()
	{
		Market.instance = this;
		console = getServer().getConsoleSender();

		//��ʼ��Easy-Use
		GuiHandler.init(this);
		FastUse.init(this);

		//��ʼ������ϵͳ
		if (!economyHandler.setupEconomy())
		{
			tellConsole("��c���ؽ���ʧ��,���ֹͣ����,��ȷ���Ƿ�װVault���.");
			return;
		}

		if (!configHandler.initConfig(this))
		{
			tellConsole("��c�����ı�����ʧ��.");
		}

		commandHandler.load();

		MarketHandler.init();
	}

	public static Market getInstance()
	{
		return instance;
	}

	public static void tellConsole(String msg)
	{
		console.sendMessage("��c[HyperMarket] ��f" + msg);
	}
}
