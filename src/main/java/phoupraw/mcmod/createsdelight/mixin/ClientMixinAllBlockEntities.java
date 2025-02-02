package phoupraw.mcmod.createsdelight.mixin;

import com.simibubi.create.AllBlockEntityTypes;
import com.simibubi.create.content.kinetics.crusher.CrushingWheelControllerBlockEntity;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.builders.BlockEntityBuilder;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import phoupraw.mcmod.createsdelight.block.entity.renderer.CrushingWheelControllerRenderer;
@Environment(EnvType.CLIENT)
@Mixin(AllBlockEntityTypes.class)
public class ClientMixinAllBlockEntities {
    @ModifyArg(method = "<clinit>", slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=millstone")), at = @At(value = "INVOKE", target = "Lcom/simibubi/create/foundation/data/CreateBlockEntityBuilder;instance(Lcom/tterrag/registrate/util/nullness/NonNullSupplier;Z)Lcom/simibubi/create/foundation/data/CreateBlockEntityBuilder;", ordinal = 0, remap = false))
    private static boolean millstoneRenderNormally(boolean renderNormally) {
        return true;
    }

    @Redirect(method = "<clinit>", slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=crushing_wheel_controller")), at = @At(value = "INVOKE", target = "Lcom/tterrag/registrate/builders/BlockEntityBuilder;register()Lcom/tterrag/registrate/util/entry/BlockEntityEntry;", ordinal = 0, remap = false))
    private static BlockEntityEntry<CrushingWheelControllerBlockEntity> crushingControllerRenderer(BlockEntityBuilder<CrushingWheelControllerBlockEntity, CreateRegistrate> instance) {
        return instance.renderer(() -> CrushingWheelControllerRenderer::new).register();
    }
}
