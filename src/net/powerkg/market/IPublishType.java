package net.powerkg.market;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class IPublishType
{
	protected IPublish publish = null;

	public IPublishType()
	{
	}

	public IPublishType(IPublish publish)
	{
		this.publish = publish;
	}

	/**
	 * ����ҵ��������ѡ�и�����ʱ
	 * **/
	public void whenTryPublish(Player user)
	{		
		if (publish != null)
			publish.tryToPublish(user);
	}

	/**
	 * ����ҵ���󶨸����͵���Ʒʱ
	 * **/
	public abstract void whenCargoClick(ICargo cargo, Player user, boolean isRightClick);

	public abstract ItemStack getDisplay();
}
