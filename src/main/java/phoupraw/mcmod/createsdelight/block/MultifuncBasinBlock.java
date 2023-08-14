package phoupraw.mcmod.createsdelight.block;

import com.simibubi.create.AllShapes;
import com.simibubi.create.foundation.block.IBE;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import phoupraw.mcmod.createsdelight.block.entity.MultifuncBasinBlockEntity;
import phoupraw.mcmod.createsdelight.registry.MyBlockEntityTypes;
public class MultifuncBasinBlock extends Block implements IBE<MultifuncBasinBlockEntity> {
    public MultifuncBasinBlock(Settings settings) {
        super(settings);
    }

    @Override
    public Class<MultifuncBasinBlockEntity> getBlockEntityClass() {
        return MultifuncBasinBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends MultifuncBasinBlockEntity> getBlockEntityType() {
        return null;
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return AllShapes.BASIN_BLOCK_SHAPE;
    }
}
