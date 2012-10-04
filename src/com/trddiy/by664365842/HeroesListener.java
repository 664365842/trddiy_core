package com.trddiy.by664365842;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import com.herocraftonline.heroes.api.events.HeroRegainManaEvent;
import com.herocraftonline.heroes.api.events.SkillUseEvent;
import com.herocraftonline.heroes.characters.Hero;

public class HeroesListener implements Listener {
	private Core plugin;
	public HeroesListener(Core plugin){
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	@EventHandler
	public void onplayerlogin(PlayerLoginEvent event){
		Player p = event.getPlayer();
		plugin.getpcl().sendcd(p);
		plugin.getpcl().sendmana(plugin.getheroesplugin().getCharacterManager().getHero(p));
		
	}
	@EventHandler
	public void onskilluse(SkillUseEvent event){
		Player p = event.getPlayer();
		plugin.getpcl().sendcd(p);
	}
	@EventHandler
	public void onmanaregain(HeroRegainManaEvent event){
		Hero h = event.getHero();
		plugin.getpcl().sendmana(h);
	}
}
