package net.powerkg.market;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class ICargo
{
	protected String ownerName;

	protected IPublishType publishType;

	protected ItemStack base;
	protected double cost;

	protected String description = null;

	public ICargo(IPublishType type, String owner, ItemStack base, double cost)
	{
		this.publishType = type;

		this.ownerName = owner;

		this.base = base;
		this.cost = cost;
	}

	public ICargo(String owner, ItemStack base, double cost)
	{
		this.publishType = null;

		this.ownerName = owner;

		this.base = base;
		this.cost = cost;
	}

	/**
	 * ����ҵ������Ʒ��
	 * **/
	public void cargoClick(Player user, boolean isRightClick)
	{
		if (publishType != null)
			publishType.whenCargoClick(this, user, isRightClick);
		else
			CommonPublishment.instance.whenCargoClick(this, user, isRightClick);
	}

	public ItemStack getBase()
	{
		return base;
	}

	public double getCost()
	{
		return cost;
	}

	/**
	 * ������ֵΪ
	 * 	True ִ�����(����ɽ����)����
	 *  False ȡ��ִ��
	 * **/
	public abstract boolean tryGetCargo(Player buyer, int amount);

	public abstract ItemStack getDisplay();

}
