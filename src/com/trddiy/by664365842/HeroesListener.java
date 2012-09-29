package com.trddiy.by664365842;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.herocraftonline.heroes.api.events.SkillUseEvent;

public class HeroesListener implements Listener {
	private Core plugin;
	public HeroesListener(Core plugin){
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	@EventHandler
	public void onskilluse(SkillUseEvent event){
		Player p = event.getPlayer();
		plugin.getpcl().sendcd(p);
	}
}
