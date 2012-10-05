package com.trddiy.by664365842;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import com.herocraftonline.heroes.api.events.CharacterDamageEvent;
import com.herocraftonline.heroes.api.events.ClassChangeEvent;
import com.herocraftonline.heroes.api.events.HeroChangeLevelEvent;
import com.herocraftonline.heroes.api.events.HeroRegainHealthEvent;
import com.herocraftonline.heroes.api.events.HeroRegainManaEvent;
import com.herocraftonline.heroes.api.events.SkillDamageEvent;
import com.herocraftonline.heroes.api.events.SkillUseEvent;
import com.herocraftonline.heroes.api.events.WeaponDamageEvent;
import com.herocraftonline.heroes.characters.Hero;

public class HeroesListener implements Listener {
	private Core plugin;
	public HeroesListener(Core plugin){
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	@EventHandler
	public void onplayerlogin(PlayerLoginEvent event){
		final Player p = event.getPlayer();
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){
			public void run(){
				plugin.getpcl().sendcd(p);
				plugin.getpcl().sendmana(plugin.getHero(p));
				plugin.getpcl().sendhp(plugin.getHero(p));
			}
		},100L);
	}
	@EventHandler
	public void onskilluse(SkillUseEvent event){
		Player p = event.getPlayer();
		plugin.getpcl().sendcd(p);
	}
	@EventHandler
	public void onmanaregain(HeroRegainManaEvent event){
		Hero h = event.getHero();
		plugin.getpcl().sendmana(h,event.getAmount());
	}
	@EventHandler
	public void onweapondamage(WeaponDamageEvent event){
		Entity e = event.getEntity();
		if(e instanceof Player){
			Player p = (Player)e;
			plugin.getpcl().sendhp(plugin.getHero(p),-event.getDamage());
		}
	}
	@EventHandler
	public void onskilldamage(SkillDamageEvent event){
		Entity e = event.getEntity();
		if(e instanceof Player){
			Player p = (Player)e;
			plugin.getpcl().sendhp(plugin.getHero(p),-event.getDamage());
		}
	}
	@EventHandler
	public void oncharacterdamage(CharacterDamageEvent event){
		Entity e = event.getEntity();
		if(e instanceof Player){
			Player p = (Player)e;
			plugin.getpcl().sendhp(plugin.getHero(p),-event.getDamage());
		}
	}
	@EventHandler
	public void onreborn(PlayerRespawnEvent event){
		final Player p = event.getPlayer();
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){
			public void run(){
				plugin.getpcl().sendcd(p);
				plugin.getpcl().sendmana(plugin.getHero(p));
				plugin.getpcl().sendhp(plugin.getHero(p));
			}
		},1L);
	}
	@EventHandler
	public void onlevelup(HeroChangeLevelEvent event){
		Hero h = event.getHero();
		int addmana = h.getMaxMana()-h.getMana();
		int addhp = h.getMaxHealth()-h.getHealth();
		if(event.getFrom()>event.getTo()){
			plugin.getpcl().sendmana(h);
			plugin.getpcl().sendhp(h);
		}
		else{
			plugin.getpcl().sendmana(h,addmana);
			plugin.getpcl().sendhp(h,addhp);
		}
	}
	
	@EventHandler
	public void onClassChange(ClassChangeEvent event){
		Hero h = event.getHero();
		int addmana = h.getMaxMana()-h.getMana();
		int addhp = h.getMaxHealth()-h.getHealth();
		//h.setHeroClass(event.getTo(), false);
		h.setMana(0);
		plugin.getpcl().sendmana(h,addmana);
		plugin.getpcl().sendhp(h,addhp);
	}
	@EventHandler
	public void onHpRegain(HeroRegainHealthEvent event){
		//System.out.println("回复");
		plugin.getpcl().sendhp(event.getHero(),event.getAmount());
	}
	@EventHandler
	public void onHealthRegain(EntityRegainHealthEvent event){
		if(event.getEntityType()!=EntityType.PLAYER)
			return;
		Player player = (Player) event.getEntity();
		Hero hero = plugin.getheroesplugin().getCharacterManager().getHero(player);
		//System.out.println("自然回复");
		plugin.getpcl().sendhp(hero,hero.getMaxHealth()/20);
	}
}
