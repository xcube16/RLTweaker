package com.charles445.rltweaker.config;

import net.minecraftforge.common.config.Config;

public class ConfigBattleTowers
{
	@Config.Comment("Master switch for this mod compatibility")
	@Config.Name("ENABLED")
	@Config.RequiresMcRestart
	public boolean enabled = true;
	
	@Config.Comment("Make tower explosions belong to nobody, avoids Infernal Mobs issues")
	@Config.Name("Change Tower Explosion Owner")
	public boolean towerExplosionNoCredit = true;
	
	@Config.Comment("Make golems stop building up speed when dormant")
	@Config.Name("Golem Dormant Speed Fix")
	public boolean golemDormantSpeedFix = true;
	
	@Config.Comment("Prevent golems from drowning")
	@Config.Name("Golem Drowning Fix")
	public boolean golemDrowningFix = true;
	
	@Config.Comment("Max Golem XZ movement to make using tools like fishing rods harder. Set to a negative number to disable")
	@Config.Name("Golem Speed Cap")
	@Config.RangeDouble(min=-1.0d)
	public double golemSpeedCap = 0.0625f;
	
	@Config.Comment("Prevent golems from riding anything, like boats or minecarts")
	@Config.Name("Golem Auto Dismount")
	public boolean golemAutoDismount = true;
	
	@Config.Comment("Replace the golem's projectile with a LycanitesMobs Projectile (Requires LycanitesMobs)")
	@Config.Name("Golem Lycanites Projectile")
	public boolean golemLycanitesProjectile = true;
	
	@Config.Comment("The name of the projectile to use")
	@Config.Name("Golem Lycanites Projectile Name")
	public String golemLycanitesProjectileName = "demonicblast";
	
	@Config.Comment("Scale modifier for the lycanites projectile")
	@Config.Name("Golem Lycanites Projectile Scale Modifier")
	public double golemLycanitesProjectileScaleModifier = 1.0d;
	
	@Config.Comment("Speed modifier for the lycanites projectile")
	@Config.Name("Golem Lycanites Projectile Speed Modifier")
	public double golemLycanitesProjectileSpeedModifier = 9.0d;
	
}
