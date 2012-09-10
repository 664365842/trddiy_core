package com.trddiy.by664365842;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.herocraftonline.heroes.characters.Hero;

public class ItemForXP {
	private Core plugin;
	public ItemForXP(Core plugin){
		this.plugin = plugin;
	}
	public void getItem(Player p,int a){
		 int id = Core.config.getInt("ItemForXP.Item");
		 int xp = Core.config.getInt("ItemForXP.XP");
		Inventory inv =  p.getInventory();
		ItemStack ist = inv.getItem(id);
		int amount = ist.getAmount();
		if(amount >= 0 ){
			if(amount > a){
				plugin.sendtoplayer(p,"你没有足够的 "+ist.getType().toString().toLowerCase());
			}else{
				ist.setAmount(amount-a);
				inv.remove(ist);
				Hero h =plugin.getheroesplugin().getCharacterManager().getHero(p);
				h.addExp(xp*a, h.getHeroClass());
				h.syncExperience();
			}
		}else{
			plugin.sendtoplayer(p,"数值必须为正整数!");
		}
		
	}
}
