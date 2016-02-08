package me.TheBukor.SkStuff.effects;

import javax.annotation.Nullable;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import me.TheBukor.SkStuff.util.ReflectionUtils;

public class EffMakeJump extends Effect {
	private Expression<LivingEntity> entities;

	private Class<?> entInsent = ReflectionUtils.getNMSClass("EntityInsentient", false);
	private Class<?> craftLivEnt = ReflectionUtils.getOBCClass("entity.CraftLivingEntity");

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean arg2, ParseResult result) {
		entities = (Expression<LivingEntity>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean arg1) {
		return "make " + entities.toString(e, false) + " jump";
	}

	@Override
	protected void execute(Event e) {
		LivingEntity[] ents = entities.getAll(e);
		for (Entity ent : ents) {
			if (ent instanceof Player || ent == null)
				continue;
			Object obcEnt = craftLivEnt.cast(ent);
			try {
				Object nmsEnt = entInsent.cast(obcEnt.getClass().getMethod("getHandle").invoke(obcEnt));
				Object controllerJump = nmsEnt.getClass().getMethod("getControllerJump").invoke(nmsEnt);
				controllerJump.getClass().getMethod("a").invoke(controllerJump);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}