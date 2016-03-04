package me.TheBukor.SkStuff.effects;

import javax.annotation.Nullable;

import org.bukkit.entity.Blaze;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Rabbit;
import org.bukkit.entity.Spider;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import me.TheBukor.SkStuff.SkStuff;
import me.TheBukor.SkStuff.util.ReflectionUtils;

public class EffRemovePathGoal extends Effect {
	private Expression<LivingEntity> entity;

	private int mark;

	private Class<?> entInsent = ReflectionUtils.getNMSClass("EntityInsentient");
	private Class<?> craftLivEnt = ReflectionUtils.getOBCClass("entity.CraftLivingEntity");

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean arg2, ParseResult result) {
		mark = result.mark;
		entity = (Expression<LivingEntity>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "remove pathfinder goal from " + entity.toString(e, debug);
	}

	@Override
	protected void execute(Event e) {
		LivingEntity ent = entity.getSingle(e);
		if (ent instanceof Player || ent == null)
			return;
		Object obcEnt = craftLivEnt.cast(ent);
		try {
			Object nmsEnt = entInsent.cast(obcEnt.getClass().getMethod("getHandle").invoke(obcEnt));
			Class<?> toRemove = null;
			boolean target = false;
			boolean resetGoalTarget = false;
			if (mark == 0) {
				if (ent instanceof Rabbit) {
					Class<?> goalRabbitAvoid = ReflectionUtils.getNMSClass("EntityRabbit$PathfinderGoalRabbitAvoidTarget");
					toRemove = goalRabbitAvoid;
				} else {
					Class<?> goalAvoid = ReflectionUtils.getNMSClass("PathfinderGoalAvoidTarget");
					toRemove = goalAvoid;
				}
			} else if (mark == 1) {
				Class<?> goalBreakDoor = ReflectionUtils.getNMSClass("PathfinderGoalBreakDoor");
				toRemove = goalBreakDoor;
			} else if (mark == 2) {
				Class<?> goalBreed = ReflectionUtils.getNMSClass("PathfinderGoalBreed");
				toRemove = goalBreed;
			} else if (mark == 3) {
				Class<?> goalEatGrass = ReflectionUtils.getNMSClass("PathfinderGoalEatTile");
				toRemove = goalEatGrass;
			} else if (mark == 4) {
				Class<?> goalFleeSun = ReflectionUtils.getNMSClass("PathfinderGoalFleeSun");
				toRemove = goalFleeSun;
			} else if (mark == 5) {
				Class<?> goalFloat = ReflectionUtils.getNMSClass("PathfinderGoalFloat");
				toRemove = goalFloat;
			} else if (mark == 6) {
				Class<?> goalFollowOwner = ReflectionUtils.getNMSClass("PathfinderGoalFollowOwner");
				toRemove = goalFollowOwner;
			} else if (mark == 7) {
				Class<?> goalFollowAdults = ReflectionUtils.getNMSClass("PathfinderGoalFollowParent");
				toRemove = goalFollowAdults;
			} else if (mark == 8) {
				target = true;
				Class<?> goalReactAttack = ReflectionUtils.getNMSClass("PathfinderGoalHurtByTarget");
				toRemove = goalReactAttack;
			} else if (mark == 9) {
				Class<?> goalJumpOnBlock = ReflectionUtils.getNMSClass("PathfinderGoalJumpOnBlock");
				toRemove = goalJumpOnBlock;
			} else if (mark == 10) {
				Class<?> goalLeapTarget = ReflectionUtils.getNMSClass("PathfinderGoalLeapAtTarget");
				toRemove = goalLeapTarget;
			} else if (mark == 11) {
				Class<?> goalLookEntities = ReflectionUtils.getNMSClass("PathfinderGoalLookAtPlayer");
				toRemove = goalLookEntities;
			} else if (mark == 12) {
				if (ent instanceof Spider) {
					Class<?> goalSpiderMelee = ReflectionUtils.getNMSClass("EntitySpider$PathfinderGoalSpiderMeleeAttack");
					toRemove = goalSpiderMelee;
				} else {
					Class<?> goalMeleeAttack = ReflectionUtils.getNMSClass("PathfinderGoalMeleeAttack");
					toRemove = goalMeleeAttack;
				}
			} else if (mark == 13) {
				if (ent instanceof Ghast) {
					Class<?> goalGhastGotoTarget = ReflectionUtils.getNMSClass("EntityGhast$PathfinderGoalMoveTowardsTarget");
					toRemove = goalGhastGotoTarget;
				} else {
					Class<?> goalGotoTarget = ReflectionUtils.getNMSClass("PathfinderGoalMoveTowardsTarget");
					toRemove = goalGotoTarget;
				}
			} else if (mark == 14) {
				target = true;
				if (ent instanceof Spider) {
					Class<?> goalSpiderNearTarget = ReflectionUtils.getNMSClass("EntitySpider$PathfinderGoalSpiderNearestAttackableTarget");
					toRemove = goalSpiderNearTarget;
				} else {
				Class<?> goalNearTarget = ReflectionUtils.getNMSClass("PathfinderGoalNearestAttackableTarget");
				toRemove = goalNearTarget;
				}
			} else if (mark == 15) {
				Class<?> goalOcelotAttack = ReflectionUtils.getNMSClass("PathfinderGoalOcelotAttack");
				toRemove = goalOcelotAttack;
			} else if (mark == 16) {
				Class<?> goalOpenDoors = ReflectionUtils.getNMSClass("PathfinderGoalOpenDoor");
				toRemove = goalOpenDoors;
			} else if (mark == 17) {
				if (ent instanceof Rabbit) {
					Class<?> goalRabbitPanic = ReflectionUtils.getNMSClass("EntityRabbit$PathfinderGoalRabbitPanic");
					toRemove = goalRabbitPanic;
				} else {
					Class<?> goalPanic = ReflectionUtils.getNMSClass("PathfinderGoalPanic");
					toRemove = goalPanic;
				}
			} else if (mark == 18) {
				Class<?> goalRandomLook = ReflectionUtils.getNMSClass("PathfinderGoalRandomLookaround");
				toRemove = goalRandomLook;
			} else if (mark == 19) {
				Class<?> goalWander = ReflectionUtils.getNMSClass("PathfinderGoalRandomStroll");
				toRemove = goalWander;
			} else if (mark == 20) {
				Class<?> goalSit = ReflectionUtils.getNMSClass("PathfinderGoalSit");
				toRemove = goalSit;
			} else if (mark == 21) {
				Class<?> goalSwell = ReflectionUtils.getNMSClass("PathfinderGoalSwell");
				toRemove = goalSwell;
			} else if (mark == 22) {
				Class<?> goalSquid = ReflectionUtils.getNMSClass("EntitySquid$PathfinderGoalSquid");
				toRemove = goalSquid;
			} else if (mark == 23) {
				resetGoalTarget = true;
				if (ent instanceof Blaze) {
					Class<?> goalBlazeFireball = ReflectionUtils.getNMSClass("EntityBlaze$PathfinderGoalBlazeFireball");
					toRemove = goalBlazeFireball;
				} else if (ent instanceof Ghast) {
					Class<?> goalGhastFireball = ReflectionUtils.getNMSClass("EntityGhast$PathfinderGoalGhastAttackTarget");
					toRemove = goalGhastFireball;
				}
			} else if (mark == 24) {
				Class<?> goalHideInBlock = ReflectionUtils.getNMSClass("EntitySilverfish$PathfinderGoalSilverfishHideInBlock");
				toRemove = goalHideInBlock;
			} else if (mark == 25) {
				Class<?> goalWakeSilverfish = ReflectionUtils.getNMSClass("EntitySilverfish$PathfinderGoalSilverfishWakeOthers");
				toRemove = goalWakeSilverfish;
			} else if (mark == 26) {
				Class<?> goalPickBlocks = ReflectionUtils.getNMSClass("EntityEnderman$PathfinderGoalEndermanPickupBlock");
				toRemove = goalPickBlocks;
			} else if (mark == 27) {
				Class<?> goalPlaceBlocks = ReflectionUtils.getNMSClass("EntityEnderman$PathfinderGoalEndermanPlaceBlock");
				toRemove = goalPlaceBlocks;
			} else if (mark == 28) {
				target = true;
				Class<?> goalAttackLooker = ReflectionUtils.getNMSClass("EntityEnderman$PathfinderGoalPlayerWhoLookedAtTarget");
				toRemove = goalAttackLooker;
			} else if (mark == 29) {
				Class<?> goalGhastMoveTarget = ReflectionUtils.getNMSClass("EntityGhast$PathfinderGoalGhastMoveTowardsTarget");
				toRemove = goalGhastMoveTarget;
			} else if (mark == 30) {
				Class<?> goalGhastIdleMove = ReflectionUtils.getNMSClass("EntityGhast$PathfinderGoalGhastIdleMove");
				toRemove = goalGhastIdleMove;
			} else if (mark == 31) {
				Class<?> goalTempt = ReflectionUtils.getNMSClass("PathfinderGoalTempt");
				toRemove = goalTempt;
			} else if (mark == 32) {
				target = true;
				Class<?> goalTargetNonTamed = ReflectionUtils.getNMSClass("PathfinderGoalRandomTargetNonTamed");
				toRemove = goalTargetNonTamed;
			} else if (mark == 33) {
				resetGoalTarget = true;
				Class<?> goalGuardianAttack = ReflectionUtils.getNMSClass("EntityGuardian$PathfinderGoalGuardianAttack");
				toRemove = goalGuardianAttack;
			} else if (mark == 34) {
				target = true;
				Class<?> goalAnger = ReflectionUtils.getNMSClass("EntityPigZombie$PathfinderGoalAnger");
				toRemove = goalAnger;
			} else if (mark == 35) {
				target = true;
				Class<?> goalAngerOther = ReflectionUtils.getNMSClass("EntityPigZombie$PathfinderGoalAngerOther");
				toRemove = goalAngerOther;
			} else if (mark == 36) {
				Class<?> goalEatCarrots = ReflectionUtils.getNMSClass("EntityRabbit$PathfinderGoalEatCarrots");
				toRemove = goalEatCarrots;
			} else if (mark == 37) {
				Class<?> goalRabbitAttack = ReflectionUtils.getNMSClass("EntityRabbit$PathfinderGoalKillerRabbitMeleeAttack");
				toRemove = goalRabbitAttack;
			} else if (mark == 38) {
				Class<?> goalJump = ReflectionUtils.getNMSClass("EntitySlime$PathfinderGoalSlimeRandomJump");
				toRemove = goalJump;
			} else if (mark == 39) {
				Class<?> goalRandomDir = ReflectionUtils.getNMSClass("EntitySlime$PathfinderGoalSlimeRandomDirection");
				toRemove = goalRandomDir;
			} else if (mark == 40) {
				Class<?> goalSlimeWander = ReflectionUtils.getNMSClass("EntitySlime$PathfinderGoalSlimeIdle");
				toRemove = goalSlimeWander;
			}
			if (toRemove == null)
				return;

			/* "Hey, why are you setting the entity's target to null?!"
			 * 
			 * For some goals (Blaze/Ghast fireball and Guardian attack), if you remove the goal while the entity is attacking, it will not stop attacking imediatelly, it will keep attacking its target.
			 * So there's a "bug" with this behavior, as soon as the entity's target resets (null, A.K.A <none>) the server crashes. Because we messed with the entity's "attack target" goal, the game
			 * still thinks it needs to get the target's location for some reason, and since the target is null... It throws an unhandled NPE (it never happens in Vanilla behavior), crashing the server.
			 * So I'm just setting the target to null before removing the goal, so it stops attacking properly, and also prevents the said crash.
			 */

			if (resetGoalTarget) {
				((Creature) entity.getSingle(e)).setTarget(null);
			}

			SkStuff.getNMSMethods().removePathfinderGoal(nmsEnt, toRemove, target);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}