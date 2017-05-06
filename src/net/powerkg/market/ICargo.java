package net.powerkg.market;

import java.io.File;
import java.util.Date;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class ICargo
{
	protected String ownerName;

	protected IPublishType publishType;

	protected ItemStack base;
	protected double cost;

	protected String description = null;

	protected Date publishTime = null;

	public ICargo(IPublishType type, Date publishTime, String owner, ItemStack base, double cost)
	{
		this.publishType = type;

		this.ownerName = owner;

		this.base = base;
		this.cost = cost;

		this.publishTime = publishTime;
	}

	public ICargo(Date publishTime, String owner, ItemStack base, double cost)
	{
		this.publishType = null;

		this.ownerName = owner;

		this.base = base;
		this.cost = cost;

		this.publishTime = publishTime;
	}

	public ICargo()
	{

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
	
	public Date getPublishTime()
	{
		return publishTime;
	}
	
	public ItemStack getBase()
	{
		return base;
	}

	public double getCost()
	{
		return cost;
	}
	
	public String getOwner()
	{
		return ownerName;
	}
	
	public IPublishType getPublishType()
	{
		return publishType;
	}
	
	public abstract void read(String path, File file);

	public abstract void write(String path, FileConfiguration file);

	/**
	 * ������ֵΪ
	 * 	True ִ�����(����ɽ����)����
	 *  False ȡ��ִ��
	 * **/
	public abstract boolean tryGetCargo(Player buyer, int amount);
	
	public abstract String getMark();
	
	public abstract ItemStack getDisplay();

}
