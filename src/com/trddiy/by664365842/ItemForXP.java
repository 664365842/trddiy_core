package com.trddiy.by664365842;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.herocraftonline.heroes.characters.Hero;

public class ItemForXP {
	private Core plugin;

	public ItemForXP(Core plugin) {
		this.plugin = plugin;
	}

	public void getItem(Player p, int a) {
		int id = Core.config.getInt("ItemForXP.Item");
		int xp = Core.config.getInt("ItemForXP.XP");
		ItemStack is = p.getItemInHand();
		if (is.getTypeId() != id) {
			plugin.sendtoplayer(p, "手中拿的物品不能兑换经验!");
			return;
		}
		if (is.getAmount() < a) {
			plugin.sendtoplayer(p, "手上的东西不够!");
			return;
		}
		is.setAmount(is.getAmount() - a);
		Hero h = plugin.getheroesplugin().getCharacterManager().getHero(p);
		h.addExp(xp * a, h.getHeroClass());
		h.syncExperience();
		plugin.sendtoplayer(p, "你使用 " + a + " 个 "
				+ is.getType().toString().toLowerCase() + " 兑换了 " + xp * a
				+ " 点经验.");
	}
}
