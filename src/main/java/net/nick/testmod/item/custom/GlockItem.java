package net.nick.testmod.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class GlockItem extends Item {
    public GlockItem(Properties pProperties) {
        super(pProperties);
    }

    // InteractionResult useOn() is mainly used for blocks. (It will only work when clicking on a block)
    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (!world.isClientSide && player != null) {
            shootFlamingArrow(world, player);
            return InteractionResultHolder.success(player.getItemInHand(hand)); // Return success
        }
        return InteractionResultHolder.pass(player.getItemInHand(hand)); // Pass if not handled
    }

    private void shootFlamingArrow(Level world, Player player) {
        Arrow arrow = new Arrow(world, player); // Make arrow projectile.
        arrow.setSecondsOnFire(10); // Set the arrow on fire for 10 seconds.
        arrow.setBaseDamage(10); // Set arrow damage. (10 = 5 hearts)
        arrow.setPos(player.getX(), player.getY() + 1.5, player.getZ()); /* Spawn the arrow on the player and not like (0,0).
                                                                                  The players height is approximately 1.8 blocks.
                                                                                  Since the player.getY() starts at the players feet,
                                                                                  you have to add 1.5 to get it to eye level. */

        Vec3 forward = player.getLookAngle(); // Get the direction player is looking
        arrow.shoot(forward.x, forward.y, forward.z, 3.0F, 1.0F); /* Shoot using the forward vector.
                                                                                        3 is the velocity, 1 is the inaccuracy
                                                                                        (Slight randomness to the arrow's trajectory) */
        world.addFreshEntity(arrow); // Add the arrow to the world
    }
}
