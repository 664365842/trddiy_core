package com.trddiy.by664365842;

import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.garbagemule.MobArena.events.ArenaPlayerJoinEvent;
import com.garbagemule.MobArena.events.NewWaveEvent;
import com.garbagemule.MobArena.framework.Arena;
import com.garbagemule.MobArena.waves.enums.WaveType;
import com.herocraftonline.heroes.characters.CharacterManager;
import com.herocraftonline.heroes.characters.Hero;

public class MobArenaListener implements Listener {
	private Core plugin;
	private CharacterManager chm; 

	public MobArenaListener(Core plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onWave(NewWaveEvent event) {
		// TODO 让boss波之后再给经验
		int wn = event.getWaveNumber();
		Arena arena = event.getArena();
		Set<Player> ap = arena.getPlayersInArena();
		if (event.getWave().getType() == WaveType.BOSS) {
			for (Player p : ap) {
				if (p != null) {
					addexp(p, wn);
				}
			}
		}
	}
	@EventHandler
	public void onJoin(ArenaPlayerJoinEvent event){
		Arena a =event.getArena();
		Player p = event.getPlayer();
	}

	public void addexp(Player p, int wn) {
		chm = plugin.getheroesplugin().getCharacterManager();
		int waven = plugin.getConfig().getInt("MobArena.multiply");
		Hero h = chm.getHero(p);
		h.addExp(wn * waven, h.getHeroClass());
		h.syncExperience();
		plugin.sendtoplayer(p, "你获得了奖励: " + wn * waven + " 经验");
	}
	public void setclass(Player p,Arena a){
		chm = plugin.getheroesplugin().getCharacterManager();
		Hero h = chm.getHero(p);
		String s = h.getHeroClass().getName();
		
	}

}
