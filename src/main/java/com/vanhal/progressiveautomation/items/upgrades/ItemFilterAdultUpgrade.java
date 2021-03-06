package com.vanhal.progressiveautomation.items.upgrades;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.oredict.ShapedOreRecipe;

import com.vanhal.progressiveautomation.ref.Ref;
import com.vanhal.progressiveautomation.upgrades.UpgradeType;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemFilterAdultUpgrade extends ItemUpgrade {

	public ItemFilterAdultUpgrade() {
		super("FilterAdultUpgrade", UpgradeType.FILTER_ADULT);
		this.setTextureName(Ref.MODID+":Filter_Adult_Upgrade");
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par) {
		list.add(EnumChatFormatting.GRAY + "Will make the Killer to kill adults only");
	   
	}
	
	@Override
	protected void addNormalRecipe() {
		ShapedOreRecipe recipe = new ShapedOreRecipe(new ItemStack(this), new Object[]{
			"pep", "lrw", "pep", 'p', Blocks.stone, 'r', Blocks.redstone_block, 'e', Items.egg, 'l', Items.leather, 'w', Blocks.wool});
		GameRegistry.addRecipe(recipe);
	}

}
