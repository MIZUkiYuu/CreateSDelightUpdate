package phoupraw.mcmod.createsdelight.api;

import com.simibubi.create.content.kinetics.belt.behaviour.DirectBeltInputBehaviour;
import com.simibubi.create.content.logistics.funnel.BeltFunnelBlock;
import com.simibubi.create.content.logistics.funnel.FunnelBlock;
import com.simibubi.create.content.logistics.funnel.FunnelBlockEntity;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import net.fabricmc.fabric.api.lookup.v1.block.BlockApiLookup;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/**
 * 是否支持安山岩漏斗像置物台、分液池、动力锯那样贴合。
 *
 * @see DirectBeltInputBehaviour#allowingBeltFunnels()
 */
public final class AllowingBeltFunnels {
    public static final BlockApiLookup<Object, @NotNull Direction> LOOKUP = BlockApiLookup.get(new Identifier("createsdelight", "allowing_belt_funnels"), Object.class, Direction.class);
    static {
        LOOKUP.registerFallback((world, pos, state, blockEntity, side)->{
            if (!(blockEntity instanceof SmartBlockEntity smart)) return null;
            var b = BlockEntityBehaviour.get(smart, DirectBeltInputBehaviour.TYPE);
            if (b == null) return null;
            return b.canSupportBeltFunnels();
        });
    }


    private AllowingBeltFunnels() {}

    /**
     * @see DirectBeltInputBehaviour#tryExportingToBeltFunnel(ItemStack, Direction, boolean)
     */
    @Nullable
    public static ItemStack tryExportingToBeltFunnel(World world, BlockPos blockPos, ItemStack stack, @Nullable Direction side, boolean simulate) {
        BlockPos funnelPos = blockPos.up();
        BlockState funnelState = world.getBlockState(funnelPos);
        if (!(funnelState.getBlock() instanceof BeltFunnelBlock))
            return null;
        if (funnelState.get(BeltFunnelBlock.SHAPE) != BeltFunnelBlock.Shape.PULLING)
            return null;
        if (side != null && FunnelBlock.getFunnelFacing(funnelState) != side)
            return null;
        BlockEntity te = world.getBlockEntity(funnelPos);
        if (!(te instanceof FunnelBlockEntity))
            return null;
        if (funnelState.get(BeltFunnelBlock.POWERED))
            return stack;
        ItemStack insert = FunnelBlock.tryInsert(world, funnelPos, stack, simulate);
        if (insert.getCount() != stack.getCount() && !simulate)
            ((FunnelBlockEntity) te).flap(true);
        return insert;
    }
}
