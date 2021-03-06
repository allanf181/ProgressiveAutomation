package com.vanhal.progressiveautomation.compat.mods;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import com.vanhal.progressiveautomation.PAConfig;
import com.vanhal.progressiveautomation.ProgressiveAutomation;
import com.vanhal.progressiveautomation.util.PlayerFake;
import com.vanhal.progressiveautomation.util.Point3I;

public class RightClick extends Vanilla {

	public RightClick() {
		this.modID = "RightClickPlants";
	}
	
	@Override
	public boolean shouldLoad() {
		if (PAConfig.config.getBoolean(modID, "ModCompatibility", true, "Enable support for "+modID)) {
			ProgressiveAutomation.logger.info(modID+" Loaded");
			return true;
		} else {
			ProgressiveAutomation.logger.info(modID+" Found, but compatibility has been disabled in the configs");
			return false;
		}
	}
	
	@Override
	public ArrayList<ItemStack> harvestPlant(Point3I point, Block plantBlock, int metadata, World worldObj) {
		PlayerFake faker = new PlayerFake((WorldServer)worldObj);
		plantBlock.onBlockActivated(worldObj, point.getX(), point.getY(), point.getZ(), faker, metadata, 0, 0, 0);
		
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		
		IInventory inv = faker.inventory;
		for (int i = 0; i < inv.getSizeInventory(); i++){
			if (inv.getStackInSlot(i)!=null) {
				items.add(inv.getStackInSlot(i).copy());
			}
		}

		AxisAlignedBB block = AxisAlignedBB.getBoundingBox(point.getX(), point.getY(), point.getZ(), 
														point.getX()+1, point.getY()+1, point.getZ()+1);
		List<EntityItem> entities = worldObj.getEntitiesWithinAABB(EntityItem.class, block);
		if (entities.isEmpty()) {
			return null;
		}
		
		for (EntityItem item: entities) {
			items.add(item.getEntityItem());
			worldObj.removeEntity(item);
		}
		
		return items;
	}
}
